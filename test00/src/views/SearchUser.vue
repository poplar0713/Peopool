<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><SideBarCompany /></el-aside>

    <el-container>
      <el-header><headerSearchUser /></el-header>
      <el-main>
        <SearchList
          :followData="followData"
          :inputdata="$route.params.keyword"
        />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
// // import ApplicantList from "@/components/MainCompany/ApplicantList.vue";
// // import CheckAnnc from "@/components/MainCompany/CheckAnnc.vue";
// import WebViewer from "@/components/MainCompany/WebViewer.vue";
import headerSearchUser from "@/components/SideBarComponents/headerSearchUser.vue";
import SearchList from "@/components/search/SearchList.vue";
export default {
  name: "SearchUser",
  components: {
    SideBarCompany,
    SearchList,
    //   WebViewer,
    headerSearchUser,
  },
  data() {
    return {
      followData: [
        {
          following: 26,
          img: "d.jfif",
          title: "항상 열정있는 자세",
          name: "문영화",
          tag: ["#java", "#javascript"],
        },
        {
          following: 10,
          img: "b.jpg",
          title: "항상 열정있는 자세",
          name: "여정동",
          tag: ["#java", "#javascript"],
        },
        {
          following: 33,
          img: "a.jpg",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#python", "#vue.js", "#javascript"],
        },
        {
          following: 15,
          img: "c.png",
          title: "항상 열정있는 자세",
          name: "하이이",
          tag: [
            "#java",
            "#javascript",
            "#mysql",
            "#SpringBoot",
            "#대한민국",
            "#만세",
            "#Docker",
            "#배포경험",
            "#python",
            "#경력",
            "#React",
          ],
        },
        {
          following: 33,
          img: "a.jpg",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#python", "#vue.js", "#javascript"],
        },
        {
          following: 33,
          img: "b.jpg",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#python", "#vue.js", "#javascript"],
        },
        {
          following: 33,
          img: "c.png",
          title: "항상 열정있는 자세",
          name: "문영화",
          tag: ["#python", "#vue.js", "#javascript"],
        },
        {
          following: 33,
          img: "a.jpg",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#python", "#vue.js", "#javascript"],
        },
        {
          following: 33,
          img: "b.jpg",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#python", "#vue.js", "#javascript"],
        },
        {
          following: 33,
          img: "c.png",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#python", "#vue.js", "#javascript"],
        },
        {
          following: 33,
          img: "a.jpg",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#python", "#vue.js", "#javascript"],
        },
      ],
    };
  },
};
</script>

<style></style>
