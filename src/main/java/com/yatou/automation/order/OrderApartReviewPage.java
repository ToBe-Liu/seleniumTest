package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台拆单审核页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderApartReviewPage extends OrderMenu {

    @FindBy(linkText="标记为拆单审核中",description = "标记为拆单审核中")
    String markApartReviewing="markApartReviewing";

    @FindBy(linkText="确定审核",description = "确定审核")
    String confirmApartReview="confirmApartReview";



    public OrderApartReviewPage(WebDriver driver){
        super(driver);
    }
    /**
     * 标记为拆单审核中
     *
     * @param orderNo 订单号
     * @author LiuXingHai
     */
    public void markApartReviewing(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), markApartReviewing);

        confirm();

        Logger.log("标记["+orderNo+"]为拆单审核中成功！");
    }
    /**
     * 确定审核
     *
     * @param orderNo 订单号
     * @author LiuXingHai
     */
    public void confirmApartReview(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), confirmApartReview);

        confirm();

        Logger.log("确定审核["+orderNo+"]成功！");
    }
}
