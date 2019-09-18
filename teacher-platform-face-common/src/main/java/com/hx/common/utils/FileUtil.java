package com.hx.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

public class FileUtil {

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	public static boolean delFileAndDir(String file) {
		return delFileAndDir(new File(file));
	}
	public static boolean delFileAndDir(File file) {
		if (!file.exists()) {
			return false;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				delFileAndDir(f);
			}
		}
		return file.delete();
	}
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String renameToUUID(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static String createRelativePathByYearMonth(String uploadPath,String relativeFile) {
		if (StringUtils.isEmpty(relativeFile) || StringUtils.isEmpty(uploadPath)){
			return null;
		}
		Calendar instance = Calendar.getInstance();
		String year = String.valueOf(instance.get(Calendar.YEAR));

		String month = String.valueOf(instance.get(Calendar.MONTH) + 1);
		month = month.length() < 2 ? "0"+month : month;
		File targetFile = new File(new File(uploadPath, year), month);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		return new StringBuffer().append("/").append(year).append("/").append(month).append("/").append(relativeFile).toString();
	}

	/**
	 *
	 * @return /year/month/
	 */
	public static String getYearMonthPath(){
		Calendar instance = Calendar.getInstance();
		String year = String.valueOf(instance.get(Calendar.YEAR));

		String month = String.valueOf(instance.get(Calendar.MONTH) + 1);
		month = month.length() < 2 ? "0"+month : month;
		return "/"+year+"/"+month+"/";
	}
}
