import React, { PureComponent } from 'react';
import axios from 'axios';
import { Button } from 'react-bootstrap';

import './Home.css';

class Home extends PureComponent {
    constructor(props) {
        super(props);
        
        this.state = {user: null};
    }

    componentDidMount() {
        this.getUser();
    }

    getUser = () => {
        const headers = {
            'Authorization': `Bearer ${localStorage.getItem("jwt")}`
        };
        axios.get(`http://localhost:8080/user/username/${localStorage.getItem("username")}`, {'headers': headers})
            .then(res => {
                this.setState({user: res.data});
            })
            .catch(err => {
                console.log(err);
            })
    }
      
    render() {
        const { user } = this.state
        return (
            <div className="Home">
                <h1 className="home-title">Welcome {user?.name}! </h1>
                <h3 className="home-subtitle">Please choose where you want to go:</h3>
                <div className="home-actions">
                    <Button variant="secondary" className="home-btn" onClick={() => { window.location.assign("/competence")}}>
                        Competence viewer
                    </Button>
                    { user?.type === 'ADMIN' ? (
                        <Button variant="secondary" className="home-btn" onClick={() => { window.location.assign("/reports")}}>
                            Report viewer
                        </Button>
                    ) : null}
                </div>
            </div>
        );
    }
}

export default Home;