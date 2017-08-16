package com.yatou.automation.store;

import com.yatou.automation.common.BasePage;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import com.yatou.automation.utils.WaitUtil;
import org.openqa.selenium.*;
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
        WebElement isLogin = WaitUtil.concurrentFindWait(driver,5,"5秒内没有找到成功界面和错误提示！",
                (driver1) -> driver.findElement(By.id("logout")),
                (driver2) -> driver.findElement(By.className("layui-layer-content")));
        if(isLogin == null ){
            Assert.assertNull(1,"登录失败：未知原因！");
        }
        if(!"退出登录".equals(isLogin.getText()) ){
            Assert.assertNull(1,"登录失败：" + isLogin.getText());
        }

        UserCenterPage userCenterPage = PageFactory.initElements(driver, UserCenterPage.class);
        Logger.log("登录成功！");
        return userCenterPage;
    }
}
