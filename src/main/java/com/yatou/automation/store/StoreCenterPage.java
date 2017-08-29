package com.yatou.automation.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店中心
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class StoreCenterPage extends StoreMenu {

    @FindBy(name="userNo")
    String userName;//用户名

    @FindBy (name="password")
    String passWord;//密码

    @FindBy (xpath="//footer/button")
    String submit;//提交


    public StoreCenterPage(WebDriver driver){
        super(driver);
    }

    public boolean login(String userName,String passWord){
        return false;
    }
}
