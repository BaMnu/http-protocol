package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();
    public static String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000).setSocketTimeout(30000)
                        .setRedirectsEnabled(false).build()).build()) {

            HttpGet request = new HttpGet(URL);

            CloseableHttpResponse response = httpClient.execute(request);

            List<Post> posts = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
            });
            Predicate<Post> unrated = vote -> vote.getUpvotes() == null;
            posts.removeIf(unrated);
            posts.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}