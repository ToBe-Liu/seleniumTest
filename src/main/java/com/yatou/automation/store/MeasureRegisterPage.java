package com.yatou.automation.store;

import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店量尺登记页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-22
 */
public class MeasureRegisterPage extends  Menu{

    @FindBy(xpath="//td/a[contains(@href,'/taskseq/index/')]")
    String pipelineNo = "pipelineNo";//流水号
    @FindBy(xpath="//a[contains(@href,'/measure/create/')]")
    String measureRegister;//量尺登记
    @FindBy(name="planDate")
    String planDate;//计划入住时间
    @FindBy(xpath="//label[@class='checkbox-inline'][1]")
    String furniture;//预购家具
    @FindBy(xpath="//button[@type='submit']")
    String submit;//确认添加
    @FindBy(linkText="上传量尺文件")
    String uploadMeasureFiles;//上传量尺文件
    @FindBy(xpath="//label[@class='radio radio-inline']/span[text()='量尺文件']")
    String measureFile;//文件类型:量尺文件
    @FindBy(id="spaceType")
    String spaceType;//子类空间类型
    @FindBy(id="parentSpaceType")
    String parentSpaceType;//父类空间类型
    @FindBy(name="price")
    String price;//单空间价格
    @FindBy(xpath="//label[@id='label-upload-file' and text()='上传成功 ']")//注意上传成功后面有一个空格
    String uploadSucceed;//文件上传成功
    @FindBy(id="dwy_file_upload")
    String uploadInput;//文件上传输入框
    @FindBy(linkText="上传方案")
    String uploadPlan;//上传方案

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

        fluentFindAndTypeDate(findElement(MeasureRegisterPage.class, "planDate"),"2099-08-22","计划入住时间");

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "furniture"),"预购家具");

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

        fluentFindAndTypeFile(findElement(MeasureRegisterPage.class, "uploadInput"),filePath,"文件上传输入框");

        fluentFind(findElement(MeasureRegisterPage.class, "uploadSucceed"), "文件上传成功");

        fluentFindAndClick(findElement(MeasureRegisterPage.class, "submit"),"确认添加");

        fluentFindReturnMessage();
    }
}
