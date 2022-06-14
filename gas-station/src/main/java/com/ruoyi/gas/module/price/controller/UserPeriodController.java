package com.ruoyi.gas.module.price.controller;

import com.github.pagehelper.Page;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.price.domain.UserPeriod;
import com.ruoyi.gas.module.price.service.impl.UserPeriodService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/gas/user-period")
public class UserPeriodController extends BaseController {

    @Resource
    private UserPeriodService userPeriodService;

    /**
     * 查询用户加油站周期列表
     */
    @PreAuthorize("@ss.hasAnyPermi('gas:user-period:list')")
    @GetMapping("list")
    public TableDataInfo getUserPeriodList(String gasStationId, Long pageNum, Long pageSize) {
        try (Page<?> page = PageUtils.startPageAndGet()) {
            Long userId = SecurityUtils.getUserId();
            List<UserPeriod> userPeriods =
                    userPeriodService.getUserPeriods(userId, gasStationId, (pageNum - 1) * pageSize, pageSize);

            TableDataInfo dataTable = getDataTable(userPeriods);
            assert page != null;
            dataTable.setTotal(page.getTotal());
            return dataTable;
        }
    }

    /**
     * 获取用户加油站周期详细信息
     */
    @PreAuthorize("@ss.hasPermi('gas:user-period:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userPeriodService.getUserPeriodById(id));
    }

    /**
     * 新增加油站周期
     */
    @PreAuthorize("@ss.hasPermi('gas:user-period:add')")
    @PostMapping("")
    @Log(title = "加油站周期", businessType = BusinessType.INSERT)
    public AjaxResult addPeriod(@RequestBody @Valid UserPeriod period) {
        period.setUserId(SecurityUtils.getUserId());
        userPeriodService.addUserPeriods(Collections.singletonList(period));
        return AjaxResult.success();
    }

    /**
     * 修改用户加油站周期
     */
    @PreAuthorize("@ss.hasPermi('gas:user-period:edit')")
    @PutMapping("")
    @Log(title = "加油站周期", businessType = BusinessType.UPDATE)
    public AjaxResult editPeriod(@RequestBody @Valid UserPeriod period) {
        if (period.getId() == null) return AjaxResult.error("修改");
        if (userPeriodService.deleteUserPeriod(period.getId()) == 0)
            return AjaxResult.error("此时间节点为系统时间节点，无法删除或修改");
        userPeriodService.addUserPeriods(Collections.singletonList(period));
        return AjaxResult.success();
    }

    /**
     * 删除用户加油站周期
     */
    @PreAuthorize("@ss.hasPermi('gas:user-period:remove')")
    @DeleteMapping("/{id}")
    @Log(title = "加油站周期", businessType = BusinessType.DELETE)
    public AjaxResult removePeriod(@PathVariable Integer id) {
        userPeriodService.deleteUserPeriod(id);
        return AjaxResult.success();
    }

}
