package com.yatou.automation.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店用户中心页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class UserCenterPage extends StoreMenu {

    @FindBy(id="logout")
    String logout;//退出登录

    public UserCenterPage(WebDriver driver){
        super(driver);
    }

    public boolean logout(){
        return  false;
    }
}
