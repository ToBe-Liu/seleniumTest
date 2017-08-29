package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店文件上传页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class FileUploadPage extends StoreMenu {

    @FindBy(xpath="//label[@class='radio radio-inline']/span",description = "文件类型")
    String fileType="fileType";//

    @FindBy(id="dwy_file_upload",description = "文件上传输入框")
    String uploadInput="uploadInput";//

    @FindBy(xpath="//label[@id='label-upload-file' and text()='上传成功 ']",description = "文件上传成功")
    String uploadSucceed="uploadSucceed";//注意上传成功后面有一个空格

    @FindBy(xpath="//button[@type='submit']",description = "确认添加")
    String submit="submit";//

    public FileUploadPage(WebDriver driver){
        super(driver);
    }

    /**
     * 上传文件
     *
     * @param fileType 文件类型
     * @param filePath 上传文件路径
     * @author LiuXingHai
     */
    public void uploadFile(String fileType,String filePath) throws InterruptedException {

        fluentFindAndClickByText(this.getClass(),this.fileType,fileType);

        fluentFindAndTypeFile(this.getClass(), uploadInput,filePath);

        fluentFind(this.getClass(), uploadSucceed,null);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();
    }
}
