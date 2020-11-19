import React, {useState} from "react";
import "../css/Login.css";
import {Button, Checkbox, Grid} from "@material-ui/core";
import TextField from "@material-ui/core/TextField";
import {AccountCircle, Lock} from "@material-ui/icons";
import FormControlLabel from "@material-ui/core/FormControlLabel";

const Login = (props) => {
    let error;

    const [rememberMeChecked, setRememberMeChecked] = useState(false);

    const handleRememberMeCheck = (event) => {
        setRememberMeChecked(event.target.checked);
    }

    function handleSubmit(event) {
        console.log("submited")
        // event.preventDefault();
        // MessagingService.messageHandler('POST',!props.register? '/users/login' : '/users/register', user).then(() => {
        //     window.location.href = "/";
        // }).catch((e) => {error.innerText = e;});
    }

    return (
        <div className="container-fluid form-signin-container">
            <div className="text-center">
                <form className="form-signin" onSubmit={(e) => handleSubmit(e)}>
                    <h1 className="h1 mb-4 font-weight-bold">Please sign in</h1>
                    <Grid container spacing={5} alignItems="center">
                        <Grid item>
                            <Grid container spacing={1} alignItems="center">
                                <Grid item>
                                    <AccountCircle fontSize="large"/>
                                </Grid>
                                <Grid item>
                                    <TextField
                                        id="input-with-icon-grid"
                                        label="Username"
                                        type="username"
                                        autoComplete="current-username"
                                        variant="filled"
                                    />
                                </Grid>
                            </Grid>
                        </Grid>

                        <Grid item>
                            <Grid container spacing={1} alignItems="center">
                                <Grid item>
                                    <Lock fontSize="large"/>
                                </Grid>
                                <Grid item>
                                    <TextField
                                        id="filled-password-input"
                                        label="Password"
                                        type="password"
                                        autoComplete="current-password"
                                        variant="filled"
                                    />
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                    <div className="checkbox mt-3">
                        <FormControlLabel
                            value="end"
                            control={<Checkbox checked={rememberMeChecked} onChange={handleRememberMeCheck}
                                               color="primary"/>}
                            label="Remember me"
                            labelPlacement="end"
                        />
                    </div>
                    <label className="error" ref={(e) => { error = e; }}/>
                    <Button variant="contained"
                            color="primary"
                            type="submit"
                            size="large">Sign in</Button>
                </form>
            </div>
        </div>
    )
};

export default Login;