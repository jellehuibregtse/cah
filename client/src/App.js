import React, {useEffect, useState} from "react";
import "./css/App.css";
import NavBar from "./components/NavBar"
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import Game from "./components/Game";
import CreateCard from "./components/CreateCard";
import Login from "./components/Login";

import 'jquery/dist/jquery.min.js';
import 'bootstrap/dist/js/bootstrap.min.js';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
    const [loggedIn, setLoggedIn] = useState(false);
    const [cards, setCards] = useState([]);

    useEffect(() => {
        if (sessionStorage.getItem("authToken") != null) {
            setLoggedIn(true);
        }

        function fetchCards() {
            setCards([
                {
                    "id": "1",
                    "status": "PLAYED",
                    "cardText": "card1",
                    "cardType": "WHITE"
                },
                {
                    "id": "2",
                    "status": "NOT_PLAYED",
                    "cardText": "card2",
                    "cardType": "WHITE"
                },
                {
                    "id": "3",
                    "status": "NOT_PLAYED",
                    "cardText": "card3",
                    "cardType": "WHITE"
                }
            ]);
        }

        fetchCards();
    }, []);

    return (
        <Router>
            <NavBar loggedIn={loggedIn}/>
            <Switch>
                <Route path="/game" exact render={() => (<Game cards={cards}/>)}/>
                <Route path="/admin" component={CreateCard}/>
                <Route path="/login" component={Login}/>
                <Route path="*"><Redirect to="/"/></Route>
            </Switch>
        </Router>
    );
}

export default App;
