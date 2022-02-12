package com.ruoyi.gas.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.gas.domain.GasStationArgument;
import com.ruoyi.gas.service.IGasStationArgumentService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 加油站关键参数Controller
 * 
 * @author ruoyi
 * @date 2022-02-05
 */
@RestController
@RequestMapping("/gas/argument")
public class GasStationArgumentController extends BaseController
{
    @Autowired
    private IGasStationArgumentService gasStationArgumentService;

    /**
     * 导出加油站关键参数列表
     */
    @PreAuthorize("@ss.hasPermi('gas:argument:export')")
    @Log(title = "加油站关键参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GasStationArgument gasStationArgument)
    {
        List<GasStationArgument> list = gasStationArgumentService.selectGasStationArgumentList(gasStationArgument);
        ExcelUtil<GasStationArgument> util = new ExcelUtil<GasStationArgument>(GasStationArgument.class);
        util.exportExcel(response, list, "加油站关键参数数据");
    }

    /**
     * 获取加油站关键参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('gas:argument:query')")
    @GetMapping(value = "/{name}")
    public AjaxResult getInfo(@PathVariable("name") String name)
    {
        return AjaxResult.success(gasStationArgumentService.selectGasStationArgumentByName(name));
    }

    /**
     * 修改加油站关键参数
     */
    @PreAuthorize("@ss.hasPermi('gas:argument:edit')")
    @Log(title = "加油站关键参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GasStationArgument gasStationArgument)
    {
        return toAjax(gasStationArgumentService.updateGasStationArgument(gasStationArgument));
    }

}
