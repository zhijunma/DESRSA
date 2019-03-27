package com.cn.school.utils.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送请求api
 * @author YiTong
 *
 */
@Slf4j
public class ApiMotest {

	/**
	 * 
	* @Title: Split 
	* @Description: url参数转map
	* @param @param urlparam
	* @param @return    入参
	* @return Map<String,String>    返回类型
	* @author 易通 
	* @throws
	* @date 2018年12月9日 上午10:02:32 
	* @version V1.0   
	 */
	public static Map<String,String> Split(String urlparam) {
		Map<String, String> map = new HashMap<String, String>();
		String[] param = urlparam.split("&");
		for (String keyvalue : param) {
			String[] pair = keyvalue.split("=");
			if (pair.length == 2) {
				map.put(pair[0], pair[1]);
			}
		}
		return map;
	}
	/**
	 * 请求api平台
	 * @param pathUrl
	 * @param method
	 * @return
	 */
	public static String sendPost(String pathUrl,String method) {
		log.info("url>>>>"+pathUrl);
		HttpURLConnection httpUrlConnection=null;
		// 建立连接
		try {
			URL url = new URL(pathUrl);
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpUrlConnection.setDoOutput(true);// 使用 URL 连接进行输出
			httpUrlConnection.setDoInput(true);// 使用 URL 连接进行输入
			httpUrlConnection.setUseCaches(false);// 忽略缓存
			httpUrlConnection.setRequestMethod(method);// 设置URL请求方法
			httpUrlConnection.setConnectTimeout(3000);
			httpUrlConnection.setReadTimeout(3000);
			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			/*byte[] requestStringBytes = requestString.getBytes("UTF-8");*/
			//httpUrlConnection.setRequestProperty("Content-length", "" + requestStringBytes.length);
			httpUrlConnection.setRequestProperty("Content-Type", "plain/text; charset=UTF-8");
			httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");
			httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:64.0) Gecko/20100101 Firefox/64.0");


			// 建立输出流，并写入数据
			OutputStream outputStream = httpUrlConnection.getOutputStream();
			//outputStream.write(requestStringBytes);
			outputStream.close();
			// 获得响应状态
			int responseCode = httpUrlConnection.getResponseCode();
			String readLine = null;
			int httpOk = HttpURLConnection.HTTP_OK;
			if (httpOk == responseCode) {// 连接成功
				// 当正确响应时处理数据
				StringBuffer sb = new StringBuffer();

				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8"));
				while (!ObjectUtils.isEmpty((readLine = responseReader.readLine()))) {
					sb.append(readLine);
				}
				responseReader.close();
				String result = sb.toString();
				return result;
			}else {
				StringBuffer sb = new StringBuffer();

				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8"));
				while (!ObjectUtils.isEmpty((readLine = responseReader.readLine()))) {
					sb.append(readLine);
				}
				responseReader.close();
				String result = sb.toString();
				log.info(result);
				return httpOk+"连接错误"+responseCode+result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "网站打不开";
		}finally {
			httpUrlConnection.disconnect();
		}
	}
	/**
	 * 请求api平台
	 * @param pathUrl
	 * @param method
	 * @return
	 */
	public static String sendPost(String pathUrl,Map<String, String> map,String method) {
		pathUrl=appendUrl(pathUrl, map);
		return sendPost(pathUrl, method);
	}

	
	/**
	 * 在指定url后追加参数
	 * @param url
	 * @param data 参数集合 key = value
	 * @return
	 */
	public static String appendUrl(String url, Map<String, String> data) {
	    String newUrl = url;
	    StringBuffer param = new StringBuffer();
	    for (String key : data.keySet()) {
	    	//排除空值请求
			param.append(key + "=" + data.get(key) + "&");

	    }
	    String paramStr = param.toString();
	    paramStr = paramStr.substring(0, paramStr.length() - 1);
	    if (newUrl.indexOf("?") >= 0) {
	        newUrl += "&" + paramStr;
	    } else {
	        newUrl += "?" + paramStr;
	    }
	    return newUrl;
	}

	/**
	 * 获取指定url中的某个参数
	 * @param url
	 * @param name
	 * @return
	 */
	public static String getParamByUrl(String url, String name) {
	    url += "&";
	    String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";
	    Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(url);
	    if (m.find( )) {
	        System.out.println(m.group(0));
	        return m.group(0).split("=")[1].replace("&", "");
	    } else {
	        return null;
	    }
	}

	
	
}
