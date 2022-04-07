<template>
  <div class="app-container">
    <h1>我的加油站页面</h1>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi=""
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stationList" @selection-change="handleSelectionChange">
      <el-table-column label="加油站名称" align="center" prop="stationName" />
      <el-table-column label="加油站状态" align="center" prop="stationStatus">
        <template slot-scope="scope">
          <el-tag :v-if="scope.row.stationStatus == 0" type="success">正常</el-tag>
          <el-tag :v-if="scope.row.stationStatus == 1" type="warning">审核中</el-tag>
          <el-tag :v-if="scope.row.stationStatus == 2" type="danger">禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="上次数据更新时间" align="center" prop="updateTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-receiving"
            @click="handleDisable(scope.row)"
            v-hasPermi="['system:dict:edit']"
          >经营数据</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-close"
            @click="handleDisable(scope.row)"
            v-hasPermi="['system:dict:edit']"
          >禁用</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:dict:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      stationList: [
        {stationName: "A-Station", stationStatus: 0, updateTime: "2022-03-04 10:23"}
      ]
    }
  }
}
</script>

<style>

</style>