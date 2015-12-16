package com.person.entity.page;

/**分页查询 */
public class Page
{
	// 当前客户选择的跳转到的页数，默认显示第一页
	private int currentPage = 1;
	// 每页固定显示的行数
	private int size = 6;
	// sql表达式不允许直接运算，所以在类中算出来给sql用
	private int begin;
	// 需要外界传入总行数条件，用于计算totalPage
	private int rows;
	// 总页数，用于页面上输出页码
	private int totalPage;

	/**
	 * 页面调用totalPage属性时，是通过该get方法调用的。 在调用该方法时，计算出totalPage的值。
	 * 此计算需要依赖rows条件，而这个条件需要在页面使用 totalPage属性之前传入到当前对象。
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
		begin = (currentPage - 1) * size;// 必须设置在这一行，get方法
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
