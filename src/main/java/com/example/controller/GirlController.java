package com.example.controller;

import com.example.domail.Girl;
import com.example.domail.Result;
import com.example.repository.GirlRepository;
import com.example.services.GirlService;
import com.example.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;
    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping( value = "/girls")
    public List<Girl> girlList(){
        logger.info("girlList");
        return girlRepository.findAll();
    }


    @PostMapping( value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl , BindingResult bindingResult){
        Result result = new Result();
        if (bindingResult.hasErrors()){
            //System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
           // return  ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }

        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 查询一个女生
     * @param id
     * @return
     */
    @GetMapping( value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }

    /**
     * 更新一个女生
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PutMapping( value="/girls/{id}" )
    public Girl girlUpdate(@PathVariable("id") Integer id ,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age ){
        Girl girl =new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);

        return girlRepository.save(girl);
    }

    /**
     * 删除一个女生
     * @param id
     */
    @DeleteMapping( value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    /**
     * 根据年龄来查询一个女生
     * @param age
     * @return
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    /**
     * Spring Boot 事物管理
     */
    @PostMapping( value = "/girls/two")
    public void girlsTwo(){
        girlService.insertTwo();
    }

    @GetMapping( value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id ) throws Exception {
        girlService.getAge(id);
    }

}
