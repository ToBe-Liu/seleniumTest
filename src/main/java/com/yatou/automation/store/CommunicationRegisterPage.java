package com.yatou.automation.store;

import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 门店沟通登记页面
 *
 * @author LiuXingHai
 * @date 2017-08-24
 */
public class CommunicationRegisterPage extends  Menu{
    @FindBy(xpath="//td/a[contains(@href,'/taskseq/index/')]")
    WebElement pipelineNo;//流水号
    @FindBy(xpath="//a[contains(@href,'/taskseq/follow/')]")
    WebElement communicationRegister;//沟通登记
    @FindBy(xpath="//button[@type='submit']")
    WebElement submit;//提交
    @FindBy(name="content")
    WebElement content;//修改意见
    @FindBy(xpath="//label[@class='checkbox-inline'][1]")
    WebElement dealPoint;//待成交关键点

    public CommunicationRegisterPage(WebDriver driver){
        super(driver);
    }

    /**
     * 新增沟通登记
     *
     * @author LiuXingHai
     */
    public void addCommunicationRegister() throws InterruptedException {
        fluentFindAndClick(findElement(CommunicationRegisterPage.class, "pipelineNo"),"流水号");
        fluentFindAndClick(findElement(CommunicationRegisterPage.class, "communicationRegister"),"沟通登记");

        fluentFindAndType(findElement(CommunicationRegisterPage.class, "content"),"自动测试","修改意见");

        fluentFindAndClick(findElement(CommunicationRegisterPage.class, "dealPoint"),"待成交关键点");

        fluentFindAndClick(findElement(CommunicationRegisterPage.class, "submit"),"提交");

        fluentFindReturnMessage();

        Logger.log("新增沟通登记成功！");
    }

}
