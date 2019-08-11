package com.familia.api.core.exception;

public class CoreException extends Exception {

	/** Serialize */
	private static final long serialVersionUID = 6365652257268547172L;

	private final String userMessage;
	private final int state;

	public CoreException(String developerMessage, String userMessage, int state, Throwable e) {
		super(developerMessage, e);
		this.userMessage = userMessage;
		this.state = state;
	}

	public CoreException(String developerMessage, String userMessage, int state) {
		super(developerMessage);
		this.userMessage = userMessage;
		this.state = state;
	}

	public CoreException(String userMessage, int state,Throwable e) {
		super(e);
		this.userMessage = userMessage;
		this.state = state;
	}

	public String getUserMessage() {
		return userMessage;
	}
	
	public int getState() {
		return state;
	}

}
