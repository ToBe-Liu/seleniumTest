package com.yatou.automation.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 门店用户中心
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class UserCenterPage extends  Menu{

    @FindBy(id="logout")
    WebElement logout;//退出登录




    public UserCenterPage(WebDriver driver){
        super(driver);
    }

    public boolean login(String userName,String passWord){

        return  false;
    }
}
