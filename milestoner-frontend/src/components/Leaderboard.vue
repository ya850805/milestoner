<template>
  <div>
    <div>
      <b-tabs content-class="mt-3">
        <b-tab title="My profile" @click="gotoProfile"></b-tab>
        <b-tab title="My milestone" @click="gotoMilestone"></b-tab>
        <b-tab title="Profession leaderboard" active></b-tab>
      </b-tabs>

      Choose a profession：
      <b-form-select v-model="profession" :options="professionOptions" class="mb-3"
                     @change="selectLeaderboardByProfession"></b-form-select><br>

      <b-list-group>
        <b-list-group-item v-for="(user, index) in leaderboard" :key="index" class="d-flex justify-content-between align-items-center">
          <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">{{user.name}}</h5>
            <small>{{ user.points }}points</small>
          </div>
        </b-list-group-item>
      </b-list-group>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "../services/auth-header";
import Swal from "sweetalert2";

export default {
  name: "Leaderboard",
  data() {
    return {
      profession: '',
      professionOptions: [{value: '', text: '---'}],
      leaderboard: []
    }
  },
  methods: {
    gotoProfile() {
      this.$router.push('/personal');
    },
    gotoMilestone() {
      this.$router.push('/milestone');
    },
    selectLeaderboardByProfession() {
      const selectId = this.profession;
      if(selectId) { //有選中職業
        axios.get(`/data/professionUserList?professionId=${selectId}`, {headers: authHeader()})
          .then(res => {
            for (let i = 0; i < res.data.length; i++) {
              this.leaderboard.push({name: res.data[i].name, points: res.data[i].points})
            }
          }).catch(err => {
          console.log(err);
          Swal.fire('Get leaderboard occurs error...');
        })
      } else {
        this.leaderboard = [];
      }
    }
  },
  mounted() {
    axios.get('/data/profession', {headers: authHeader()})
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
