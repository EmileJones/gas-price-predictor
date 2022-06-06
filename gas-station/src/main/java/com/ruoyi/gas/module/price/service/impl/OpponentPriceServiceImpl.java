package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.geo.domain.OpponentMessage;
import com.ruoyi.gas.module.geo.domain.vo.OpponentMessageVO;
import com.ruoyi.gas.module.geo.service.IGasStationInfoService;
import com.ruoyi.gas.module.geo.service.IOpponentMessageService;
import com.ruoyi.gas.module.price.domain.OpponentPrice;
import com.ruoyi.gas.module.price.domain.UserPeriod;
import com.ruoyi.gas.module.price.domain.dto.ExportExcelDTO;
import com.ruoyi.gas.module.price.mapper.OpponentPriceMapper;
import com.ruoyi.gas.module.price.service.IOpponentPriceService;
import com.ruoyi.gas.module.price.service.IUserPeriodService;
import com.ruoyi.gas.module.price.util.ExcelWriteUtil;
import com.ruoyi.gas.module.price.util.OpponentPriceExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class OpponentPriceServiceImpl implements IOpponentPriceService {
    @Autowired
    private OpponentPriceMapper opponentPriceMapper;
    @Autowired
    private IOpponentMessageService opponentMessageService;
    @Autowired
    public IUserPeriodService userPeriodService;

    @Override
    public int addOpponentPrice(List<OpponentPrice> opponentPrice) {
        return opponentPriceMapper.addOpponentPrice(opponentPrice);
    }

    @Override
    public List<OpponentPrice> getHistoryOpponentPrice(Long userId, String gasStationId, String outGasStationId) {
        OpponentPrice opponentPrice = new OpponentPrice();
        opponentPrice.setUserId(userId);
        opponentPrice.setGasStationId(gasStationId);
        opponentPrice.setOutGasStationId(outGasStationId);
        return opponentPriceMapper.selectOpponentPrices(opponentPrice, true, OpponentPriceMapper.ID, null, null);
    }

    @Override
    public boolean deleteOpponentPrice(Long id) {
        OpponentPrice opponentPrice = new OpponentPrice();
        opponentPrice.setId(id);
        int num = opponentPriceMapper.deleteOpponentPrice(opponentPrice);
        if (num == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Workbook getExcelToImportData(Long userId, String gasStationId, int periodNumber) {
        Map<Date, List<ExportExcelDTO>> map = new HashMap<>();
        List<UserPeriod> userPeriods = userPeriodService.getUserPeriods(userId, gasStationId, 0l, (long) periodNumber);
        OpponentPrice condition = new OpponentPrice();
        condition.setUserId(userId);
        condition.setGasStationId(gasStationId);
        for (UserPeriod userPeriod : userPeriods){
            condition.setUserPeriodId(userPeriod.getId());
            List<OpponentPrice> opponentPrices = opponentPriceMapper.selectOpponentPrices(condition,null,null,null,null);
            List<ExportExcelDTO> exportExcelDTOS = convertOpponentPrice2ExportExcelDTO(opponentPrices, userId, gasStationId);
            map.put(userPeriod.getTimeStamp(), exportExcelDTOS);
        }
        Workbook workbook = OpponentPriceExcelUtil.generateExcel(map);
        return workbook;
    }

    private List<ExportExcelDTO> convertOpponentPrice2ExportExcelDTO(List<OpponentPrice> opponentPriceList,
                                                                     Long userId,
                                                                     String gasStationId) {
        LinkedList<ExportExcelDTO> exportExcelDTOS = new LinkedList<>();
        // 将对手加油站的信息全部查出放入内存中
        Map<String, String> nameMap = new HashMap<>();
        List<OpponentMessageVO> opponentMessageVOs = opponentMessageService.getOpponentMessage(userId, gasStationId);

        opponentMessageVOs.forEach(opponentMessageVO -> {
            nameMap.put(opponentMessageVO.getOutGasStationId(), opponentMessageVO.getOutGasStationName());
        });

        // 转换 并且删除 已记录的加油站
        for (OpponentPrice opponentPrice : opponentPriceList) {
            ExportExcelDTO dto = new ExportExcelDTO();
            dto.setOutGasStationId(opponentPrice.getOutGasStationId());
            String name = null;
            if (nameMap.containsKey(opponentPrice.getOutGasStationId())) {
                name = nameMap.get(opponentPrice.getOutGasStationId());
                nameMap.remove(opponentPrice.getOutGasStationId());
            } else {
                OpponentMessage opponentMessage = opponentMessageService.getOpponentMessageByStationId(opponentPrice.getUserId(),
                        opponentPrice.getGasStationId(),
                        opponentPrice.getOutGasStationId());
                name = opponentMessage != null ? opponentMessage.getOutGasStationName() : "";
            }
            dto.setOutGasStationName(name);
            dto.setPrice00(opponentPrice.getPrice00().doubleValue());
            dto.setPrice92(opponentPrice.getPrice92().doubleValue());
            dto.setPrice95(opponentPrice.getPrice95().doubleValue());
            dto.setPrice98(opponentPrice.getPrice98().doubleValue());
            exportExcelDTOS.add(dto);
        }

        // NameMap Remains
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            ExportExcelDTO dto = new ExportExcelDTO();
            dto.setOutGasStationId(entry.getKey());
            dto.setOutGasStationName(entry.getValue());
            dto.setPrice00(0.0);
            dto.setPrice92(0.0);
            dto.setPrice95(0.0);
            dto.setPrice98(0.0);
            exportExcelDTOS.add(dto);
        }

        return exportExcelDTOS;
    }
}
