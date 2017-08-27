package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import com.yatou.automation.utils.RandomUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店合同登记页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-23
 */
public class ContractRegisterPage extends  Menu{
    @FindBy(xpath="//td/a[contains(@href,'/taskseq/index/')]",
            description = "流水号")
    String pipelineNo="pipelineNo";

    @FindBy(xpath="//a[contains(@href,'/contract/create/')]",
            description = "合同登记")
    String contractRegister="contractRegister";

    @FindBy(xpath="//button[@type='submit']",
            description = "提交")
    String submit="submit";

    @FindBy(xpath="//button[@id='submit']",
            description = "确认添加")
    String idSubmit="idSubmit";

    @FindBy(linkText="新增空间",
            description = "新增空间")
    String addSpace="addSpace";

    @FindBy(linkText="新增订单",
            description = "新增订单")
    String addOrder="addOrder";

    @FindBy(name="prodInfo",
            description = "订单信息")
    String prodInfo="prodInfo";

    @FindBy(id="spaceType",
            description = "子类空间类型")
    String spaceType="spaceType";

    @FindBy(id="parentSpaceType",
            description = "父类空间类型")
    String parentSpaceType="parentSpaceType";

    @FindBy(name="price",
            description = "单空间价格")
    String price="price";

    @FindBy(xpath="//label[@id='label-upload-file' and text()='上传成功 ']",
            description = "文件上传成功")//注意上传成功后面有一个空格
    String uploadSucceed="uploadSucceed";

    @FindBy(id="dwy_file_upload",
            description = "文件上传输入框")
    String uploadInput="uploadInput";

    @FindBy(name="tagName",
            description = "订单标签")
    String tagName="tagName";

    @FindBy(name="contNo",
            description = "合同编号")
    String contNo="contNo";

    @FindBy(name="amount",
            description = "合同总金额")
    String amount="amount";

    @FindBy(name="deliveryDate",
            description = "订单交付日期")
    String deliveryDate="deliveryDate";

    @FindBy(name="lastDate",
            description = "最晚下单时间")
    String lastDate="lastDate";

    @FindBy(xpath="//label[@class='checkbox-inline'][1]",
            description = "购买原因")
    String buyReason="buyReason";


    public ContractRegisterPage(WebDriver driver){
        super(driver);
    }

    /**
     * 合同登记
     *
     * @author LiuXingHai
     */
    public void addContractRegister() throws InterruptedException {
        fluentFindAndClick(ContractRegisterPage.class, pipelineNo);
        fluentFindAndClick(ContractRegisterPage.class, contractRegister);
        fluentFindAndType(ContractRegisterPage.class, contNo, "TEST"+ RandomUtil.code(10));
        fluentFindAndType(ContractRegisterPage.class, amount, "10000");
        fluentFindAndTypeDate(ContractRegisterPage.class, deliveryDate,"2099-08-22");
        fluentFindAndTypeDate(ContractRegisterPage.class, lastDate,"2099-08-21");
        fluentFindAndClick(ContractRegisterPage.class, buyReason);

        fluentFindAndClick(ContractRegisterPage.class, idSubmit);

        fluentFindReturnMessage();

        Logger.log("合同登记成功！");
    }
    /**
     * 新增空间
     *
     * @author LiuXingHai
     */
    public void addSpace() throws InterruptedException {
        fluentFindAndClick(ContractRegisterPage.class, pipelineNo);
        fluentFindAndClick(ContractRegisterPage.class, addSpace);

        fluentFindAndSelectByValue(ContractRegisterPage.class, parentSpaceType, StoreConstants.PARENT_SPACE_TYPE);
        fluentFindAndSelectByValue(ContractRegisterPage.class, spaceType, StoreConstants.SPACE_TYPE);

        fluentFindAndClick(ContractRegisterPage.class, submit);

        fluentFindReturnMessage();

        Logger.log("新增空间成功！");
    }
    /**
     * 新增订单
     *
     * @author LiuXingHai
     */
    public void addOrder() throws InterruptedException {
        fluentFindAndClick(ContractRegisterPage.class, pipelineNo);


        fluentFindAndClick(ContractRegisterPage.class, addOrder);

        //不知道什么原因（可能是前端用的插件导致的）这里的input框直接sendKeys会报错，所以换成设置属性
        fluentFindAndSetAttribute(ContractRegisterPage.class, prodInfo,"value","自动测试");

        //不知道什么原因（可能是前端用的插件导致的）这里的select框直接点击会报错，所以换成设置属性
        fluentFindAndSetSelectedByValue(ContractRegisterPage.class, tagName, "自动测试");

        fluentFindAndClick(ContractRegisterPage.class, submit);

        fluentFindReturnMessage();

        Logger.log("新增空间成功！");
    }
    /**
     * 上传量尺文件
     *
     * @author LiuXingHai
     */
    public void uploadMeasureFile() throws InterruptedException {

        //fluentFindAndClick(findElement(ContractRegisterPage.class, uploadMeasureFiles),"上传量尺文件");

        fluentFindAndSelectByValue(ContractRegisterPage.class, parentSpaceType, StoreConstants.PARENT_SPACE_TYPE);
        fluentFindAndSelectByValue(ContractRegisterPage.class, spaceType, StoreConstants.SPACE_TYPE);

        fluentFindAndType(ContractRegisterPage.class, price,"10000");

        fluentFindAndTypeFile(ContractRegisterPage.class, uploadInput,StoreConstants.MEASURE_FILE);

        fluentFind(ContractRegisterPage.class, uploadSucceed,null);

        fluentFindAndClick(ContractRegisterPage.class, submit);

        fluentFindReturnMessage();
        Logger.log("上传量尺文件成功！");
    }
}
