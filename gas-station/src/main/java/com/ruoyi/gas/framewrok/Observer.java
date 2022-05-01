package com.ruoyi.gas.framewrok;

public interface Observer<T extends Generator> {
    void update(T parameter);
}
