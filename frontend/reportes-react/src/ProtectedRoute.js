import React, { useState, useEffect } from 'react';
import { validateLogin } from 'api';
import {
    BrowserRouter as Router,
    Route,
    Redirect,
} from "react-router-dom";

function ProtectedRoute({ children, ...rest }) {

    function isLoggedIn() {
        const token = localStorage.getItem("token")
        return token ? true : false;
    }

    return (
        <Route
            {...rest}
            render={({ location }) =>
                isLoggedIn() ? (
                    children
                ) : (
                        <Redirect
                            to={{
                                pathname: "/sign-in",
                                state: { from: location }
                            }}
                        />
                    )
            } />
    )
}

export default ProtectedRoute;