package smackclient.smack.iq;

import java.util.List;

public class NotiMsg {
	
	private String from;
	
	private String body;
	
	private List<String> to;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}
}
