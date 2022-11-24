package cn.itcast.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建联想公司的代理商,也就是lenovo的代理对象
 * 代理逻辑:
 * 因为代理对象执行和被代理对象一样的方法时,永远绕不开invoke方法,而且在invoke方法中
 * 仅用Object invoke = method.invoke(lenovo, args)就可以达到与String computer = lenovo.sale(8000);一样的效果
 * 并且在invoke方法中,还可以从   方法返回类型,方法参数,方法体   三个方面增强代理对象,从而为  被代理对象  创造更多的可能
 */
public class Proxy_test {
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();

        /**
                三个参数:
                    1.类加载器:真实对象.getClass().getClassLoader()
                    2.接口数组:真实对象.getClass().getInterfaces()
                    3.处理器:new InvocationHandler()
         */
        //创建lenovo的代理对象
        SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * invoke
             * @param proxy 代理对象
             * @param method 代理对象调用的方法,被封装成了对象
             * @param args 代理对象调用方法时,传递的实际参数
             * @return Object
             * @throws Throwable 异常
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//                System.out.println("我是联想公司的代理商(Lenovo代理对象),我从联想公司拿货(执行Lenovo的方法,不包括方法体)," +
//                        "都必须得经过invoke.....");
//                System.out.println(method.getName());
//                System.out.println(args[0]);

                if(method.getName().equals("sale")) {
                    //改变参数,达到目的
                    double money = (double) args[0];
                    money = money * 0.85;
                    Object obj = method.invoke(lenovo, money);
                    return obj + "_鼠标垫";
                } else {
                    //lenovo执行sale方法
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }
            }
        });

        //2.调用方法
      String computer = proxy_lenovo.sale(8000);
      System.out.println(computer);
//      proxy_lenovo.show();
    }
}
