import axios from 'axios';

export const API_REPORTS = 'http://10.0.101.27:8090/api/reports';
export const API_AUTH = 'http://10.0.101.27:8090/api/auth';
// export const HOST = 'http://10.18.3.108:8080';
// console.log('HOST', HOST);

export function authHeader() {
  // return authorization header with basic auth credentials
  let token = localStorage.getItem('token');

  if (token) {
    return { 'Authorization': 'Basic ' + token };
  } else {
    return {};
  }
}

export function storyCountMonth(year, month, callback) {
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  };

  return axios.get(API_REPORTS + '/storycount/' + year + '/' + month, requestOptions)
    .then(res => callback(res))
    .catch(err => console.log(err));

}

export function storyCountYear(year, callback) {
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  };

  return axios.get(API_REPORTS + '/storycount/' + year, requestOptions)
    .then(res => callback(res))
    .catch(err => console.log(err));

}

export function loadEmpleados(year, month, callback) {
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  };

  return axios.get(API_REPORTS + '/storycount/' + year + '/' + month + '/users', requestOptions)
    .then(res => callback(res))
    .catch(err => console.log(err));
}

export function validateLogin(succesCallback, errorCallback) {
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  };

  return axios.get(API_AUTH + '/validate', requestOptions)
    .then(res => succesCallback(res))
    .catch(err => errorCallback(err));
}

export function login(username, password, callback) {
  return axios.get(API_AUTH + '/validate', {
    auth: {
      username: username,
      password: password
    }
  })
    .then(res => callback(res))
    .catch(err => console.log(err));
}