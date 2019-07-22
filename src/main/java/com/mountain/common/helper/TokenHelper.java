package com.mountain.common.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mountain.common.exception.token.TokenException;
import com.mountain.common.util.response.GlobalStatusCode;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.helper
 * @Description: TODO 系统－token助手 用来做权限校验
 * @date 2019/4/15
 */
@Component
public class TokenHelper {

    /**
     * 校验token是否正确
     * @param token token
     * @param secret 平台分配的秘钥
     * @return 是否正确
     */
    public boolean verify(String token, String appId, String secret) throws TokenException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("appId", appId)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            throw new TokenException(GlobalStatusCode.CODE_400001.code(), GlobalStatusCode.CODE_400001.value());
        }
    }

    /**
     * 获取平台用户appid
     * @param token
     * @return
     * @throws TokenException
     */
    public String getAppid(String token) throws TokenException {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("appId").asString();
        } catch (JWTDecodeException e) {
            throw new TokenException(GlobalStatusCode.CODE_400001.code(), GlobalStatusCode.CODE_400001.value());
        }
    }

    /**
     *  签名
     * @param appId 接入平台分配的appId
     * @param secret 平台分配的秘钥
     * @param time 加密失效时间
     * @return 加密的token
     */
    public String sign(String appId, String secret, Integer time) throws TokenException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("appId", appId)
                    .withExpiresAt(DateUtils.addDays(new Date(), time))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new TokenException(GlobalStatusCode.CODE_400001.code(), GlobalStatusCode.CODE_400001.value());
        }
    }
}
