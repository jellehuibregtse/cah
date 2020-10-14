import React from "react";
import "./App.css";
import NavBar from "./components/NavBar";
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';

function App() {
    return (
        <Router>
            <Switch>
                <NavBar/>
                <Route path="*"><Redirect to="/"/></Route>
            </Switch>
        </Router>
    );
}

export default App;
