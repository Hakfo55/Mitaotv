package com.mountain.common.util;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.google.common.collect.Lists;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class QrcodeUtil {
	public static void createQrcode(String path, String content, Integer width) throws Exception {
		List<Path> generatedQrcodePaths = Lists.newArrayList();
        byte[] bytes = QrcodeUtils.createQrcode(content, width, null);
		Path pathFile = new File(path).toPath();
		generatedQrcodePaths.add(pathFile);
        Files.write(pathFile, bytes);
	}
	
	public static void main(String[] a)  throws Exception{
        QrcodeUtil qrcodeUtil = new QrcodeUtil();
        for(int i=0;i<2;i++) {
			createQrcode("/home/chzz/Temp/"+i+".jpg", "aaa", 150);
            System.out.println("正在生成第"+i+"张");
        }

    }
}
