package smackclient.smack.listener;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

public class NotifiStanzaListener implements StanzaListener {

	@Override
	public void processStanza(Stanza packet) throws NotConnectedException, InterruptedException {
		System.out.println("process notifi stanza: " + packet.toString());

		if (packet instanceof Message) {
			Message msg = (Message) packet;
			System.out.println("msg body: " + msg.getBody());
		}
	}
}
