package com.yatou.automation.store;

import com.yatou.automation.common.BasePage;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店登录页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-14
 */
public class LoginPage extends BasePage{

    @FindBy(name="userNo")
    String userName;//用户名

    @FindBy (name="password")
    String passWord;//密码

    @FindBy (xpath="//footer/button")
    String submit;//提交

    @FindBy (className="layui-layer-content")
    String layer;//提示信息

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginPage.class);
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
        logger.debug("login"+driver);

        driver.get(StoreConstants.LOGINURL);
        fluentFindAndType(findElement(LoginPage.class, this.userName),userName,"用户名");
        fluentFindAndType(findElement(LoginPage.class, this.passWord),passWord,"密码");
        fluentFindAndClick(findElement(LoginPage.class, submit),"登录");

        WebElement isLogin = concurrentFind("成功界面和错误提示！",
                findElement(Menu.class, "logout"),
                findElement(LoginPage.class, layer));
        if(isLogin == null ){
            Assert.assertNull(1,"登录失败！");
        }
        if(!StoreConstants.LOGOUT.equals(isLogin.getText().trim()) ){
            Assert.assertNull(1,"登录失败：" + isLogin.getText());
        }
        Logger.log("登录成功！");
    }
}
