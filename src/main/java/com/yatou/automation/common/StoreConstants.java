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
 * 门店常量
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class StoreConstants {
    @Inject
    @Named("store.loginURL")
    public static String LOGINURL;
    @Inject
    @Named("store.space_type")
    public static String SPACE_TYPE;
    @Inject
    @Named("store.parent_space_type")
    public static String PARENT_SPACE_TYPE;
    @Inject
    @Named("store.measure_file")
    public static String MEASURE_FILE;
    @Inject
    @Named("store.plan_file")
    public static String PLAN_FILE;
    @Inject
    @Named("store.effect_file")
    public static String EFFECT_FILE;


    private static final Logger logger = LoggerFactory.getLogger(StoreConstants.class);

    static {
        com.google.inject.Injector injector = Guice.createInjector(new Module() {
            @SuppressWarnings("unchecked")
            public void configure(Binder binder) {
                Properties p = new Properties();
                try {
                    p.load(new InputStreamReader(this.getClass()
                            .getResourceAsStream("/store.properties")));
                } catch (IOException e) {
                    e.printStackTrace();
                    assert false;
                }
                String use = (String) p.get("use");
                if (use == null || "".equals(use.trim())) {
                    logger.error("store.properties没有指定use属性或use属性值为空！");
                }
                Map<Object, Object> collect = p.entrySet().stream()
                        .filter(map -> map.getKey().toString().contains("_" + use))
                        .collect(Collectors.toMap(map -> map.getKey().toString().replace("_" + use,""), map -> map.getValue()));
                collect.forEach((k,v) -> {
                    String key = (String) k;
                    String value = (String) v;
                    binder.bindConstant().annotatedWith(named("store." + key)).to(value);
                    binder.requestStaticInjection(StoreConstants.class);
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
}  