package com.fifedu.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.fifedu.util.zip.ZipUtil;

public class FileUtil {
	/**图片格式**/
	private static String []  imgArr= {"jpg","jpeg","gif","png","bmp"};
	private static String imgFlag = ""; 
	/**题库系统音频资源路径**/
	private static String servers_root_url_media = "http://117.121.9.117/static/itest/upload/media/";
	/**题库系统图片资源路径**/
	private static String servers_root_url_image = "http://117.121.9.117/static/itest/upload/image/";
	/**冲刺宝系统模考资源包存放路径**/
	private static String local_root_path_p= "J:/DevelopTool/Workspaces/Resource/Exam/";
	/**冲刺宝系统专练包资源包存放路径**/
	private static String local_root_path_r = "J:/DevelopTool/Workspaces/Resource/Package/";
	
	/**冲刺宝系统118端资源包存放路径**/
	//private static String sys_root_path= "/home/ccb_data/";//http://117.121.9.118:8181/download/
	
	/**冲刺宝系统本地资源包存放路径**/
	private static String sys_root_path= "J:/DevelopTool/Workspaces/Resource/";

	
	
	/**模考-压缩zip文件存放根路径**/
	private static String resource_path_exam = "J:/resource/exam/";
	/**专练-压缩zip文件存放根路径**/
	private static String resource_path_package = "J:/resource/package/";
	/**设置压缩zip文件的密码**/
	private static String pwd = "ccb110";
	
	
	
	/**MP3、图片格式化路径，创建文件目录**/
	public static String FileCopyToLocal(String fileDriName,String paperId,String examType) throws Exception{
		//回传url
		String returnUrl = "";
		//按“/”切割，取文件名
		String [] dirArr = fileDriName.split("/");
		String fileName =dirArr[dirArr.length-1];
		
		//local_root_path.endsWith("/"));//判断字符串最后一个字符是否以某个字符来结尾
		//生成当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date currentDate = new Date();
		String strDate = sdf.format(currentDate);
		
		//判断服务器地址类型
		String url = "";
		//判断是否MP3格式
		if (fileName.toLowerCase().endsWith(".mp3")) {//大小转小写后再对比
			url = servers_root_url_media + fileDriName;
		//判断图片是否存在，并获取图片格式  fileName.toLowerCase().endsWith("."+imgFlag)
		}else if (findImage(fileName)) {
			url = servers_root_url_image + fileDriName;
			//临时标记，用完清空
			imgFlag = "";
		}
		//判断本地存储路径类型
		String localPath = "";
		if ("0".equals(examType)) {//模考
			localPath = local_root_path_p+strDate+"/"+paperId+"/";
		}else if ("1".equals(examType)) {//专练
			localPath = local_root_path_r+strDate+"/"+paperId+"/";
		}
		returnUrl = strDate+"/"+paperId+"/"+fileName;
		//必须指定考试类型才存音频到固定的文件夹
		if (examType != null) {
			creatDir(localPath);
			localPath = localPath + fileName;
			File file = new File(localPath);
			if (!file.exists()) {
				FileDownToLocal(url, localPath);
			}
		}
		
		return returnUrl;
	}
	
	
	
