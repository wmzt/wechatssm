package com.frame.util;

import org.apache.commons.httpclient.*;

import java.io.IOException;

public class HttpClientUtils {
    private static HttpClient httpClient = null;

    public HttpClientUtils(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static synchronized HttpClient getHttpClent() {
        if (httpClient == null && !(httpClient instanceof HttpClient)) {
            MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
            connectionManager.getParams().setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, 10);
            connectionManager.getParams().setMaxTotalConnections(25);
            httpClient = new HttpClient(connectionManager);
        }
        return httpClient;
    }

    public static String httpRequest(HttpMethod httpMethod) throws ConnectTimeoutException, HttpException, IOException {
        String result = "none";
        //int status = true;
        HttpClient client = getHttpClent();
        client.getParams().setContentCharset("UTF-8");
        client.getParams().setVersion(HttpVersion.HTTP_1_1);
        client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        client.getHttpConnectionManager().getParams().setSoTimeout(60000);
        client.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler(3, false));

        try {
            int status = client.executeMethod(httpMethod);
            if (status != 200) {
                throw new Exception("http请求失败!");
            }

            if (status == 200) {
                result = httpMethod.getResponseBodyAsString();
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            httpMethod.releaseConnection();
        }

        return result;
    }

}
