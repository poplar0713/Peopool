<template>
    <div>
      
      <el-main>
      
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
     
      </el-main>
   </div>
</template>

<script>
import UserDetail from '@/components/UserDetail.vue'


export default {
    components:{
        UserDetail,
        
     },
  name: 'HelloWorld',
  props: {
   
    followData: Array,
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.box-card{
 display: inline-block;
 margin: 5px;
}
</style>
