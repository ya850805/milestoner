<template>
  <div>
    <div>
      <b-tabs content-class="mt-3">
        <b-tab title="My profile" active></b-tab>
        <b-tab title="My milestone" @click="gotoMilestone"></b-tab>
        <b-tab title="Profession leaderboard" @click="gotoLeaderboard"></b-tab>
      </b-tabs>
    </div>
    <b-card>
      <b-card-text>
        <h2>{{ this.user.name }}</h2>
        <p>Email：{{ user.email }}</p>
        <p>Profession：{{ user.profession }}
        <p>Current points
          <b-icon icon="award-fill"></b-icon>
          ：{{ points }}
        </p>
      </b-card-text>
    </b-card>
  </div>
</template>

<script>
import {USER_DATA_KEY} from "../constant/milestoner-constant";
import axios from "axios";
import authHeader from "../services/auth-header";
import Swal from "sweetalert2";

export default {
  name: "Personal",
  data() {
    return {
      user: JSON.parse(localStorage.getItem(USER_DATA_KEY)),
      points: 0
    }
  },
  methods: {
    gotoMilestone() {
      this.$router.push('/milestone');
    },
    gotoLeaderboard() {
      this.$router.push('/leaderboard');
    }
  },
  mounted() {
    axios.get(`/data/usersPoints?userId=${this.user.userId}`, {headers: authHeader()})
      .then(res => {
        this.points = res.data;
      }).catch(err => {
        Swal.fire({
          icon: 'error',
          title: 'Search for user\'s points occurs error...'
        })
    })
  }
}
</script>

<style scoped>

</style>
