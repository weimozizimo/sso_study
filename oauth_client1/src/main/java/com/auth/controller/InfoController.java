package com.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@Controller
public class InfoController {

    @GetMapping("/getUser")
    public ResponseEntity<Object> userPage(Principal principal){
        //客户端认证成功过后返回这个用户信息
        log.info(principal.getName());
        return new ResponseEntity<Object>(principal, HttpStatus.OK);
    }

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }
}
