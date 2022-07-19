package com.yadas.web.spark.wordcount.controller;

import com.yadas.web.spark.wordcount.service.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class WordCountController {

    @Autowired
    @Lazy
    private WordCountService service;

    @RequestMapping(method = RequestMethod.POST, path = "/wordcount")
    public Map<String, Long> count(@RequestParam(required = true) String words) {
        List<String> wordList = Arrays.asList(words.split(" "));
        return service.getCount(wordList);
    }
}
