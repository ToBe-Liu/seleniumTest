package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台订单审核页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderReviewPage extends OrderMenu {

    @FindBy(linkText="标记为审核中",description = "标记为审核中")
    String markReviewing="markReviewing";

    @FindBy(linkText="提交审核",description = "提交审核")
    String submitReview="submitReview";

    @FindBy(id="money",description = "审核总价")
    String reviewPrice="reviewPrice";

    @FindBy(className="layui-layer-btn0", description = "确定")
    String confirm="confirm";

    @FindBy(className="//div[contains(@class,'layui-layer')]//button[contains(text()[2],'审核提交')]", description = "审核提交")
    String submit="submit";

    @FindBy(xpath="//td/a[contains(@href,'/order/detail/')]",description = "订单号")
    String orderNo="orderNo";

    public OrderReviewPage(WebDriver driver){
        super(driver);
    }
    /**
     * 标记为审核中
     *
     * @param orderNo 需要标记的订单号
     * @author LiuXingHai
     */
    public void markReviewing(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), markReviewing);

        fluentFindAndClick(this.getClass(), confirm);

        fluentFindReturnMessage();
        //返回到订单详情页面以便下面的操作
        fluentFindAndClickByText(this.getClass(), this.orderNo,orderNo);

        Logger.log("标记["+orderNo+"]为审核中成功！");
    }
    /**
     * 提交审核
     *
     * @param orderNo 需要提交的订单号
     * @author LiuXingHai
     */
    public void submitReview(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), submitReview);

        fluentFindAndType(this.getClass(), reviewPrice,"10000");

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        Logger.log("提交审核["+orderNo+"]成功！");
    }
}
