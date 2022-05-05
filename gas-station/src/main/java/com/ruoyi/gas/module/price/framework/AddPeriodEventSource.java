package com.ruoyi.gas.module.price.framework;

import com.ruoyi.gas.framewrok.Generator;
import com.ruoyi.gas.module.price.domain.Period;

public class AddPeriodEventSource extends Generator {
    private Period period;

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
