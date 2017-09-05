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
public class ContractRegisterPage extends StoreMenu {
    @FindBy(xpath="//td/a[contains(@href,'/taskseq/index/')]",description = "流水号")
    String pipelineNo="pipelineNo";

    @FindBy(xpath="//a[contains(@href,'/contract/create/')]",description = "合同登记")
    String contractRegister="contractRegister";

    @FindBy(xpath="//button[@type='submit']",description = "提交")
    String submit="submit";

    @FindBy(xpath="//button[@id='submit']",description = "确认添加")
    String idSubmit="idSubmit";

    @FindBy(linkText="新增空间",description = "新增空间")
    String addSpace="addSpace";

    @FindBy(linkText="新增订单",description = "新增订单")
    String addOrder="addOrder";

    @FindBy(name="prodInfo",description = "订单信息")
    String prodInfo="prodInfo";

    @FindBy(id="spaceType",description = "子类空间类型")
    String spaceType="spaceType";

    @FindBy(id="parentSpaceType",description = "父类空间类型")
    String parentSpaceType="parentSpaceType";

    @FindBy(name="price",description = "单空间价格")
    String price="price";

    @FindBy(xpath="//label[@id='label-upload-file' and text()='上传成功 ']",description = "文件上传成功")//注意上传成功后面有一个空格
    String uploadSucceed="uploadSucceed";

    @FindBy(id="dwy_file_upload",description = "文件上传输入框")
    String uploadInput="uploadInput";

    @FindBy(name="tagName",description = "订单标签")
    String tagName="tagName";

    @FindBy(name="contNo",description = "合同编号")
    String contNo="contNo";

    @FindBy(name="amount",description = "合同总金额")
    String amount="amount";

    @FindBy(name="deliveryDate",description = "订单交付日期")
    String deliveryDate="deliveryDate";

    @FindBy(name="lastDate",description = "最晚下单时间")
    String lastDate="lastDate";

    @FindBy(xpath="//label[@class='checkbox-inline'][1]",description = "购买原因")
    String buyReason="buyReason";

    @FindBy(xpath="(//a[starts-with(@href,'/order/detail/')])[1]", description = "订单号")
    String orderNo="orderNo";

    @FindBy(xpath="(//a[starts-with(@href,'/file/order/create/')])[1]", description = "上传")
    String uploadCreateOrderFile="uploadCreateOrderFile";//上传建单文件

    @FindBy(linkText="提交审核", description = "提交审核")
    String submitReview="submitReview";

    @FindBy(className="layui-layer-btn0", description = "确定")
    String confirm="confirm";

    @FindBy(xpath="//a[contains(@href,'/taskseq/index/')]",description = "流水详情")
    String pipelineInfo="pipelineInfo";//

    @FindBy(className="layui-layer-shade",description = "弹出遮罩层")
    String layerShade="layerShade";//

    public ContractRegisterPage(WebDriver driver){
        super(driver);
    }

    /**
     * 合同登记
     *
     * @author LiuXingHai
     */
    public void addContractRegister() throws InterruptedException {

        fluentFindAndClick(this.getClass(), contractRegister);
        fluentFindAndType(this.getClass(), contNo, "TEST"+ RandomUtil.code(10));
        fluentFindAndType(this.getClass(), amount, "10000");
        fluentFindAndTypeDate(this.getClass(), deliveryDate,"2099-08-22");
        fluentFindAndTypeDate(this.getClass(), lastDate,"2099-08-21");
        fluentFindAndClick(this.getClass(), buyReason);

        fluentFindAndClick(this.getClass(), idSubmit);

        fluentFindReturnMessage();

        Logger.log("合同登记成功！");
    }
    /**
     * 新增空间
     *
     * @author LiuXingHai
     */
    public void addSpace() throws InterruptedException {

        fluentFindAndClick(this.getClass(), addSpace);
        fluentFindAndSelectByValue(this.getClass(), parentSpaceType, StoreConstants.PARENT_SPACE_TYPE);
        fluentFindAndSelectByValue(this.getClass(), spaceType, StoreConstants.SPACE_TYPE);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        Logger.log("新增空间成功！");
    }
    /**
     * 新增订单
     *
     * @author LiuXingHai
     */
    public void addOrder() throws InterruptedException {

        fluentFindAndClick(this.getClass(), addOrder);

        //不知道什么原因（可能是前端用的插件导致的）这里的input框直接sendKeys会报错，所以换成设置属性
        fluentFindAndSetAttribute(this.getClass(), prodInfo,"value","自动测试");

        //不知道什么原因（可能是前端用的插件导致的）这里的select框直接点击会报错，所以换成设置属性
        fluentFindAndSetSelectedByValue(this.getClass(), tagName, StoreConstants.TAG_NAME);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        String orderNo = fluentFind(this.getClass(), this.orderNo, null).getText().trim();
        StoreConstants.setOrderNoS(orderNo,"订单号");
        Logger.log("新增订单["+orderNo+"]成功！");
    }
    /**
     * 上传建单文件
     *
     * @author LiuXingHai
     */
    public void uploadCreateOrderFile() throws InterruptedException {

        fluentFindAndClick(this.getClass(), uploadCreateOrderFile);

        fluentFindAndTypeFile(this.getClass(), uploadInput,StoreConstants.CREATE_ORDER_FILE);

        fluentFind(this.getClass(), uploadSucceed,null);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        fluentFindAndClick(this.getClass(), submitReview);

        confirm();

        fluentFind(StoreMenu.class, "messageDisappear",null);//上一步提交后会刷新页面，这里等刷新的弹出框消失
        fluentFindAndClick(this.getClass(), pipelineInfo);

        Logger.log("上传建单文件成功！");
    }

}
