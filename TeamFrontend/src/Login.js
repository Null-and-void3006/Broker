import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import axios from 'axios';

import './Login.css';

function Login() {
    const [formValues, setFormValues] = useState({
        "username": null,
        "password": null,
    });
    const [error, setError] = useState(null);

    const handleSubmit = event => {
        const headers = {
            'Content-Type': 'application/json',
        };

        setError(null);

        axios.post(`http://localhost:8080/authenticate`, formValues, headers)
        .then(res => {
            localStorage.setItem("jwt", res.data.jwt);
            localStorage.setItem("username", formValues.username);
            window.location.assign("/");
        })
        .catch(err => {
            console.log(err)
            setError('Invalid username or password');
        })
    }

    const onFormChange = (fieldName, value) => {
        const newValues = formValues;
        newValues[fieldName] = value;
        setFormValues(newValues);
    }

    return (
        <div className="Login container">
            <h1 className="login-title">Log in</h1>
            <div className="login-form">
                <Form className="form-log">
                    <Form.Group controlId="formBasicUsername">
                        <Form.Label>Username</Form.Label>
                        <Form.Control 
                            type="username" 
                            placeholder="Enter username" 
                            onChange={(e) => { onFormChange("username", e.target.value)}}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control 
                            type="password" 
                            placeholder="Password" 
                            onChange={(e) => { onFormChange("password", e.target.value)}}
                        />
                    </Form.Group>
                    <Button variant="primary" onClick={handleSubmit}>
                        Submit
                    </Button>
                </Form>
                <div className="login-error">
                    {error}
                </div>
            </div>
        </div>
    );
}

export default Login;