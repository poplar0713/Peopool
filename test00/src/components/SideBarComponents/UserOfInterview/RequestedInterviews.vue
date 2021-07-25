<template>
  <el-table
    :data="
      InterviewReq.filter(
        (data) =>
          !search ||
          data.company_name.toLowerCase().includes(search.toLowerCase())
      )
    "
    style="width: 100%"
    height="250"
  >
    <el-table-column label="date" prop="date"> </el-table-column>
    <el-table-column label="time" prop="time"> </el-table-column>

    <el-table-column label="Company" prop="company_name"> </el-table-column>
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button
          size="mini"
          type="primary"
          @click="handleEdit(scope.$index, scope.row)"
          >Yes</el-button
        >
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
          >No</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      InterviewReq: [
        {
          company_id: 4,
          company_name: "구미 2반 6팀",
          company_field: "??????????",
          date: "2021-08-30",
          time: "13:00-14:00",
        },
        {
          company_id: 3,
          company_name: "구미 2반 6팀",
          company_field: "??????????",
          date: "2021-12-30",
          time: "13:00-14:00",
        },
        {
          company_id: 6,
          company_name: "구미 2반 6팀",
          company_field: "??????????",
          date: "2021-09-30",
          time: "13:00-14:00",
        },
      ],
    };
  },
};
</script>

<style></style>
