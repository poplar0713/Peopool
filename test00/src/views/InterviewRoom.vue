<template>
  <div>인터뷰룸</div>
  <div>
    {{$route.params.company}}기업의 인터뷰룸입니다.
  </div>
  <div>지원자 {{$route.params.user}}님의 면접을 기대합니다.</div>
</template>

<script>
export default {
  name:'InterviewRoom'
};
</script>

<style></style>
