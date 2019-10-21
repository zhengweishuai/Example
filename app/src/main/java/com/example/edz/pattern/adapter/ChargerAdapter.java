package com.example.edz.pattern.adapter;

/**
 * created by zhengweis on 2019/10/16.
 * description：
 */
public class ChargerAdapter implements IPhoneCharger {

    private Charger iPhoneCharger;

    public ChargerAdapter(Charger iPhoneCharger) {
        this.iPhoneCharger = iPhoneCharger;
    }

    public int voltage110() {
        return iPhoneCharger.valtage110();
    }

    @Override
    public int voltage24() {
        return 24;
    }
}
