import React from "react";
import "./App.css";
import NavBar from "./components/NavBar"
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import Game from "./components/Game";
import CreateCard from "./components/CreateCard"

function App() {
    return (
        <Router>
            <NavBar/>
            <Switch>
                <Route path="/game" component={Game}/>
                <Route path="/admin" component={CreateCard}/>
                <Route path="*"><Redirect to="/"/></Route>
            </Switch>
        </Router>
    );
}

export default App;
