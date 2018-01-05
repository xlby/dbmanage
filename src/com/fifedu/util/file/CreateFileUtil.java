package com.fifedu.util.file;


import java.io.File;
import java.io.IOException;


public class CreateFileUtil {

	public static boolean CreatDir2(String destFileName) {
		File file = new File(destFileName);
		if (!file.getParentFile().exists()) {
			//System.out.println("Target File Path does not exist,Preparing to Create...目标文件所在路径不存在，准备创建。。。");
			System.out.println("Target File Path does not exist,Preparing to Create...");

			if (!file.getParentFile().mkdirs()) {
				System.out.println("Create File Directory Failure!");
			//	System.out.println("Create File Directory Failure!创建目录文件所在的目录失败！");

				return false;
			}
		}
		return true;
	}

	public static boolean CreateFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			System.out.println("Creating a single file " + destFileName + " Failure,File already exists!");
//			System.out.println("Creating a single file 创建单个文件" + destFileName + "Failure,File already exists!失败，目标文件已存在！");

			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			System.out.println("Creating a single file " + destFileName + " Failure,Target can not be a directory!");
		//	System.out.println("Creating a single file 创建单个文件" + destFileName + "Failure,Target can not be a directory!失败，目标不能是目录！");

			return false;
		}
		if (!file.getParentFile().exists()) {
			System.out.println("Target File Path does not exist,Preparing to Create...");
			//System.out.println("Target File Path does not exist,Preparing to Create...目标文件所在路径不存在，准备创建。。。");

			if (!file.getParentFile().mkdirs()) {
				System.out.println("Create File Directory Failure!");
			//	System.out.println("Create File Directory Failure! 创建目录文件所在的目录失败！");

				return false;
			}
		}

		// 创建目标文件
		try {
			if (file.createNewFile()) {
				System.out.println("Creating a single file " + destFileName + "Success!");
				return true;
			} else {
				System.out.println("Creating a single file " + destFileName + "Failure!");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Creating a single file " + destFileName + "Failure!");
			return false;
		}
	}

	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			System.out.println("Create File Directory " + destDirName + " Failure,File already exists!");
		//	System.out.println("创建目录" + destDirName + " 失败，目标目录已存在！");

			return false;
		}
		if (!destDirName.endsWith(File.separator))
			destDirName = destDirName + File.separator;
		// 创建单个目录
		if (dir.mkdirs()) {
			System.out.println("Creating a single file " + destDirName + "Success!");
//			System.out.println("创建单个文件 " + destDirName + "成功!");

			return true;
		} else {
			System.out.println("Creating a single file " + destDirName + "Failure!");
		//	System.out.println("创建单个文件 " + destDirName + "失败!");

			return false;
		}
	}

	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		try {
			if (dirName == null) {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				return tempFile.getCanonicalPath();
			} else {
				File dir = new File(dirName);
				// 如果临时文件所在目录不存在，首先创建
				if (!dir.exists()) {
					if (!CreateFileUtil.createDir(dirName)) {
						System.out.println("Create temp File Failure,Can not Createing File Temp Directory!");
					//	System.out.println("创建临时文件失败，不能创建临时文件所在目录！");
						return null;
					}
				}
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Create temp File Failure" + e.getMessage());
			//System.out.println("创建临时文件失败" + e.getMessage());

			return null;
		}
	}
}
