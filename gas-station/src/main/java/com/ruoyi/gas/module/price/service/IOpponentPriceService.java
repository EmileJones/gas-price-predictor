package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.OpponentPrice;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

/**
 * 提供关于 竞争对手价格数据 的类
 */
public interface IOpponentPriceService {
    /**
     * 添加对手数据
     * @param opponentPrice 对手数据
     * @return  添加成功的数据条数
     */
    int addOpponentPrice(List<OpponentPrice> opponentPrice);

    /**
     * 获取历史数据
     * @param userId 用户ID
     * @param gasStationId 系统内加油站ID
     * @param outGasStationId 系统外加油站ID
     * @return 竞争对手数据
     */
    List<OpponentPrice> getHistoryOpponentPrice(Long userId, String gasStationId, String outGasStationId);

    /**
     * 通过id删除数据
     * @param id 数据唯一标识
     * @return true 删除成功 <br>
     * false 删除失败
     */
    boolean deleteOpponentPrice(Long id);

    /**
     * 获取导入数据的Excel表
     * @param userId 用户ID
     * @param gasStationId 加油站ID
     * @param periodNumber 需要加载的周期数
     * @return Excel表的工作簿
     */
    Workbook getExcelToImportData(Long userId, String gasStationId, int periodNumber);
}
