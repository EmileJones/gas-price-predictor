package com.ruoyi.gas.module.geo.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.gas.module.geo.domain.form.GasStationAddForm;
import com.ruoyi.gas.module.geo.domain.vo.UserStationVO;
import com.ruoyi.gas.module.geo.service.GasStationService;
import com.ruoyi.gas.module.price.domain.excel.SaleDataExcel;
import com.ruoyi.gas.module.price.util.PriceConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户加油站管理
 *
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
@RestController
@RequestMapping("/gas/station")
public class GasStationController extends BaseController {
    private final GasStationService gasStationService;

    /**
     * 根据用户的登陆状态获取用户所拥有的加油站
     */
    @PreAuthorize("@ss.hasPermi('gas:station:list')")
    @GetMapping("/list")
    public TableDataInfo userStationList() {
        Long userId = SecurityUtils.getLoginUser()
                .getUser()
                .getUserId();
        List<UserStationVO> usersStation = gasStationService.listGasStationByUserId(userId);
        return getDataTable(usersStation);
    }

    /**
     * 下载加油站数据导入模板
     */
    @PreAuthorize("@ss.hasPermi('gas:station:upload')")
    @PostMapping("/sale-data/template")
    public void saleDataTemplate(HttpServletResponse response) {
        ExcelUtil<SaleDataExcel> excelUtil = new ExcelUtil<>(SaleDataExcel.class);
        excelUtil.importTemplateExcel(response, PriceConstant.SALE_DATA_IMPORT_SHEET);
    }

    /**
     * 上传加油站数据
     */
    @PreAuthorize("@ss.hasPermi('gas:station:upload')")
    @PostMapping("/import")
    public AjaxResult importStationSaleData(MultipartFile file) throws Exception {
        ExcelUtil<SaleDataExcel> excelUtil = new ExcelUtil<>(SaleDataExcel.class);
        List<SaleDataExcel> saleData =
                excelUtil.importExcel(PriceConstant.SALE_DATA_IMPORT_SHEET, file.getInputStream(), 0);
        gasStationService.importSaleData(saleData);
        return AjaxResult.success("数据更新中，请稍后查看");
    }

    /**
     * 新增用户加油站
     */
    @PreAuthorize("@ss.hasPermi('gas:station:add')")
    @PostMapping("/add")
    public AjaxResult addUserStation(@RequestBody GasStationAddForm stationAddForm) {
        gasStationService.addUserStation(stationAddForm);
        return AjaxResult.success();
    }

    /**
     * 修改加油站状态（启用/禁用）
     */
    @PreAuthorize("@ss.hasPermi('gas:station:edit')")
    @PostMapping("/{stationId}/status/{status}")
    public AjaxResult changeStationStatus(@PathVariable String stationId, @PathVariable Integer status) {
        gasStationService.changeStationStatus(stationId, status);
        return AjaxResult.success();
    }

    /**
     * 删除加油站
     */
    @PreAuthorize("@ss.hasPermi('gas:station:remove')")
    @PostMapping("/remove/{stationId}")
    public AjaxResult removeStation(@PathVariable String stationId) {
        gasStationService.removeStation(stationId);
        return AjaxResult.success();
    }

    public GasStationController(GasStationService gasStationService) {
        this.gasStationService = gasStationService;
    }
}
