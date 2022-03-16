<template>
  <div class="app-container">
    <p><strong>注意！！！</strong>尽量不要修改默认的搜索距离信息，可能会对您的期望结果出现误差</p>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label-width="300" label="加油站所在地区">
        <template>
          <v-region @values="regionChange" />
        </template>
      </el-form-item>
      <el-form-item label-width="300" label="加油站名称">
        <el-select v-model="queryListParams.location" filterable remote placeholder="请选择" :remote-method="refreshCandidateList">
          <el-option
            v-for="item in candidateGasStations"
            :key="item.location"
            :label="item.stationName"
            :value="item.location">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label-width="300" label="搜索距离范围">
        <el-input-number v-model="queryListParams.distance" :step="100"></el-input-number>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['gas:geo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="geoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系统内加油站ID" align="center" prop="systemStationId" />
      <el-table-column label="系统内加油站名称" align="center" prop="systemStationName" />
      <el-table-column label="系统外加油站ID" align="center" prop="outSystemStationId" />
      <el-table-column label="系统外加油站名称" align="center" prop="outSystemStationName" />
      <el-table-column label="距离" align="center" prop="distance" />
      <el-table-column label="红绿灯数" align="center" prop="trafficLights" />
      <el-table-column label="红绿灯影响因子" align="center" prop="trafficFactor" />
      <el-table-column label="路线形状" align="center" prop="routeShape" />
      <el-table-column label="路线曲折度影响系数" align="center" prop="routeShapeFactor" />
      <el-table-column label="路线影响系数" align="center" prop="routeFactor" />
    </el-table>

  </div>
</template>

<script>
import { listGeo, listGasStationCandidate } from "@/api/gas/geo";

export default {
  name: "Geo",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 加油站地理信息表格数据
      geoList: [],
      // 加油站查询信息候选数据
      candidateGasStations: [],
      // 查询加油站周边地理信息参数
      queryListParams: {
        location: '',
        distance: 4000
      },
      // 查询候选加油站参数
      queryParams: {
        adcode: '',
        query: '',
      }
    };
  },
  created() {
    this.loading = false;
  },
  methods: {
    /** 查询加油站地理信息列表 */
    getList() {
      this.loading = true;
      listGeo(this.queryListParams).then(response => {
        this.geoList = response.data;
        console.log(response.data)
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams.adcode = null
      this.queryParams.query = ""
      this.geoList = [];
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('gas/geo/export', {
        ...this.queryListParams
      }, `geo_${new Date().getTime()}.xlsx`)
    },
    /** 寻找最详细的行政区划单位代码 */
    regionChange (data){
      let adcode = -1
      for (let key in data) {
        if (data[key] != null) {
          adcode = Math.max(data[key].key, adcode)
        }
      }
      this.queryParams.adcode = adcode
      this.candidateGasStations = []
      console.log("重置候选列表, adcode=", adcode)
    },
    /** 刷新候选加油站列表 */
    refreshCandidateList(query) {
      this.queryParams.query = query
      // console.log("重新查询候选加油站", JSON.stringify(this.queryParams))
      listGasStationCandidate(this.queryParams).then(response => {
        this.candidateGasStations = response.rows
        this.loading = false;
      })
    }
  }
};
</script>
