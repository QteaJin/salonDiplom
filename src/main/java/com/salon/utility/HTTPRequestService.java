package com.salon.utility;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HTTPRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPRequestService.class);

    public static String requestPost(String URL) {
        String json = StringUtils.EMPTY;

        if (StringUtils.isEmpty(URL)) {
            throw new IllegalArgumentException("URL fail");
        }

        HttpPost httpPost = new HttpPost(URL);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = IOUtils.toString(response.getEntity().getContent());
            }

        } catch (IOException e) {
            LOGGER.debug("Post request fail");
            throw new IllegalArgumentException("Post request fail");
        }
        return json;
    }

    public static String requestGet(String URL) {
        String json = StringUtils.EMPTY;

        if (StringUtils.isEmpty(URL)) {
            throw new IllegalArgumentException("URL fail");
        }

        HttpGet httpPost = new HttpGet(URL);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = IOUtils.toString(response.getEntity().getContent());
            }

        } catch (IOException e) {
            LOGGER.debug("Get request fail");
            throw new IllegalArgumentException("Get request fail");
        }
        return json;
    }

}
