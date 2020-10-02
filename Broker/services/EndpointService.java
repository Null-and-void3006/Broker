package com.Tim401_6.Broker.services;


import java.util.*;

import com.Tim401_6.Broker.model.*;
import com.Tim401_6.Broker.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class EndpointService {
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private EndpointRepository endpointRepository;
	@Autowired
	private AuthorizationGroupRepository authorizationGroupRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private LogsService logsService;
	@Autowired
	private EndpointComplexOrderRepository endpointComplexOrderRepository;
	@Autowired
	private MethodTypeRepository methodTypeRepository;

	private Service service;
	private Endpoint endpoint;
	private RestTemplateBuilder rtb = new RestTemplateBuilder();
	private final RestTemplate restTemplate = rtb.build();
	private String response = "";
	
	public String route(String serviceString, String end, Map<String, String> allRequestParams) {
		response = "";
		service = serviceRepository.findFirstByServiceName(serviceString).get(0);

		//Ako je servis apstraktan
		if(service.isAbstract()){
			List<Service> concreteServices = serviceRepository.findConcrete(service.getId());
			int noOfConcrete = concreteServices.size();
			Random random = new Random();
			int ind = random.nextInt(noOfConcrete);
			service = concreteServices.get(noOfConcrete);
		}

		endpoint = endpointRepository.findRoute(end, service.getId()).get(0);
		String hostname = userRepository.findFirstByServiceId(service.getId()).get(0).getHostname();

		//Ako je endpoint kompleksan
		if(endpoint.isSlozen()){
			response = orkestracija(allRequestParams, hostname);
			return response;
		}else {
			System.out.println("Get nekompleksan");
            String url = hostname + service.getPath() + endpoint.getPath();

            if (allRequestParams.size() > 0) {
                url += "?";

                boolean first = true;
                for (String key : allRequestParams.keySet()) {
                    if (!first) {
                        url += "&";
                    } else {
                        first = false;
                    }

                    url += key + "=" + allRequestParams.get(key);
                }
            }

            System.out.println(url);

            if (authorized()) {
                //HTTP REQUEST
                response = validateResponseForUser(this.restTemplate.getForObject(url, String.class));
            } else response = "UNAUTHORIZED ACCESS";
        }
		if(!authorized()){
            response = "UNAUTHORIZED ACCESS";
        }
		return response;
	}

	public String routeWithPayload(String serviceString, String end, Map<String, Object> payload) {
		response = "";
		service = serviceRepository.findFirstByServiceName(serviceString).get(0);

		//Ako je servis apstraktan
		if(service.isAbstract()){
			List<Service> concreteServices = serviceRepository.findConcrete(service.getId());
			int noOfConcrete = concreteServices.size();
			Random random = new Random();
			int ind = random.nextInt(noOfConcrete);
			service = concreteServices.get(noOfConcrete);
		}

		endpoint = endpointRepository.findRoute(end, service.getId()).get(0);
		String hostname = userRepository.findFirstByServiceId(service.getId()).get(0).getHostname();

		//Ako je endpoint kompleksan
		if(endpoint.isSlozen()){
			Map<String, String> allRequestParams = new HashMap<>();
			response = orkestracija(allRequestParams, hostname);
			return response;
		}else {

			String url = hostname + service.getPath() + endpoint.getPath();

			System.out.println(url);

			if (authorized()) {
				//HTTP REQUEST
				response = validateResponseForUser(this.restTemplate.postForObject(url, payload, String.class));
			} else response = "UNAUTHORIZED ACCESS";

		}
		if(!authorized()){
			response = "UNAUTHORIZED ACCESS";
		}
		return response;
	}

	public String routePutWithPayload(String serviceString, String end, String payload) {
		response = "";
		String postResponse = "";
		service = serviceRepository.findFirstByServiceName(serviceString).get(0);

		//Ako je servis apstraktan
		if(service.isAbstract()){
			List<Service> concreteServices = serviceRepository.findConcrete(service.getId());
			int noOfConcrete = concreteServices.size();
			Random random = new Random();
			int ind = random.nextInt(noOfConcrete);
			service = concreteServices.get(noOfConcrete);
		}


		endpoint = endpointRepository.findRoute(end, service.getId()).get(0);
		String hostname = userRepository.findFirstByServiceId(service.getId()).get(0).getHostname();

		//Ako je endpoint kompleksan
		if(endpoint.isSlozen()){
			Map<String, String> allRequestParams = new HashMap<>();
			response = orkestracija(allRequestParams, hostname);
			return response;
		}else {

			String url = hostname + service.getPath() + endpoint.getPath();


			System.out.println(url);

			if (authorized()) {
				//HTTP REQUEST
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
				ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
				postResponse = validateResponseForUser(response.getBody());
			} else postResponse = "UNAUTHORIZED ACCESS";
		}
		return postResponse;
	}
	
	private boolean authorized() {
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		int userId = userRepository.findFirstByUsername(username).get(0).getId();
		int endpointId = endpoint.getId();

		List<AuthorizationGroup> authorizationGroup = authorizationGroupRepository.findAuthorizationForUserForEndpoint(userId, endpointId);

		boolean isAuthorized = !authorizationGroup.isEmpty();

		if(isAuthorized) {
		    Logs newLog = new Logs(userId, endpointId, true);
		    logsService.saveLog(newLog);
        } else {
            Logs newLog = new Logs(userId, endpointId, false);
            logsService.saveLog(newLog);
        }

		return isAuthorized;
	}

	private String validateResponseForUser(String originalResponse) {
		String endpointResponseParams = endpoint.getResponseParams();

		if(endpointResponseParams.equals("null")) {
			return originalResponse;
		}

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		User user = userRepository.findFirstByUsername(username).get(0);

		JSONArray responseParamsJSON = new JSONArray(endpointResponseParams);
		JSONArray responseJSON = new JSONArray(originalResponse);

		for (int i = 0; i < responseParamsJSON.length(); i++) {
			JSONObject paramObject = responseParamsJSON.getJSONObject(i);

			try {
				String permissions = paramObject.get("permissions").toString();

				if(ResponsePermissions.valueOf(permissions).equals(ResponsePermissions.NONE)) {
					for (int j = 0; j < responseJSON.length(); j++) {
						JSONObject responseObject = responseJSON.getJSONObject(j);

						responseObject.remove(paramObject.get("name").toString());

						responseJSON.put(j, responseObject);
					}
				}
			} catch(Exception e) {}

			try {
				String exclude = paramObject.get("exclude").toString();

				if(exclude.contains(user.getType())) {
					for (int j = 0; j < responseJSON.length(); j++) {
						JSONObject responseObject = responseJSON.getJSONObject(j);

						responseObject.remove(paramObject.get("name").toString());

						responseJSON.put(j, responseObject);
					}
				}
			} catch(Exception e) {}

			try {
				String include = paramObject.get("include").toString();

				if(!include.contains(user.getType())) {
					for (int j = 0; j < responseJSON.length(); j++) {
						JSONObject responseObject = responseJSON.getJSONObject(j);

						responseObject.remove(paramObject.get("name").toString());

						responseJSON.put(j, responseObject);
					}
				}
			} catch(Exception e) {}
		}

		return responseJSON.toString();
	}
	
	public String getResponse() {
		return response;
	}

	private String orkestracija(Map<String, String> allRequestParams, String hostname) {
		Map<String, String> parametriMap = new HashMap<>();
		for (String key : allRequestParams.keySet()) {
			parametriMap.put(key, allRequestParams.get(key));
		}
		//Orkerstracija
		List<EndpointComplexOrder> endpointComplexOrderList = endpointComplexOrderRepository.findChildEndpoints(endpoint.getId());
		endpointComplexOrderList.sort(Comparator.comparingInt(EndpointComplexOrder::getEndOrder));
		for (int i = 0; i < endpointComplexOrderList.size(); i++) {
		//	System.out.println(i + " " + endpointComplexOrderList.get(i).getParamName() + " " + endpointComplexOrderList.get(i).getParamValue());
			if (endpointComplexOrderList.get(i).getParamName() == null) {
				Endpoint e = endpointRepository.findById(endpointComplexOrderList.get(i).getCurrentEndpointId()).orElse(null);
				endpoint = e;
				if(!authorized()){
					return "UNAUTHORIZED ACCESS";
				}
				if(endpoint.isSlozen()){
					response = orkestracija(allRequestParams, hostname);
				}else {
					if (e != null) {

						String url = hostname + service.getPath() + e.getPath();

						response = executeRequest(e, response, hostname);
					}
				}
			} else if (parametriMap.containsKey(endpointComplexOrderList.get(i).getParamName())) {
				if (endpointComplexOrderList.get(i).getParamValue().equals(parametriMap.get(endpointComplexOrderList.get(i).getParamName()))) {
					Endpoint e = endpointRepository.findById(endpointComplexOrderList.get(i).getCurrentEndpointId()).orElse(null);
					endpoint = e;
					if(!authorized()){
						return  "UNAUTHORIZED ACCESS";
					}
					if(endpoint.isSlozen()){
						response = orkestracija(allRequestParams, hostname);
					}else {
						if (!e.equals(null)) {
							response = executeRequest(e, response, hostname);
						}
					}
				}
			}
			if (response.equals("BAD REQUEST BODY")) {
				return response;
			}else if(response.equals("BAD DEFINED COMPLEX SERVICE")){
				return response;
			}else if(response.equals("UNAUTHORIZED ACCESS")){
				return response;
			}
		}
		return response;
	}

	private String executeRequest(Endpoint e, String response, String hostname) {
		String url = hostname + service.getPath() + e.getPath();
		String reqPar = e.getRequestParams();
	//	System.out.println(response + " " + reqPar + " " + e.getEndpointName());

		try {
			if (reqPar.equals("")) {
				if (!response.equals("")) {
					response = "BAD REQUEST BODY";
					return response;
				}
			} else {
				try {
					JSONArray reqParJSON = new JSONArray(reqPar);
					JSONArray wantToSendJSON = new JSONArray(response);
					JSONObject sendThis;

					Map<String, String> map = new HashMap<>();

					JSONArray ovoPosalji = new JSONArray();

					JSONObject ovoPosaljiObj = new JSONObject();

					try {
						for (int i = 0; i < wantToSendJSON.length(); i++) {
							JSONObject paramObject = wantToSendJSON.getJSONObject(i);
					//		System.out.println(paramObject.toString());
							JSONObject putInArr = new JSONObject();
							for (int j = 0; j < reqParJSON.length(); j++) {
								JSONObject responseObject = reqParJSON.getJSONObject(j);
								String param = responseObject.get("name").toString();
								String value = paramObject.get(param).toString();
								System.out.println(param + " " + value);
								putInArr.put(param, value);
								ovoPosaljiObj = responseObject;
							}
							ovoPosalji.put(i, putInArr);
						}
						System.out.println(ovoPosalji.toString());
						if (wantToSendJSON.length() == 1) {
							response = ovoPosaljiObj.toString();
						} else {
							response = ovoPosalji.toString();
						}
					} catch (Exception ex) {
						response = "BAD REQUEST BODY";
						return response;
					}
				} catch (Exception exc) {
					JSONObject reqParJSON = new JSONObject(reqPar);
					JSONObject wantToSendJSON = new JSONObject(response);
					JSONObject sendThis;

					Map<String, String> map = new HashMap<>();

					JSONArray ovoPosalji = new JSONArray();

					JSONObject ovoPosaljiObj = new JSONObject();

					try {
						for (int i = 0; i < wantToSendJSON.length(); i++) {
							JSONObject paramObject = wantToSendJSON;
				//			System.out.println(paramObject.toString());
							JSONObject putInArr = new JSONObject();
							for (int j = 0; j < reqParJSON.length(); j++) {
								JSONObject responseObject = reqParJSON;
								String param = responseObject.get("name").toString();
								String value = paramObject.get(param).toString();
					//			System.out.println(param + " " + value);
								putInArr.put(param, value);
								ovoPosaljiObj = responseObject;
							}
							ovoPosalji.put(i, putInArr);
						}
				//		System.out.println(ovoPosalji.toString());
						if (wantToSendJSON.length() == 1) {
							response = ovoPosaljiObj.toString();
						} else {
							response = ovoPosalji.toString();
						}
					} catch (Exception ex) {
						response = "BAD REQUEST BODY";
						return response;
					}
				}

			}
			if (methodTypeRepository.findById(e.getMethodId()).orElse(null).getName().equals("GET")) {
				response = validateResponseForUser(this.restTemplate.getForObject(url, String.class));
			} else if (methodTypeRepository.findById(e.getMethodId()).orElse(null).getName().equals("POST")) {
				response = validateResponseForUser(this.restTemplate.postForObject(url, response, String.class));
			} else if (methodTypeRepository.findById(e.getMethodId()).orElse(null).getName().equals("PUT")) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>(response, headers);
				ResponseEntity<String> pr = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
				response = validateResponseForUser(pr.getBody());
			}
			System.out.println(response);
			return response;
		} catch (Exception exception) {
			response = "BAD DEFINED COMPLEX SERVICE";
			return response;
		}

	}
}
