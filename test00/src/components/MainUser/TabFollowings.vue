<template>
  <el-table
    :data="
      followList.filter(
        (data) =>
          !search ||
          data.company_name.toLowerCase().includes(search.toLowerCase())
      )
    "
    style="width: 100%"
    height="250"
  >
    <el-table-column label="company_id" prop="company_id"> </el-table-column>
    <el-table-column label="Company" prop="company_name"> </el-table-column>
    <el-table-column label="Field" prop="company_field"> </el-table-column>
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
          >Edit</el-button
        >
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
          >unFollow</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      search: "",
      username: "싸피 김",
      followList: [
        {
          company_id: 0,
          company_name: "삼성전자",
          company_field: "전자",
        },
        {
          company_id: 1,
          company_name: "삼성SDS",
          company_field: "삼성",
        },
        {
          company_id: 2,
          company_name: "카카오",
          company_field: "IT",
        },
        {
          company_id: 3,
          company_name: "갓카오",
          company_field: "IT",
        },
        {
          company_id: 4,
          company_name: "카갓오",
          company_field: "IT",
        },
      ],
      InterviewReq: [
        {
          company_id: 6,
          company_name: "구미 2반 6팀",
          company_field: "??????????",
        },
      ],
      events: [
        {
          time: "2021/07/21 09:00",
          title: "면접 1",
          content: "A사 면접",
        },
        {
          time: "2021/07/21 10:00",
          title: "면접 2",
          content: "B사 면접",
        },
        {
          time: "2021/07/21 13:00",
          title: "면접 3",
          content: "C사 면접",
        },
        {
          time: "2021/07/21 16:00",
          title: "면접 4",
          content: "D사 면접",
        },
      ],
    };
  },
  computed: {
    hasfollowing: function() {
      if (this.followList.length > 0) return true;
      else return false;
    },
    hasInterviewReq: function() {
      if (this.InterviewReq.length > 0) return true;
      else return false;
    },
  },
  methods: {
    uploadFile() {},
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file.size);
    },
    handleExceed() {
      this.$message.warning(`최대 1개의 파일만 업로드 가능합니다`);
    },
    beforeRemove(file) {
      return this.$confirm(`Cancel the transfert of ${file.name} ?`);
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
       this.rows.splice(index, 1);
    },
  },
};
</script>

<style></style>
