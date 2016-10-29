#include <stdio.h> /* for printf() and fprintf() */
#include <stdlib.h> /* for atoi() and exit() */
#include <string.h> /* for memset() */
#include <unistd.h> /* for close() */

#include <sys/socket.h> /* for socket, bind(), and connect() */
#include <arpa/inet.h> /* for sockaddr_in and inet_ntoa() */

#define MAX_PENDING 5 /* maximum outstanding connection requests */

void exit_with_error(char *errorMessage); /* error handling function */
void handle_tcp_client(int clientSocket); /* tcp client handling function */

int main (int argc, char *argv[])
{
  int serverSock;
  int clientSock;
  struct sockaddr_in echoServerAddr;
  struct sockaddr_in echoClientAddr;

  unsigned short echoServerPort;
  unsigned int clientLen;

  if (argc != 2)
  {
    fprintf(stderr, "Usage: %s <Server Port>\n", argv[0]);
    exit(1);
  }

  echoServerPort = atoi(argv[1]);

  /* create socket */
  if ((serverSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0 )
  {
    exit_with_error("socket() failed");
  }

  memset(&echoServerAddr, 0, sizeof(echoServerAddr)); /* zero out structure */

  echoServerAddr.sin_family = AF_INET; /* interface address family */
  echoServerAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* any incoming interface */
  echoServerAddr.sin_port = htons(echoServerPort); /* local port */

  /* bind */
  if (bind(serverSock, (struct sockaddr *) &echoServerAddr, sizeof(echoServerAddr)) < 0)
  {
    exit_with_error("bind() failed");
  }

  /* listen */
  if (listen(serverSock, MAX_PENDING) < 0)
  {
    exit_with_error("listen() failed");
  }

  for (;;)
  {
    clientLen = sizeof(echoClientAddr);

    /* accept */
    if ((clientSock = accept(serverSock, (struct sockaddr *) &echoClientAddr, &clientLen)) < 0)
    {
      exit_with_error("accept() failed");
    }

    /* connect */
    printf("client %s connected\n", inet_ntoa(echoClientAddr.sin_addr));
    handle_tcp_client(clientSock);
  }

}
