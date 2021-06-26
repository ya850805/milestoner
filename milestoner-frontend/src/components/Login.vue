<template>
  <div>
    Email
    <b-form-input type="text" v-model="email"/>
    <br>
    Password
    <b-form-input type="password" v-model="password"/>
    <br>
    <b-button variant="success" @click="login">Login</b-button>
    <br><br>
    Don't have an account?&nbsp;<router-link to="/register">Register</router-link>
    <br>
    <b-button variant="success" @click="test">test API</b-button>
  </div>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';

import authHeader from "../services/auth-header";
import App from "../App";
import {AUTH_TOKEN_KEY, USER_DATA_KEY} from "../constant/milestoner-constant";

export default {
  name: "Login",
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    login() {
      const data = {
        email: this.email,
        password: this.password
      }

      this.$store.dispatch("auth/login", data)
        .then(res => {
          Swal.fire({
            icon: 'success',
            title: 'Login success!',
            text: 'Reach your milestone now!'
          })

          this.$root.$emit('login', JSON.parse(localStorage.getItem(USER_DATA_KEY)));
          this.$router.push("/personal");
        }).catch(err => {
        if (err.response.status === 401) {
          Swal.fire({
            icon: 'error',
            title: 'Email or Password was wrong.'
          })
          this.password = '';
        }
      })

    },
    test() {
      axios.get('/data/test', {headers: authHeader()})
        .then(res => {
          console.log(res)
        }).catch(err => {
        if (err.response.status === 401) {
          Swal.fire({
            icon: 'error',
            title: 'Test api failed, login first'
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
