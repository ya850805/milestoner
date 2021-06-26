<template>
  <div>
    <div>
      <b-tabs content-class="mt-3">
        <b-tab title="My profile" @click="gotoProfile"></b-tab>
        <b-tab title="My milestone" active ></b-tab>
        <b-tab title="Profession leaderboard" @click="gotoLeaderboard"></b-tab>
      </b-tabs>
    </div>
    <div>
      Mission title
      <b-input type="text" v-model="inputTitle"></b-input>
      <br>
      Mission description
      <b-input type="text" v-model="inputDescription"></b-input>
      <br>
      Deadline
      <b-form-datepicker v-model="inputDate" :min="minDate"
                         placeholder="Choose one date to complete the mission"></b-form-datepicker>
      <br>
      Gained points when mission complete
      <b-input type="number" v-model="inputCompletePoints"></b-input>
      <br>
      <b-button variant="primary" @click="createMission">Create mission</b-button>
      <b-button v-b-toggle.sidebar-1>View Current mission</b-button>
      <b-sidebar id="sidebar-1" title="Current mission" shadow>
        <div class="px-3 py-2">
          <b-list-group>
            <draggable v-model="missionList" handle=".mission">
            <b-list-group-item v-for="(mission, index) in missionList" :key="index">
              <div class="d-flex w-100 justify-content-between mission">
                <h5 class="mb-1">{{mission.title}}</h5>
                <small>{{mission.completePoints}}points</small>
              </div>
              <p class="mb-1">
                {{mission.description}}
              </p>
              <b-button-group>
                <b-button variant="warning" @click="completeMission(mission.missionId)">Complete</b-button>
                <b-button variant="danger" @click="giveupMission(mission.missionId)">Give up</b-button>
              </b-button-group>
            </b-list-group-item>
            </draggable>
          </b-list-group>
        </div>
      </b-sidebar>
    </div>
    <br><br>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "../services/auth-header";
import Swal from "sweetalert2";
import draggable from 'vuedraggable';

const OPERATOR_COMPLETE = '1';
const OPERATOR_GIVEUP = '2';

export default {
  name: "Milestone",
  components : {
    draggable
  },
  data() {
    return {
      inputTitle: '',
      inputDescription: '',
      inputDate: '',
      minDate: new Date(),
      inputCompletePoints: 0,
      missionList: []
    }
  },
  methods: {
    gotoProfile() {
      this.$router.push('/personal');
    },
    gotoLeaderboard() {
      this.$router.push('/leaderboard');
    },
    createMission() {
      axios.post('/data/mission', {
        title: this.inputTitle,
        description: this.inputDescription,
        date: this.inputDate,
        completePoints: this.inputCompletePoints
      }, {headers: authHeader()})
        .then(res => {
          Swal.fire({
            icon: 'success',
            title: 'Create mission success!',
            text: 'Try to complete your mission before the deadline!'
          })
          this.resetInputValue();

          //reset missionList
          axios.get("/data/mission", {headers: authHeader()})
            .then(res => {
              this.missionList = res.data;
            })
        })
        .catch(err => {
          Swal.fire({
            icon: 'error',
            title: 'Something wrong...',
          })
          this.resetInputValue();
        })
    },
    resetInputValue() {
      this.inputTitle = '';
      this.inputDescription = '';
      this.inputDate = null;
      this.inputCompletePoints = 0;
    },
    completeMission(id) {
      //完成任務
      Swal.fire({
        title: 'Are you sure that you had completed this mission?',
        showCancelButton: true,
        confirmButtonText: 'Yes, I had completed it!'
      }).then(res => {
        if (res.isConfirmed) {
          axios.put("/data/mission", {id : id, operator : OPERATOR_COMPLETE}, {headers: authHeader()})
            .then(res => {
              if(res.data.status === 200) {
                Swal.fire('you had complete up the mission.');
                this.missionList = this.missionList.filter(m => m.missionId !== id)
              }
            }).catch(err => {
            Swal.fire('Complete missions error...');
          })
        }
      })
    },
    giveupMission(id) {
      //放棄任務()
      Swal.fire({
        title: 'Are you sure that you want to give up this mission?',
        showCancelButton: true,
        confirmButtonText: 'Yes, I choose to give up!'
      }).then(res => {
        if (res.isConfirmed) {
          axios.put("/data/mission", {id : id, operator : OPERATOR_GIVEUP}, {headers: authHeader()})
            .then(res => {
              if(res.data.status === 200) {
                Swal.fire('you had give up the mission.');
                this.missionList = this.missionList.filter(m => m.missionId !== id)
              }
            }).catch(err => {
            Swal.fire('Give up missions error...');
          })
        }
      })
    }
  },
  mounted() {
    axios.get("/data/mission", {headers: authHeader()})
      .then(res => {
        // initial mission list
        for (let i = 0; i < res.data.length; i++) {
          this.missionList.push(res.data[i]);
        }
      }).catch(err => {
        Swal.fire('Initial missions error...');
    })
  }
}
</script>

<style scoped>

</style>
