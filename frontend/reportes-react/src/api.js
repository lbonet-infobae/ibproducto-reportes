import axios from 'axios';

export const HOST = 'http://10.0.101.27:8090/api/reports';
// export const HOST = 'http://10.18.3.108:8080';
// console.log('HOST', HOST);

export function storyCount(year, month, callback) {
  return axios.get(HOST + '/storycount/' + year + '/' + month)
    .then(res => callback(res))
    .catch(err => console.log(err));

}

export function loadEmpleados(year, month, callback) {
  return axios.get(HOST + '/storycount/' + year + '/' + month + '/users')
    .then(res => callback(res))
    .catch(err => console.log(err));

}