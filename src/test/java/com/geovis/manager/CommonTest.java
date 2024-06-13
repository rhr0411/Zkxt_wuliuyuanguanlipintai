package com.geovis.manager;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.geovis.common.auth.constant.EncoderTypeEnum;
import com.geovis.common.auth.props.AuthProperties;
import com.geovis.common.auth.util.AuthUtil;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

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
