<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :span="24">
          <el-form :inline="true" class="demo-form-inline">
            <el-row>
              <el-col :span="24">
                <el-form-item label="请选择石油类型: ">
                  <el-select v-model="formData.oilType" placeholder="石油类型">
                    <el-option
                      v-for="item of oilType"
                      :key="item"
                      :label="item"
                      :value="item">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="6">
                <el-form-item label="您的目标价格: ">
                  <el-input v-model="formData.targetMoney" placeholder=""></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="您的当前价格: ">
                  <el-input v-model="formData.presentMoney" placeholder=""></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="您的平均销量: ">
                  <el-input v-model="formData.presentAverageSalesVolume" placeholder=""></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-table
                  :data="formData.opponentPriceData"
                  stripe
                  style="width: 100%">
                  <el-table-column
                    prop="outSystemGasStationName"
                    label="对手加油站名称"
                    width="350">
                  </el-table-column>
                  <el-table-column
                    prop="chaseMoney"
                    label="当前价格"
                    width="350">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.presentMoney" placeholder=""></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="chaseMoney"
                    label="预计跟随价格"
                    width="350">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.chaseMoney" placeholder=""></el-input>
                    </template>
                  </el-table-column>
                </el-table>
              </el-col>
            </el-row>
          </el-form>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="4">
          <el-button type="primary" @click="calculate">开始计算</el-button>
        </el-col>
        <el-col :span="6" :offset="2">
          <h4>日均销量: {{ avgSalesVolume }}</h4>
<!--          <h4>计算参数: {{ calculationParam }}</h4>-->
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import { listEnabledOpponentStation } from '@/api/gas/opponent-station';
import {calculator, getOilTypes} from '@/api/gas/predict';

export default {
  name: "Input",
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
      calculationParam: "",
      avgSalesVolume: "",
      oilType: []
    }
  },
  methods: {
    calculate() {
      calculator(this.formData).then(resp => {
        console.log(resp)
        this.avgSalesVolume = Number(resp.asv).toFixed(2);
        this.calculationParam = resp.y;
      });
    }
  },
  created() {
    this.formData.stationId = this.$route.params && this.$route.params.stationId;
    getOilTypes().then(resp => {
      this.oilType = resp;
    });
    listEnabledOpponentStation(this.formData.stationId).then(resp => {
      let basicData = resp.rows;
      basicData.forEach(row => {
        this.formData.opponentPriceData.push({
          outSystemGasStationId: row.outGasStationId,
          outSystemGasStationName: row.outGasStationName,
          chaseMoney: 0,
          presentMoney: 0
        });
      });
    })
  }
}
</script>

<style>

</style>
