package br.com.wickrss.reader;

public class RSSFeedParserException extends Exception {

	private static final long serialVersionUID = 1L;

	public RSSFeedParserException(Exception e) {
		super(e);
	}

	public RSSFeedParserException(String string) {
		super(string);
	}

}
