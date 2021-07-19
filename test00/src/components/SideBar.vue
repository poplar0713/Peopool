<template>
  <el-container>
    <el-aside width="200px">
      <router-link to="/home"><img src="@/assets/PeoPool.png" alt="" style="width:70%"></router-link>
      <el-select
        v-model="value"
        multiple
        filterable
        allow-create
        default-first-option
        placeholder="검색어를 입력해주세요."
        @keyup.enter="search">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        <!--  -->
        <el-submenu index="1">
          <template #title>
            <i class="el-icon-location"></i>
            <span>Menu</span>
          </template>
          <el-menu-item-group title="Account">
            <el-menu-item index="1-1">계정 관리</el-menu-item>
            <el-menu-item index="1-2">이력서 관리</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="#소제목">
            <el-menu-item index="1-3">#내용</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template #title>#제목</template>
            <el-menu-item index="1-4-1">#소제목, #내용</el-menu-item>
          </el-submenu>
        </el-submenu>
        <!--  -->
        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span>Navigator Two</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="3" disabled>
          <i class="el-icon-document"></i>
          <span>공고관리</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span>settings</span>
        </el-menu-item>
        <!--  -->
      </el-menu>
    </el-aside>
    <router-view></router-view>
  </el-container>
</template>

<script>
  export default {
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      search(data) {
        console.log(data)
        this.$router.push('searchDetail')
      }
    },
    data() {
      return {
        options: [{
          value: 'HTML',
          label: 'HTML'
        }, {
          value: 'CSS',
          label: 'CSS'
        }, {
          value: 'JavaScript',
          label: 'JavaScript'
        }],
        value: []
      }
    }
  }
</script>

<style>
img { 
  display: block; 
  margin: 0px auto; 
  }
</style>