package com.fifedu.util.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**@author Yangtse
 * @version 1.0 2016-11-22 14:49
 * 读取excel工具类**/
public class ExcelUtil {
	
	/**Excel2003版**/
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	/**Excel2007或2010版**/
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    /**空字符**/
    public static final String EMPTY = "";
    /**点**/
    public static final String POINT = ".";
	
	
	
	public static void main(String[] args) throws IOException {
		String path = "J:/cet4-12-201506.xlsx";
//		String path = "J:/wei.xlsx";
		List<List<String>> result = readExcel(path);
		for (int i = 0; i < result.size(); i++) {
//			if (i == 1000) {
//				break;
//			}
			List<String> lst = result.get(i);
			System.out.print("第"+i+"行：");
			for (int j = 0; j < lst.size(); j++) {
				System.out.print(lst.get(j)+"  ");
			}
			System.out.println();
		}
	}
	
	
	/**读取excel中所有数据到List集合中**/
	public static List<List<String>> readExcel(String path) throws IOException{
		List<List<String>> result = new ArrayList<List<String>>();
		if (path == null || EMPTY.equals(path)) {
			return null;
		}else{
			//获取文件后缀名
			String postfix = getPostfix(path);
			//不为空时
			if (!EMPTY.equals(postfix)) {
				if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {//excel2003调用方法
					result = readXls(path);
				}else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {//excel2007和2010调用方法
					result = readXlsx(path);
				}else {
					result = null;
				}
			}else{
				result = null;
			}
		}
		return result;
	}
	
	
	
	
	

	/**读取后缀.xls的excl表格**/
	private static List<List<String>> readXls(String path) throws IOException{
		List<List<String>> result = new ArrayList<List<String>>();
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorbook = new HSSFWorkbook(is);
		//循环每一页，并处理当前循环页
		for (int numSheet = 0; numSheet < hssfWorbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorbook.getSheetAt(numSheet);//HSSFSheet表示excel表格的某一页
			if (hssfSheet == null) {
				continue;
			}
			//处理当前页，循环读取每一行
			for (int rowNum = 1; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);//HSSFRow表示excel表格的行
				
				int minColIx = hssfRow.getFirstCellNum();
				int maxColIx = hssfRow.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				
				for (int colIx = minColIx; colIx < maxColIx; colIx++) {
					HSSFCell cell = hssfRow.getCell((short)colIx);//HSSFCell表示excel表格的列
					if (cell == null) {
						continue;
					}
					rowList.add(getStringVal(cell));
				}
				result.add(rowList);
			}
		}
		
		return result;
	}
	
	
	
	
	/**读取后缀.xlsx的excl表格**/
	private static List<List<String>> readXlsx(String path) throws IOException{
		List<List<String>> result = new ArrayList<List<String>>();
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorbook = new XSSFWorkbook(is);
		//循环每一页，并处理当前循环页
		for (int numSheet = 0; numSheet < xssfWorbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorbook.getSheetAt(numSheet);//XSSFSheet表示excel表格的某一页
			if (xssfSheet == null) {
				continue;
			}
			//处理当前页，循环读取每一行
			for (int rowNum = 0; rowNum < xssfSheet.getLastRowNum()+1; rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);//XSSFRow表示excel表格的行
				
				int minColIx = xssfRow.getFirstCellNum();
				int maxColIx = xssfRow.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				
				for (int colIx = minColIx; colIx < maxColIx; colIx++) {
					XSSFCell cell = xssfRow.getCell(colIx);//XSSFCell表示excel表格的列
					if (cell == null) {
						continue;
					}
					rowList.add(cell.toString());
				}
				result.add(rowList);
			}
		}
		return result;
	}
	
	
	
	
	
	/**对excel单元格参数进行格式化**/
	public static String getStringVal(HSSFCell cell){
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_NUMERIC:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return "";
		}
	}
	
	
	/**获取excel的后缀（不包含.）**/
	public static String getPostfix(String path) {
        if (path == null || EMPTY.equals(path.trim())) {
            return EMPTY;
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT) + 1, path.length());
        }
        return EMPTY;
    }
	
	
	
}
