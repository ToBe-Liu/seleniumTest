package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店用户中心页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class UserCenterPage extends StoreMenu {

    @FindBy(id="logout",description = "退出登录")
    String logout="logout";//

    @FindBy(id="bot2-Msg1",description = "Yes")
    String yes="yes";

    public UserCenterPage(WebDriver driver){
        super(driver);
    }

    /**
     * 退出登录
     *
     * @author LiuXingHai
     */
    public void logout() throws InterruptedException {
        fluentFindAndClick(this.getClass(), logout);
        fluentFindAndClick(this.getClass(), yes);
    }
}
