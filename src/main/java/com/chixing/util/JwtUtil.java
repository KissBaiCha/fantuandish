package com.chixing.util;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chixing.entity.vo.CustomerTokenVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/11 11:23
 */
@Slf4j
public class JwtUtil {
    /**
     * 密钥
     */
    private static final String SECRET = "fanTuanMeiShi";

    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 600 * 1000L;

    /**
     * 生成用户token,设置token超时时间
     * expireDate 超时设置,设置过期的日期
     */
    public static String createToken(CustomerTokenVO customerTokenVO) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION);

        Map<String, Object> map = new HashMap<>(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withHeader(map)
                .withIssuer("FanTuan")
                .withClaim("token", JSON.toJSONString(customerTokenVO))
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));
    }
    public static boolean verifyToken(String token){
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withIssuer("FanTuan").build();
            jwt = verifier.verify(token);
            CustomerTokenVO token1 = JSON.to(CustomerTokenVO.class, jwt.getClaim("token").asString());
            Integer customerId = token1.getCusId();
            String customerName = token1.getCusName();
            Long customerTelno = token1.getCustomerTelno();
            LocalDate customerCreateDate = token1.getCustomerCreateDate();
            String customerHeadImg = token1.getCustomerHeadImg();
            log.info("用户id = " + customerId);
            log.info("用户名 = " + customerName);
            log.info("用户手机 = " + customerTelno);
            log.info("用户注册时间 = " + customerCreateDate);
            log.info("用户头像URL = " + customerHeadImg);
            Date expiresAt = jwt.getExpiresAt();
            log.info("当前token过期时间:" + expiresAt.toString());
        }catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
            System.out.println("token无效");
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }
    private static String getCusName(String token){
        try{
            log.info("获得用户名方法内的token=" + token);
            DecodedJWT jwt= JWT.decode(token);
            CustomerTokenVO token1 = JSON.to(CustomerTokenVO.class, jwt.getClaim("token").asString());
            return token1.getCusName();
        }catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Integer getCusId(String token){
        try{
            DecodedJWT jwt= JWT.decode(token);
            CustomerTokenVO token1 = JSON.to(CustomerTokenVO.class, jwt.getClaim("token").asString());
            return token1.getCusId();
        }catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getCusNameBySession(HttpServletRequest request){
        return getCusName((String)request.getSession().getAttribute("token"));
    }
    public static Integer getCusIdBySession(HttpServletRequest request){
        return getCusId((String)request.getSession().getAttribute("token"));
    }


}
