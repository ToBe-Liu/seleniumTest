package com.yatou.automation.common;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.name.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.google.inject.name.Names.named;

/**
 * 平台账户常量
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderAccountConstants {
    @Inject
    @Named("order.review_username")
    public static String REVIEW_USERNAME;//审单员
    @Inject
    @Named("order.review_password")
    public static String REVIEW_PASSWORD;
    @Inject
    @Named("order.apart_username")
    public static String APART_USERNAME;//拆单员
    @Inject
    @Named("order.apart_password")
    public static String APART_PASSWORD;
    @Inject
    @Named("order.apart_review_username")
    public static String APART_REVIEW_USERNAME;//拆审员
    @Inject
    @Named("order.apart_review_password")
    public static String APART_REVIEW_PASSWORD;
    @Inject
    @Named("order.schedule_username")
    public static String SCHEDULE_USERNAME;//排单员
    @Inject
    @Named("order.schedule_password")
    public static String SCHEDULE_PASSWORD;
    @Inject
    @Named("order.finance_username")
    public static String FINANCE_USERNAME;//财务
    @Inject
    @Named("order.finance_password")
    public static String FINANCE_PASSWORD;
    private static final Logger logger = LoggerFactory.getLogger(OrderAccountConstants.class);

    static {
        com.google.inject.Injector injector = Guice.createInjector(new Module() {
            @SuppressWarnings("unchecked")
            public void configure(Binder binder) {
                Properties p = new Properties();
                try {
                    p.load(new InputStreamReader(this.getClass()
                            .getResourceAsStream("/orderaccount.properties")));
                } catch (IOException e) {
                    e.printStackTrace();
                    assert false;
                }
                String use = (String) p.get("use");
                if (use == null || "".equals(use.trim())) {
                    logger.error("orderaccount.properties没有指定use属性或use属性值为空！");
                    assert false;
                }
                Map<Object, Object> collect = p.entrySet().stream()
                        .filter(map -> map.getKey().toString().contains("_" + use))
                        .collect(Collectors.toMap(map -> map.getKey().toString().replace("_" + use,"") , map -> map.getValue()));
                collect.forEach((k,v) -> {
                    String key = (String) k;
                    String value = (String) v;
                    binder.bindConstant().annotatedWith(named("order." + key)).to(value);
                    binder.requestStaticInjection(OrderAccountConstants.class);
                });
            }
        });
    }

    public static String getField(String field) throws NoSuchFieldException, IllegalAccessException {
        return OrderAccountConstants.class.getField(field).get(new OrderAccountConstants()).toString();
    }


}