<template>
  <div id="app">
    <div class="mx-auto" style="width: 600px;">
      <h1>
        <span style="text-align:left;">Milestoner</span>
      </h1>
      <h3>
        <span style="text-align:right;">
          <p v-if="user">
            Hi, {{user.name}}
            <b-button variant="danger" @click="logout">Logout</b-button>
          </p>
        </span>
      </h3>
      <br>
      <router-view/>
    </div>
  </div>
</template>

<script>
import {AUTH_TOKEN_KEY, USER_DATA_KEY} from "./constant/milestoner-constant";
import Swal from "sweetalert2";

export default {
  name: 'App',
  data() {
    return {
      user: JSON.parse(localStorage.getItem(USER_DATA_KEY))
    }
  },
  methods: {
    gotoLogin() {
      this.$router.push('/');
    },
    logout() {
      Swal.fire({
        title: 'You had been logout!',
        text: 'Bye! See you next time!'
      })
      this.user = null;
      localStorage.clear();
      this.$router.push('/');
    }
  },
  mounted() {
    //掛載時判斷有沒有登入資訊
    if(localStorage.getItem(AUTH_TOKEN_KEY)) {
      this.$router.push('/personal');
    }

    this.$root.$on('login', (user) => { // here you need to use the arrow function
      this.user = user;
    })
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  /*width: 50%;*/
}
</style>
