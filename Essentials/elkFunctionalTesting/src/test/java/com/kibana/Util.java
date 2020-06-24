package com.kibana;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Util {
	public static  String takeSnapShot(WebDriver webdriver) throws Exception{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		//TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		File SrcFile = ((TakesScreenshot)(webdriver)).getScreenshotAs(OutputType.FILE);
		String fileWithPath = System.getProperty("user.dir") + "/Screenshots/"+dateName+".png";
		File DestFile=new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
		return fileWithPath;

	}
}
