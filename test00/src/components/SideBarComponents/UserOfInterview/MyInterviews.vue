<template>
  <el-table
    :data="tableData"
    :default-sort="{ prop: 'date', order: 'descending' }"
    style="width: 100%"
    height="250px"
    text-align="center"
  >
    <el-table-column prop="date" label="Date" sortable width="180">
    </el-table-column>
    <el-table-column prop="company" label="Company" width="180">
    </el-table-column>
    <el-table-column prop="time" label="time" width="180"> </el-table-column>
    <el-table-column prop="url" label="Interview URL">
      <el-button type="success"
        ><router-link to="/interviewroom"
          >Interview Room</router-link
        ></el-button
      >
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
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
    };
  },
  methods: {
    formatter(row, column) {
      console.log(column);
      return row.address;
    },
  },
};
</script>

<style>
</style>
