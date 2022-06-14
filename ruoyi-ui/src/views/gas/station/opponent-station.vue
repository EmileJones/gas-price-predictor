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

     <!-- 上传对话框 -->
    <el-dialog title="竞争对手数据上传" :visible.sync="uploadOpen" width="500px" append-to-body>
      <el-upload
        ref="upload"
        class="upload-demo"
        :action="uploadUrl"
        :headers="uploadHeaders"
        :on-success="onUploadSuccess"
        accept=".xls,.xlsx">
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传xls/xlsx文件</div>
      </el-upload>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listOpponentStation, changeStatus, changeStationName, importOpponentPrice } from '@/api/gas/opponent-station.js'
import { getToken } from '@/utils/auth'

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

            // 表格上传
            uploadOpen: false,
            // 上传地址
            uploadUrl: "",
            // 权限头
            uploadHeaders: {Authorization: "Bearer " + getToken()},

            // 用户加油站ID 页面内
            gasStationId: null,
            // 竞争对手加油站列表 页面内
            opponentStationList: []
        }
    },
    created() {
        this.gasStationId = this.$route.params && this.$route.params.stationId
        this.getList()
        this.uploadUrl = importOpponentPrice()
    },
    methods: {
        /** 刷新当前列表 */
        getList() {
            listOpponentStation(this.gasStationId).then(response => {
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
        },

        /** 更新加油站信息 */
        handleUpdate(message) {

        },

        // 上传经营数据按钮
        handleUpload() {
            this.uploadOpen = true
        },

        // 关闭窗口
        close() {
            this.uploadOpen = false
        },
        // 上传成功
        onUploadSuccess(response, file, fileList) {
            if (response.code === 200) {
                this.$modal.msgSuccess(response.msg)
                this.uploadOpen = false
            } else {
                this.$modal.msgError(response.msg)
            }

            this.getList()
            this.$refs.upload.clearFiles()
        },
    }
}
</script>

<style>

</style>
