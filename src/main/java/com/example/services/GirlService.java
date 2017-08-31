package com.example.services;

import com.example.domail.Girl;
import com.example.enums.ResultEnum;
import com.example.exception.GirlException;
import com.example.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    // 事物管理 查询不需要加事物
    @Transactional
    public void insertTwo(){
        Girl girlA= new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB= new Girl();
        girlB.setCupSize("BBBB");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);

        Integer age = girl.getAge();
        if(age<10){
            //返回"你还在上小学"
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age>10&&age<18) {
            //返回"你可能还在上初中"
            throw new GirlException(ResultEnum.MIDDLE_SHOOL);
        }

    }

    /**
     * 通过ID查询一个女生的信息
     * @param id
     * @return
     */
    public Girl findOne(Integer id){
        return girlRepository.findOne(id);
    }
}
