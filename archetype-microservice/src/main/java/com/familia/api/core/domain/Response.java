package com.familia.api.core.domain;

public class Response<T> {

	private int state;
	private String userMessage;
	private String developerMessage;
	private String moreInfo;
	private T data;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [state=" + state + ", userMessage=" + userMessage + ", developerMessage=" + developerMessage
				+ ", moreInfo=" + moreInfo + ", data=" + data + "]";
	}

}
