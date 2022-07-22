package com.lovejava;

import com.lovejava.mapper.RecordMapper;
import com.lovejava.pojo.*;
import com.lovejava.pojo.Record;
import com.lovejava.service.QuestionService;
import com.lovejava.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class OnlinetestsystemApplicationTests {

    @Autowired
    private RecordMapper recordMapper;

//    @Test
//    void fun1(){
//        int a=2;
//        StringBuilder stringBuilder = new StringBuilder();
//        List<Integer> integers = Arrays.asList(1, 2, 3);
//        integers.stream().forEach(p->{
//            stringBuilder.append(p.toString());
//        });
//        integers.stream().forEach(p->{
//            stringBuilder=null;
//        });
//        System.out.println(stringBuilder.toString());
//    }

    @Test
    void func(){

        Date date = new Date();//获得系统时间.



        Timestamp timestamp = new Timestamp(date.getTime());



    }

    @Test
    public void timeTest() throws InterruptedException {




        Record record = new Record();
        record.setStartTime(new Date());
        Thread.sleep(1000*60);
        record.setEndTime(new Date());


        record.setUid(14);
        record.setSingleList("11");
        record.setMultiList("22");
        record.setJudgeList("33");
        record.setSaqList("44");
        System.out.println(record);
        recordMapper.insert(record);


    }
//    @Autowired
//    UserService userService;
//    @Test
//    void func2(){
//         String phone = userService.getPhone(14);
//        System.out.println(phone);
//    }
}
