<template>
  <div class="app-container">
    <h1>方向参数矩阵修改</h1>
    <p><strong style="color: red;">注意！！！！</strong>请慎重使用此功能，在更新方向参数后，数据库地理信息将会被全部清除。建议仅在调整参数时使用，否则不建议使用此功能！</p>
    <p>下面的参数一共8行8列，从左到右、从上到下分别表示：东、西、北、南、东北、东南、西北、西南。每个参数代表从列代表的方向到行代表的方向。
      例如，第一行第二列的参数代表从东向西行驶所使用的权值（也就是调头）。</p>
    <table v-loading="loading" :data="directionMatrix">
      <tr v-for='(rowData, i) in directionMatrix'>
        <td v-for='(colData, j) in rowData'>
          <el-input-number v-model="directionMatrix[i][j]" size="20" :precision="2" :step="0.1" :max="10"></el-input-number>
        </td>
      </tr>
    </table>

    <el-button type="primary" @click="updateArgument">提交更改</el-button>
  </div>
</template>

<script>
import { getArgument, updateArgument } from "@/api/gas/argument";

export default {
  name: "Argument",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 方向参数矩阵
      directionEntity: null,
      directionMatrix: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 获取参数详情
    getList() {
      getArgument('DIRECTION_MATRIX').then(response => {
        this.directionEntity = response.data
        this.directionMatrix = eval(this.directionEntity.value);
        console.log(eval(this.directionEntity.value))
        this.loading = false;
      });
    },
    // 更新参数
    updateArgument() {
      this.directionEntity.value = JSON.stringify(this.directionMatrix)
      console.log(JSON.stringify(this.directionMatrix))
      updateArgument(this.directionEntity).then(response => {
        this.getList();
      });
    }
  }
};
</script>

<style scoped lang="css">
.el-input-number.is-without-controls .el-input__inner {
  width: 10px;
  line-height : 30px;
  height: 28px;
}
</style>