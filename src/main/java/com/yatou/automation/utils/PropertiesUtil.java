package com.yatou.automation.utils;

import com.yatou.automation.common.SafeProperties;
import com.yatou.automation.common.StoreConstants;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class PropertiesUtil {
    private SafeProperties props;
    //private Properties props;
    private String filePath;
    private static final String DEFAULT_FILE_PATH =
            new File("").getAbsolutePath()+"\\src\\main\\resources\\store.properties";

    public PropertiesUtil(String filePath){
        readProperties(filePath);
    }    
    public PropertiesUtil(){
        readProperties(DEFAULT_FILE_PATH.replace("\\target",""));
    }

    private void readProperties(String filePath) {
        try {    
            props = new SafeProperties();
            //InputStream fis =getClass().getResourceAsStream(filePath);
            InputStream fis =new BufferedInputStream(new FileInputStream(filePath));
            props.load(fis);
            this.filePath = filePath;
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }    
    /**  
     * 获取某个属性  
     */    
    public String getProperty(String key){    
        return props.getProperty(key);    
    }    
    /**  
     * 获取所有属性，返回一个map,不常用  
     * 可以试试props.putAll(t)  
     */    
    public Map getAllProperty(){    
        Map map=new HashMap();
        Enumeration enu = props.propertyNames();
        while (enu.hasMoreElements()) {    
            String key = (String) enu.nextElement();    
            String value = props.getProperty(key);    
            map.put(key, value);    
        }    
        return map;    
    }    
    /**  
     * 在控制台上打印出所有属性，调试时用。  
     */    
    public void printProperties(){    
        props.list(System.out);    
    }    
    /**  
     * 写入properties信息
     * @param comment 注释
     */    
    public void writeProperties(String key, String value,String comment) {
        try {    
            OutputStream fos = new FileOutputStream(filePath);
            props.put(key, value,null,comment);
            props.store(fos,null);
        } catch (Exception e) {    
        e.printStackTrace();  
        }    
    }       
    public static void main(String[] args) {    
       /* PropertiesUtil util=new PropertiesUtil();
        Map allProperty = util.getAllProperty();
        System.out.println(allProperty);*/
        /*PropertiesUtil util=new PropertiesUtil();

        util.writeProperties("fsf","个梵蒂冈","测试");*/
        StoreConstants.setOrderNoS("ss","订单号");
    }        
} 