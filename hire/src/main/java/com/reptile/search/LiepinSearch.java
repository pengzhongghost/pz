package com.reptile.search;

import com.reptile.pojo.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class LiepinSearch {
    private static List<Job> jobList=new ArrayList<>();

    public static List<Job> getJobs(){
        //设置webdriver路径
        System.setProperty("webdriver.chrome.driver","E:/BaiduNetdiskDownload/spring复习/hire/src/main/resources/chromedriver.exe");
        //创建webdriver
        WebDriver webDriver=new ChromeDriver();
        webDriver.get("https://search.51job.com/list/070300,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=");
//        WebElement chosen1Element = webDriver.findElement(By.xpath("//div[@class='dw_recommend']//a[contains(text(),'Java 实习')]"));
//        chosen1Element.click();
        //通过xpath选中元素进行点击
        List<Job> jobList = extractJobsByPages(webDriver);

        return jobList;
    }
    private static List<Job> extractJobsByPages(WebDriver webDriver) {
        Job job = null;
        List<WebElement> jobNames = webDriver.findElements(By.className("t1"));
        List<WebElement> company_names = webDriver.findElements(By.className("t2"));
        List<WebElement> locations = webDriver.findElements(By.className("t3"));
        List<WebElement> moneys = webDriver.findElements(By.className("t4"));

        for (int i = 0; i < jobNames.size(); i++) {
            String jobName=jobNames.get(i).getText();
            String companyName=company_names.get(i).getText();
            String money=moneys.get(i).getText();
            String location=locations.get(i).getText();
            job = new Job();
            if ("职位名".equals(jobName)&&"公司名".equals(companyName)&&"薪资".equals(money)&&"工作地点".equals(location)){
            }else {
                job.setJobName(jobNames.get(i).getText());
                job.setCompanyName(company_names.get(i).getText());
                job.setMoney(moneys.get(i).getText());
                job.setLocation(location);
            }
            if (job.getCompanyName()==null&&job.getMoney()==null&&job.getJobName()==null&&job.getLocation()==null){
            }else {
                jobList.add(job);
            }

        }


        WebElement pagerNext = webDriver.findElement(By.id("rtNext"));
        if (pagerNext.getAttribute("class").contains("dicon Dm on")){
            pagerNext.click();
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            extractJobsByPages(webDriver);

        }
        return jobList;
    }

}

