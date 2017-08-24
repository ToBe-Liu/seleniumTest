package com.yatou.automation.store;

import com.yatou.automation.common.BasePage;
import com.yatou.automation.common.ReturnMessageConstants;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * 门店登录页面
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
    /**
     * 登录
     *
     * @author LiuXingHai
     */
    public void login(String userName,String passWord){
        WebDriver driver = threadDriver.get();
        System.out.println("login"+driver);

        driver.get(StoreConstants.LOGINURL);
        fluentFindAndType(findElement(LoginPage.class, "userName"),userName,"用户名");
        fluentFindAndType(findElement(LoginPage.class, "passWord"),passWord,"密码");
        fluentFindAndClick(findElement(LoginPage.class, "submit"),"登录");

        WebElement isLogin = concurrentFind("成功界面和错误提示！",
                findElement(Menu.class, "logout"),
                findElement(LoginPage.class, "layer"));
        if(isLogin == null ){
            Assert.assertNull(1,"登录失败！");
        }
        if(!ReturnMessageConstants.LOGOUT.equals(isLogin.getText().trim()) ){
            Assert.assertNull(1,"登录失败：" + isLogin.getText());
        }
        Logger.log("登录成功！");
    }
}
