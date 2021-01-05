import React from 'react';
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import {ChakraProvider, theme} from '@chakra-ui/react';
import SignIn from './components/account/SignIn';
import SignUp from './components/account/SignUp';
import NavBar from './components/navigation/NavBar';
import GameSearch from './components/game/game-search/GameSearch';

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
                    <Route path='*'><Redirect to='/'/></Route>
                </Switch>
            </Router>
        </ChakraProvider>
    );
}
