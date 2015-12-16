package com.person.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ������֤��ͼƬ������
 */
public final class ImageUtil
{
	/**
	 * ����һ����֤��ͼƬ������Map��key��װ�� ��֤���ַ�����Map��value��װ����֤��ͼƬ��
	 */
	public static Map<String, BufferedImage> createImage()
	{
		// ��������
		BufferedImage image = new BufferedImage(75, 30, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();// ��������
		g.setColor(new Color(255, 255, 255));// ���������óɰ�ɫ
		g.fillRect(0, 0, 80, 30);// �ѻ���Ϳ�ɰ�ɫ

		Random ran = new Random();
		// �ȹ���һ���ַ���
		String str = "ABCDEFGHJKLMNPQRSTUVWXY3456789";
		String answer = "";
		for (int i = 0; i < 4; i++)
		{
			char temp = str.charAt(ran.nextInt(str.length()));
			answer += temp;
		}

		// �ٸ�����������ɫ����ɫ�����ÿ�λ�������һ��
		g.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
		// �������������壬��ʽ����С
		g.setFont(new Font(null, Font.ITALIC, 24));
		// ���û��ʴӣ�3,25���򶫱�����answer
		g.drawString(answer, 3, 25);

		// ����֤������Ӹ�����
		for (int i = 0; i < 4; i++)
		{
			g.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
			// ����ȷ��һ��ֱ�ߣ�x1,y1,x2,y2��
			g.drawLine(ran.nextInt(80), ran.nextInt(30), ran.nextInt(80), ran.nextInt(30));
		}
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(answer, image);
		return map;
	}
}
