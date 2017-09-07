package smackclient.smack.core;

public class Client {
    /**
     * Describe username here.
     */
    private String username;
    /**
     * Describe password here.
     */
    private String password;
    /**
     * Describe host here.
     */
    private String host;
    /**
     * Describe port here.
     */
    private int port;
    
    public Client() {
    	
    }

    /**
     * Get the <code>Username</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Set the <code>Username</code> value.
     *
     * @param username The new Username value.
     */
    public final void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get the <code>Password</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Set the <code>Password</code> value.
     *
     * @param password The new Password value.
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Get the <code>Host</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getHost() {
        return host;
    }

    /**
     * Set the <code>Host</code> value.
     *
     * @param host The new Host value.
     */
    public final void setHost(final String host) {
        this.host = host;
    }

    /**
     * Get the <code>Port</code> value.
     *
     * @return an <code>Integer</code> value
     */
    public final int getPort() {
        return port;
    }

    /**
     * Set the <code>Port</code> value.
     *
     * @param port The new Port value.
     */
    public final void setPort(final int port) {
        this.port = port;
    }

}
