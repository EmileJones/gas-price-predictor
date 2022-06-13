<template>
  <el-container>
    <el-main>
      <el-table v-loading="loading" :data="stationList">
        <el-table-column label="加油站名称" align="center" prop="stationName" width="500"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="500">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-receiving"
              @click="redirectCalculator(scope.row.stationId)"
              v-hasPermi="['gas:predict:predict']"
            >计算销量
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
import {listOpponentStation} from '@/api/gas/opponent-station';
import {calculator, getOilTypes} from '@/api/gas/predict';
import {listStation} from '@/api/gas/station';

export default {
  name: "PredictIndex",
  data() {
    return {
      formData: {
        stationId: "",
        presentMoney: 0,
        targetMoney: 0,
        presentAverageSalesVolume: 0,
        opponentPriceData: [],
        oilType: "",
      },
      avgSalesVolume: -1,
      oilType: [],
      loading: true,
      stationList: [],
    }
  },
  methods: {
    calculate() {
      calculator(this.formData).then(resp => {
        console.log(resp);
      });
    },
    redirectCalculator(stationId){
      this.$router.push(`/gas/calculator/index/${stationId}`)
    }
  },
  created() {
    listStation().then(response => {
      this.stationList = response.rows
      this.loading = false
    })
  }
}
</script>

<style>

</style>
