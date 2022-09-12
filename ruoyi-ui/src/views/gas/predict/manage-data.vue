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
      <el-table-column prop="startTime" label="起始时间" min-width="60">
      </el-table-column>
      <el-table-column prop="endTime" label="终止时间" min-width="60">
      </el-table-column>
      <el-table-column
        prop="comprehensiveUnitPrice"
        label="综合单价"
        min-width="60"
      >
      </el-table-column>
      <el-table-column prop="dayAvgSalesVolume" label="日均销量" min-width="60">
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
      manageData: [
        {
          startTime: "7/13/21",
          endTime: "7/26/21",
          comprehensiveUnitPrice: 7.036,
          dayAvgSalesVolume: 9465.3985,
        },
        {
          startTime: "7/21/31",
          endTime: "8/23/21",
          comprehensiveUnitPrice: 6.969,
          dayAvgSalesVolume: 8986.66575,
        },
        {
          startTime: "8/24/21",
          endTime: "9/6/21",
          comprehensiveUnitPrice: 6.776,
          dayAvgSalesVolume: 9009.234214,
        },
      ],
      Data: {
        startTime: 0,
        endTime: 0,
        comprehensiveUnitPrice: 0,
        dayAvgSalesVolume: 0,
      },
    };
  },
  mounted() {
    this.$bus.$on("oilType", (data) => {
      // console.log(777);
      getOilManageData(data.stationId, data.oilType).then((res) => {
        //剥离出传回来的数据
        let data = res.data;
        console.log(res);
        data.forEach((item) => {
          this.manageData.push({
            startTime: item.startTime,
            endTime: item.endTime,
            comprehensiveUnitPrice: item.comprehensiveUnitPrice,
            dayAvgSalesVolume: item.dayAvgSalesVolume,
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