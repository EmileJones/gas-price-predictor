<template>
  <div class="manageData">
    <h3>近三个周期内的经营数据</h3>
    <el-table
      :data="manageData"
      max-height="250"
      border
      style="width: 100%"
      class="box"
    >
      <el-table-column
        prop="startTime"
        label="起始时间"
        min-width="60"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="endTime"
        label="终止时间"
        min-width="60"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="comprehensiveUnitPrice"
        label="综合单价"
        min-width="40"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="dayAvgSalesVolume"
        label="日均销量"
        min-width="60"
        align="center"
      >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getOilManageData } from "@/api/gas/predict";

export default {
  name: "Manage_data",
  data() {
    return {
      manageData: [],
    };
  },
  mounted() {
    this.$bus.$on("oilType", (data) => {
      // console.log(777);
      getOilManageData(data.stationId, data.oilType).then((res) => {
        //剥离出传回来的数据
        let data = res.rows;
        this.manageData = [];
        data.forEach((item) => {
          //判断日均销量和综合单间是否为 null
          if (item.unitPrice === null) {
            item.unitPrice = "空";
          }
          if (item.oilSaleOnPeriod === null) {
            item.oilSaleOnPeriod = "空";
          }
          this.manageData.push({
            startTime: item.startTime,
            endTime: item.endingTime,
            comprehensiveUnitPrice: item.unitPrice,
            dayAvgSalesVolume: item.oilSaleOnPeriod,
          });
        });
      });
    });
  },
};
</script>

<style scoped>
.manageData {
  text-align: center;
}
.box {
  margin: 8px;
}
</style>