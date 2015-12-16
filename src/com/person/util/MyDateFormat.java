package com.person.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**����ת�������� 2015��3��15�� 18:26:31*/
public class MyDateFormat
{
	public static Date dateFormat(String str, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try
		{
			date = sdf.parse(str);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}
}
