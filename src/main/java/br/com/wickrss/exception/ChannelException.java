package br.com.wickrss.exception;

public class ChannelException extends Exception {

	public ChannelException(String message) {
		super(message);
	}

	public ChannelException(Exception e) {
		super(e);
	}

}
