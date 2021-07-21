<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <router-link to="/home">
        <div class="title">
          <div>
            <span class="f">P</span>
            <span class="s">eo</span>
            <span class="s">P</span>
            <span class="f">ool</span>
          </div>
        </div>
      </router-link>
    </el-col>
    <el-col :span="12" :offset="6">
      <div style="margin-top: 15px; width:100%">
        <el-input
          placeholder="Please input"
          v-model="input3"
          class="input-with-select"
        >
          <template #append>
            <el-button icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {};
</script>

<style></style>
