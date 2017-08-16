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
 * 账户常量
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class AccountConstants {
    @Inject
    @Named("store.adviser_username")
    public static String ADVISER_USERNAME;
    @Inject
    @Named("store.adviser_password")
    public static String ADVISER_PASSWORD;

    private static final Logger logger = LoggerFactory.getLogger(AccountConstants.class);

    static {
        com.google.inject.Injector injector = Guice.createInjector(new Module() {
            @SuppressWarnings("unchecked")
            public void configure(Binder binder) {
                Properties p = new Properties();
                try {
                    p.load(new InputStreamReader(this.getClass()
                            .getResourceAsStream("/storeaccount.properties")));
                } catch (IOException e) {
                    e.printStackTrace();
                    assert false;
                }
                String use = (String) p.get("use");
                if (use == null || "".equals(use.trim())) {
                    logger.error("storeaccount.properties没有指定use属性或use属性值为空！");
                }
                Map<Object, Object> collect = p.entrySet().stream()
                        .filter(map -> map.getKey().toString().contains("_" + use))
                        .collect(Collectors.toMap(map -> map.getKey().toString().replace("_" + use,"") , map -> map
                                .getValue()));
                collect.forEach((k,v) -> {
                    String key = (String) k;
                    String value = (String) v;
                    binder.bindConstant().annotatedWith(named("store." + key)).to(value);
                    binder.requestStaticInjection(AccountConstants.class);
                });
            }
        });
    }
}  