package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店流水查询页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-27
 */
public class PipelineQueryPage extends  Menu{

    @FindBy(xpath="//button[@type='submit']",description = "查询")
    String submit="submit";//

    @FindBy(name="lid",description = "流水号输入框")
    String pipelineNoInput="pipelineNoInput";//

    @FindBy(xpath="//td/a[contains(@href,'/taskseq/index/')]",description = "流水号")
    String pipelineNo="pipelineNo";//查询结果的流水号

    public PipelineQueryPage(WebDriver driver){
        super(driver);
    }
    /**
     * 流水查询
     *
     * @param pipelineNo 需要查询的流水号
     * @author LiuXingHai
     */
    public void pipelineQuery(String pipelineNo) throws InterruptedException {

        fluentFindAndSetAttribute(PipelineQueryPage.class, this.pipelineNoInput,"value",pipelineNo);

        fluentFindAndClick(PipelineQueryPage.class, submit);

        fluentFindAndClickByText(PipelineQueryPage.class, this.pipelineNo,pipelineNo);

        Logger.log("流水查询成功！");
    }
}
