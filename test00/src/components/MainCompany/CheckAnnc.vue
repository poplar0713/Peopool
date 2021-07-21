<template>
  <div>
    <el-scrollbar height="300px">
      <el-table
        :scroll="true"
        :data="tableData"
        :default-sort="{ prop: 'date', order: 'descending' }"
        style="width: 80%"
      >
        <el-table-column prop="state" label="상태" width="50">
          <template #default="scope">
            <i
              v-if="scope.row.state == true"
              class="el-icon-success"
              style="color:green"
            ></i>
            <i v-else class="el-icon-error" style="color:red"></i>
          </template>
        </el-table-column>
        <el-table-column prop="title" label="공고" width="300">
        </el-table-column>
        <el-table-column prop="date" label="등록일" width="180">
        </el-table-column>
        <el-table-column prop="applicant" label="지원자"> </el-table-column>
        <el-table-column prop="noofrect" label="모집인원"> </el-table-column>
        <el-table-column prop="url" label="바로가기"> </el-table-column>
      </el-table>
    </el-scrollbar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          state: false,
          date: "2016-05-03",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 0,
        },
        {
          state: true,
          date: "2016-05-02",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 0,
        },
        {
          state: true,
          date: "2016-05-04",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 2,
        },
        {
          state: false,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 1,
        },
        {
          state: false,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 1,
        },
        {
          state: false,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 1,
        },
        {
          state: false,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 1,
        },
        {
          state: false,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 1,
        },
      ],
    };
  },
};
</script>

<style></style>
