package com.ruoyi.gas.module.price.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.gas.module.price.service.IOpponentPriceService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/gas/opponent-price")
public class OpponentPriceController {

    private final IOpponentPriceService opponentPriceService;

    @GetMapping("/export")
    public void export(HttpServletResponse response, String stationId)
    {
//        Long userId = SecurityUtils.getUserId();
        Long userId = 1L;

        response.addHeader("Content-Composition", "attatchment;filename=export.xls");
        Workbook list = opponentPriceService.getExcelToImportData(userId, stationId, 16);
        try (ServletOutputStream servletOutputStream = response.getOutputStream()) {
            list.write(servletOutputStream);
        } catch (IOException e) {
            // Do Nothing
        }
    }

    public OpponentPriceController(IOpponentPriceService opponentPriceService) {
        this.opponentPriceService = opponentPriceService;
    }

}
