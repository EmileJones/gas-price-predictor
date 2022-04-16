package com.ruoyi.gas.module.geo.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.form.GasStationAddForm;
import com.ruoyi.gas.module.geo.domain.vo.UserStationVO;
import com.ruoyi.gas.module.geo.service.GasStationService;
import com.ruoyi.gas.module.geo.service.GeoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 删除加油站
     */
    @PreAuthorize("@ss.hasPermi('gas:station:upload')")
    @PostMapping("/import")
    public AjaxResult importStationSaleData() {
        // TODO be going to impl
        return AjaxResult.success();
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
