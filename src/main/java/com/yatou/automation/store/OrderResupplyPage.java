package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店订单补单页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderResupplyPage extends StoreMenu {

    @FindBy(linkText="新建补单",description = "新建补单")
    String createOrderSupply="createOrderSupply";//

    @FindBy(xpath="//label[@class='radio radio-inline']/span",description = "增补类型")
    String orderSupplyType="orderSupplyType";//

    @FindBy(name="describe",description = "问题反馈")
    String describe="describe";

    @FindBy(name="quantity",description = "补单数量以及规格")
    String quantity="quantity";

    @FindBy(xpath="//form[@id='add-resupply']//button[@type='submit']",description = "确认添加")
    String submit="submit";//

    @FindBy(xpath="//td[text()='流水号']/following-sibling::*[1]",description = "补单号")
    String orderResupplyNo="orderResupplyNo";//新建补单之后生成的补单号

    @FindBy(linkText="文件管理",description = "文件管理")
    String uploadCreateOrderSupplyFile="uploadCreateOrderSupplyFile";//

    @FindBy(linkText="提交审核", description = "提交审核")
    String submitReview="submitReview";

    public OrderResupplyPage(WebDriver driver){
        super(driver);
    }
    /**
     * 新建补单
     *
     * @param orderNo 订单号
     * @author LiuXingHai
     */
    public void createOrderSupply(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), createOrderSupply);

        fluentFindAndClickByText(this.getClass(), this.orderSupplyType, StoreConstants.ORDER_RESUPPLY_TYPE);

        fluentFindAndType(this.getClass(),describe,"自动测试");

        fluentFindAndType(this.getClass(),quantity,"自动测试");

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        String orderResupplyNo = fluentFind(this.getClass(), this.orderResupplyNo, null).getText().trim();

        StoreConstants.setOrderResupplyNoS(orderResupplyNo,"补单号");

        Logger.log("订单[" + orderNo + "]新建补单[" + orderResupplyNo + "]成功！");
    }
    /**
     * 上传建单文件
     *
     * @param orderResupplyNo 补单号
     * @author LiuXingHai
     */
    public void uploadCreateOrderSupplyFile(String orderResupplyNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), uploadCreateOrderSupplyFile);

        new FileUploadPage(threadDriver.get()).uploadFile("建单文件",StoreConstants.CREATE_ORDER_FILE);

        fluentFindAndClick(this.getClass(), submitReview);

        confirm();

        Logger.log("补单[" + orderResupplyNo + "]上传建单文件成功！");
    }

}
