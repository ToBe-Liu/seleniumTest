package com.yatou.automation.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 门店用户管理页面
 *
 * @author LiuXingHai
 * @date 2017-08-18
 */
public class CustomerManagePage extends  Menu{

    @FindBy(id="logout")
    WebElement logout;//退出登录

    public CustomerManagePage(WebDriver driver){
        super(driver);
    }

    public boolean logout(){
        return  false;
    }
}
