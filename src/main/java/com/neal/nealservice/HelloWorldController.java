package com.neal.nealservice;

import com.neal.nealcore.base.controller.BaseController;
import com.neal.nealcore.base.resultmodel.internal.RpcResultModel;
import com.neal.nealcore.common.RedisUtil;
import com.neal.nealcore.exception.beans.impl.RpcExceptionDesc;
import com.neal.nealcore.exception.impl.RpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloWorldController extends BaseController {
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/hello")
    public String hello() {
//        throw new RpcException(new RpcExceptionDesc("000000","faile"));
        RpcResultModel result = new RpcResultModel(null,"success");
        logger.info("log4j-----------------------------test");
        return result.toString();
    }

    @RequestMapping("/exception")
    public String exception() {
        throw new RpcException(new RpcExceptionDesc("000000","faile"));
    }

    @GetMapping("/user")
    public String user() {
        List<Map<String,Object>> list = new ArrayList();
        Map<String,Object> mapA = new HashMap<String, Object>();
        mapA.put("title","A");
        List<Map<String,Object>> listA = new ArrayList();
        Map<String,Object> mapItemA1 = new HashMap<String, Object>();
        mapItemA1.put("name","aom");
        mapItemA1.put("done",true);
        Map<String,Object> mapItemA2 = new HashMap<String, Object>();
        mapItemA2.put("name","allen");
        mapItemA2.put("done",true);
        listA.add(mapItemA1);
        listA.add(mapItemA2);
        mapA.put("items",listA);

        Map<String,Object> mapB = new HashMap<String, Object>();
        mapB.put("title","B");
        List<Map<String,Object>> listB = new ArrayList();
        Map<String,Object> mapItemB1 = new HashMap<String, Object>();
        mapItemB1.put("name","Bob");
        mapItemB1.put("done",true);
        Map<String,Object> mapItemB2 = new HashMap<String, Object>();
        mapItemB2.put("name","Ben");
        mapItemB2.put("done",true);
        listB.add(mapItemB1);
        listB.add(mapItemB2);
        mapB.put("items",listB);

        list.add(mapA);
        list.add(mapB);

        redisUtil.lSet("list",list);
        RpcResultModel result = new RpcResultModel(redisUtil.lGet("list",0, -1),"success");
        return result.toString();
    }
}
