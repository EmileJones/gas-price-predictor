package com.ruoyi.gas.module.geo.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.gas.module.geo.domain.form.GasStationForm;
import com.ruoyi.gas.module.geo.domain.form.GasStationGeoForm;
import com.ruoyi.gas.module.geo.domain.vo.GasStationCandidateVO;
import com.ruoyi.gas.module.geo.domain.vo.GasStationGeoVO;
import com.ruoyi.gas.module.geo.service.GeoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
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
    private final GeoService geoService;

    /**
     * 查询加油站地理信息列表
     */
    @PreAuthorize("@ss.hasPermi('gas:geo:query')")
    @GetMapping("/list")
    public AjaxResult list(GasStationGeoForm gasStationGeo)
    {
        String location = gasStationGeo.getLocation();
        Integer distance = gasStationGeo.getDistance();
        if (location == null || "".equals(location) ||
            distance == null || distance == 0
        ) {
            return AjaxResult.error("参数缺失，请重新查询");
        }
        List<GasStationGeoVO> list = geoService.listStationGeo(gasStationGeo);
        return AjaxResult.success(list);
    }

    /**
     * 查询候选加油站列表
     */
    @PreAuthorize("@ss.hasPermi('gas:geo:query')")
    @GetMapping("/station")
    public TableDataInfo station(GasStationForm gasStation)
    {
        startPage();
        List<GasStationCandidateVO> list = geoService.listCandidateStations(gasStation);
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
        List<GasStationGeoVO> list = geoService.listStationGeo(gasStationGeo);
        ExcelUtil<GasStationGeoVO> util = new ExcelUtil<>(GasStationGeoVO.class);
        util.exportExcel(response, list, "加油站地理信息数据");
    }

    public GasStationGeoController(GeoService geoService) {
        this.geoService = geoService;
    }

}
