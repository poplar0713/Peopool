<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >Followers</el-button
  >

  <el-dialog
    title="Followers"
    v-model="dialogVisible"
    width="30%"
    :before-close="handleClose"
  >
    <!--  -->
    <el-table
      :data="
        followers.filter(
          (data) =>
            !search ||
            data.company_name.toLowerCase().includes(search.toLowerCase()) ||
            data.company_field.toLowerCase().includes(search.toLowerCase())
        )
      "
      width="100%"
      height="250px"
    >
      <el-table-column label="Company" prop="name"> </el-table-column>
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="mini" placeholder="Type to search" />
        </template>
        <template #default="scope">
          <el-button
            size="mini"
            type="primary"
            @click="
              (dialogVisible_company = true), companydetail(scope.row.following)
            "
            >정보보러가기</el-button
          >
          <el-dialog v-model="dialogVisible_company" class="info">
            <el-container>
              <el-header>
                <h2>
                  {{ this.ent_info.ent_name }}
                  <!-- 팔로우일경우 -->
                  <span v-if="follow" style="color: Tomato;">
                    <i
                      class="fas fa-heart fa-2x"
                      size:7x
                      @click="clickfollowBtn"
                      style="cursor:pointer"
                    ></i>
                  </span>
                  <!-- 언팔로우일경우 -->
                  <span v-if="follow == false" style="color: Tomato;">
                    <i
                      @click="clickfollowBtn"
                      class="far fa-heart fa-2x"
                      style="cursor:pointer"
                    ></i>
                  </span>
                </h2>
              </el-header>
              <el-container>
                <el-aside width="300px"
                  ><el-image
                    style="width: 300px; height: 300px"
                    :src="ent_img"
                  ></el-image
                ></el-aside>
                <el-main>
                  <h4>기업 대표 : {{ this.ent_info.ent_ceo }}</h4>
                  {{ this.ent_info.ent_info }}</el-main
                >
              </el-container>
              <el-footer> </el-footer>
            </el-container>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
    <!--  -->
  </el-dialog>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.user_index = index;
    //팔로워정보 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/fol/follower", {
        params: {
          index: index,
          type: 0,
        },
      })
      // 팔로워데이터 넣어주기
      .then((res) => {
        console.log(res);
        this.followers = res.data;
      })
      .catch((err) => console.log(err));
    return {
      dialogVisible: false,
      dialogVisible_company: false,
      user_index: "",
      followers: [],
      search: "",
      follow: "",
      ent_info: {
        ent_index: "",
        ent_name: "",
        ent_contact: "",
        ent_email: "",
        ent_image: "",
        ent_ceo: "",
        ent_history: "",
        ent_address: "",
        ent_website: "",
        ent_introduce: "",
      },
    };
  },
  methods: {
    companydetail(ent) {
      console.log(ent);
      //기업정보 가져오기
      axios
        .get(`https://i5d206.p.ssafy.io:8443/poe/index/${ent}`)
        .then((res) => {
          this.ent_info.ent_index = res.data.ent_index;
          this.ent_info.ent_name = res.data.ent_name;
          this.ent_info.ent_contact = res.data.ent_contact;
          this.ent_info.ent_email = res.data.ent_email;
          this.ent_info.ent_image = res.data.ent_image;
          this.ent_info.ent_ceo = res.data.ent_ceo;
          this.ent_info.ent_history = res.data.ent_history;
          this.ent_info.ent_address = res.data.ent_address;
          this.ent_info.ent_website = res.data.ent_website;
          this.ent_info.ent_introduce = res.data.ent_introduce;
        })
        .catch();
      // 팔로우했는지 체크해보기
      axios
        .post("https://i5d206.p.ssafy.io:8443/fol/check", {
          fol_type: 0,
          follower: this.ent,
          following: this.user_index,
        })
        .then((res) => {
          // 팔로우가 되어있는것
          console.log(res), (this.follow = true);
        })
        .catch((err) => {
          // 팔로우가 안되어있는것
          console.log(err), (this.follow = false);
        });
    },
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            data: {
              fol_type: 0,
              following: this.user_index,
              follower: this.ent_info.ent_index,
            },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
          })
          .catch((err) => console.log(err));
      } else if (this.follow == false) {
        console.log("팔로잉");
        axios
          .post("https://i5d206.p.ssafy.io:8443/fol", {
            fol_type: 0,
            follower: this.ent_info.ent_index,
            following: this.user_index,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
          })
          .catch();
      }
    },
    handleClose(done) {
      this.$confirm("창을 닫으시겠습니까?")
        .then(() => {
          done();
          this.dialogVisible = false;
          location.reload();
        })
        .catch(() => {});
    },
  },
};
</script>

<style></style>
