package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店量尺登记页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-22
 */
public class MeasureRegisterPage extends StoreMenu {

    @FindBy(xpath="//a[contains(@href,'/measure/create/')]",description = "量尺登记")
    String measureRegister="measureRegister";//

    @FindBy(name="planDate",description = "计划入住时间")
    String planDate="planDate";//

    @FindBy(xpath="//label[@class='checkbox-inline'][1]",description = "预购家具")
    String furniture="furniture";//

    @FindBy(xpath="//button[@type='submit']",description = "确认添加")
    String submit="submit";//

    @FindBy(linkText="上传量尺文件",description = "上传量尺文件")
    String uploadMeasureFiles="uploadMeasureFiles";//

    @FindBy(id="spaceType",description = "子类空间类型")
    String spaceType="spaceType";//

    @FindBy(id="parentSpaceType",description = "父类空间类型")
    String parentSpaceType="parentSpaceType";//

    @FindBy(name="price",description = "单空间价格")
    String price="price";//

    @FindBy(xpath="//label[@id='label-upload-file' and text()='上传成功 ']",description = "文件上传成功")
    String uploadSucceed="uploadSucceed";//注意上传成功后面有一个空格

    @FindBy(id="dwy_file_upload",description = "文件上传输入框")
    String uploadInput="uploadInput";//

    @FindBy(linkText="上传方案",description = "上传方案")
    String uploadPlan="uploadPlan";//

    @FindBy(xpath="//label[@class='radio radio-inline']/span[text()='效果图']",description = "效果图")
    String effectFile="effectFile";//

    @FindBy(xpath="//a[contains(@href,'/taskseq/index/')]",description = "流水信息")
    String pipelineInfo="pipelineInfo";//

    public MeasureRegisterPage(WebDriver driver){
        super(driver);
    }
    /**
     * 量尺登记
     *
     * @author LiuXingHai
     */
    public void measureRegister() throws InterruptedException {

        fluentFindAndClick(this.getClass(), measureRegister);

        fluentFindAndTypeDate(this.getClass(), planDate,"2099-08-22");

        fluentFindAndClick(this.getClass(), furniture);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();
        Logger.log("量尺登记成功！");
    }
    /**
     * 一次上传所有量尺文件,平面图文件,效果图文件
     *
     * @author LiuXingHai
     */
    public void uploadAllFile() throws InterruptedException {

        fluentFindAndClick(this.getClass(), uploadMeasureFiles);
        uploadMeasureFile();

        fluentFindAndClick(this.getClass(), uploadPlan);
        uploadPlanFile();
        uploadEffectFile();
        fluentFindAndClick(this.getClass(), pipelineInfo);//文件上传后返回到流水详情页面方便下面的操作
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

        fluentFindAndClick(this.getClass(), effectFile);
        uploadFile(StoreConstants.EFFECT_FILE);
        Logger.log("上传效果图文件成功！");
    }
    /**
     * 上传文件
     *
     * @author LiuXingHai
     */
    private void uploadFile(String filePath) throws InterruptedException {

        fluentFindAndSelectByValue(this.getClass(), parentSpaceType, StoreConstants.PARENT_SPACE_TYPE);
        fluentFindAndSelectByValue(this.getClass(), spaceType, StoreConstants.SPACE_TYPE);

        fluentFindAndType(this.getClass(), price,"10000");

        fluentFindAndTypeFile(this.getClass(), uploadInput,filePath);

        fluentFind(this.getClass(), uploadSucceed,null);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();
    }
}
