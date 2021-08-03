<template>
  <el-container>
    <el-aside width="200px"><SideBarCompany /></el-aside>
    <el-main>
      <div>
        <el-header>#선택태그</el-header>
        <el-main>
          <el-space>
            <el-card
              class="box-card"
              style="width: 250px"
              v-for="i in 50"
              :key="i"
            >
              <template #header>
                <div class="card-header">
                  <span>{{ i }}번 지원자(나이, 지역)</span>
                  <br />
                  <UserDetail />
                </div>
              </template>
              <div v-for="o in 4" :key="o" class="text item">
                {{ "List item " + o }}
              </div>
            </el-card>
          </el-space>
        </el-main>
      </div>
      <div>
        <el-header>#선택태그</el-header>
        <el-main>
          <el-space>
            <el-card
              class="box-card"
              style="width: 250px"
              v-for="i in 50"
              :key="i"
            >
              <template #header>
                <div class="card-header">
                  <span>{{ i }}번 지원자(나이, 지역)</span>
                  <br />
                  <UserDetail />
                </div>
              </template>
              <div v-for="o in 4" :key="o" class="text item">
                {{ "List item " + o }}
              </div>
            </el-card>
          </el-space>
        </el-main>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import UserDetail from "@/components/UserDetail.vue";
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
export default {
  components: {
    UserDetail,
    SideBarCompany,
  },
  data() {
    return {
      dialogVisible: false,
      activeNames: ["1"],
      activeName: "1",
      value1: "",
      value: "",
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("창을 닫으시겠습니까?")
        .then(() => {
          done();
          this.dialogVisible = false;
        })
        .catch(() => {});
    },
  },
};
</script>

<style></style>
