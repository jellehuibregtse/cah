import React from "react";
import "./App.css";
import NavBar from "./components/NavBar"
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import Game from "./components/Game";

function App() {
    return (
        <Router>
            <NavBar/>
            <Switch>
                <Route path="/game" component={Game}/>
                <Route path="*"><Redirect to="/"/></Route>
            </Switch>
        </Router>
    );
}

export default App;
