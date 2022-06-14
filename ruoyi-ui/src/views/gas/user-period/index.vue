<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="加油站" prop="gasStationId">
        <el-select v-model="queryParams.gasStationId" placeholder="请选择" @change="getList">
          <el-option
            v-for="item in userGasStations"
            :key="item.stationId"
            :label="item.stationName"
            :value="item.stationId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['gas:period:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['gas:period:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['gas:period:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="periodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="调整时间" align="center" prop="timeStamp" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.timeStamp, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['gas:user-period:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['gas:user-period:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改加油站周期对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="调整时间" prop="timeStamp" required>
          <el-date-picker clearable size="small"
            v-model="form.timeStamp"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
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
import { listPeriod, getPeriod, delPeriod, addPeriod, updatePeriod } from "@/api/gas/user-period";
import { listStation } from "@/api/gas/station"

export default {
  name: "Period",
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
      // 总条数
      total: 0,
      // 加油站周期表格数据
      periodList: [],
      // 用户加油站列表
      userGasStations: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        gasStationId: '',
        pageNum: 1,
        pageSize: 10,
        startTime: null,
        endTime: null,
        isRise: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        startTime: [
          { required: true, message: "开始时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.refreshStationList();
  },
  methods: {
    /** 查询加油站周期列表 */
    getList() {
      this.loading = true;
      listPeriod(this.queryParams).then(response => {
        this.periodList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        gasStationId: null,
        timeStamp: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      if (this.queryParams.gasStationId == null || 
          this.queryParams.gasStationId == '' || 
          this.queryParams.gasStationId == undefined) {
        this.$modal.msgError("请选择加油站后再完成新增");
        return;
      }
      this.reset();
      this.open = true;
      this.title = "添加加油站周期";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPeriod(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改加油站周期";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.gasStationId = this.queryParams.gasStationId;

      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePeriod(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPeriod(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除加油站周期编号为"' + ids + '"的数据项？').then(function() {
        return delPeriod(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 查询加油站列表 */
    refreshStationList() {
      listStation().then(response => {
        this.userGasStations = response.rows
      })
    }
  }
};
</script>
