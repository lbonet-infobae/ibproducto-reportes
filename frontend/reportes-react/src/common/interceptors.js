import React from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';


function Interceptors() {
    let history = useHistory();

    axios.interceptors.response.use(response => {
        return response;
    }, error => {
        if (error.response.status === 401) {
            history.push("/sign-in");
        } else {
            return error;
        }
    });

    return (<></>);
}

export default Interceptors;