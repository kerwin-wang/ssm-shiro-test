package com.kerwin.shiro.test.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具
 *
 * @ClassName: ExcelUtil
 * @version: v1.0.0
 * @Author: d.w
 * @Date: 2019-07-23 14:38
 */
@Slf4j
public class ExcelUtil
{
    private static ExcelUtil excelUtil;

    private static final String EXTEND = "xls";

    static
    {
        initConstruct();
    }

    private static void initConstruct()
    {
        excelUtil = new ExcelUtil();
    }

    private ExcelUtil()
    {
    }

    /**
     * 导出Excel到文件
     *
     * @param mapList  数据集合
     * @param map      表头集合
     * @param filePath 导出地址
     * @auther: d.w
     * @date: 2019-07-23 04:03
     */
    public static void expExcel(List<Map<String, Object>> mapList, Map<String, String> map, String filePath)
    {
        excelUtil.doExpExcel(mapList, map, filePath, null, null);
    }

    /**
     * 导出Excel到web
     *
     * @param mapList  数据集合
     * @param map      表头集合
     * @param response web HttpServletResponse
     * @param fileName 文件名
     * @auther: d.w
     * @date: 2019-07-23 04:05
     */
    public static void expExcel(List<Map<String, Object>> mapList, Map<String, String> map, HttpServletResponse response, String fileName)
    {
        excelUtil.doExpExcel(mapList, map, null, response, fileName);
    }

    /**
     * 处理Excel导出
     *
     * @param mapList  数据集合
     * @param map      表头集合
     * @param filePath 导出地址
     * @param response web HttpServletResponse
     * @param fileName 文件名
     * @auther: d.w
     * @date: 2019-07-23 04:02
     */
    private void doExpExcel(List<Map<String, Object>> mapList, Map<String, String> map, String filePath, HttpServletResponse response,
            String fileName)
    {
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 将传进的map转换为linkedHashMap，目的为确定排序
        Map<String, String> linkedMap = new LinkedHashMap<>(map);
        HSSFSheet dataSheet = fileTitle(workbook, linkedMap);
        fillContent(mapList, linkedMap, dataSheet);
        if (filePath != null && response == null)
        {
            writeToFile(filePath, workbook);
        }
        else if (filePath == null && response != null && fileName != null)
        {
            writeToResponse(response, fileName, workbook);
        }
    }

    /**
     * 填充表头
     *
     * @param workbook  Excel文件对象
     * @param linkedMap 表头map
     * @auther: d.w
     * @date: 2019-07-23 03:59
     */
    private HSSFSheet fileTitle(HSSFWorkbook workbook, Map<String, String> linkedMap)
    {
        CellStyle style = getTitleCellStyle(workbook);

        HSSFSheet dataSheet = workbook.createSheet("dataSheet");
        dataSheet.setDefaultColumnWidth(20);
        HSSFRow firstRow = dataSheet.createRow(0);
        int cellNum = 0;
        for (String mapName : linkedMap.keySet())
        {
            firstRow.createCell(cellNum).setCellValue(linkedMap.get(mapName));
            firstRow.getCell(cellNum).setCellStyle(style);
            cellNum++;
        }
        return dataSheet;
    }

    /**
     * 设置标题格式
     *
     * @param workbook 工作表
     * @auther: d.w
     * @date: 2019-07-23 04:44
     */
    private CellStyle getTitleCellStyle(HSSFWorkbook workbook)
    {
        // 创建单元格格式
        CellStyle style = workbook.createCellStyle();
        // 创建单元格字体格式
        HSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short) 14);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setFont(font);
        return style;
    }

    /**
     * 向Excel中填充数据
     *
     * @param mapList   数据集合
     * @param linkedMap 标题map
     * @param dataSheet 工作表
     * @auther: d.w
     * @date: 2019-07-23 03:56
     */
    private void fillContent(List<Map<String, Object>> mapList, Map<String, String> linkedMap, HSSFSheet dataSheet)
    {
        // 第一行为标题已经确定，数据填充从第二行开始
        int rowNum = 1;
        for (Map<String, Object> dataMap : mapList)
        {
            HSSFRow row = dataSheet.createRow(rowNum);
            int columnNum = 0;
            for (String keyMap : linkedMap.keySet())
            {
                if (dataMap.get(keyMap) == null)
                {
                    row.createCell(columnNum).setCellValue("--");
                }
                else
                {
                    row.createCell(columnNum).setCellValue(dataMap.get(keyMap).toString());
                }
                columnNum++;
            }
            rowNum++;
        }
    }

    /**
     * 导出Excel到文件
     *
     * @param filePath Excel导出地址
     * @param workbook Excel文件对象
     * @auther: d.w
     * @date: 2019-07-23 03:52
     */
    private void writeToFile(String filePath, HSSFWorkbook workbook)
    {
        OutputStream outputStream = null;
        try
        {
            String extend = filePath.substring(filePath.lastIndexOf(".") + 1);
            if (!EXTEND.equals(extend))
            {
                return;
            }
            File file = new File(filePath);
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        }
        catch (IOException e)
        {
            log.error("导出Excel到文件异常 error[{}]", e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导出Excel到web response
     *
     * @param response HttpServletResponse
     * @param fileName 导出Excel名
     * @param workbook Excel文件对象
     * @auther: d.w
     * @date: 2019-07-23 03:52
     */
    private void writeToResponse(HttpServletResponse response, String fileName, HSSFWorkbook workbook)
    {
        OutputStream outputStream = null;
        try
        {
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        }
        catch (IOException e)
        {
            log.error("导出Excel到web response异常 error[{}]", e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
