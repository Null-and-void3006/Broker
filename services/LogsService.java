package com.Tim401_6.Broker.services;

import com.Tim401_6.Broker.model.Logs;
import com.Tim401_6.Broker.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsService {
    @Autowired
    private LogsRepository logsRepository;

    public boolean saveLog(Logs log) {
        logsRepository.save(log);

        return true;
    }
}
