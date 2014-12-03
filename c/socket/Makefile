ALL:

common_objs = handle_tcp_client.o exit_with_error.o

tcpserver_objs = tcp_server.o $(common_objs)

tcpclient_objs = tcp_client.o $(common_objs)

udpserver_objs = udp_server.o $(common_objs)

udpclient_objs = udp_client.o $(common_objs)

tcpserver: $(tcpserver_objs)
	gcc -o tcpserver $(tcpserver_objs)

tcpclient: $(tcpclient_objs)
	gcc -o tcpclient $(tcpclient_objs)

udpserver: $(udpserver_objs)
	gcc -o udpserver $(udpserver_objs)

udpclient: $(udpclient_objs)
	gcc -o udpclient $(udpclient_objs)

clean:
	rm tcpserver $(tcpserver_objs) \
	   tcpclient $(tcpclient_objs) \
	   udpserver $(udpserver_objs) \
	   udpclient $(udpclient_objs) 

udp_server.o: udp_server.c
	gcc -c udp_server.c
