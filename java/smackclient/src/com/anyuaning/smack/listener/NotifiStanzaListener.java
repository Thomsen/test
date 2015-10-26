package com.anyuaning.smack.listener;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

public class NotifiStanzaListener implements StanzaListener {

	@Override
	public void processPacket(Stanza packet) throws NotConnectedException {
		System.out.println("notifi stanza: " + packet.toString());
		
		if (packet instanceof Message) {
			Message msg = (Message) packet;
			System.out.println("msg body: " + msg.getBody());
		}
	}

}
