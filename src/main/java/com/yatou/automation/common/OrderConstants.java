package com.yatou.automation.common;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.name.Named;
import com.yatou.automation.utils.PropertiesUtil;
import com.yatou.automation.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.inject.name.Names.named;

/**
 * 平台常量
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class OrderConstants {

    public static final String OPERATION_SUCCEED = "操作成功";//操作返回信息
    public static final String LOGOUT = "退出登录";
    @Inject
    @Named("order.SETTLEMENT_TYPE")
    public static String SETTLEMENT_TYPE;//结算方式：0 直减 1 折扣率
    @Inject
    @Named("order.discount")
    public static String DISCOUNT;//直减金额/折扣率


    private static final Logger logger = LoggerFactory.getLogger(OrderConstants.class);

    static {
        com.google.inject.Injector injector = Guice.createInjector(new Module() {
            @SuppressWarnings("unchecked")
            public void configure(Binder binder) {
                Properties p = new Properties();
                try {
                    p.load(new InputStreamReader(this.getClass()
                            .getResourceAsStream("/order.properties")));
                } catch (IOException e) {
                    e.printStackTrace();
                    assert false;
                }
                String use = (String) p.get("use");
                if (use == null || "".equals(use.trim())) {
                    logger.error("order.properties没有指定use属性或use属性值为空！");
                    Assert.assertNull(1,"order.properties没有指定use属性或use属性值为空！");
                }
                Map<Object, Object> collect = p.entrySet().stream()
                        .filter(map -> map.getKey().toString().contains("_" + use))
                        .collect(Collectors.toMap(map -> map.getKey().toString().replace("_" + use,""), map -> map.getValue()));
                collect.forEach((k,v) -> {
                    String key = (String) k;
                    String value = (String) v;
                    binder.bindConstant().annotatedWith(named("order." + key)).to(value);
                    binder.requestStaticInjection(OrderConstants.class);
                });
                /*while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();
                    String value = (String) p.get(key);
                    binder.bindConstant().annotatedWith(named("store." + key)).to(value);
                    binder.requestStaticInjection(StoreConstants.class);
                }*/
            }
        });
    }

    /**
     * 获取流水号
     *
     * @return
     */
    public static List<String> getPipelineNoS() {
        return getProperties("pipelineNoS");
    }

    /**
     * 添加流水号（在原有流水号的基础上通过逗号分开添加）
     *
     * @param value 需要添加的流水号
     * @param comment 注释
     */
    public static void setPipelineNoS(String value,String comment) {
        setProperties("pipelineNoS",value,comment);
    }
    /**
     * 获取订单号
     *
     * @return
     */
    public static List<String> getOrderNoS() {
        return getProperties("orderNoS");
    }

    /**
     * 添加订单号（在原有订单号的基础上通过逗号分开添加）
     *
     * @param value 需要添加的订单号
     * @param comment 注释
     */
    public static void setOrderNoS(String value,String comment) {
        setProperties("orderNoS",value,comment);
    }
    /**
     * 根据key获取List<value>
     *
     * @param key
     * @return List<value>
     */
    public static List<String> getProperties(String key) {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        String use = propertiesUtil.getProperty("use");
        if (use == null || "".equals(use.trim())) {
            logger.error("order.properties没有指定use属性或use属性值为空！");
            Assert.assertNull(1,"order.properties没有指定use属性或use属性值为空！");
        }
        logger.debug("use:"+use);
        String property = propertiesUtil.getProperty(key+"_" + use);
        if (property == null || "".equals(property)){
            return null;
        }
        List<String> properties = StringUtil.splitByComma(property, String.class);
        Set<String> set = new HashSet<>();
        set.addAll(properties);
        List<String> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    /**
     * 添加属性（在原有值的基础上通过逗号分开添加）
     *
     * @param key
     * @param value 需要添加的值
     * @param comment 注释
     */
    public static void setProperties(String key ,String value,String comment) {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        String use = propertiesUtil.getProperty("use");
        if (use == null || "".equals(use.trim())) {
            logger.error("order.properties没有指定use属性或use属性值为空！");
            Assert.assertNull(1,"order.properties没有指定use属性或use属性值为空！");
        }
        List<String> properties = getProperties(key);
        key = key + "_" + use;
        if ( properties != null){
            properties.add(value);
            Set<String> set = new HashSet<>();
            set.addAll(properties);
            List<String> list = new ArrayList<>();
            list.addAll(set);
            value = StringUtil.splice2String(list);
        }
        propertiesUtil.writeProperties(key,value,comment);
    }

}