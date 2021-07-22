<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><CompanySidebarHwa /></el-aside>

    <el-container>
      <el-header><Header /></el-header>
      <el-main>
        <SearchList :followData="followData" inputdata="Java" />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CompanySidebarHwa from "@/components/SideBarComponents/CompanySidebarHwa.vue";
// // import ApplicantList from "@/components/MainCompany/ApplicantList.vue";
// // import CheckAnnc from "@/components/MainCompany/CheckAnnc.vue";
// import WebViewer from "@/components/MainCompany/WebViewer.vue";
import Header from "@/components/SideBarComponents/header.vue";
import SearchList from "@/components/search/SearchList.vue";
export default {
  components: {
    CompanySidebarHwa,
    SearchList,
    //   WebViewer,
    Header,
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
