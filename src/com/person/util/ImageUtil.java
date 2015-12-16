package com.person.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 生成验证码图片工具类
 */
public final class ImageUtil
{
	/**
	 * 创建一个验证码图片，其中Map的key封装了 验证码字符串，Map的value封装了验证码图片。
	 */
	public static Map<String, BufferedImage> createImage()
	{
		// 创建画布
		BufferedImage image = new BufferedImage(75, 30, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();// 创建画笔
		g.setColor(new Color(255, 255, 255));// 给画笔设置成白色
		g.fillRect(0, 0, 80, 30);// 把画布涂成白色

		Random ran = new Random();
		// 先构造一个字符串
		String str = "ABCDEFGHJKLMNPQRSTUVWXY3456789";
		String answer = "";
		for (int i = 0; i < 4; i++)
		{
			char temp = str.charAt(ran.nextInt(str.length()));
			answer += temp;
		}

		// 再给画笔设置颜色，颜色随机，每次画出来不一样
		g.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
		// 给画笔设置字体，格式，大小
		g.setFont(new Font(null, Font.ITALIC, 24));
		// 设置画笔从（3,25）向东北方向画answer
		g.drawString(answer, 3, 25);

		// 给验证码上添加干扰线
		for (int i = 0; i < 4; i++)
		{
			g.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
			// 两点确定一条直线（x1,y1,x2,y2）
			g.drawLine(ran.nextInt(80), ran.nextInt(30), ran.nextInt(80), ran.nextInt(30));
		}
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(answer, image);
		return map;
	}
}
