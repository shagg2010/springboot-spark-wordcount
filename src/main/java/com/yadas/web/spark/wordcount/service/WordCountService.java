package com.yadas.web.spark.wordcount.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordCountService {
    @Autowired
    @Lazy
    private JavaSparkContext sc;

    public Map<String, Long> getCount(List<String> wordList) {
        JavaRDD<String> words = sc.parallelize(wordList);
        Map<String, Long> wordCounts = words.countByValue();

        return sortByValue(wordCounts);
    }

    public static Map<String, Long> sortByValue(Map<String, Long> map)
    {
        List<Map.Entry<String, Long> > list =
                new LinkedList<Map.Entry<String, Long> >(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Long> >() {
            public int compare(Map.Entry<String, Long> o1,
                               Map.Entry<String, Long> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        //Collections.reverse(list);
        HashMap<String, Long> temp = new LinkedHashMap<String, Long>();
        for (Map.Entry<String, Long> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
