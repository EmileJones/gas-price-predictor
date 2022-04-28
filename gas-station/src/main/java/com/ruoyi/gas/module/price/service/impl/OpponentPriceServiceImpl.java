package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.price.domain.OpponentPrice;
import com.ruoyi.gas.module.price.mapper.OpponentPriceMapper;
import com.ruoyi.gas.module.price.service.IOpponentPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpponentPriceServiceImpl implements IOpponentPriceService {
    @Autowired
    private OpponentPriceMapper opponentPriceMapper;

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
}
