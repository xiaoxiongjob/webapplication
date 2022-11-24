package cn.itcast.proxy;

/**
 * 公司和其代理商都需实现的接口
 */
public interface SaleComputer {

    public String sale(double money);

    public void show();
}
