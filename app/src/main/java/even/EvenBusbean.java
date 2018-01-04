package even;


import bean.Xiangqingbean;

/**
 * Created by zhangyueyi on 2017/12/30.
 */

public class EvenBusbean {
    private Xiangqingbean xiangqingbean;

    public EvenBusbean(Xiangqingbean xiangqingbean) {
        this.xiangqingbean = xiangqingbean;
    }

    public EvenBusbean() {
    }

    public Xiangqingbean getXiangqingbean() {
        return xiangqingbean;
    }

    public void setXiangqingbean(Xiangqingbean xiangqingbean) {
        this.xiangqingbean = xiangqingbean;
    }
}
