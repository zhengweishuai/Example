package com.example.edz.pattern.factory.B;

/**
 * created by zhengweis on 2019/10/15.
 * description：
 */
public class ComputerFactory extends ComputerFactoryAbstract{
    @Override
    public Computer createComputer(int type) {
        Computer phone = null;
        switch (type) {
            case 1:
                phone = new HuaweiComputer();
                break;
            case 2:
                phone = new ThinkPadComputer();
                break;
        }
        return phone;
    }
}
