package com.ruoyi.gas.module.geo.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.geo.domain.vo.OpponentMessageVO;
import com.ruoyi.gas.module.geo.service.IOpponentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户加油站的对手加油站信息管理
 * @author KlenKiven
 */
@RestController
@RequestMapping("/gas/opponent-station")
public class OpponentStationController extends BaseController {

    @Autowired
    private IOpponentMessageService opponentMessageService;

    /**
     * 查询加油站所有的对手加油站
     */
    @PreAuthorize("@ss.hasAnyPermi('gas:station:list')")
    @GetMapping("list")
    public TableDataInfo opponentStationList(String gasStationId) {
        Long userId = SecurityUtils.getUserId();
        List<OpponentMessageVO> opponentMessage =
                opponentMessageService.getAllOpponentMessage(userId, gasStationId);

        return getDataTable(opponentMessage);
    }

    /**
     * 修改加油站状态（启用/禁用）
     */
    @PreAuthorize("@ss.hasPermi('gas:station:edit')")
    @PostMapping("/{messageId}/status/{status}")
    public AjaxResult changeStationStatus(@PathVariable Long messageId,
                                          @PathVariable Integer status) {
        Long userId = SecurityUtils.getUserId();
        opponentMessageService.changeStationStatus(messageId, status);
        return AjaxResult.success();
    }

    /**
     * 修改加油站名称
     */
    @PreAuthorize("@ss.hasPermi('gas:station:edit')")
    @PostMapping("/{messageId}/name")
    public AjaxResult changeStationStatus(@PathVariable Long messageId,
                                          String newName) {
        Long userId = SecurityUtils.getUserId();
        opponentMessageService.modifyOpponentGasStationName(messageId, newName);
        return AjaxResult.success();
    }
}
