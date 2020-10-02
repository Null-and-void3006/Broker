import React, { Component } from 'react';
import axios from 'axios';
import { Form, Button } from 'react-bootstrap';

import './Reports.css';

class Reports extends Component {
    constructor(props) {
        super(props);

        this.state = {
            selectedReport: null,
            formValues: {}
        }
    }

    onFormChange = (fieldName, value) => {
        const { formValues } = this.state 

        const newValues = formValues;
        newValues[fieldName] = value;
        
        this.setState({formValues: newValues});
    }

    generateReport = (type) => {
        const { formValues } = this.state
        if(type === 'tech') {
            const headers = {
                'Authorization': `Bearer ${localStorage.getItem("jwt")}`
            };
    
            axios.get(`http://localhost:8080/reports/generate_subj?tech=${formValues.subject}`, {'headers': headers})
            .then(res => {
                console.log(res.data)
            })
            .catch(err => {
                console.log(err)
            })
        }
    }

    renderReportGenerator = () => {
        const { selectedReport } = this.state
        switch(selectedReport) {
            case "student":
                return(
                    <div className="gen-report">
                        <Form.Group controlId="formStudName">
                            <Form.Label>Student name</Form.Label>
                            <Form.Control 
                                type="text" 
                                placeholder="Enter student name" 
                                onChange={(e) => { this.onFormChange("student", e.target.value)}}
                            />
                        </Form.Group>
                    </div>
                )
            case "tech":
                return(
                    <div className="gen-report">
                        <Form.Group controlId="formSubjName">
                            <Form.Label>Competence name</Form.Label>
                            <Form.Control 
                                type="text" 
                                placeholder="Enter competence" 
                                onChange={(e) => { this.onFormChange("subject", e.target.value)}}
                            />
                        </Form.Group>
                        <Button variant="primary" className="report-btn" onClick={this.generateReport.bind(this, "tech")}>
                            Generate report
                        </Button>
                    </div>
                )
            case "prof":
                return(
                    <div className="gen-report">
                        <Form.Group controlId="formProfName">
                            <Form.Label>Professor name</Form.Label>
                            <Form.Control 
                                type="text" 
                                placeholder="Enter a professor's name" 
                                onChange={(e) => { this.onFormChange("subject", e.target.value)}}
                            />
                        </Form.Group>
                    </div>
                )
        }
    }
      
    render() {
        const { selectedReport } = this.state
        return (
            <div className="Reports container">
                <h1 className="comp-title">Reports</h1>
                <h3 className="comp-subtitle">Please select report:</h3>
                <div className="report-btn-row">
                    <Button variant="secondary" className="report-btn" onClick={() => this.setState({selectedReport: "student"})}>
                        Student report
                    </Button>
                    <Button variant="secondary" className="report-btn" onClick={() => this.setState({selectedReport: "tech"})}>
                        Competence report
                    </Button>
                    <Button variant="secondary" className="report-btn" onClick={() => this.setState({selectedReport: "prof"})}>
                        Professor report
                    </Button>
                </div>
                {selectedReport ? this.renderReportGenerator() : null }
            </div>
        );
    }
}

export default Reports;