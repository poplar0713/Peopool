<template>
  <el-container>
    <el-aside width="200px"><SideBar/></el-aside>
    <el-container>

        <el-header>
        <div style="margin-top: 15px; width:100%">
  <el-input placeholder="Please input" v-model="input3" class="input-with-select">
    
    <template #append>
      <el-button icon="el-icon-search"></el-button>
    </template>
  </el-input>
</div>
    </el-header>
 <el-main>
      <el-table
      :data="tableData"
    :default-sort = "{prop: 'date', order: 'descending'}"
      style="width: 100%">

       <el-table-column
        prop="state"
        label="상태"
        width="50"
       >
       <template #default="scope">
        <i v-if="scope.row.state==true" class="el-icon-success" style="color:green"></i>
        <i v-else class="el-icon-error" style="color:red"></i>
      </template>
      </el-table-column>
      <el-table-column
        prop="title"
        label="공고"
        width="300">
      </el-table-column>
      <el-table-column
        prop="date"
        label="등록일"
        width="180">
      </el-table-column>
        <el-table-column
        prop="applicant"
        label="지원자">
      </el-table-column>
       <el-table-column
        prop="noofrect"
        label="모집인원">
      </el-table-column>
      <el-table-column
        prop="url"
        label="바로가기">
      </el-table-column>
      
    </el-table>
    <div>
      <div>
        <el-row :gutter="24">
          <el-col :span="6"> <h1><i class="el-icon-user-solid"></i>우리회사를 팔로우한 지원자</h1></el-col>
         <el-col :span="6" :offset="12"> <h1>>> 더보기</h1></el-col>
        </el-row>

      </div>
      <el-main>
      <el-space>
        <el-card class="box-card" style="width: 250px" v-for="fd in followData" :key="fd">
          <template #header>
            <div class="card-header">
              
              <img src="a.jpg" style="width: 150px"/>
              <br>
              
            </div>
          </template>
          <h1>{{fd.title}}</h1>
          <div v-for="tag in fd.tag" :key="tag" class="text item">
            {{tag}}
          </div>
          <UserDetail/>
        </el-card>
      </el-space>
      </el-main>
    
   </div>
  <applicant-list title="팔로잉한 지원자" followData="this.followData"/>
 </el-main>
    </el-container>
  
  </el-container>
</template>

<script>
import SideBar from '@/components/SideBar.vue'
import UserDetail from '@/components/UserDetail.vue'
import ApplicantList from '@/components/ApplicantList.vue'


export default {
  components:{
    SideBar,
    UserDetail,
    ApplicantList
    
  },
  data() {
        return {
          tableData: [{
            state:true,
            date: '2016-05-03',
            title: '안드로이드 개발 및 ios 개발',
            url: 'localhost:8080/company',
            applicant:5,
            noofrect:0,
          }, {
              state:true,
            date: '2016-05-02',
            title: '안드로이드 개발 및 ios 개발',
            url: 'localhost:8080/company',
            applicant:5,
            noofrect:0,
          }, {
              state:true,
            date: '2016-05-04',
            title: '안드로이드 개발 및 ios 개발',
            url: 'localhost:8080/company',
            applicant:5,
            noofrect:2,
          }, {
              state:false,
            date: '2016-05-01',
            title: '안드로이드 개발 및 ios 개발',
            url: 'localhost:8080/company',
            applicant:5,
            noofrect:1,
          }],
          followData:[{
            img:'',
            title:'항상 열정있는 자세',
            name:'문영화',
            tag:['#java','#javascript'],
          },
          {
            img:'',
            title:'항상 열정있는 자세',
            name:'여정동',
            tag:['#java','#javascript'],

          },
          {
            img:'',
            title:'항상 열정있는 자세',
            name:'조영우',
            tag:['#vue.js','#javascript'],

          }],
        }
      },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  },
}
</script>

<style>

</style>
