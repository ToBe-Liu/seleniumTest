package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店补单确认页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderResupplyConfirmPage extends StoreMenu {

    @FindBy(linkText="加盟确认", description = "加盟确认")
    String confirm="confirm";

    public OrderResupplyConfirmPage(WebDriver driver){
        super(driver);
    }


    /**
     * 补单加盟确认
     *
     * @param orderResupplyNo 补单号
     * @author LiuXingHai
     */
    public void OrderSupplyconfirm(String orderResupplyNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), confirm);

        confirm();

        Logger.log("补单[" + orderResupplyNo + "]加盟确认成功！");
    }
}
