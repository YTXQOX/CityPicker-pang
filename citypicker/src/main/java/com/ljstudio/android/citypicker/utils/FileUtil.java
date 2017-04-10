package com.ljstudio.android.citypicker.utils;


import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FileUtil {

	//Save Local FilePath
	public static File getSDCardFolderPath(String filePathName) {
		File folderFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
				+ "Launch" + File.separator
				+ "Hesvit" + File.separator
				+ "Health" + File.separator
				+ filePathName
				+ File.separator);
		return folderFile;
	}
	
	//get FilePath 
	public static String getSDCardFilePath(String filePathName, String fileName) {
		File file = new File(getSDCardFolderPath(filePathName), fileName);
		return file.getAbsolutePath();
	}
	
	//create File
	public static String createFile2SD(String strFilePathName, String strFileName) throws IOException {
		//创建文件夹
		File filePath = getSDCardFolderPath(strFilePathName);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		// 创建文件
//		String file = filePath.getAbsolutePath() + File.separator + strFileName;
//		File f = new File(file);
		File f = new File(filePath.getAbsolutePath(), strFileName); 
		if (!f.exists()) {
			f.createNewFile();
		}

		return f.getAbsolutePath();
	}
	
	//isExist File 
	public static boolean isFileExist(String filePathName, String fileName) {
		File file = new File(getSDCardFolderPath(filePathName), fileName);
		return file.exists();
	}
	
	//
	public static boolean saveFile2SD(Context context, byte[] fileByte, String strFilePathName, String strFileName) throws IOException {
		boolean b = false;
		
		// 创建文件夹
		File filePath = getSDCardFolderPath(strFilePathName);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		// 创建文件
		String file = filePath.getAbsolutePath() + File.separator + strFileName;
		File f = new File(file);
		if (!f.exists()) {
			f.createNewFile();
		}

		// 字节输出流  文件写入SD卡
		FileOutputStream fos = new FileOutputStream(f);
		// 字节流
		InputStream inStream = new ByteArrayInputStream(fileByte);
		// 字符流
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream, "gb2312")); //防止中文出现乱码  gb2312 
		// FileInputStream fis = (FileInputStream)inStream;

		long max = fileByte.length;
		try {
			byte[] buffer = new byte[1024];
			int length = 0;

			while (true) {
				//InputStream 字节流
				int temp = inStream.read(buffer, 0, buffer.length);
				if (temp == -1) {
					break;
				}
				fos.write(buffer, 0, temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
				fos.close();

//				String strDownloadFilePath = getSDCardFile(strFilePathName, strFileName);
//				Toast.makeText(context, "文件已保存至: " + strDownloadFilePath, Toast.LENGTH_LONG).show();
				
				b = true;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return b;
	} // -end of _method saveFile2SD()_ by LJ.
	
	
}




