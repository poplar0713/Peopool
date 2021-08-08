<template>
  회원탈퇴를 진행하게되면<br />
  더이상 PeoPool의 서비스를 이용하실 수 없습니다.<br />
  고객님의 정보는 즉시 폐기됩니다.<br />
  탈퇴 후 재가입하신 다음 서비스를 이용하실 수 있습니다.<br />
  <div style="text-align:center">
    <el-button type="danger" @click="deleteaccountcompany">회원탈퇴</el-button>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
export default {
  data() {
    return {};
  },
  methods: {
    deleteaccountcompany() {
      // 토큰가져오기
      const token = localStorage.getItem("token");
      const decoded = jwt_decode(token);
      const index = decoded.index;
      // 회원정보 가져오기
      axios
        .delete(`https://i5d206.p.ssafy.io:8443/ent/${index}`, {
          headers: { Authorization: token },
        })
        .then((res) => {
          this.openFullScreen2();
          console.log(res);
          // 깔끔하게 비우기
          localStorage.clear();
          setTimeout(() => {
            localStorage.clear();
            this.$message({
              message: "PeoPool을 사랑해주셔서 감사합니다",
              type: "success",
            });
            this.$router.push("/");
          }, 3000);
        })
        .catch((err) => {
          this.openFullScreen2();
          console.log(err);
          this.$message({
            message: "회원탈퇴에 실패했습니다, 다시 시도해주세요",
            type: "warning",
          });
          if (err.response.data.status == 401) {
            this.$message.error('로그인세션이 만료되었습니다');
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    // 스크린로딩
    openFullScreen2() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
      }, 3000);
    },
  },
};
</script>

<style></style>
