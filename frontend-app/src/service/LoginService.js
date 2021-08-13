import axios from 'axios';
import React, { useState } from 'react';

const USER_LOGIN_API_URL = "http://localhost:8080/api/v1/login";

class LoginService {
    submitLoginForm(username, password) {
        let body = {username: username, password: password};
        return axios.post(USER_LOGIN_API_URL, body);
    }
}

export default new LoginService();