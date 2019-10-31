package com.hx.external.service.impl;

import com.hx.common.exception.BDException;
import com.hx.external.api.HttpUtils;
import com.hx.external.domain.TrialUsers;
import com.hx.external.service.UtilService;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UtilServiceImp implements UtilService {


    @Override
    public String sendSMS(TrialUsers TrialUsers){
        String host = "https://ali-sms.showapi.com";
        String path = "/sendSms";
        String method = "GET";
        String appcode = "1ea706520a9c43698bfbab84827b6463";
        String codes = String.valueOf((Math.random()*9+1)*10000);
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "{\"name\":"+TrialUsers.getName()+",\"code\":"+codes+",\"minute\":\"5\"}");
        querys.put("mobile", TrialUsers.getPhone());
        querys.put("tNum", "T170317005347");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            return codes;
        } catch (Exception e) {
            throw new BDException("发送失败");
        }
    }

}
