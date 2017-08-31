package com.example;


import com.example.domail.Girl;
import com.example.services.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest //启动SpringBoot工程
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findOneGirl(){
        Girl girl = girlService.findOne(12);
        Assert.assertEquals( new Integer(22),girl.getAge() );
    }

}
