import React from 'react';
import {Button} from "@material-ui/core";

const NavBar = (props) => {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <a href="/" className="navbar-brand">Card Against Humanity</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"/>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item first">
                        <a href={"/game"} className="nav-link active">
                            Games
                        </a>
                    </li>
                </ul>
                <ul className="navbar-nav my-2 my-lg-0">

                    <li className="nav-item dropdown">
                        <a className="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Dropdown link
                        </a>
                        <div className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a className="dropdown-item" href="#">Action</a>
                            <a className="dropdown-item" href="#">Another action</a>
                            <a className="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li className="nav-item last">
                        {!props.loggedIn ? <Button className="ml-2" variant="contained" href={'/login'}>Login</Button> :
                            <Button className="ml-2" variant="contained" onClick={() => sessionStorage.clear()}
                                    href="/">Logout</Button>}
                    </li>
                </ul>
            </div>
        </nav>
    );
};

export default NavBar;