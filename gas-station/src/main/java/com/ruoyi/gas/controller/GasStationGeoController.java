package com.ruoyi.gas.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.gas.domain.vo.GasStationForm;
import com.ruoyi.gas.domain.vo.GasStationGeoForm;
import com.ruoyi.gas.domain.vo.GasStationGeoVO;
import com.ruoyi.gas.domain.vo.GasStationVO;
import com.ruoyi.gas.service.IGasStationInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.gas.domain.GasStationGeo;
import com.ruoyi.gas.service.IGasStationGeoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 加油站地理信息Controller
 * 
 * @author ruoyi
 * @date 2022-01-21
 */
@RestController
@RequestMapping("/gas/geo")
public class GasStationGeoController extends BaseController
{
    @Autowired
    private IGasStationInfoService gasStationInfoService;
    @Autowired
    private IGasStationGeoService gasStationGeoService;

    /**
     * 查询加油站地理信息列表
     */
    @PreAuthorize("@ss.hasPermi('gas:geo:list')")
    @GetMapping("/list")
    public AjaxResult list(GasStationGeoForm gasStationGeo)
    {
        List<GasStationGeoVO> list = gasStationInfoService.selectGasStationGeoInfoList(gasStationGeo);
        return AjaxResult.success(list);
    }

    /**
     * 查询候选加油站列表
     */
    @PreAuthorize("@ss.hasPermi('gas:geo:list')")
    @GetMapping("/station")
    public TableDataInfo station(GasStationForm gasStation)
    {
        startPage();
        List<GasStationVO> list = gasStationGeoService.listGasStationCandidateList(gasStation);
        return getDataTable(list);
    }

    /**
     * 导出加油站地理信息列表
     */
    @PreAuthorize("@ss.hasPermi('gas:geo:export')")
    @Log(title = "加油站地理信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GasStationGeoForm gasStationGeo)
    {
        List<GasStationGeoVO> list = gasStationInfoService.selectGasStationGeoInfoList(gasStationGeo);
        ExcelUtil<GasStationGeoVO> util = new ExcelUtil<>(GasStationGeoVO.class);
        util.exportExcel(response, list, "加油站地理信息数据");
    }

}
