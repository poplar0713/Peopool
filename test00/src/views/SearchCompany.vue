<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><SideBarUser /></el-aside>

    <el-container>
      <el-header><Header /></el-header>
      <el-main>
        <el-container>
          <h2>'{{ this.keyword }}' 검색결과</h2>
        </el-container>
        <el-container>
          <div v-if="this.ents.length > 0">
            <el-aside width="50%"
              ><h3>기업 {{ this.ents.length }}</h3></el-aside
            >
            <el-main>
              <div class="contents">
                <company-card
                  v-for="(item, i) in listData"
                  :key="i"
                  :ent_id="item.ent_id"
                  :ent_img="item.ent_img"
                  :ent_name="item.ent_name"
                  :ent_info="item.ent_info"
                  :ent_ceo="item.ent_ceo"
                  :follow="item.follow"
                ></company-card>
              </div>
              <br />
              <pagination
                :pageSetting="pageDataSetting(this.getLength(), this.limit, this.block, this.page)"
                @paging="pagingMethod"
            /></el-main>
          </div>
          <div v-else>
            <el-main>
              <h3>검색결과가 없어요</h3>
            </el-main>
          </div>
        </el-container>

        <el-divider />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import Header from "@/components/SideBarComponents/header.vue";
import CompanyCard from "../components/search/CompanyCard.vue";
import Pagination from "../components/Pagination.vue";