	/**MP3、图片格式化路径，创建文件目录(118端)**   参数：fileDriName：题库系统文件名称（格式：20161124/1305146161.MP3），paperId：试卷id，examType：试卷类型（0模考，1专练，2预览）,userType:使用类型（1应用apply，2预览view）**/
	public static String FileCopyToLocalSys(String fileDriName,String paperId,String examType,String userType) throws Exception{
		//回传url
		String returnUrl = "";
		//按“/”切割，取文件名
		String [] dirArr = fileDriName.split("/");
		String fileName =dirArr[dirArr.length-1];
		
		//local_root_path.endsWith("/"));//判断字符串最后一个字符是否以某个字符来结尾
		//生成当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date currentDate = new Date();
		String strDate = sdf.format(currentDate);
		//strDate = "20170331";
		
		//判断服务器地址类型
		String url = "";
		//判断是否MP3格式
		if (fileName.toLowerCase().endsWith(".mp3")) {//大小转小写后再对比
			url = servers_root_url_media + fileDriName;
		//判断图片是否存在，并获取图片格式  fileName.toLowerCase().endsWith("."+imgFlag)
		}else if (findImage(fileName)) {
			url = servers_root_url_image + fileDriName;
			//临时标记，用完清空
			imgFlag = "";
		}
		//判断本地存储路径类型
		String localPath = "";
		
		if ("0".equals(examType)) {//模考
			if("1".equals(userType)){//存放到应用的文件夹
				localPath = sys_root_path + "apply/exam/" + strDate + "/" + paperId + "/";
			}else if("2".equals(userType)){//存放到预览的文件夹下
				localPath = sys_root_path + "view/exam/" + strDate + "/" + paperId + "/";
			}
			returnUrl = "exam/" + strDate + "/" + paperId + "/" +fileName;
		}else if ("1".equals(examType)) {//专练
			if("1".equals(userType)){//存放到应用的文件夹
				localPath = sys_root_path + "apply/package/" +strDate+"/"+paperId+"/";
			}else if("2".equals(userType)){//存放到预览的文件夹下
				localPath = sys_root_path + "view/package/" +strDate+"/"+paperId+"/";
			}
			returnUrl = "package/" + strDate + "/" + paperId + "/" +fileName;
		}
		
		
		
		
		//必须指定考试类型才存音频到固定的文件夹
		if (examType != null) {
			creatDir(localPath);
			localPath = localPath + fileName;
			File file = new File(localPath);
			if (!file.exists()) {
				FileDownToLocal(url, localPath);
			}
		}
		
		return returnUrl;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**content：要生成json的试卷内容；paperId：试卷id；examType：试卷类型【0模考、1专练】；uerType：使用类型【0app展示apply，1试卷预览view】
	 * @throws Exception **/
	public static String writeTxtFileToLocal(String content,String paperId,String examType,String useType) throws Exception{
		boolean flag = false;
		//生成当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date currentDate = new Date();
		String strDate = sdf.format(currentDate);
		
		//定义本地存储文件的全路径（包括文件名称）
		String localPath = "";
		//定义本地存储文件的路径（不包括文件名称）
		String localUrl = "";
		//判断本地存储路径类型
		if ("0".equals(examType)) {//模考
			String local_p = local_root_path_p+strDate+"/"+paperId;
			localUrl = local_p;
			//判断路径是否存在，不存在则创建
			creatDir(local_p);
			if ("0".equals(useType)) {
				localPath = local_p+"/"+paperId+".txt";
			}else if("1".equals(useType)){
				localPath = local_p+"/"+paperId+"_view.txt";
			}
			//判断文件夹是否存在，不存在则创建
//			creatFile(new File(local_p));
		}else if ("1".equals(examType)) {//专练
			String local_r = local_root_path_r+strDate+"/"+paperId;
			localUrl = local_r;
			//判断路径是否存在，不存在则创建
			creatDir(local_r);
			if ("0".equals(useType)) {
				localPath = local_r+"/"+paperId+".txt";
			}else if ("1".equals(useType)) {
				localPath = local_r+"/"+paperId+"_view.txt";
			}
			//判断文件夹是否存在，不存在则创建
			creatFile(new File(local_r));
		}
		//必须指定考试类型才存音频到固定的文件夹
		if (examType != null) {
			writeTxtFile(content, new File(localPath));
		}
		
		//拼接zip存放的路径
		String url = "";
		//数据库存储路径
		String sto_url = "";
		//压缩文件成zip文件，并加密(密码：123456)
		if ("0".equals(examType)) {
			//模考试卷存放路径
			String examUrl = resource_path_exam+strDate+"/"+paperId;
			//判断路径是否存在，不存在则创建
			creatDir(examUrl);
			url = examUrl+"/"+paperId+".zip";
			sto_url = "exam/"+strDate+"/"+paperId+"/"+paperId+".zip";
		}else if ("1".equals(examType)) {
			//专练试卷存放路径
			String packageUrl = resource_path_package+strDate+"/"+paperId;
			//判断路径是否存在，不存在则创建
			creatDir(packageUrl);
			url = packageUrl+"/"+paperId+".zip";
			sto_url = "package/"+strDate+"/"+paperId+"/"+paperId+".zip";
		}
		//调用压缩文件方法
//		ZipUtil.zipFilesWithPwd(localUrl, url, pwd);//加密文件方法
		ZipUtil.zipFiles(localUrl, url);//未加密
		
		return sto_url;
	}
	
	
	
	
	
	/**118端
	 * content：要生成json的试卷内容；paperId：试卷id；examType：试卷类型【0模考、1专练】,userType:使用类型【1应用apply，2预览view】
	 * @throws Exception **/
	public static String writeTxtFileToLocalSys(String content,String paperId,String examType,String useType) throws Exception{
		boolean flag = false;
		//生成当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date currentDate = new Date();
		String strDate = sdf.format(currentDate);
		//strDate = "20170331";
		//定义本地存储资源文件的全路径（包括文件名称）
		String localPath = "";
		//定义本地存储压缩文件的路径（不包括文件名称）
		String localUrl = "";
		//判断本地存储路径类型
		if ("0".equals(examType)) {//模考
			String local_p  = "";
			if("1".equals(useType)){//存放到应用的文件夹
				//文件夹路径
				local_p = sys_root_path + "apply/exam/" + strDate + "/" + paperId;
				localUrl = sys_root_path + "apply/exam/" + strDate;
			}else if("2".equals(useType)){//存放到预览的文件夹下
				//文件夹路径
				local_p = sys_root_path + "view/exam/" + strDate + "/" + paperId;
				localUrl = sys_root_path + "view/exam/" + strDate;
			}
			//判断路径是否存在，不存在则创建
			creatDir(local_p);
			//拼接文件路径
			localPath = local_p + "/" + paperId + ".txt";
		}else if ("1".equals(examType)) {//专练
			String local_r = "";
			if("1".equals(useType)){//存放到应用的文件夹
				//文件夹路径
				local_r = sys_root_path + "apply/package/" + strDate + "/" + paperId;
				localUrl = sys_root_path + "apply/package/" + strDate;
			}else if("2".equals(useType)){//存放到预览的文件夹下
				//文件夹路径
				local_r = sys_root_path + "view/package/" + strDate + "/" + paperId;
				localUrl = sys_root_path + "view/package/" + strDate;
			}
			//判断路径是否存在，不存在则创建
			creatDir(local_r);
			//拼接文件路径
			localPath = local_r + "/" + paperId + ".txt";
		}
		
		
		//必须指定考试类型才存音频到固定的文件夹
		if (examType != null) {
			writeTxtFile(content, new File(localPath));
		}
		/**压缩文件处理******************************************/
		//拼接zip存放的路径和文件名称
		String url = "";
		//数据库存储路径
		String sto_url = "";
		//压缩文件成zip文件，并加密(密码：123456)
		if ("0".equals(examType)) {
			//模考试卷存放路径
			String examUrl = "";
			if("1".equals(useType)){
				examUrl = sys_root_path + "apply/exam/" + strDate + "/" + paperId;
				//判断路径是否存在，不存在则创建
				creatDir(examUrl);
				url = examUrl+"/"+paperId+".zip";
				//url = localUrl + "/" + paperId + ".zip";
				sto_url = "exam/"+strDate+"/"+paperId+"/"+paperId+".zip";
				//压缩(参数说明：需要压缩的文件夹，压缩的格式及存放位置)
				//ZipUtil.zipFiles(examUrl, url);
				ZipUtil.zipFilesWithPwd(examUrl, url, pwd);//加密文件方法
				
				//删除文件及文件夹
				deleteFile(new File(examUrl));
			}
		}else if ("1".equals(examType)) {
			//专练试卷存放路径
			String packageUrl = "";
			if("1".equals(useType)){
				packageUrl = sys_root_path + "apply/package/" + strDate + "/" + paperId;
				//判断路径是否存在，不存在则创建
				creatDir(packageUrl);
				url = packageUrl+"/"+paperId+".zip";
				//url = localUrl + "/" + paperId + ".zip";
				sto_url = "package/"+strDate+"/"+paperId+"/"+paperId+".zip";
				//压缩(参数说明：需要压缩的文件夹下的内容，压缩的格式及存放位置)
				//ZipUtil.zipFiles(packageUrl, url);
				ZipUtil.zipFilesWithPwd(packageUrl+"/", url, pwd);//加密文件方法
				
				//删除文件及文件夹
				deleteFile(new File(packageUrl));
			}
		}

		
		
		return sto_url;
	}
	
	
	
	/**删除文件夹和文件夹下的文件**/
	public static void deleteFile(File file){
		if (file.exists()) {//判断文件是否存在
			if (file.isFile()) {//判断是否是文件
				file.delete();
			}else if(file.isDirectory()){//否则是一个目录
				File[] files = file.listFiles();//声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {//遍历目录下所有的文件
					String fileName = files[i].getName();//获取文件名
					String prefix=fileName.substring(fileName.lastIndexOf(".")+1);//获取文件后缀名
					if(!prefix.equals("zip")){//删除文件后缀名不是zip的文件
						deleteFile(files[i]);
					}
				}
				//file.delete();//删除文件夹
			}
		}else{
			System.out.println("所要删除的文件不存在！");
		}
		
	}
	
	
	
	
	
	
	
