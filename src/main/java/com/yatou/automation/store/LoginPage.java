package com.yatou.automation.store;

import com.yatou.automation.common.BasePage;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import com.yatou.automation.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public boolean login(String userName,String passWord){
        driver.get("http://localhost:8088/login");
        Logger.log("打开门店首页！");
        type(this.userName,userName);
        type(this.passWord,passWord);
        click(this.submit);
        WaitUtil.fluentWait(driver,(driver1) -> {
            if(verifyElementIsPresent(layer)){
                String text = layer.getText();
                Logger.log("登录失败："+text);
                return null;
            }
            if(!StoreConstants.LOGINURL.equals(getCurrentPageUrl())){
                return null;
            }
            return null;
        });
        return  false;
    }
}
