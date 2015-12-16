package com.person.exception;

public class SalCalculatrException extends Exception
{
	private static final long serialVersionUID = 1L;

	public SalCalculatrException()
	{
		super();
	}

	public SalCalculatrException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SalCalculatrException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SalCalculatrException(String message)
	{
		super(message);
	}

	public SalCalculatrException(Throwable cause)
	{
		super(cause);
	}
	
}
