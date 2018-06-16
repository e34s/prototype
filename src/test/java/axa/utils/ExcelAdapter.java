/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ExcelAdapter {


	@DataProvider(name = "datamap", parallel = false)
	public Object[][] dataSupplier() throws IOException {
//		File file = new File("C:\\Users\\mpalotas\\IdeaProjects\\axaprototype\\src\\test\\resources\\motorfahrzeug.xlsx");
		//File file = new File("C:\\Users\\mpalotas\\IdeaProjects\\axaprototype\\src\\test\\resources\\Testfaelle_Motorfahrzeug-single.xlsx");
		File file = new File("C:\\Users\\mpalotas\\IdeaProjects\\axaprototype\\src\\test\\resources\\Testfaelle_Motorfahrzeug_V2.xlsx");
		//File file = new File("C:\\Users\\mpalotas\\IdeaProjects\\axaprototype\\src\\test\\resources\\Testfaelle_Motorfahrzeug_V3.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		wb.close();
		int lastRowNum = sheet.getLastRowNum() ;
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];
		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<>();
			System.out.println("ROW: " + i);
			for (int j = 0; j < lastCellNum; j++) {
				System.out.println("COLUMN:" + j);

				XSSFCell cell =  sheet.getRow(i+1).getCell(j);

				if(cell.getCellTypeEnum() == CellType.NUMERIC) {
					//System.out.println(cell.getCellTypeEnum());

					//if cell is a date, format it in dd.mm.yyyy
					if(HSSFDateUtil.isCellDateFormatted(cell)) {
						System.out.println("BEFORE: " + cell.getDateCellValue());
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						String date = formatter.format(cell.getDateCellValue());
						System.out.println("AFTER:" + date );
						cell.setCellValue(date.toString());
					}

				}

				//TODO: not working yet
				else {
					//check if formula (vlookup)
					if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
						System.out.println("FORMULA is: " + cell.getCellFormula());
						switch(cell.getCachedFormulaResultType()) {
							case Cell.CELL_TYPE_NUMERIC:
								System.out.println("MUMERIC - Last evaluated as: " + cell.getNumericCellValue());
								double value = cell.getNumericCellValue();
								cell.setCellValue(value);
								break;
							case Cell.CELL_TYPE_STRING:
								System.out.println("STRING - Last evaluated as" + cell.getRichStringCellValue());
								cell.setCellValue(cell.getRichStringCellValue());
								break;
						}
					}
				}

				datamap.put(sheet.getRow(0).getCell(j).toString(), cell.toString());
//				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
			}
			obj[i][0] = datamap;
		}
		return  obj;
	}
}