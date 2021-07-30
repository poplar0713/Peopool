<template>
  <el-table
    :data="
      tableData.filter(
        (data) =>
          !search || data.company.toLowerCase().includes(search.toLowerCase())
      )
    "
    :default-sort="{ prop: 'date', order: 'ascending' }"
    height="300"
  >
    <el-table-column label="Date" prop="date" sortable> </el-table-column>
    <el-table-column label="Company" prop="company"> </el-table-column>
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button
          size="mini"
          @click="Cancel(scope.$index, scope.row, scope.row.company)"
          >Cancel</el-button
        >
        <!-- {{scope.row.company}} -->
        <el-button
          size="mini"
          type="danger"
          @click="GoToInteriewRoom(scope.row.company, user)"
          >Interview Room</el-button
        >
        <!-- {{scope.row.url}} -->
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      user: "김백수",
      tableData: [
        {
          date: "2021-08-30",
          company: "삼성",
          time: "13:00",
          url: "www.naver.com",
        },
        {
          date: "2021-08-31",
          company: "카카오",
          time: "13:00",
          url: "www.naver.com",
        },
        {
          date: "2021-08-32",
          company: "네이버",
          time: "13:00",
          url: "www.naver.com",
        },
        {
          date: "2021-08-33",
          company: "싸피",
          time: "13:00",
          url: "www.naver.com",
        },
      ],
      search: "",
    };
  },
  methods: {
    Cancel(index, row, company) {
      console.log(index, row, company);
    },
    GoToInteriewRoom(company, user) {
      console.log(company, user);
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        // this.$router.push(`interviewroom/${company}/${user}`);
        this.$router.push({
          name: "InterviewRoom",
          params: { company: company, user: user },
        });
        // user/interviewroom으로 넘어감
      }, 1000);
    },
  },
};
</script>

<style></style>
