import React from "react";
import "./css/App.css";
import NavBar from "./components/NavBar"
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import Game from "./components/Game";
import CreateCard from "./components/CreateCard";
import Login from "./components/Login";

import 'jquery/dist/jquery.min.js';
import 'bootstrap/dist/js/bootstrap.min.js';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
    return (
        <Router>
            <NavBar/>
            <Switch>
                <Route path="/game" component={Game}/>
                <Route path="/admin" component={CreateCard}/>
                <Route path="/login" component={Login}/>
                <Route path="*"><Redirect to="/"/></Route>
            </Switch>
        </Router>
    );
}

export default App;
