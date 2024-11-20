package hemangs.own.project.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class WebsitesScraping {

    private static final OkHttpClient client = new OkHttpClient();
    public static String scrapeLeetCodeWebPage(String LEETCODE_URL) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(LEETCODE_URL);
        requestBuilder.get();

        Request request = new Request.Builder()
                .url(LEETCODE_URL)
                .get()
                .header("User-Agent", "Mozilla/5.0 (compatible; Bot/1.0)") // Add User-Agent header
                .build();


        try(Response response = client.newCall(request).execute()){
            if(!response.isSuccessful())
                return "response is not successfull...";
            return "SUcessfull HTTPS response"+response.body().string();
        }
    }

    public static String getUserNameFromLeetcode(String url) throws IOException {
        String userWebPage =  scrapeLeetCodeWebPage(url);
        String searchUsernameStart = "<div class=\"text-label-1 dark:text-dark-label-1 break-all text-base font-semibold\">";
        int indexOfUsernameStart = userWebPage.indexOf(searchUsernameStart);
        indexOfUsernameStart +=83;
        String searchUsernameStop = "</div></div><div class=\"flex items-center\" translate=\"no\"><div class=\"text-label-3 dark:text-dark-label-3 text-xs\">";
        int indexOfUsernameStop = userWebPage.indexOf(searchUsernameStop);
        String userName = userWebPage.substring(indexOfUsernameStart, indexOfUsernameStop);
        return userName;
    }
}