	/**将服务器地址上的文件转化为流然后输出拷贝到本地**/
	public static void FileDownToLocal(String url,String localPath) throws Exception {
		InputStream in = null;
		OutputStream out = null;
		int connectTimeout = 30*1000;//连接超时:30s
		int readTimeout = 1*1000*1000;//IO超时:1min
		byte[] buffer = new byte[8*1024];//IO缓冲区:8KB
		URL urlPath = new URL(url);
		File file = new File(localPath);
		
		
		URLConnection conn = urlPath.openConnection();
		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		conn.connect();
		in = conn.getInputStream();
		out = new FileOutputStream(file);
		for (;;) {
			int bytes = in.read(buffer);
			if (bytes == -1) {
				break;
			}
			out.write(buffer, 0, bytes);
		}
		in.close();
		out.close();
	}
	
	/**如果没有当前目录则创建**/
	public static boolean creatDir(String destDirName){
		boolean flag = false;
		File dir = new File(destDirName);
		if (dir.exists()) {
			flag = true;
		}else{
			//File.separator---windows是\，unix是/
			flag = dir.mkdirs();//创建目录
		}
		return flag;
	}
	
	
	
	
	/**创建文件**/
	public static boolean creatFile(File fileName){
		boolean flag = false;
		if (!fileName.exists()) {
			try {
				fileName.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = true;
		}
		
		return flag;
	}
	
	
	/**向文件中写入内容**/
	public static boolean writeTxtFile(String content,File fileName) throws Exception{
		boolean flag = false;
		RandomAccessFile mm = null;
		FileOutputStream os = null;
		
		try {
			os = new FileOutputStream(fileName);
			
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write(content);
			bw.close();
			
			flag = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	
	
	
	/**查找是否存在图片**/
	public static boolean findImage(String str){
		boolean flag = false;
		for (int i = 0; i < imgArr.length; i++) {
			if (str.indexOf(imgArr[i]) != -1 || str.indexOf(imgArr[i].toUpperCase()) != -1) {
				flag = true;
				imgFlag = imgArr[i];
			}
		}
		return flag;
	}
	

	
	/**
	 * 读取txt文件
	 * @param filePath 文件路径
	 */
	public static String readTxt(String filePath){
		String content = "";
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
					InputStreamReader reader = new InputStreamReader(new FileInputStream(file),encoding);
					BufferedReader br = new BufferedReader(reader);
					String lineTxt = "";
					while ((lineTxt = br.readLine()) != null) {
						content += lineTxt;
					}
			}else{
				System.out.println("找不到指定的文件!");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错!");
			e.printStackTrace();
		}
		return content;
	}
	
	
	
	
	/**
	 * 读取txt文件
	 * @param filePath 读取网络文件
	 */
	public static String readUrlFile(String filePath){
		String content = "";
		OutputStream os = null;
		InputStream is = null;
		URLConnection con = null;
		try {
			String encoding = "UTF-8";
			URL urlt = new URL(filePath);
			con = urlt.openConnection();
			is = con.getInputStream();
			byte[] bytes = new byte[10240000];
			
			InputStreamReader reader = new InputStreamReader(is,encoding);
			BufferedReader br = new BufferedReader(reader);
			String lineTxt = "";
			while ((lineTxt = br.readLine()) != null) {
				content += lineTxt;
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错!");
			e.printStackTrace();
		}
		return content;
	}
	
	
	public static void creatDirToSys(){
		String destDirName = "/home/ccb_data/exam/20170112/8a59a235c40944fcabe3ac2daa33bbd0/";
		System.out.println("创建文件夹路径："+destDirName);
		boolean flag = creatDir(destDirName);
		System.out.println("创建文件夹状态："+flag);
	}
	
	
	public static void main(String[] args){

		System.out.println("123");
		String strPath = "J:/DevelopTool/Workspaces/Resource/apply/exam/20170215/ff241e01c5934bb2a6be079953237ba1/";
		//deleteFile(new File(strPath));
		System.out.println("456");
	}
}
