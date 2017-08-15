package com.yatou.automation.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 门店中心
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class StoreCenterPage extends  Menu{

    @FindBy(name="userNo")
    WebElement userName;//用户名

    @FindBy (name="password")
    WebElement passWord;//密码

    @FindBy (xpath="//footer/button")
    WebElement submit;//提交


    public StoreCenterPage(WebDriver driver){
        super(driver);
    }

    public boolean login(String userName,String passWord){
        type(this.userName,userName);
        type(this.passWord,passWord);
        click(this.submit);
        return  false;
    }
}
