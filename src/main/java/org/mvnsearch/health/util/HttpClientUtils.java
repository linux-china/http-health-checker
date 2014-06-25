package org.mvnsearch.health.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

/**
 * http client utils
 *
 * @author linux_china
 */
public class HttpClientUtils {
    private static HttpClient httpClient = createHttpClient();

    /**
     * create http client instance
     *
     * @return HttpClient object
     */
    public static HttpClient createHttpClient() {
        HttpClient clientTemp = new HttpClient();     //HttpClient create
        HttpClientParams clientParams = clientTemp.getParams();
        clientParams.setParameter("http.useragent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
        clientParams.setParameter("http.socket.timeout", 10000); //10 seconds for socket waiting
        clientParams.setParameter("http.connection.timeout", 10000); //10 seconds http connection creation
        clientParams.setParameter("http.connection-manager.timeout", 3000L); //3 seconds waiting to get connection from http connection manager
        clientParams.setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler()); // if failed, try 3
        return clientTemp;
    }

    /**
     * get response body as string
     *
     * @param httpUrl http url
     * @return response body
     * @throws Exception connection exception
     */
    public static String getResponseBody(String httpUrl) throws Exception {
        GetMethod getMethod = new GetMethod(httpUrl);
        httpClient.executeMethod(getMethod);
        return getMethod.getResponseBodyAsString();
    }

    /**
     * get response as jdom document
     *
     * @param httpUrl http url
     * @return jdom document
     * @throws Exception connection or xml exception
     */
    public static Document getResponseDocument(String httpUrl) throws Exception {
        GetMethod getMethod = new GetMethod(httpUrl);
        httpClient.executeMethod(getMethod);
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(getMethod.getResponseBodyAsStream());
    }

}
