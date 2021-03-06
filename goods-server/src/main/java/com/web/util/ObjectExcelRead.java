package com.web.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 从EXCEL导入到数据库
 * 创建人：FH 创建时间：2014年12月23日
 * @version
 */
public class ObjectExcelRead {

	/**
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	@SuppressWarnings("deprecation")
	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();
		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			HSSFWorkbook wb = new HSSFWorkbook(fi);
			HSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号
			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				Map<String,Object> varpd=new HashMap<>();
				HSSFRow row = sheet.getRow(i); 	//行
				if(StringUtil.isNull(row)) {
					continue;
				}
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置
				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					
					HSSFCell cell = row.getCell(Short.parseShort(j + ""));
					DecimalFormat df=new DecimalFormat("#");
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case HSSFCell.CELL_TYPE_NUMERIC:
							cellValue=df.format(cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue ="";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
					} else {
						cellValue = "";
					}
					
					varpd.put("var"+j, cellValue);
					
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return varList;
	}
	
	public static void main(String[] args) {
		String filepath="C://Users//Administrator//Desktop";
		String filename="user.xls";
		int startrow=0;
		int startcol=0;
		int sheetnum=0;
		readExcel(filepath, filename, startrow, startcol, sheetnum);
	}
	
	/**
    *基本的读取excel获取每行每列数据
    * @throws InvalidFormatException 
    */
   public static void poiReadExcel(File file) throws IOException, InvalidFormatException{
       //创建文件输入流对象
       InputStream is = new FileInputStream(file);
       //创建 POI文件系统对象
       Workbook wb = WorkbookFactory.create(is);
       //获取工作薄
       Sheet sheet = wb.getSheetAt(0);
       //声明行对象
       Row row = null;
       //通过循环获取每一行
       for (int i = 0; sheet.getRow(i)!=null; i++) {
           row = sheet.getRow(i);
           //循环获取一行的中列
           String str="";
           for (int j = 0; row.getCell(j)!=null; j++) {
               str+=row.getCell(j).toString()+",";
           }
           System.out.println(str);
       }
   }
}