export default {
  components: {
    SideBarUser,
    Header,
    CompanyCard,
    Pagination,
  },

  data() {
    return {
      keyword: "삼성",
      listData: [],
      ents: [
        {
          ent_ind: 0,
          ent_name: "삼성전자",
          ent_info:
            "대한민국에 본사를 두고 전자제품을 제조하는 대한민국 최대의 다국적 기업. 창사 이래로 세계 시장에서 한국 기업으로서는 매우 큰 성공을 이루어냈으며, 현재 가장 인지도가 높은 대표적인 한국 기업으로 자리매김하였다.",
          ent_ceo: "이재용",
          ent_img: "../assets/삼성전자.jpg",
          follow: true,
        },
        {
          ent_id: 1,
          ent_name: "삼성SDS",
          ent_info:
            "삼성(三星) 계열 전산업체로, 삼성의 IT서비스와 물류 사업을 담당하고 있다. 뒷글자만 다른 삼성SDI와 혼동하기 쉽다. 매출이나 이익면에서 국내 IT서비스/시스템 통합 업종에서 단연 최고라 할 수 있다.",
          ent_ceo: "황성우",
          ent_img: "../assets/삼성전자.jpg",
          follow: false,
        },
        {
          ent_id: 2,
          ent_name: "삼성물산주식회사",
          ent_info:
            "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
          ent_ceo: "고정석 외 2인",
          ent_img: "../assets/삼성전자.jpg",
          follow: false,
        },
        {
          ent_ind: 0,
          ent_name: "삼성전자",
          ent_info:
            "대한민국에 본사를 두고 전자제품을 제조하는 대한민국 최대의 다국적 기업. 창사 이래로 세계 시장에서 한국 기업으로서는 매우 큰 성공을 이루어냈으며, 현재 가장 인지도가 높은 대표적인 한국 기업으로 자리매김하였다.",
          ent_ceo: "이재용",
          ent_img: "../assets/삼성전자.jpg",
          follow: true,
        },
        {
          ent_id: 1,
          ent_name: "삼성SDS",
          ent_info:
            "삼성(三星) 계열 전산업체로, 삼성의 IT서비스와 물류 사업을 담당하고 있다. 뒷글자만 다른 삼성SDI와 혼동하기 쉽다. 매출이나 이익면에서 국내 IT서비스/시스템 통합 업종에서 단연 최고라 할 수 있다.",
          ent_ceo: "황성우",
          ent_img: "../assets/삼성전자.jpg",
          follow: false,
        },
        {
          ent_id: 2,
          ent_name: "삼성물산주식회사",
          ent_info:
            "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
          ent_ceo: "고정석 외 2인",
          ent_img: "../assets/삼성전자.jpg",
          follow: false,
        },
        {
          ent_ind: 0,
          ent_name: "삼성전자",
          ent_info:
            "대한민국에 본사를 두고 전자제품을 제조하는 대한민국 최대의 다국적 기업. 창사 이래로 세계 시장에서 한국 기업으로서는 매우 큰 성공을 이루어냈으며, 현재 가장 인지도가 높은 대표적인 한국 기업으로 자리매김하였다.",
          ent_ceo: "이재용",
          ent_img: "../assets/삼성전자.jpg",
          follow: true,
        },
        {
          ent_id: 1,
          ent_name: "삼성SDS",
          ent_info:
            "삼성(三星) 계열 전산업체로, 삼성의 IT서비스와 물류 사업을 담당하고 있다. 뒷글자만 다른 삼성SDI와 혼동하기 쉽다. 매출이나 이익면에서 국내 IT서비스/시스템 통합 업종에서 단연 최고라 할 수 있다.",
          ent_ceo: "황성우",
          ent_img: "../assets/삼성전자.jpg",
          follow: false,
        },
        {
          ent_id: 2,
          ent_name: "삼성물산주식회사",
          ent_info:
            "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
          ent_ceo: "고정석 외 2인",
          ent_img: "../assets/삼성전자.jpg",
          follow: false,
        },
        {
          ent_ind: 0,
          ent_name: "삼성전자",
          ent_info:
            "대한민국에 본사를 두고 전자제품을 제조하는 대한민국 최대의 다국적 기업. 창사 이래로 세계 시장에서 한국 기업으로서는 매우 큰 성공을 이루어냈으며, 현재 가장 인지도가 높은 대표적인 한국 기업으로 자리매김하였다.",
          ent_ceo: "이재용",
          ent_img: "../assets/삼성전자.jpg",
          follow: true,
        },
        {
          ent_id: 1,
          ent_name: "삼성SDS",
          ent_info:
            "삼성(三星) 계열 전산업체로, 삼성의 IT서비스와 물류 사업을 담당하고 있다. 뒷글자만 다른 삼성SDI와 혼동하기 쉽다. 매출이나 이익면에서 국내 IT서비스/시스템 통합 업종에서 단연 최고라 할 수 있다.",
          ent_ceo: "황성우",
          ent_img: "../assets/삼성전자.jpg",
          follow: false,
        },
        {
          ent_id: 2,
          ent_name: "삼성물산주식회사",
          ent_info:
            "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
          ent_ceo: "고정석 외 2인",
          ent_img: "../assets/삼성전자.jpg",
          follow: false,
        },
      ],
      page: 1,
      limit: 5,
      block: 5,
    };
  },
  methods: {
    getLength() {
      console.log(this.ents.length);
      return this.ents.length;
    },
    mounted() {
      this.pagingMethod(this.page);
    },
    pagingMethod(page) {
      this.listData = this.ents.slice((page - 1) * this.limit, page * this.limit);
      this.page = page;
      this.pageDataSetting(this.getLength(), this.limit, this.block, page);
    },
    pageDataSetting(total, limit, block, page) {
      const totalPage = Math.ceil(total / limit);
      let currentPage = page;
      const first = currentPage > 1 ? parseInt(currentPage, 10) - parseInt(1, 10) : null;
      const end = totalPage !== currentPage ? parseInt(currentPage, 10) + parseInt(1, 10) : null;

      let startIndex = (Math.ceil(currentPage / block) - 1) * block + 1;
      let endIndex = startIndex + block > totalPage ? totalPage : startIndex + block - 1;
      let list = [];
      for (let index = startIndex; index <= endIndex; index++) {
        list.push(index);
      }
      return { first, end, list, currentPage };
    },
  },
};
</script>

<style>
.contents {
  text-align: center;
  align-content: center;
  width: 100%;
}
.card {
  justify-content: center;
}
</style>
