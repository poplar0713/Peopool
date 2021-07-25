<template>
  <el-table :data="tableData" height="300">
    <el-table-column type="expand">
      <template #default="props">
        <div style="text-align:center;">
          <div>
            <el-radio v-model="radio" label="1" border style=""
              >{{ props.row.date1 }} {{ props.row.time1 }}</el-radio
            >
          </div>
          <div>
            <el-radio v-model="radio" label="2" border style=" margin:10px"
              >{{ props.row.date2 }} {{ props.row.time2 }}</el-radio
            >
          </div>
          <div>
            <el-radio
              v-model="radio"
              label="3"
              border
              style="margin-bottom:10px"
              >{{ props.row.date3 }} {{ props.row.time3 }}</el-radio
            >
          </div>
          <div>
            <el-button @click="submit(props.row, radio)">제출</el-button>
            <el-button type="danger" @click="reject(props.row)">거절</el-button>
          </div>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="" prop="company_name"></el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      radio: "1",
      submitteddate: "",
      submittedtime: "",
      responseform: [{ submitteddate: "" }, { submittedtime: "" }],
      tableData: [
        {
          company_id: 1,
          company_name: "삼성피자",
          company_field: "??????????",
          date1: "2021-08-30",
          time1: "13:00-14:00",
          date2: "2021-08-31",
          time2: "13:00-14:00",
          date3: "2021-08-32",
          time3: "13:00-14:00",
        },
        {
          company_id: 2,
          company_name: "삼성햄버거",
          company_field: "??????????",
          date1: "2021-04-30",
          time1: "13:00-14:00",
          date2: "2021-03-30",
          time2: "13:00-14:00",
          date3: "2021-02-30",
          time3: "13:00-14:00",
        },
        {
          company_id: 3,
          company_name: "삼성양말",
          company_field: "??????????",
          date1: "2021-02-30",
          time1: "13:00-14:00",
          date2: "2021-03-30",
          time2: "13:00-14:00",
          date3: "2021-04-30",
          time3: "13:00-14:00",
        },
        {
          company_id: 4,
          company_name: "삼성볶음밥",
          company_field: "??????????",
          date1: "2021-02-30",
          time1: "13:00-14:00",
          date2: "2021-03-30",
          time2: "13:00-14:00",
          date3: "2021-04-30",
          time3: "13:00-14:00",
        },
        {
          company_id: 5,
          company_name: "삼성정장",
          company_field: "??????????",
          date1: "2021-02-30",
          time1: "13:00-14:00",
          date2: "2021-03-30",
          time2: "13:00-14:00",
          date3: "2021-04-30",
          time3: "13:00-14:00",
        },
        {
          company_id: 5,
          company_name: "삼성신발",
          company_field: "??????????",
          date1: "2021-02-30",
          time1: "13:00-14:00",
          date2: "2021-03-30",
          time2: "13:00-14:00",
          date3: "2021-04-30",
          time3: "13:00-14:00",
        },
      ],
    };
  },
  methods: {
    submit(row, radio) {
      this.tableData.splice(this.tableData.indexOf(row), 1);
      this.$message({
        message: "면접일정이 확정되었습니다.",
        type: "success",
      });
      // console.log(row.date);
      // console.log(radio1);
      const date = eval(`row.date${radio}`);
      this.submitteddate = date;
      console.log(this.submitteddate);
      const time = eval(`row.time${radio}`);
      this.submittedtime = time;
      console.log(this.submittedtime);
      return row;
    },
    reject(row) {
      console.log(row);
      // 삭제
      this.tableData.splice(this.tableData.indexOf(row), 1);
      this.$message({
        message: "면접일정이 거부되었습니다.",
        type: "warning",
      });
    },
  },
};
</script>

<style></style>
