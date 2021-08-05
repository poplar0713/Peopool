<template>
  <div>
    <input v-model="id" placeholder="id" />
    <input v-model="pw" placeholder="pw" />
    <button @click="enter">join</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      pw: "",
    };
  },
  methods: {
    enter() {
      console.log(this.id);
      console.log(this.pw);
    },
  },
};
</script>

<style></style>
