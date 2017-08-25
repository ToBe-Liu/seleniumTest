package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店沟通登记页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-24
 */
public class CommunicationRegisterPage extends  Menu{
    @FindBy(xpath="//td/a[contains(@href,'/taskseq/index/')]",description = "流水号")
    String pipelineNo="pipelineNo";//

    @FindBy(xpath="//a[contains(@href,'/taskseq/follow/')]",description = "沟通登记")
    String communicationRegister="communicationRegister";//

    @FindBy(xpath="//button[@type='submit']",description = "提交")
    String submit="submit";//

    @FindBy(name="content",description = "修改意见")
    String content="content";//

    @FindBy(xpath="//label[@class='checkbox-inline'][1]",description = "待成交关键点")
    String dealPoint="dealPoint";//

    public CommunicationRegisterPage(WebDriver driver){
        super(driver);
    }

    /**
     * 新增沟通登记
     *
     * @author LiuXingHai
     */
    public void addCommunicationRegister() throws InterruptedException {
        fluentFindAndClick(CommunicationRegisterPage.class, pipelineNo);

        fluentFindAndClick(CommunicationRegisterPage.class, communicationRegister);

        fluentFindAndType(CommunicationRegisterPage.class, content,"自动测试");

        fluentFindAndClick(CommunicationRegisterPage.class, dealPoint);

        fluentFindAndClick(CommunicationRegisterPage.class, submit);

        fluentFindReturnMessage();

        Logger.log("新增沟通登记成功！");
    }

}
