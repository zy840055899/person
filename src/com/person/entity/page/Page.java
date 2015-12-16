package com.person.entity.page;

/**��ҳ��ѯ */
public class Page
{
	// ��ǰ�ͻ�ѡ�����ת����ҳ����Ĭ����ʾ��һҳ
	private int currentPage = 1;
	// ÿҳ�̶���ʾ������
	private int size = 6;
	// sql���ʽ������ֱ�����㣬�����������������sql��
	private int begin;
	// ��Ҫ��紫�����������������ڼ���totalPage
	private int rows;
	// ��ҳ��������ҳ�������ҳ��
	private int totalPage;

	/**
	 * ҳ�����totalPage����ʱ����ͨ����get�������õġ� �ڵ��ø÷���ʱ�������totalPage��ֵ��
	 * �˼�����Ҫ����rows�����������������Ҫ��ҳ��ʹ�� totalPage����֮ǰ���뵽��ǰ����
	 */
	public int getTotalPage()
	{
		if (rows % size == 0)
		{
			totalPage = rows / size;
		} else
		{
			totalPage = rows / size + 1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}

	public int getRows()
	{
		return rows;
	}

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	public int getBegin()
	{
		begin = (currentPage - 1) * size;// ������������һ�У�get����
		return begin;
	}

	public void setBegin(int begin)
	{
		this.begin = begin;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	@Override
	public String toString()
	{
		return "Page [currentPage=" + currentPage + ", size=" + size + ", begin=" + begin + ", rows=" + rows + ", totalPage=" + totalPage + "]";
	}
}
