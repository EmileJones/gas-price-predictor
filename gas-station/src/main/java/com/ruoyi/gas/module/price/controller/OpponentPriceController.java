package com.ruoyi.gas.module.price.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.geo.service.IOpponentMessageService;
import com.ruoyi.gas.module.price.service.IOpponentPriceService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/gas/opponent-price")
public class OpponentPriceController {
    private static final int GAS_STATION_ID_LENGTH = 10;

    private final IOpponentPriceService opponentPriceService;

    @GetMapping("/export")
    public void export(HttpServletResponse response, String gasStationId)
    {
        Long userId = SecurityUtils.getUserId();

        response.addHeader("Content-Disposition", "attachment;filename="+gasStationId+".xls");
        Workbook list = opponentPriceService.getExcelToImportData(userId, gasStationId, 16);
        try (ServletOutputStream servletOutputStream = response.getOutputStream()) {
            list.write(servletOutputStream);
        } catch (IOException e) {
            // Do Nothing
        }
    }

    @PostMapping("/import")
    public AjaxResult export(MultipartFile file)
    {
//        Long userId = SecurityUtils.getUserId();
        Long userId = 1L;
        String gasStationId = file.getName().split("\\.")[0];
        if (gasStationId == null || gasStationId.length() < GAS_STATION_ID_LENGTH)
            return AjaxResult.error("文件名被修改，请重新下载并导入");

        opponentPriceService.importOpponentData(file, userId, gasStationId);

        return AjaxResult.success();
    }

    public OpponentPriceController(IOpponentPriceService opponentPriceService) {
        this.opponentPriceService = opponentPriceService;
    }

}
