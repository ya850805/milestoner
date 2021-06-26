import axios from "axios";
import {AUTH_TOKEN_KEY, USER_DATA_KEY} from "../constant/milestoner-constant";

export class AuthService {

  static login(user)  {
    //clear first
    localStorage.clear();

    return axios.post('/data/login', {email : user.email, password : user.password})
      .then(res => {
        if(res.headers.authorization) {
          localStorage.setItem(AUTH_TOKEN_KEY, JSON.stringify(res.headers.authorization));
          localStorage.setItem(USER_DATA_KEY, JSON.stringify(res.data));
        }
        return res.headers.authorization;
      })
  }

  static logout() {
    localStorage.removeItem(AUTH_TOKEN_KEY);
    //todo
    // window.location = '/';
  }

  static register(user) {
    //todo
  }

}
