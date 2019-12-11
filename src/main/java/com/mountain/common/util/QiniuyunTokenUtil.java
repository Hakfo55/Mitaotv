package com.mountain.common.util;

import com.qiniu.util.Auth;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/06/10:11
 * @Description:
 */
public class QiniuyunTokenUtil {
    private static String accessKey = "0qS3gobuwx8Ku83ya70QhpdlOUyVIcltp8teSfWO";

    private static String secretKey = "w3nrDTUya9N6qsGzVQAsocr3z2BSIbLg7RXhUWSH";

    private static String bucket = "mitaotv";

    public static String getUpToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }



}
