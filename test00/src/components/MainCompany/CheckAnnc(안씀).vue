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
              v-if="scope.row.state == 1 || scope.row.state == 2"
              class="el-icon-success"
              style="color:#f1c40f"
            ></i>
            <i
              v-else-if="
                scope.row.state == 3 && scope.row.detailstate == '합격'
              "
              class="el-icon-success"
              style="color:green"
            ></i>
            <i v-else class="el-icon-error" style="color:red"></i>
          </template>
        </el-table-column>
        <el-table-column prop="title" label="제안포지션" width="300">
        </el-table-column>
        <el-table-column prop="date" label="제안일" width="180">
        </el-table-column>
        <el-table-column prop="applicant" label="지원자"> </el-table-column>
        <el-table-column prop="detailstate" label="상세상태"> </el-table-column>
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
          state: 3,
          date: "2016-05-03",
          title: "안드로이드 개발",
          url: "localhost:8080/company",
          applicant: "문영화",
          detailstate: "합격",
        },
        {
          state: 1,
          date: "2016-05-02",
          title: "backend 개발",
          url: "localhost:8080/company",
          applicant: "문영화",
          detailstate: "면접 제안",
        },
        {
          state: 3,
          date: "2016-05-04",
          title: "ios 개발",
          url: "localhost:8080/company",
          applicant: "문영화",
          detailstate: "탈락",
        },
        {
          state: 2,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: "문영화",
          detailstate: "결과 대기",
        },
        {
          state: 2,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: "문영화",
          detailstate: "결과 대기",
        },
        {
          state: 3,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: "문영화",
          detailstate: "탈락",
        },
        {
          state: 3,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: "문영화",
          detailstate: "합격",
        },
        {
          state: 1,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: "문영화",
          detailstate: "면접 제안",
        },
      ],
    };
  },
};
</script>

<style></style>
