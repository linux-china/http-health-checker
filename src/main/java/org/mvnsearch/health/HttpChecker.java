package org.mvnsearch.health;

import org.jdom2.Document;
import org.mvnsearch.health.util.HttpClientUtils;

/**
 * http checker
 *
 * @author linux_china
 */
public class HttpChecker {
    public static void main(String[] args) {
        try {
            String siteUrl = args[1];
            String healthXmlUrl = siteUrl + "/health.xml";
            Document document = HttpClientUtils.getResponseDocument(healthXmlUrl);
        } catch (Exception e) {

        }
    }
}
