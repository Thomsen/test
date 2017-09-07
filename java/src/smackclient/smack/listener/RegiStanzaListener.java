package smackclient.smack.listener;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;

public class RegiStanzaListener implements StanzaListener {

	@Override
	public void processPacket(Stanza packet) throws NotConnectedException {
		System.out.println("process regi stanza: " + packet.toXML());
		if (packet instanceof IQ) {
			
		}
	}

}
