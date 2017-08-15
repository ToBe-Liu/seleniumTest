package com.yatou.automation.store;

import com.yatou.automation.common.BasePage;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import com.yatou.automation.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * 门店登录
 *
 * @author LiuXingHai
 * @date 2017-08-14
 */
public class LoginPage extends BasePage{

    @FindBy(name="userNo")
    WebElement userName;//用户名

    @FindBy (name="password")
    WebElement passWord;//密码

    @FindBy (xpath="//footer/button")
    WebElement submit;//提交

    @FindBy (className="layui-layer-content")
    WebElement layer;//提示信息


    public LoginPage (WebDriver driver){
        super(driver);
    }

    public UserCenterPage login(String userName,String passWord){
        driver.get(StoreConstants.LOGINURL);
        Logger.log("打开门店首页！");
        type(this.userName,userName);
        type(this.passWord,passWord);
        click(this.submit);
        layer = WaitUtil.fluentWait(driver, (driver1) -> driver.findElement(By.className("layui-layer-content")));
        if (layer != null){
            Logger.log("登录失败：" + layer.getText());
            Assert.assertNull(layer);
        }
        UserCenterPage userCenterPage = PageFactory.initElements(driver, UserCenterPage.class);
        if (userCenterPage.logout == null) {
            Logger.log("登录失败：未知原因");
            Assert.assertNull(layer);
        }
        return userCenterPage;
    }
}
