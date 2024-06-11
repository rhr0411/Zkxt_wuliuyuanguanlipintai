package com.geovis.manager;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonTest {

    @Test
    public void scheduleExec() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("Hiko@20201204"));
    }

    private String post(String url, String params) {
        HttpRequest post = HttpUtil.createPost(url);
        post.body(params, "application/json");
        HttpResponse execute = post.execute();
        return execute.body();
    }

}
