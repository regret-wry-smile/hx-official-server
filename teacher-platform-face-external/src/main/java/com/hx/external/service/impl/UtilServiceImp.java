package com.hx.external.service.impl;

import com.hx.common.exception.BDException;
import com.hx.external.api.HttpUtils;
import com.hx.external.domain.TrialUsers;
import com.hx.external.service.UtilService;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.velocity.runtime.directive.Break;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UtilServiceImp implements UtilService {

    //手机号发送次数
    private static Map<String,Integer> phoneMap = Collections.synchronizedMap(new HashMap<String, Integer>());

    @Override
    public Integer sendSMS(TrialUsers trialUsers){
        String host = "https://ali-sms.showapi.com";
        String path = "/sendSms";
        String method = "GET";
        String appcode = "1ea706520a9c43698bfbab84827b6463";
        Integer codes = (int)((Math.random()*9+1)*100000);
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "{\"name\":\""+trialUsers.getName()+"\",\"code\":\""+codes+"\",\"minute\":\"5\"}");
        querys.put("mobile", trialUsers.getPhone());
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
            System.out.println(EntityUtils.toString(response.getEntity()));
            Integer i = phoneMap.get(trialUsers.getPhone());
            phoneMap.put(trialUsers.getPhone(),i++);
            return codes;
        } catch (Exception e) {
            throw new BDException("发送失败");
        }
    }

    @Override
    public boolean phoneVerify(TrialUsers trialUsers){
        /*如果phoneMap里没有该手机号,表示手机未存*/
        if (phoneMap.containsKey(trialUsers.getPhone())){
            phoneMap.put(trialUsers.getPhone(),1);
            return true;
        }else {
            if (phoneMap.get(trialUsers.getPhone())>3){
                throw new BDException("申请次数已达上限");
            }else{
                return true;
            }
        }
    }

    @Component
    public class SchedulerTask{
        @Scheduled(fixedRate = 24*60*60*1000)
        public void phoneClear(){
            phoneMap.clear();
        }
    }

}
