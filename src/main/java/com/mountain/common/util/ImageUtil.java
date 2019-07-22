package com.mountain.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageUtil {
	
	public static String IMAGE_TYPE_GIF = "gif";// 图形交换格式
    public static String IMAGE_TYPE_JPG = "jpg";// 联合照片专家组
    public static String IMAGE_TYPE_JPEG = "jpeg";// 联合照片专家组
    public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
    public static String IMAGE_TYPE_PNG = "png";// 可移植网络图形
    public static String IMAGE_TYPE_PSD = "psd";// Photoshop的专用格式Photoshop
	private static Font font = new Font("宋体", Font.PLAIN, 30);// 默认字体的属性设置

	private static Graphics2D g = null;

	private static int fontsize = 0;

	/**
	 * 
	 * @Title: 构造图片
	 * @Description: 生成水印并返回java.awt.image.BufferedImage
	 * @param file
	 *            源文件(图片)
	 * @param waterFile
	 *            水印文件(图片)
	 * @param x
	 *            距离右下角的X偏移量
	 * @param y
	 *            距离右下角的Y偏移量
	 * @param alpha
	 *            透明度, 选择值从0.0~1.0: 完全透明~完全不透明
	 * @return BufferedImage
	 * @throws IOException
	 */
	public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) {
		try {
			// 获取底图
			BufferedImage buffImg = ImageIO.read(file);
			// 获取层图
			BufferedImage waterImg = ImageIO.read(waterFile);
			// 创建Graphics2D对象，用在底图对象上绘图
			Graphics2D g2d = buffImg.createGraphics();
			int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
			int waterImgHeight = waterImg.getHeight();// 获取层图的高度
			// 在图形和图像中实现混合和透明效果
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 绘制
			g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
			g2d.dispose();// 释放图形上下文使用的系统资源
			return buffImg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: 构造图片
	 * @Description: 生成水印并返回java.awt.image.BufferedImage
	 * @param file
	 *            源文件(图片)
	 * @param waterFile
	 *            水印文件(图片)
	 * @param x
	 *            距离右下角的X偏移量
	 * @param y
	 *            距离右下角的Y偏移量
	 * @param alpha
	 *            透明度, 选择值从0.0~1.0: 完全透明~完全不透明
	 * @return BufferedImage
	 * @throws IOException
	 */
	public static BufferedImage watermark(BufferedImage b1, BufferedImage b2, int x, int y, float alpha){
		
		// 创建Graphics2D对象，用在底图对象上绘图
		Graphics2D g2d = b1.createGraphics();
		int waterImgWidth = b2.getWidth();// 获取层图的宽度
		int waterImgHeight = b2.getHeight();// 获取层图的高度
		// 在图形和图像中实现混合和透明效果
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// 绘制
		g2d.drawImage(b2, x, y, waterImgWidth, waterImgHeight, null);
		g2d.dispose();// 释放图形上下文使用的系统资源
		return b1;
	}
	
	/**
	 * 输出水印图片
	 * 
	 * @param buffImg
	 *            加水印之后的BufferedImage对象
	 * @param savePath
	 *            加水印之后的保存路径
	 */
	public static void generateWaterFile(BufferedImage buffImg, String savePath) {
		int temp = savePath.lastIndexOf(".") + 1;
		try {
			ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 导入本地图片到缓冲区
	 * @param imgName
	 * @return
	 */
	public BufferedImage loadImageLocal(String imgName) {
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 按照比例放大缩小图片
	 * 
	 * @param srcImageFile
	 *            图片路径
	 * @param result
	 *            缩放后图片路径
	 * @param scale
	 *            缩放倍率
	 * @param flag
	 *            true:放大 false 缩小
	 * @param format
	 *            生成图片格式
	 */
	public final static BufferedImage scale(URL srcImageFile, String result, int scale, boolean flag, String format) {
		try {
			BufferedImage src = ImageIO.read(srcImageFile); // 读入文件
			int width = src.getWidth();
			int height = src.getHeight();
			if (flag) {// 放大
				width = width * scale/100;
				height = height * scale/100;
			} else {// 缩小
				//width = width / scale;
				//height = height / scale;
				width = height =106;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			if(result != null)
				ImageIO.write(tag, format, new File(result));// 输出到文件流
			return tag;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成新图片到本地
	 * 
	 * @param newAddress
	 * @param img
	 * @param format
	 *            生成图片格式
	 */
	public static void writeImageLocal(String newAddress, BufferedImage img, String format) {

		try {
			File outputfile = new File(newAddress);
			ImageIO.write(img, format, outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage modifyImage(BufferedImage img, Object content, int x, int y) {

		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.BLACK);
			g.setColor(Color.BLACK);// 设置字体颜色
			if (font != null)
				g.setFont(font);
			// 验证输出位置的纵坐标和横坐标
			if (x >= h || y >= w) {
				x = h - fontsize + 2;
				y = w;
			}
			if (content != null) {
				g.drawString(content.toString(), y, x);
			}
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img;
	}

}
