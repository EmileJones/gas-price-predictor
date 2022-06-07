<template>
  <div class="app-container">
    <h1>我的加油站</h1>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAddStation"
          v-hasPermi="['gas:station:add']"
        >增加加油站</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleUpload"
          v-hasPermi="['gas:station:upload']"
        >上传经营数据</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-link style="" type="primary" @click="handleExport">下载导入模板</el-link>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="stationList">
      <el-table-column label="加油站名称" align="center" prop="stationName" />
      <el-table-column label="加油站状态" align="center" prop="stationStatus">
        <template slot-scope="scope">
          <el-tag v-for="(item,index) in statusMap" v-show="scope.row.status == item.value" :key="index" :type="item.cssName">
            {{item.label}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="上次数据更新时间" align="center" prop="updateTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-receiving"
            @click="redirectOpponentStation(scope.row.stationId)"
            v-hasPermi="['gas:station:list']"
          >对手加油站</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-receiving"
            @click="redirectStationSaleData(scope.row.stationId)"
            v-hasPermi="['gas:station:list']"
          >经营数据</el-button>
          <el-button
            size="mini"
            type="text"
            :icon="scope.row.status == 2 ? 'el-icon-circle-close' : 'el-icon-circle-check'"
            @click="handleChangeStatus(scope.row)"
            v-hasPermi="['gas:station:edit']"
          >{{scope.row.status == 2 ? '启用' : '禁用'}}</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['gas:station:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 上传对话框 -->
    <el-dialog title="经营数据上传" :visible.sync="uploadOpen" width="500px" append-to-body>
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

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label-width="300" label="加油站所在地区">
          <template>
            <v-region @values="regionChange" />
          </template>
        </el-form-item>
        <el-form-item label-width="300" label="加油站名称">
          <el-select v-model="form.location" filterable remote placeholder="请选择" :remote-method="refreshCandidateList">
            <el-option
              v-for="item in candidateGasStations"
              :key="item.location"
              :label="item.stationName"
              :value="item.location">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStation, addStation, deleteStation, changeStationStatus, uploadAction } from '@/api/gas/station'
import { listGasStationCandidate } from "@/api/gas/geo"
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      // 加油站数据正在加载中
      loading: true,
      // 加油站状态数据
      statusMap: [
        { label: '创建', value: 0, cssName: 'primary' },
        { label: '正常', value: 1, cssName: 'success' },
        { label: '禁用', value: 2, cssName: 'danger' },
        { label: '审核中', value: 3, cssName: 'warning' },
      ],
      // 加油站加载数据列表
      stationList: [],

      // 弹出层标题
      title: "增加加油站",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {
        location: "",
        stationName: ""
      },

      // 上传会话窗
      uploadOpen: false,
      // 上传地址
      uploadUrl: "",
      // 权限头
      uploadHeaders: {Authorization: "Bearer " + getToken()},

      // 加油站查询信息候选数据
      candidateGasStations: [],
      // 查询候选加油站参数
      queryParams: {
        adcode: '',
        query: '',
      },
    }
  },
  created() {
    this.getList()
    this.uploadUrl = uploadAction()
  },
  methods: {
    getList() {
      listStation().then(response => {
        this.stationList = response.rows
        this.loading = false
      })
    },

    /**
     * 增加加油站
     */
    // 增加加油站按钮
    handleAddStation() {
      this.open = true
      this.form = {
        location: "",
        stationName: ""
      },
      this.candidateGasStations = []
    },
    // 提交增加加油站表单
    submitForm() {
      addStation(this.form).then(response => {
        if (response != null && response.code === 200) {
          this.$modal.msgSuccess("新增成功")
          this.getList()
        }
        this.open = false
      })
      console.log("提交加油站：", this.form)
    },
    // 关闭Dialog
    cancel() {
      this.open = false
    },

    /** 下载加油站导入模板 */
    handleExport() {
      this.download('gas/station/sale-data/template', {}, `加油站经营数据导入模板.xlsx`)
    },

    /**
     * 上传经营数据
     */
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

    /**
     * 修改加油站状态
     */
    handleChangeStatus(data) {
      if (data.status === 2) {
        // 0：标识开启加油站，具体状态修改由后端决定
        changeStationStatus(data.stationId, 0).then(() => {
          this.getList();
          this.$modal.msgSuccess("状态修改成功")
        }).catch(() => {})
      } else {
        // 2：禁用加油站
        changeStationStatus(data.stationId, 2).then(() => {
          this.getList();
          this.$modal.msgSuccess("状态修改成功")
        }).catch(() => {})
      }
    },

    /**
     * 删除加油站
     */
    handleDelete(data) {
      this.$modal.confirm('是否确认删除名称为"' + data.stationName + '"的数据项？').then(function() {
        return deleteStation(data.stationId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
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
    },

    // 跳转到经营数据
    redirectStationSaleData(stationId) {
      this.$router.push(`/gas/station-data/index/${stationId}`)
    },

    /** 跳转到对手加油站页面 */
    redirectOpponentStation(stationId) {
      this.$router.push(`/gas/opponent-station/index/${stationId}`)
    }
  }
}
</script>

<style>

</style>