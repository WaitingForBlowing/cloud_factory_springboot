package com.moozlee.cloud_factory.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class TokenUtil {

    public static final long EXPIRE_TIME= 10*60*1000;//token到期时间5分钟，毫秒为单位
    public static final long REFRESH_EXPIRE_TIME=30*60;//RefreshToken到期时间为30分钟，秒为单位
    private static final String TOKEN_SECRET="ljdyaishijin**3nkjnj??";  //密钥盐

    /**
     * @author       : lj
     * @return       : java.lang.String
     */
    public static String sign(String account,Long currentTime){

        String token=null;
        try {
            Date expireAt=new Date(currentTime+EXPIRE_TIME);
            token = JWT.create()
                .withIssuer("auth0")//发行人
                .withClaim("account",account)//存放数据
                .withClaim("currentTime",currentTime)
                .withExpiresAt(expireAt)//过期时间
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {
            je.printStackTrace();
        }
        return token;
    }


    /**
     * @author       : lj
     * @return       : java.lang.Boolean
     */
    public static Boolean verify(String token) throws Exception{

        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();//创建token验证器
        DecodedJWT decodedJWT=jwtVerifier.verify(token);
        System.out.println("认证通过：");
        System.out.println("account: " + decodedJWT.getClaim("account").asString());
        System.out.println("过期时间：      " + decodedJWT.getExpiresAt());
        return true;
    }



    public static String getAccount(String token){
        try{
            DecodedJWT decodedJWT= JWT.decode(token);
            return decodedJWT.getClaim("account").asString();

        }catch (JWTCreationException e){
            return null;
        }
    }
    public static Long getCurrentTime(String token){
        try{
            DecodedJWT decodedJWT=JWT.decode(token);
            return decodedJWT.getClaim("currentTime").asLong();

        }catch (JWTCreationException e){
            return null;
        }
    }

}
