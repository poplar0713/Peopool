<template>
  <el-container>
    <el-aside width="200px">
      <router-link to="/home"
        ><img src="@/assets/PeoPool.png" alt="" style="width:70%"
      /></router-link>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#F5dF4D"
      >
        <!--  -->
        <el-menu-item index="1">
          <i class="el-icon-user"></i>
          <span>Profile</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-circle-plus-outline"></i>
          <span><TagBar /></span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-setting"></i>
          <span><DragSetting/></span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-document"></i>
          <span>CV</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="5">
          <i class="el-icon-video-camera"></i>
          <span>Interview</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="6" disabled>
          <i class="el-icon-document"></i>
          <span>혹시나</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="7">
          <i class="el-icon-setting"></i>
          <span>settings</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="7">
          <i class="el-icon-turn-off"></i>
          <span>Logout</span>
        </el-menu-item>
        <!--  -->
      </el-menu>
    </el-aside>
    <router-view></router-view>
  </el-container>
</template>

<script>
import DragSetting from "@/components/Recruitment/DragSetting.vue";
import TagBar from "@/components/SideBarComponents/TagBar.vue";
export default {
  components: {
    DragSetting,
    TagBar,
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    search(data) {
      console.log(data);
      this.$router.push("searchDetail");
    },
  },
  data() {
    return {
      options: [
        {
          value: "HTML",
          label: "HTML",
        },
        {
          value: "CSS",
          label: "CSS",
        },
        {
          value: "JavaScript",
          label: "JavaScript",
        },
      ],
      value: [],
    };
  },
};
</script>

<style>
img {
  /* display: block;
  margin: 0px auto; */
}
</style>
