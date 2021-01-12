import React from 'react';
import { BrowserRouter as Router, Redirect, Route, Switch } from 'react-router-dom';
import { ChakraProvider, theme } from '@chakra-ui/react';
import SignIn from './components/account/SignIn';
import SignUp from './components/account/SignUp';
import NavBar from './components/navigation/NavBar';
import GameSearch from './components/game/game-search/GameSearch';
import Game from './components/game/Game';

const parseSubFromJwt = (token) => {
    try {
        let base64Url = token.split('.')[1];
        let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        let jsonPayload = decodeURIComponent(atob(base64).split('').map((c) => {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        return JSON.parse(jsonPayload).sub;
    } catch (e) {
        return null;
    }
};

export default function App() {
    const cards = [
        {
            'id': '1',
            'status': 'PLAYED',
            'cardText': 'card1',
            'cardType': 'WHITE'
        },
        {
            'id': '2',
            'status': 'PLAYED',
            'cardText': 'card2',
            'cardType': 'WHITE'
        },
        {
            'id': '3',
            'status': 'PLAYED',
            'cardText': 'card3',
            'cardType': 'WHITE'
        },
        {
            'id': '3',
            'status': 'PLAYED',
            'cardText': 'card4',
            'cardType': 'WHITE'
        },
        {
            'id': '5',
            'status': 'PLAYED',
            'cardText': 'card5',
            'cardType': 'WHITE'
        },
        {
            'id': '6',
            'status': 'NOT_PLAYED',
            'cardText': 'card6',
            'cardType': 'WHITE'
        },
        {
            'id': '7',
            'status': 'NOT_PLAYED',
            'cardText': 'card7',
            'cardType': 'WHITE'
        }
    ];
    const loggedIn = localStorage.getItem('token') != null;

    return (
        <ChakraProvider theme={theme}>
            <Router>
                <NavBar loggedIn={loggedIn}
                        username={loggedIn ? parseSubFromJwt(localStorage.getItem('token')) : null}/>
                <Switch>
                    <Route path='/sign-in' component={SignIn}/>
                    <Route path='/sign-up' component={SignUp}/>
                    <Route path='/games' component={GameSearch}/>
                    <Route path="/game" exact render={() => (<Game cards={cards}/>)}/>
                    <Route path='*'><Redirect to='/games'/></Route>
                </Switch>
            </Router>
        </ChakraProvider>
    );
}
