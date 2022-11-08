package com.chixing.util;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.chixing.commons.R;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/18 15:29
 */
public class SmsUtil {
    public static R<Integer> sendMsg(String telno){
        //随机生成六位随机数
        StringBuffer stringBuffer = new StringBuffer();
        for (int x=0;x<=5;x++) {
            int random = (int) (Math.random() * (10 - 1));
            stringBuffer.append(random);
        }
        String codeStr = stringBuffer.toString();
        Integer code = Integer.parseInt(codeStr);
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5tG7nJQaHNGWeSvwmYn9")
                .accessKeySecret("cgXNd3ACgoXXJeu94TviYo66nzerHd")
                .build());
        AsyncClient client = AsyncClient.builder()
                .region("cn-shanghai")
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("阿里云短信测试")
                .templateCode("SMS_154950909")
                .phoneNumbers(String.valueOf(telno))
                .templateParam("{\"code\":\"" + code + "\"}")
                .build();
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = null;
        try {
            resp = response.get();
        } catch (InterruptedException e) {
            return R.fail();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new Gson().toJson(resp));
        client.close();
        return R.ok("code",code);
    }
}
