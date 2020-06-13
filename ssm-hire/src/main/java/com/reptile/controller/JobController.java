package com.reptile.controller;


import com.reptile.pojo.Job;
import com.reptile.service.JobService;
import com.reptile.util.PageBean;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;


@Controller
@RequestMapping("/JobController")
public class JobController {
    @Autowired
    private JobService jobService;
    @RequestMapping("/showJobs")
    public String showJobs(Model model, String index1){
        Integer index=1;
        if (index1!=null){
            index=Integer.parseInt(index1);
        }
        PageBean<Job> pageBean=jobService.select(index);
       model.addAttribute("pageBean",pageBean);

        return "/jobs";
    }

    @RequestMapping("/insert")
    public String insert(){
        jobService.insert();
        return "redirect:/JobController/showJobs";
    }

    @RequestMapping("/excelCreate")
    public void excelCreate(HttpServletResponse resp, String index1){
        Integer index=1;
        PageBean<Job> pageBean=null;
        if (index1!=null){
            index=Integer.parseInt(index1);
            pageBean=jobService.select(index);
            createExcel(pageBean.getList(),resp);
        }else {
            List<Job> jobList=jobService.selectAll();
            createExcel(jobList,resp);
        }

    }
    /**
     * 创建Excel
     *
     * @param list
     *            数据
     */
    private static void createExcel(List<Job> list, HttpServletResponse resp) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("招聘信息表");

        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                2 // last column
        );
        sheet.addMergedRegion(region);

        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("招聘信息表");

        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        /*

        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       // cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);

        HSSFFont font = workbook.createFont();
        font.setFontName("楷体"); //字体
        font.setFontHeightInPoints((short)30); //字体大小
        font.setColor(HSSFColor.RED.index);//颜色
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        font.setItalic(true); //倾斜
        cellStyle.setFont(font);
        */
        headCell.setCellStyle(cellStyle);


        // 添加表头行
        hssfRow = sheet.createRow(1);
        // 添加表头内容
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("ID");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("职务名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("薪水");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("公司名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("地址");
        headCell.setCellStyle(cellStyle);
        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Job job= list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(job.getId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(job.getJobName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(job.getMoney());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(3);
            cell.setCellValue(job.getCompanyName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(job.getLocation());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-disposition", "attachment; fileName=" + new String(("work.xls").getBytes(), "iso8859-1"));
            OutputStream outputStream = resp.getOutputStream();

            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

