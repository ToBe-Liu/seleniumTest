package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台订单排料页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderSchedulePage extends OrderMenu {

    @FindBy(linkText="标记为排料中",description = "标记为排料中")
    String markScheduling="markScheduling";

    public OrderSchedulePage(WebDriver driver){
        super(driver);
    }
    /**
     * 标记为排料中
     *
     * @param orderNo 需要标记的订单号
     * @author LiuXingHai
     */
    public void markScheduling(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), markScheduling);

        confirm();

        Logger.log("标记["+orderNo+"]为排料中成功！");
    }

}
