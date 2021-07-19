<template>
<div style="text-align: center; align-items: center; margin-top: 10px;">
  <Login/>
  <br>
  <NotLoginMain/>
</div>
</template>

<script>
import Login from '@/components/Login.vue'
import NotLoginMain from '@/components/NotLoginMain.vue'
export default {
  components:{
    Login,
    NotLoginMain,
  }

}
</script>

<style>
div {
  
}
</style>