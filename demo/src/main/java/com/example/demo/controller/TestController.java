package com.example.demo.controller;

import com.example.demo.resp.BlockResponse;
import com.example.demo.resp.StreamResponse;
import com.example.demo.service.DifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    @Value("${dify.key.test}")
    private String testKey;

    private final DifyService difyService;

    @GetMapping("/block")
    public String test1() {
        String query = "鲁迅和周树人什么关系？";
        BlockResponse blockResponse = difyService.blockingMessage(query, 0L, testKey);
        return blockResponse.getAnswer();
    }

    @GetMapping("/stream")
    public Flux<StreamResponse> test2() {
        String query = "鲁迅和周树人什么关系？";
        return difyService.streamingMessage(query, 0L, testKey);
    }
}
