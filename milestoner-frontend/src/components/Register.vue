<template>
  <div>
    <h3>Register in milestoner</h3>
    <div>
      name：<b-form-input type="text" v-model="name"></b-form-input><br>
      email：<b-form-input type="email" v-model="email"></b-form-input><br>
      password：<b-form-input type="password" v-model="password"></b-form-input><br>
      profession：<b-form-select v-model="profession" :options="professionOptions"></b-form-select><br><br>
    </div>

    <b-button variant="success" @click="registerAccount">Register</b-button>
    <b-button @click="gotoLogin">Back to login</b-button>

  </div>
</template>

<script>
import axios from "axios";
import authHeader from "../services/auth-header";
import Swal from "sweetalert2";

export default {
  name: "Register",
  data() {
    return {
      name : '',
      email : '',
      password : '',
      profession : '',
      professionOptions : [{text : '---', value : ''}]
    }
  },
  methods : {
    registerAccount() {
      axios.post('/data/user', {name : this.name, email : this.email, password : this.password, profession : this.profession})
        .then(res => {
          Swal.fire({
            icon: 'success',
            title: 'Register success!',
          })
          this.$router.push('/');
        })
        .catch(err => {
          console.log(err);
          Swal.fire('Register occurs error...')
        })
    },
    gotoLogin() {
      this.$router.push('/')
    }
  },
  mounted() {
    axios.get('/data/profession')
      .then(res => {
        for (let i = 0; i < res.data.length; i++) {
          this.professionOptions.push({text: res.data[i].name, value: res.data[i].professionId});
        }
      }).catch(err => {
      console.log(err);
      Swal.fire('Initial professions error...');
    })
  }
}
</script>

<style scoped>

</style>
