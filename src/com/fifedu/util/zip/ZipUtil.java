package com.fifedu.util.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.training.commons.file.FileUtils;

import nochump.util.extend.ZipOutput;
public class ZipUtil {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//zipFilesWithPwd("D:/tmp/t/","D:/tmp/1111.zip","111");
		try {
			zipFiles("J:/DevelopTool/Workspaces/Resource/Exam/20161120/a72d41028cc04078b7926a802ddb8f7e/","D:/tmp/1111.zip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
    *
    * @param path 文件夹路径
    * @param returnFileName
    * @throws IOException
    */
   public static void zipFiles(String path, String returnFileName)
           throws IOException {
       File inFile = new File(path);
       ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
               returnFileName));
       zos.setComment("多文件处理");
       zipFile(inFile, zos, "");
       zos.close();
   }

   /*
    * 压缩时候加密..
    */
   public static void zipFilesWithPwd(String zipDir, String zipName,
           String password) {
       File file = new File(zipDir);
       byte[] zipByte = ZipOutput
               .getEncryptZipByte(file.listFiles(), password);

       FileUtils.writeByteFile(zipByte, new File(zipName));

   }

   public static void zipFile(File inFile, ZipOutputStream zos, String dir)
           throws IOException {

       if (inFile.isDirectory()) {
           File[] files = inFile.listFiles();
           for (File file : files)
               zipFile(file, zos, inFile.getName());
       } else {
           String entryName = null;
           if (!"".equals(dir))
               entryName = inFile.getName();
           else
               entryName = inFile.getName();

           ZipEntry entry = new ZipEntry(entryName);
           zos.putNextEntry(entry);
           InputStream is = new FileInputStream(inFile);
           int len = 0;
           while ((len = is.read()) != -1)
               zos.write(len);
           is.close();
       }

   }

   public static void deleteFile(File file) {
       if (file.exists()) { // 判断文件是否存在
           if (file.isFile()) { // 判断是否是文件
               file.delete(); // delete()方法 你应该知道 是删除的意思;
           } else if (file.isDirectory()) { // 否则如果它是一个目录
               File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
               for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                   deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
               }
           }
           file.delete();
       } else {
           System.out.println("所删除的文件不存在！" + '\n');
       }
   }

}
