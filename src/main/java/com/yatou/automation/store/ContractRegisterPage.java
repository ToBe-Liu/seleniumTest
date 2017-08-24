package com.yatou.automation.store;

import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import com.yatou.automation.utils.RandomUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店合同登记页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-23
 */
public class ContractRegisterPage extends  Menu{
    @FindBy(xpath="//td/a[contains(@href,'/taskseq/index/')]")
    String pipelineNo;//流水号
    @FindBy(xpath="//a[contains(@href,'/contract/create/')]")
    String contractRegister;//合同登记
    @FindBy(xpath="//button[@type='submit']")
    String submit;//提交
    @FindBy(linkText="新增空间")
    String addSpace;//新增空间
    @FindBy(linkText="新增订单")
    String addOrder;//新增订单
    @FindBy(name="prodInfo")
    String prodInfo;//订单信息
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
    @FindBy(className="select2-choices")
    String choices;//订单标签选择
    @FindBy(className="select2-input")
    String input;//订单标签输入框
    @FindBy(name="tagName")
    String tagName;//订单标签
    @FindBy(name="contNo")
    String contNo;//合同编号
    @FindBy(name="amount")
    String amount;//合同总金额
    @FindBy(name="deliveryDate")
    String deliveryDate;//订单交付日期
    @FindBy(name="lastDate")
    String lastDate;//最晚下单时间
    @FindBy(xpath="//label[@class='checkbox-inline'][1]")
    String buyReason;//购买原因
    @FindBy(xpath="//button[@id='submit']")
    String idSubmit;//确认添加
    @FindBy(xpath="//div[contains(@class,'open')]")
    String dateLayer;//日期弹出框

    public ContractRegisterPage(WebDriver driver){
        super(driver);
    }

    /**
     * 合同登记
     *
     * @author LiuXingHai
     */
    public void addContractRegister() throws InterruptedException {
        fluentFindAndClick(findElement(ContractRegisterPage.class, "pipelineNo"),"流水号");
        fluentFindAndClick(findElement(ContractRegisterPage.class, "contractRegister"),"合同登记");

        fluentFindAndType(findElement(ContractRegisterPage.class, "contNo"), "TEST"+ RandomUtil.code(10),"合同编号");
        fluentFindAndType(findElement(ContractRegisterPage.class, "amount"), "10000","合同总金额");

        fluentFindAndClick(findElement(ContractRegisterPage.class, "buyReason"),"购买原因");

        fluentFindAndTypeDate(findElement(ContractRegisterPage.class, "deliveryDate"),"2099-08-22","订单交付日期");

        fluentFindAndTypeDate(findElement(ContractRegisterPage.class, "lastDate"),"2099-08-21","最晚下单时间");

        //fluentFindAndClick(findElement(ContractRegisterPage.class, "idSubmit"),"确认添加");

        fluentFindReturnMessage();

        Logger.log("合同登记成功！");
    }
    /**
     * 新增空间
     *
     * @author LiuXingHai
     */
    public void addSpace() throws InterruptedException {
        fluentFindAndClick(findElement(ContractRegisterPage.class, "pipelineNo"),"流水号");
        fluentFindAndClick(findElement(ContractRegisterPage.class, "addSpace"),"新增空间");

        fluentFindAndSelectByValue(findElement(ContractRegisterPage.class, "parentSpaceType"), StoreConstants.PARENT_SPACE_TYPE,"父类空间类型");
        fluentFindAndSelectByValue(findElement(ContractRegisterPage.class, "spaceType"), StoreConstants.SPACE_TYPE,"子类空间类型");

        fluentFindAndClick(findElement(ContractRegisterPage.class, "submit"),"提交");

        fluentFindReturnMessage();

        Logger.log("新增空间成功！");
    }
    /**
     * 新增订单
     *
     * @author LiuXingHai
     */
    public void addOrder() throws InterruptedException {
        fluentFindAndClick(findElement(ContractRegisterPage.class, "pipelineNo"),"流水号");

        fluentFindAndClick(findElement(ContractRegisterPage.class, "addOrder"),"新增订单");

        //不知道什么原因（可能是前端用的插件导致的）这里的input框直接sendKeys会报错，所以换成设置属性
        fluentFindAndSetAttribute(findElement(ContractRegisterPage.class, "prodInfo"),"value","自动测试","订单信息");

        //不知道什么原因（可能是前端用的插件导致的）这里的select框直接点击会报错，所以换成设置属性
        fluentFindAndSetSelectedByValue(findElement(ContractRegisterPage.class, "tagName"), "自动测试","订单标签");

        fluentFindAndClick(findElement(ContractRegisterPage.class, "submit"),"提交");

        fluentFindReturnMessage();

        Logger.log("新增空间成功！");
    }
    /**
     * 上传量尺文件
     *
     * @author LiuXingHai
     */
    public void uploadMeasureFile() throws InterruptedException {

        fluentFindAndClick(findElement(ContractRegisterPage.class, "uploadMeasureFiles"),"上传量尺文件");

        fluentFindAndSelectByValue(findElement(ContractRegisterPage.class, "parentSpaceType"), StoreConstants.PARENT_SPACE_TYPE,"父类空间类型");
        fluentFindAndSelectByValue(findElement(ContractRegisterPage.class, "spaceType"), StoreConstants.SPACE_TYPE,"子类空间类型");

        fluentFindAndType(findElement(ContractRegisterPage.class, "price"),"10000","单空间价格");

        fluentFindAndTypeFile(findElement(ContractRegisterPage.class, "uploadInput"),StoreConstants.MEASURE_FILE,"文件上传输入框");

        fluentFind(findElement(ContractRegisterPage.class, "uploadSucceed"), "文件上传成功");

        fluentFindAndClick(findElement(ContractRegisterPage.class, "submit"),"确认添加");

        fluentFindReturnMessage();
        Logger.log("上传量尺文件成功！");
    }
}
