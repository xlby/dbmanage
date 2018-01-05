package com.fifedu.util.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.UploadContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fifedu.base.vo.OperResult;
import com.fifedu.util.iplat.IplatServerImpl;
import com.fifedu.util.iplat.IplatUrlConfig;
import com.fifedu.util.json.JacksonUtil;

/**
 * http工具类，模拟浏览器地址访问
 */
public class HttpClientUtil {
	private static PoolingHttpClientConnectionManager cm;
	private static String EMPTY_STR = "";
	private static String UTF_8 = "UTF-8";

	private static void init() {
		if (cm == null) {
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(50);// 整个连接池最大连接数
			cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
		}
	}

	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 */
	private static CloseableHttpClient getHttpClient() {
		init();
		return HttpClients.custom().setConnectionManager(cm).build();
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGetRequest(String url) {
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}

	public static String httpGetRequest(String url, Map<String, Object> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		return getResult(httpGet);
	}

	public static String httpGetRequest(String url,
			Map<String, Object> headers, Map<String, Object> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}
		return getResult(httpGet);
	}

	public static String httpPostRequest(String url) {
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}

	public static String httpPostRequest(String url, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
		return getResult(httpPost);
	}

	public static String httpPostRequest(String url,
			Map<String, Object> headers, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);

		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

		return getResult(httpPost);
	}

	private static ArrayList<NameValuePair> covertParams2NVPS(
			Map<String, Object> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), String
					.valueOf(param.getValue())));
		}

		return pairs;
	}

	/**
	 * 处理Http请求
	 * 
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request) {
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpClient httpClient = getHttpClient();
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			// response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// long len = entity.getContentLength();// -1 表示长度未知
				String result = EntityUtils.toString(entity);
				response.close();
				// httpClient.close();
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

		return EMPTY_STR;
	}

	// 平台文件上传
	public static String httpPostUpload(String url, String localFile) {
		String result = "";
		CloseableHttpClient httpClient = getHttpClient();
		CloseableHttpResponse response = null;
		try {
			// 把一个普通参数和文件上传给下面这个地址 是一个servlet
			HttpPost httpPost = new HttpPost(url);
			// 把文件转换成流对象FileBody
			FileBody bin = new FileBody(new File(localFile));
			HttpEntity reqEntity = MultipartEntityBuilder.create()
			// 相当于<input type="file" name="file"/>
					.addPart("file", bin).build();

			httpPost.setEntity(reqEntity);
			// 发起请求 并返回请求的响应
			response = httpClient.execute(httpPost);
			// 获取响应对象
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				// 打印响应内容
				// System.out.println(EntityUtils.toString(resEntity,
				// Charset.forName("UTF-8")));
				result = EntityUtils.toString(resEntity,
						Charset.forName("UTF-8"));
			}
			// 销毁
			//EntityUtils.consume(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		return result;
	}

	public static void main(String[] args) {
		// testUpload();
		//httpPostUpload("", "");
		myUpload();
	}


	public static void testUpload() {
		String BOUNDARY = "========7d4a6d158c9";

		String url = "http://www.fifedu.com/iplat/kyxl/userinfo/upload";
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("connection", "Keep-Alive");
		headers.put("Charsert", "UTF-8");
		headers.put("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		Map<String, Object> params = new HashMap<String, Object>();
		File file = new File("D:/demoUpload1.jpg");
		// 文件参数内容
		FileBody fileBody = new FileBody(file);
		params.put("file", fileBody);

		String json = "";
		try {
			json = HttpClientUtil.httpPostRequest(url, headers, params);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("json ================  " + json);

	}

	public static void testUpload2() {

		// 定义请求url
		// String uri = "http://test.fifedu.com/iplat/kyxl/userinfo/upload";

		String uri = "http://www.fifedu.com/iplat/kyxl/userinfo/upload";
		// String uri = "http://192.168.71.38:8080/ccb/testTeacher/testUpload";

		// 实例化http客户端
		HttpClient httpClient = new DefaultHttpClient();
		// 实例化post提交方式
		HttpPost post = new HttpPost(uri);

		// 添加json参数
		try {
			// 实例化参数对象
			MultipartEntity params = new MultipartEntity();

			// 图片文本参数
			/*
			 * params.addPart("textParams", new StringBody(
			 * "{'user_name':'我的用户名','channel_name':'却道明','channel_address':'(123.4,30.6)'}"
			 * , Charset.forName("UTF-8")));
			 */

			// 设置上传文件
			File file = new File("D:/demoUpload1.jpg");
			// 文件参数内容
			FileBody fileBody = new FileBody(file);
			// 添加文件参数
			params.addPart("file", fileBody);
			// params.addPart("photoName", new StringBody(file.getName()));
			// 将参数加入post请求体中
			post.setEntity(params);

			// 执行post请求并得到返回对象 [ 到这一步我们的请求就开始了 ]
			HttpResponse resp = httpClient.execute(post);

			// 解析返回请求结果
			HttpEntity entity = resp.getEntity();
			InputStream is = entity.getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuffer buffer = new StringBuffer();
			String temp;

			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
			}

			System.out.println(buffer);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	public static void myUpload() {
		CloseableHttpClient httpClient = getHttpClient();
		CloseableHttpResponse response = null;
		// CloseableHttpClient httpClient = null;
		String localFile = "D:/demoUpload1.jpg";
		//String url = "http://www.fifedu.com/iplat/kyxl/userinfo/upload";
		String url = "http://192.168.71.38:8080/ccb/v1/app/common/upload";
		try {

			// 把一个普通参数和文件上传给下面这个地址 是一个servlet
			HttpPost httpPost = new HttpPost(url);
			// 把文件转换成流对象FileBody
			FileBody bin = new FileBody(new File(localFile));
			HttpEntity reqEntity = MultipartEntityBuilder.create()
			// 相当于<input type="file" name="file"/>
					.addPart("file", bin)
					.addTextBody("username", "bfsu2005").addTextBody("roleName", "teacher").addTextBody("password", "123456").addTextBody("schoolId", "2000000026000000001")
					.build();

			httpPost.setEntity(reqEntity);
			// 发起请求 并返回请求的响应
			response = httpClient.execute(httpPost);
			// 获取响应对象
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				// 打印响应长度
				System.out.println("Response content length: "
						+ resEntity.getContentLength());
				// 打印响应内容
				System.out.println(EntityUtils.toString(resEntity,
						Charset.forName("UTF-8")));
			}

			// 销毁
			//EntityUtils.consume(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}
}
