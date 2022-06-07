<template>
  <div class="app-container">
      <p>对手加油站数据</p>
      <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleUpload"
          v-hasPermi="['gas:station:upload']"
        >上传对手加油站数据</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-link style="" type="primary" @click="handleExport">下载导入模板</el-link>
      </el-col>
    </el-row>

    <!-- 对手加油站表格 -->
    <el-table v-loading="loading" :data="opponentStationList">
      <el-table-column label="对手加油站名称" align="center" prop="outGasStationName" />
      <el-table-column label="加油站状态" align="center" prop="stationStatus">
        <template slot-scope="scope">
          <el-tag v-for="(item,index) in statusMap" v-show="scope.row.stationStatus == item.value" :key="index" :type="item.cssName">
            {{item.label}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            :icon="scope.row.stationStatus == 0 ? 'el-icon-circle-close' : 'el-icon-circle-check'"
            @click="handleChangeStatus(scope.row)"
            v-hasPermi="['gas:station:edit']"
          >{{scope.row.stationStatus == 0 ? '启用' : '禁用'}}</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['gas:station:edit']"
          >修改</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import { listOpponentStation, changeStatus, changeStationName } from '@/api/gas/opponent-station.js'

export default {
    data() {
        return {
            // 加油站数据正在加载中
            loading: true,
            // 加油站状态数据
            statusMap: [
                { label: '禁用', value: 0, cssName: 'danger' },
                { label: '正常', value: 1, cssName: 'success' },
            ],
            gasStationId: null,
            opponentStationList: []
        }
    },
    created() {
        this.gasStationId = this.$route.params && this.$route.params.stationId
        this.getList()
    },
    methods: {
        /** 刷新当前列表 */
        getList() {
            listOpponentStation().then(response => {
                this.opponentStationList = response.rows
                this.loading = false
            })
        },
        /** 下载导入模板 */
        handleExport() {
            this.download('/gas/opponent-price/export', 
            {gasStationId: this.gasStationId}, 
            `${this.gasStationId}.xlsx`)
        },

        /** 更新对手加油站状态 */
        handleChangeStatus(message) {
            let expectStatus = 0
            if (message.stationStatus == 0) {
                expectStatus = 1
            }

            changeStatus(message.id, expectStatus).then(response => {
                if (response != null && response.code === 200) {
                    this.$modal.msgSuccess("修改状态成功")
                    this.getList()
                }
            })
        }
    }
}
</script>

<style>

</style>