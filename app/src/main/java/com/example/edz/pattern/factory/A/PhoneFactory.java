package com.example.edz.pattern.factory.A;

/**
 * created by zhengweis on 2019/10/15.
 * description：工程实现类
 */
public class PhoneFactory extends PhoneFactoryAbstract {

    @Override
    public Phone creatPhone(int type) {
        Phone phone = null;
        switch (type) {
            case 1:
                phone = new MeizuPhone();
                break;
            case 2:
                phone = new XiaomiPhone();
                break;
        }
        return phone;
    }
}
