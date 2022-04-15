<template>
  <div class="app-container">
    <h1>这是加油站{{stationId}}的经营数据列表</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {
      stationId: ""
    }
  },
  created() {
    this.stationId = this.$route.params && this.$route.params.stationId
  }
}
</script>

<style>

</style>