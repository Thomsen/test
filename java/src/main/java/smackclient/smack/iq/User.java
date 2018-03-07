package smackclient.smack.iq;

import org.jivesoftware.smack.packet.IQ;

public class User extends IQ {

	public User(IQ iq) {
		super(iq);
	}

	@Override
	protected IQChildElementXmlStringBuilder getIQChildElementBuilder(
			IQChildElementXmlStringBuilder xml) {
		return null;
	}

}
