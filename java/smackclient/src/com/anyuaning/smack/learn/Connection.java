package com.anyuaning.smack.learn;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.TopLevelStreamElement;

public class Connection extends AbstractXMPPConnection {

    public static final String HOST = "192.168.1.130";
    public static final int PORT = 5222;

    private boolean reconnect = false;

    public static BlockingQueue<TopLevelStreamElement> queue = new LinkedBlockingQueue<TopLevelStreamElement>();
    
    private Socket socket;
//    private PacketWriter packetWriter;  // smack-tcp protected

    public static ConnectionConfiguration.Builder<?,?> getConnectionBuilder() {
        Configuration.Builder builder = Configuration.builder();
        builder.setHost(HOST);
        builder.setPort(PORT);

        builder.setServiceName(HOST);
        builder.setSecurityMode(SecurityMode.required);

        return builder;
    }
    public Connection() {
        this(getConnectionBuilder().build());
    }

    public Connection(ConnectionConfiguration configuration) {
        super(configuration);

//        for (ConnectionCreationListener listener : XMPPConnectionRegistry.getConnectionCreationListeners()) { // no package protected perm
//            listener.connectionCreated(this);
//        }

        user = config.getUsername()
            + "@"
            + config.getServiceName()
            + "/"
            + (config.getResource() != null ? config.getResource() : "Test");
    }

    @Override
    public boolean isSecureConnection() {
        return false;
    }

    @Override
    protected void sendStanzaInternal(Stanza packet)
        throws NotConnectedException {
        if (SmackConfiguration.DEBUG) {
            System.out.println("[SEND]: " + packet.toXML());
        }
        queue.add(packet);
    }

    @Override
    public void send(PlainStreamElement element) throws NotConnectedException {
        if (SmackConfiguration.DEBUG) {
            System.out.println("[SEND]: " + element.toXML());
        }
        queue.add(element);
    }

    @Override
    public boolean isUsingCompression() {
        return false;
    }

    @Override
    protected void connectInternal() throws SmackException, IOException,
                                            XMPPException {
        connected = true;
        streamId = "smack-" + new Random(new Date().getTime()).nextInt();
        
        this.socket = new Socket(HOST, PORT);

        if (reconnect) {
            notifyReconnection();
        }
    }

    @Override
    protected void loginNonAnonymously(String username, String password,
                                       String resource) throws XMPPException, SmackException, IOException {
        StringBuilder sbud = new StringBuilder();
        sbud.append(username);
        sbud.append("@");
        sbud.append(config.getServiceName());
        sbud.append("/");
        sbud.append((resource != null ? resource : "Smack"));
        user = sbud.toString();

        authenticated = true;
    }

    @Override
    protected void loginAnonymously() throws XMPPException, SmackException,
                                             IOException {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected to server.");
        }

        if (!isAuthenticated()) {
            throw new IllegalStateException("Already logged in to server.");
        }

        authenticated = true;
    }

    @Override
    protected void shutdown() {
        user = null;
        authenticated = false;
//        callConnectionClosedListener(); // no package default perm
        reconnect = true;
    }

    public static class Configuration extends ConnectionConfiguration {
        protected Configuration(Builder builder) {
            super(builder);
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder extends ConnectionConfiguration.Builder<Builder, Configuration> {
            private Builder() {}

            @Override
            public Configuration build() {
                return new Configuration(this);
            }

            @Override
            protected Builder getThis() {
                return this;
            }

        }
    }

}
