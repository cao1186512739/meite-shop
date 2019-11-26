package com.opendev.pay.StrategyFactory;

import com.opendev.pay.strategy.PayStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class StrategyFactory {

    private static StrategyFactory instance = new StrategyFactory();

    private static Map<String, PayStrategy> strategyBean;

    //私有构造器，上来便初始化对象字段
    private StrategyFactory() {
        strategyBean = new ConcurrentHashMap<String, PayStrategy>();
    }


    public static StrategyFactory getInstance() {
        if (instance == null) {
            instance = new StrategyFactory();
        }
        return instance;
    }

    public static PayStrategy getPayStrategy(String classAddres) {
        try {
            if (StringUtils.isEmpty(classAddres)) {
                return null;
            }
            PayStrategy beanPayStrategy = strategyBean.get(classAddres);
            if (beanPayStrategy != null) {
                return beanPayStrategy;
            }
            // 1.使用Java的反射机制初始化子类
            Class<?> forName = Class.forName(classAddres);
            PayStrategy payStrategy = (PayStrategy) forName.newInstance();
            if (null != payStrategy) {
                strategyBean.put(classAddres, payStrategy);
            }
            return payStrategy;
        } catch (Exception ex) {
            log.info("失败，失败原因如下:" + ex);
            return null;
        }
    }

    public static void main(String[] args) {
        PayStrategy payStrategy = StrategyFactory.getPayStrategy("com.opendev.pay.strategy.impl.AliPayStrategy");
        System.out.println(payStrategy.toString());
    }
}
