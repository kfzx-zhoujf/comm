package com.zjf.provider;

import com.zjf.dto.AccessTokenDTO;
import com.zjf.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * @author zjf
 * @create 2020/1/10-15:53
 */
@Component// IOC对象实例化
public class GithubProvider {
    //参数超过2个封装为对象=>dto 数据传输对象
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            String token = string.split("&")[0].split("=")[1];
//            System.out.println(string);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string=response.body().string();
            //string的json对象解析成java对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
