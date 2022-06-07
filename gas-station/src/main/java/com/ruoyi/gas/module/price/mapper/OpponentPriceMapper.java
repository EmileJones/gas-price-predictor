package com.ruoyi.gas.module.price.mapper;

import com.ruoyi.gas.module.price.domain.OpponentPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpponentPriceMapper {
    String ID = "id";
    String USER_ID = "user_id";
    String GAS_STATION_ID = "gas_station_id";
    String OUT_GAS_STATION_ID = "out_gas_station_id";
    String TIME_STAMP = "time_stamp";
    String PRICE_92 = "price_92";
    String PRICE_95 = "price_95";
    String PRICE_98 = "price_98";
    String PRICE_00 = "price_00";

    /**
     * 按照条件查询数据 <br>
     * <strong>startIndex和amount同时不为null分页才能成立</strong>
     *
     * @param opponentPrice 查询条件
     * @param isDesc        是否是降序
     * @param startIndex    开始数据的下标
     * @param amount        需要查询的数量
     * @return 符合结果的数据
     */
    List<OpponentPrice> selectOpponentPrices(@Param("opponentPrice") OpponentPrice opponentPrice,
                                             @Param("isDesc") Boolean isDesc,
                                             @Param("orderField") String orderField,
                                             @Param("startIndex") Long startIndex,
                                             @Param("amount") Long amount);

    /**
     * 添加一组对手价格
     *
     * @param opponentPrices 对手价格
     * @return 受到影响的数据量
     */
    int addOpponentPrice(@Param("opponentPrices") List<OpponentPrice> opponentPrices);

    /**
     * 按照条件删除记录
     *
     * @param opponentPrice 查询条件
     * @return 受到影响的数据量
     */
    int deleteOpponentPrice(@Param("opponentPrices") OpponentPrice opponentPrice);

    /**
     * 查询符合条件的数据总数
     *
     * @param opponentPrice 查询条件
     * @return 符合条件的数据量
     */
    int selectAmountOfData(OpponentPrice opponentPrice);
}
