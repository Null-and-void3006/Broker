import React, { Fragment } from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import { Navbar, Nav } from 'react-bootstrap';

import PrivateRoute from './PrivateRoute'
import Home from './Home';
import Login from './Login'
import Logout from './Logout';
import Competences from './Competences';
import Reports from './Reports';

import './App.css';

function App() {
  return (
    <div className="App">
      <Router>
        <div>
          <Navbar bg="light" expand="lg">
            <Navbar.Brand href="/">DiTra</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="mr-auto">
                { localStorage.getItem("jwt") ? (
                  <Fragment>
                    <Nav.Link href="/">Home</Nav.Link>
                    <Nav.Link href="/logout">Logout</Nav.Link>
                  </Fragment>
                ) : (
                  <Nav.Link href="/login">Login</Nav.Link>
                )}
              </Nav>
            </Navbar.Collapse>
          </Navbar>

          {/* A <Switch> looks through its children <Route>s and
              renders the first one that matches the current URL. */}
          <Switch>
            <Route path="/login">
              <Login />
            </Route>
            <Route path="/logout">
              <Logout />
            </Route>
            <PrivateRoute path="/competence">
              <Competences />
            </PrivateRoute>
            <PrivateRoute path="/reports">
              <Reports />
            </PrivateRoute>
            <PrivateRoute path="/">
              <Home />
            </PrivateRoute>
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
