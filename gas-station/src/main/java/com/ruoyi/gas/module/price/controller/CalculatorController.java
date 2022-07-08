package com.ruoyi.gas.module.price.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.domain.vo.OpponentPriceDataVO;
import com.ruoyi.gas.module.price.domain.vo.PriceDataVO;
import com.ruoyi.gas.module.price.math.PriceMath;
import com.ruoyi.gas.module.price.service.ICalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/gas/price")
public class CalculatorController {

    @Autowired
    private ICalculatorService calculatorService;

    @PreAuthorize("@ss.hasPermi('gas:predict:predict')")
    @PostMapping("/calculation")
    public PriceMath calculate(@RequestBody String content) {
        PriceDataVO data = new PriceDataVO();
        // 开始解析JSON字符串
        Map map = JSONObject.parseObject(content, HashMap.class);
        // 解析 stationId 属性
        String stationId = map.get("stationId").toString();
        // 解析 presentMoney 属性
        Double presentMoney = Double.valueOf(map.get("presentMoney").toString());
        // 解析 targetMoney 属性
        Double targetMoney = Double.valueOf(map.get("targetMoney").toString());
        // 解析 presentAverageSalesVolume 属性
        Double presentAverageSalesVolume = Double.valueOf(map.get("presentAverageSalesVolume").toString());
        // 解析 opponentPriceData 属性
        List<String> tableData = JSONObject.parseArray(map.get("opponentPriceData").toString(), String.class);
        List<OpponentPriceDataVO> opponentPriceData = parseJsonToPriceDataVO(tableData);
        // 解析 OilType 属性
        OilType oilType = OilType.getOilByTypeName(map.get("oilType").toString());
        // 封装属性
        data.setStationId(stationId);
        data.setTargetMoney(targetMoney);
        data.setPresentMoney(presentMoney);
        data.setPresentAverageSalesVolume(presentAverageSalesVolume);
        data.setOilType(oilType);
        data.setOpponentPriceData(opponentPriceData);
        // 获取用户ID
        Long userId = SecurityUtils.getLoginUser()
                .getUser()
                .getUserId();
        return calculatorService.getAverageSalesVolume(userId, data);
    }

    @PreAuthorize("@ss.hasPermi('gas:predict:predict')")
    @GetMapping("/oilType")
    public List<String> calculate() {
        List<String> oilTypes = new ArrayList<>();
        for (OilType o : OilType.values()) {
            oilTypes.add(o.getTypeName());
        }
        return oilTypes;
    }

    /**
     * 封装对象
     *
     * @param tableData PriceDataVO 的 JSON 列表
     * @return PriceDataVO 列表
     */
    private static final List<OpponentPriceDataVO> parseJsonToPriceDataVO(List<String> tableData) {
        // 新建列表list用来存放PriceDataVO
        LinkedList<OpponentPriceDataVO> list = new LinkedList<>();
        for (String data : tableData) {
            // 解析对象为HashMap
            HashMap map = JSONObject.parseObject(data, HashMap.class);
            OpponentPriceDataVO priceDataVO = new OpponentPriceDataVO();
            String outSystemGasStationId = map.get("outSystemGasStationId").toString();
            String outSystemGasStationName = map.get("outSystemGasStationName").toString();
            Double chaseMoney = Double.valueOf(map.get("chaseMoney").toString());
            Double presentMoney = Double.valueOf(map.get("presentMoney").toString());
            // 将对象存入PriceDataVO中
            priceDataVO.setOutSystemGasStationId(outSystemGasStationId);
            priceDataVO.setOutSystemGasStationName(outSystemGasStationName);
            priceDataVO.setChaseMoney(chaseMoney);
            priceDataVO.setPresentMoney(presentMoney);
            // 将PriceDataVO存入list中
            list.add(priceDataVO);
        }
        // 将存放PriceDataVO的list返回
        return list;
    }
}
