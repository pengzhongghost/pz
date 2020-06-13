package com.reptile.search;

import com.reptile.pojo.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class LagouSearch {
    private static List<Job> jobList=new ArrayList<>();
    public static List<Job> getJobs(){
        //设置webdriver路径
        System.setProperty("webdriver.chrome.driver","E:/BaiduNetdiskDownload/spring复习/hire/src/main/resources/chromedriver.exe");
        //创建webdriver
        WebDriver webDriver=new ChromeDriver();
        webDriver.get("https://www.lagou.com/zhaopin/Java/?labelWords=label");

        //通过xpath选中元素进行点击

        WebElement chosen1Element = webDriver.findElement(By.xpath("//div[@class='body-container showData']//div[@class='body-box']//div[contains(text(),'给也不要')]"));
        chosen1Element.click();
        WebElement chosen1Element1 = webDriver.findElement(By.xpath("//div[@class='has-more']//div[@class='choose-detail']//div[@class='current-handle-position']//span[@class='title']"));
        WebElement optionElement1 = chosen1Element1.findElement(By.xpath("..//..//div[@class='other-hot-city']//div[@class='city-wrapper dn']//a[contains(text(),'苏州')]"));
        optionElement1.click();
        //clickOption(webDriver, "工作经验", "应届毕业生");
        //clickOption(webDriver, "融资阶段", "不限");
        //clickOption(webDriver, "学历要求", "硕士");

        //解析页面的元素
       return extractJobsByPages(webDriver);

    }


    private static List<Job> extractJobsByPages(WebDriver webDriver) {
        //List<Job> jobList=new ArrayList<>();
        Job job=null;
//        List<WebElement> jobElements = webDriver.findElements(By.className("con_list_item"));

            List<WebElement> moneys = webDriver.findElement(By.className("position")).findElements(By.className("money"));
            List<WebElement> companyNames =webDriver.findElements(By.className("company_name"));
        List<WebElement> jobNames=webDriver.findElement(By.className("position")).findElements(By.className("position_link"));
        List<WebElement> locations= webDriver.findElement(By.className("position")).findElements(By.className("add"));

        for (int i = 0; i < jobNames.size(); i++) {
            String jobName=jobNames.get(i).getText();
            String companyName=companyNames.get(i).getText();
            String money=moneys.get(i).getText();
            String location=locations.get(i).getText();
            job = new Job();
            if ("职位名".equals(jobName)&&"公司名".equals(companyName)&&"薪资".equals(money)&&"工作地点".equals(location)){
            }else {
                job.setJobName(jobNames.get(i).getText());
                job.setCompanyName(companyNames.get(i).getText());
                job.setMoney(moneys.get(i).getText());
                job.setLocation(location);
            }
            if (job.getCompanyName()==null&&job.getMoney()==null&&job.getJobName()==null&&job.getLocation()==null){
            }else {
                jobList.add(job);
            }

        }


            //System.out.println(company_name+"   "+moneyElement.getText());


        WebElement pagerNext = webDriver.findElement(By.className("next"));
        if (!pagerNext.getAttribute("class").contains("next ban")){
            pagerNext.click();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            extractJobsByPages(webDriver);

        }
            return jobList;

    }

    private static void clickOption(WebDriver webDriver, String str1, String str2) {
        WebElement chosenElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']//span[contains(text(),'" + str1 + "')]"));
        WebElement optionElement = chosenElement.findElement(By.xpath("../a[contains(text(),'" + str2 + "')]"));
        optionElement.click();
    }
}
