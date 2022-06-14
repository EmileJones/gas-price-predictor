package com.ruoyi.gas.module.price.controller;

import com.github.pagehelper.Page;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.gas.module.price.domain.form.PeriodForm;
import com.ruoyi.gas.module.price.service.IPeriodService;
import com.ruoyi.gas.module.price.domain.vo.PeriodVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 变价周期编辑
 *
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
@RestController
@RequestMapping("/gas/period")
public class PricePeriodController extends BaseController {
    private final IPeriodService periodService;

    /**
     * 查询加油站周期列表
     */
    @PreAuthorize("@ss.hasPermi('gas:period:list')")
    @GetMapping("/list")
    public TableDataInfo list(PeriodForm period) {
        try (Page<?> page = PageUtils.startPageAndGet()) {
            List<PeriodVO> pagePeriod =  periodService.getPeriodList(period);

            TableDataInfo dataTable = getDataTable(pagePeriod);
            assert page != null;
            dataTable.setTotal(page.getTotal());
            return dataTable;
        }
    }

    /**
     * 获取加油站周期详细信息
     */
    @PreAuthorize("@ss.hasPermi('gas:period:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(periodService.getPeriodById(id));
    }

    /**
     * 新增加油站周期
     */
    @PreAuthorize("@ss.hasPermi('gas:period:add')")
    @PostMapping("")
    @Log(title = "加油站周期", businessType = BusinessType.INSERT)
    public AjaxResult addPeriod(@RequestBody @Valid PeriodForm period) {
        periodService.addPeriod(period);
        return AjaxResult.success();
    }

    /**
     * 修改加油站周期
     */
    @PreAuthorize("@ss.hasPermi('gas:period:edit')")
    @PutMapping("")
    @Log(title = "加油站周期", businessType = BusinessType.UPDATE)
    public AjaxResult editPeriod(@RequestBody @Valid PeriodForm period) {
        periodService.updatePeriod(period);
        return AjaxResult.success();
    }

    /**
     * 删除加油站周期
     */
    @PreAuthorize("@ss.hasPermi('gas:period:remove')")
    @DeleteMapping("/{ids}")
    @Log(title = "加油站周期", businessType = BusinessType.DELETE)
    public AjaxResult removePeriod(@PathVariable Integer[] ids) {
        periodService.removePeriod(ids);
        return AjaxResult.success();
    }

    public PricePeriodController(IPeriodService periodService) {
        this.periodService = periodService;
    }
}
