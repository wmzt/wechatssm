package HttpClient;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.URI;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


//import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.logging.Logger;  


public class HttpClient {

	private static Logger logger = Logger.getLogger(HttpClient.class);  

	/**
	 * get 请求
	 * @param url 
	 * @return 
	 */

	public String doGet(String url){
		String result=null;
		// 1. 创建一个默认的client实例
		CloseableHttpClient client = HttpClients.createDefault();
		try{		 
			// 2. 创建一个 get 对象
			HttpGet request = new HttpGet(url);  
			System.out.println("httpclient"+request);
			//执行GET 获取响应
			HttpResponse response = client.execute(request); 
			
			try{
				//请求成功 响应200
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){			
					//读取服务器返回过来的json字符串数据
				    result = EntityUtils.toString(response.getEntity(),"UTF-8"); 
					client.close();//关闭 client
					//return result;
				}

			}catch(IOException e){
				e.printStackTrace();
			}

		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				client.close();   //关闭 client
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}	

	/** 
	 * post请求 
	 * @param url 
	 * @param params 
	 * @return 
	 */  

	public String doPost(String url, HashMap params){

		CloseableHttpClient client = HttpClients.createDefault();
		// 创建httppost实例
		HttpPost request = new HttpPost(url);

		//设置post参数  
		List<NameValuePair> nvp = new ArrayList<NameValuePair>();   //NameValuePair 类型存放post参数
		for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {  
			String key = (String) iter.next();  
			String value = params.get(key).toString();
			nvp.add(new BasicNameValuePair(key, value));  
		}  

		try{
			//设置请求实体 完成传参 输入数据为UTF-8编码
			
			request.setEntity(new UrlEncodedFormEntity(nvp,HTTP.UTF_8));
			//执行 发送请求
			HttpResponse response = client.execute(request);   
			// 获取响应实体
			HttpEntity respEntity = response.getEntity();

			try{
				//输出响应
				if(respEntity != null){
					//System.out.println(response.getStatusLine());
					String result = EntityUtils.toString(response.getEntity());
					client.close(); //关闭资源
					return result;
				}
			}catch(IOException e){
				e.printStackTrace();
			}

		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				client.close(); //关闭资源
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	//自定义的dopost
	public static String doPostStr(String url,String outStr){
		 System.out.println("doPostStr");
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		//JSONObject jsonresult=new JSONObject();
		String result=null;
		try {
			httpPost.setEntity(new StringEntity(outStr,"utf-8"));
			HttpResponse response = client.execute(httpPost);
			
			// 获取响应实体
			HttpEntity respEntity = response.getEntity();
			
			if(respEntity != null){
				//System.out.println(response.getStatusLine());
			//	String result = EntityUtils.toString(response.getEntity());
				
				result = EntityUtils.toString(response.getEntity(),"utf-8");
				client.close(); //关闭资源
			//转成json
			//	jsonresult = JSONObject.parseObject(result);
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //关闭资源
		}
	//	jsonresult = JSONObject.fromObject(result);
		
		return result;
	}

}

	
