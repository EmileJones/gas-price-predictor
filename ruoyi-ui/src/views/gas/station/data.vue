<template>
  <div class="app-container">
    <button @click="pullSaleData(1)">点我获取数据</button>

    <el-table v-loading="loading" :data="saleDatas">
      <el-table-column label="加油站名称" align="center" prop="gasStationId"/>
      <el-table-column label="物料名称" align="center" prop="oilType"/>
      <el-table-column label="业务发生时间" align="center" prop="date"/>
      <el-table-column label="销售数量/KG" align="center" prop="kgNumber"/>
      <el-table-column label="销售数量/L" align="center" prop="lNumber"/>
      <el-table-column label="销售金额" align="center" prop="price"/>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="pullSaleData"
    />

  </div>
</template>

<script>
import {Loading} from 'element-ui'
import {getSaleData} from "@/api/gas/data";


export default {
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      loading: true,
      stationId: "",
      saleDatas: [],
      total: 0
    }
  },
  methods: {
    pullSaleData() {
      console.log(this.queryParams);
      getSaleData(this.queryParams).then(resp => {
        this.saleDatas = resp.rows;
        this.loading = false;
        this.total = resp.total;
        console.log(resp);
      });
    }
  },

  created() {
    this.stationId = this.$route.params && this.$route.params.stationId;
    this.saleDatas = this.pullSaleData();
  }
}
</script>

<style>

</style>
