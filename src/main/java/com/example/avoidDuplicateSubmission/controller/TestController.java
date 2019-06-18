package com.example.avoidDuplicateSubmission.controller;

import com.example.avoidDuplicateSubmission.annotation.Token;
import com.example.avoidDuplicateSubmission.enums.TokenTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abc")
public class TestController {
    @GetMapping(value = "/test")
    @Token(type = TokenTypeEnum.PreventingRefreshCommit)
    public Object test() throws InterruptedException {
        System.out.println("进来了");
        Thread.sleep(10000);
        return "重复提交";
    }
}
