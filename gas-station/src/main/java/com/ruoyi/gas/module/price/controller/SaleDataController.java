package com.ruoyi.gas.module.price.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.price.domain.vo.OilSaleDataVO;
import com.ruoyi.gas.module.price.service.ISaleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gas/price")
public class SaleDataController extends BaseController {
//    @Autowired
//    private ICalculatorService calculatorService;
//
//    @Autowired
//    private IOilPriceService oilPriceService;
//
//    @Autowired
//    private IPeriodService periodService;

    @Autowired
    private ISaleDataService saleDataService;

    @PreAuthorize("@ss.hasPermi('gas:price:list')")
    @GetMapping("/saleData")
    public TableDataInfo getSaleData(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {
        Long userId = SecurityUtils.getLoginUser()
                .getUser()
                .getUserId();
        List<OilSaleDataVO> historySaleData = saleDataService.getHistorySaleDataByUserId(userId,pageNum,pageSize);
        TableDataInfo dataTable = getDataTable(historySaleData);
        dataTable.setTotal(saleDataService.selectHistorySaleDataAmount(userId));
        return dataTable;
    }
}
