package com.yatou.automation.store;

import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 门店量尺登记页面
 *
 * @author LiuXingHai
 * @date 2017-08-22
 */
public class MeasureRegisterPage extends  Menu{

    @FindBy(xpath="//td/a[contains(@href,'/taskseq/index/')]")
    WebElement pipelineNo;//流水号
    @FindBy(xpath="//a[contains(@href,'/measure/create/')]")
    WebElement measureRegister;//量尺登记
    @FindBy(name="planDate")
    WebElement planDate;//计划入住时间
    @FindBy(xpath="//label[@class='checkbox-inline'][1]")
    WebElement furniture;//预购家具
    @FindBy(xpath="//button[@type='submit']")
    WebElement submit;//确认添加
    @FindBy(linkText="上传量尺文件")
    WebElement uploadMeasureFiles;//上传量尺文件
    @FindBy(xpath="//label[@class='radio radio-inline']/span[text()='量尺文件']")
    WebElement measureFile;//文件类型:量尺文件
    @FindBy(id="spaceType")
    WebElement spaceType;//子类空间类型
    @FindBy(id="parentSpaceType")
    WebElement parentSpaceType;//父类空间类型
    @FindBy(name="price")
    WebElement price;//单空间价格
    @FindBy(xpath="//label[@id='label-upload-file' and text()='上传成功 ']")//注意上传成功后面有一个空格
    WebElement uploadSucceed;//文件上传成功
    @FindBy(id="dwy_file_upload")
    WebElement uploadInput;//文件上传输入框
    @FindBy(linkText="上传方案")
    WebElement uploadPlan;//上传方案

    public MeasureRegisterPage(WebDriver driver){
        super(driver);
    }
    /**
     * 量尺登记
     *
     * @author LiuXingHai
     */
    public void measureRegister() throws InterruptedException {

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "pipelineNo"), "流水号");

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "measureRegister"),"量尺登记");

        //移除输入框的只读属性不然无法直接设置日期
        fluentFindAndRemoveAttribute(findElement(MeasureRegisterPage.class, "planDate"),"readonly","计划入住时间");
        fluentFindAndType(findElement(MeasureRegisterPage.class, "planDate"),"2099-08-22","计划入住时间");

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "furniture"),"预购家具");
        //fluentFindAndClick(findElement(MeasureRegisterPage.class, "furniture"),"预购家具");

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "submit"),"确认添加");

        fluentFindReturnMessage();
        Logger.log("量尺登记成功！");
    }
    /**
     * 一次上传所有量尺文件,平面图文件,效果图文件
     *
     * @author LiuXingHai
     */
    public void uploadAllFile() throws InterruptedException {

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "uploadMeasureFiles"),"上传量尺文件");
        uploadMeasureFile();

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "uploadPlan"),"上传方案");
        uploadPlanFile();
        uploadEffectFile();
    }
    /**
     * 上传量尺文件
     *
     * @author LiuXingHai
     */
    public void uploadMeasureFile() throws InterruptedException {

        uploadFile(StoreConstants.MEASURE_FILE);
        Logger.log("上传量尺文件成功！");
    }
    /**
     * 上传平面图文件
     *
     * @author LiuXingHai
     */
    public void uploadPlanFile() throws InterruptedException {

        uploadFile(StoreConstants.PLAN_FILE);
        Logger.log("上传平面图文件成功！");
    }
    /**
     * 上传效果图文件
     *
     * @author LiuXingHai
     */
    public void uploadEffectFile() throws InterruptedException {

        uploadFile(StoreConstants.EFFECT_FILE);
        Logger.log("上传效果图文件成功！");
    }
    /**
     * 上传文件
     *
     * @author LiuXingHai
     */
    private void uploadFile(String filePath) throws InterruptedException {

        fluentFindAndSelectByValue(findElement(MeasureRegisterPage.class, "parentSpaceType"), StoreConstants.PARENT_SPACE_TYPE,"父类空间类型");
        fluentFindAndSelectByValue(findElement(MeasureRegisterPage.class, "spaceType"), StoreConstants.SPACE_TYPE,"子类空间类型");

        fluentFindAndType(findElement(MeasureRegisterPage.class, "price"),"10000","单空间价格");

        //移除文件上传输入框的隐藏属性
        fluentFindAndRemoveAttribute(findElement(MeasureRegisterPage.class, "uploadInput"),"style","文件上传输入框");
        fluentFindAndType(findElement(MeasureRegisterPage.class, "uploadInput"),filePath,"文件上传输入框");

        fluentFind(findElement(MeasureRegisterPage.class, "uploadSucceed"), "文件上传成功");

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "submit"),"确认添加");

        fluentFindReturnMessage();
    }
}
