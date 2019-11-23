package com.airplane.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.airplane.po.Registration;


public class FillExcelDataWithTemplate {
	
	public static Workbook fillExcelDataWithTemplate(List<Registration> list ,String templateFileUrl) {
		
		Workbook wb = null ;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFileUrl));
			 wb = new HSSFWorkbook(fs);
			// 取得 模板的 第一个sheet 页
			Sheet sheet = wb.getSheetAt(0);
			// 拿到sheet页有 多少列
			int cellNums = sheet.getRow(0).getLastCellNum();
			// 从第2行 开搞    下标1  就是第2行
			int rowIndex = 1;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Row row ; 
			for(Registration registration : list){
				row = sheet.createRow(rowIndex);
				rowIndex ++;
				row.createCell(0).setCellValue(registration.getLocationnumber());
				row.createCell(1).setCellValue(registration.getAirline());
				row.createCell(2).setCellValue(registration.getFlightno());
				row.createCell(3).setCellValue(registration.getPlaneno());
				row.createCell(4).setCellValue(registration.getNo());
				row.createCell(5).setCellValue(registration.getContent());
				row.createCell(6).setCellValue(formatter.format(registration.getStarttime()));
				if(registration.getStoptime()!=null){
					row.createCell(7).setCellValue(formatter.format(registration.getStoptime()));
				}
				row.createCell(8).setCellValue(registration.getElapsedtime());
				row.createCell(9).setCellValue(registration.getMark());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

}
