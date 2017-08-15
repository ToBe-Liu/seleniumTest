package com.yatou.automation.common;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.name.Named;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

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
                    Enumeration e = p.keys();
                    while(e.hasMoreElements()) {
                        String key = (String)e.nextElement();
                        String value = (String)p.get(key);
                        binder.bindConstant().annotatedWith(named("store." + key)).to(value);
                        binder.requestStaticInjection(StoreConstants.class);
                    }
                }
            });
        }
}  