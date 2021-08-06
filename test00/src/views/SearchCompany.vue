<template>
  <el-container class="mainLayout">
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main>
        <el-container>
          <h2>'{{ $route.params.keyword }}' 검색결과</h2>
        </el-container>
        <!-- <el-table :data="nowPageData" style="width: 100%">
          <el-table-column prop="date" label="日期" sortable> </el-table-column>
          <el-table-column prop="ent_name" label="姓名" sortable> </el-table-column>
          <el-table-column prop="address" label="地址"> </el-table-column>
        </el-table> -->
        <el-row :gutter="24">
          <el-col :span="6" v-for="(item, i) in nowPageData" :key="i">
            <company-card :item="item"></company-card>
          </el-col>
        </el-row>
        <div style="text-align:center">
          <el-pagination
            layout="prev, pager, next"
            :total="result.length"
            :page-size="8"
            :page-sizes="[8, 16, 24, 32]"
            v-model:currentPage="nowPage"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          >
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { ref } from "vue";
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";
import CompanyCard from "../components/search/CompanyCard.vue";

export default {
  name: "SearchCompany",
  components: {
    SideBarUser,
    headerSearchCompany,
    CompanyCard,
  },
  data() {
    const result = [
      {
        ent_ind: 0,
        ent_name: "삼성전자1",
        ent_info:
          "대한민국에 본사를 두고 전자제품을 제조하는 대한민국 최대의 다국적 기업. 창사 이래로 세계 시장에서 한국 기업으로서는 매우 큰 성공을 이루어냈으며, 현재 가장 인지도가 높은 대표적인 한국 기업으로 자리매김하였다.",
        ent_ceo: "이재용",
        ent_img: "../assets/삼성전자.jpg",
        follow: true,
      },
      {
        ent_id: 1,
        ent_name: "삼성SDS2",
        ent_info:
          "삼성(三星) 계열 전산업체로, 삼성의 IT서비스와 물류 사업을 담당하고 있다. 뒷글자만 다른 삼성SDI와 혼동하기 쉽다. 매출이나 이익면에서 국내 IT서비스/시스템 통합 업종에서 단연 최고라 할 수 있다.",
        ent_ceo: "황성우",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식회사3",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_ind: 0,
        ent_name: "삼성전자4",
        ent_info:
          "대한민국에 본사를 두고 전자제품을 제조하는 대한민국 최대의 다국적 기업. 창사 이래로 세계 시장에서 한국 기업으로서는 매우 큰 성공을 이루어냈으며, 현재 가장 인지도가 높은 대표적인 한국 기업으로 자리매김하였다.",
        ent_ceo: "이재용",
        ent_img: "../assets/삼성전자.jpg",
        follow: true,
      },
      {
        ent_id: 1,
        ent_name: "삼성SDS5",
        ent_info:
          "삼성(三星) 계열 전산업체로, 삼성의 IT서비스와 물류 사업을 담당하고 있다. 뒷글자만 다른 삼성SDI와 혼동하기 쉽다. 매출이나 이익면에서 국내 IT서비스/시스템 통합 업종에서 단연 최고라 할 수 있다.",
        ent_ceo: "황성우",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식회사6",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_ind: 0,
        ent_name: "삼성전자7",
        ent_info:
          "대한민국에 본사를 두고 전자제품을 제조하는 대한민국 최대의 다국적 기업. 창사 이래로 세계 시장에서 한국 기업으로서는 매우 큰 성공을 이루어냈으며, 현재 가장 인지도가 높은 대표적인 한국 기업으로 자리매김하였다.",
        ent_ceo: "이재용",
        ent_img: "../assets/삼성전자.jpg",
        follow: true,
      },
      {
        ent_id: 1,
        ent_name: "삼성SDS8",
        ent_info:
          "삼성(三星) 계열 전산업체로, 삼성의 IT서비스와 물류 사업을 담당하고 있다. 뒷글자만 다른 삼성SDI와 혼동하기 쉽다. 매출이나 이익면에서 국내 IT서비스/시스템 통합 업종에서 단연 최고라 할 수 있다.",
        ent_ceo: "황성우",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식회사9",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_ind: 0,
        ent_name: "삼성전자0",
        ent_info:
          "대한민국에 본사를 두고 전자제품을 제조하는 대한민국 최대의 다국적 기업. 창사 이래로 세계 시장에서 한국 기업으로서는 매우 큰 성공을 이루어냈으며, 현재 가장 인지도가 높은 대표적인 한국 기업으로 자리매김하였다.",
        ent_ceo: "이재용",
        ent_img: "../assets/삼성전자.jpg",
        follow: true,
      },
      {
        ent_id: 1,
        ent_name: "삼성SDS-=",
        ent_info:
          "삼성(三星) 계열 전산업체로, 삼성의 IT서비스와 물류 사업을 담당하고 있다. 뒷글자만 다른 삼성SDI와 혼동하기 쉽다. 매출이나 이익면에서 국내 IT서비스/시스템 통합 업종에서 단연 최고라 할 수 있다.",
        ent_ceo: "황성우",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식회사123",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식sdasdas회사123",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식sdasd153131as회사123",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식sdasdas회사123",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식sdasdas회사123",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식sdasdas회사123",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
      {
        ent_id: 2,
        ent_name: "삼성물산주식sdasdas회사123",
        ent_info:
          "삼성그룹(三星) 계열 종합물류/무역/레저/건설 회사이자 삼성의 모태가 되는 회사. 그렇지만, 이런 경제적 위상보다는 삼성그룹의 실질 지주회사라는 정치적 위상이 더 크다. 삼성생명 주식 19.47%, 삼성전자 주식 5.01%[3], 삼성바이오로직스 주식 43.44%를 소유했기 때문. 오너 일가가 직접 보유한 삼성전자 지분과 삼성물산, 삼성생명이 보유한 삼성전자 지분을 모두 합하면 19.31%에 달하고, 기타 계열사들과 재단 지분까지 합하면 21%에 가깝다.",
        ent_ceo: "고정석 외 2인",
        ent_img: "../assets/삼성전자.jpg",
        follow: false,
      },
    ];
    let tableSize = 8;
    let nowPage = ref(1);
    let nowPageData = ref(result);

    function initData() {
      sliceData();
    }

    function handleSizeChange(val) {
      tableSize = val;
      sliceData();
    }

    function handleCurrentChange(val) {
      nowPage.value = val;
      sliceData();
    }

    function sliceData() {
      nowPageData.value = result.slice(
        tableSize * nowPage.value - tableSize,
        tableSize * nowPage.value
      );
    }

    initData();

    return {
      nowPage,
      result,
      handleSizeChange,
      handleCurrentChange,
      tableSize,
      nowPageData,
    };
  },
};
</script>

<style></style>
