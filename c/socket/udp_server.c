#include <stdio.h>
#include <stdlib.h> /* for atoi() and exit() */
#include <string.h> /* for memset() */
#include <unistd.h> /* for close() */

#include <sys/socket.h> /* for socket() and bind() */
#include <arpa/inet.h> /* for sockaddr_in and inet_ntoa() */

#define RECEIVE_BUFFER_SIZE 25 /* longest string to echo */

void exit_with_error(char *errorMessage);

int main(int argc, char *argv[])
{
  int sock;
  struct sockaddr_in echoServerAddr;
  struct sockaddr_in echoClientAddr;

  unsigned int clientAddrLen;
  char echoBuffer[RECEIVE_BUFFER_SIZE];
  unsigned short echoServerPort;
  int recvMsgSize;

  if (argc != 2)
    {
      fprintf(stderr, "Usage: %<UDP SERVER PORT>\n", argv[0]);
    }

  echoServerPort = atoi(argv[1]);

  /* create socket */
  if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
    {
      exit_with_error("socket() failed");
    }

  memset(&echoServerAddr, 0, sizeof(echoServerAddr));
  echoServerAddr.sin_family = AF_INET;
  echoServerAddr.sin_addr.s_addr = htonl(INADDR_ANY);
  echoServerAddr.sin_port = htons(echoServerPort);

  /* bind */
  if (bind(sock, (struct sockaddr *) &echoServerAddr, sizeof(echoServerAddr)) < 0)
    {
      exit_with_error("bind() failed");
    }

  for (;;)
    {
      clientAddrLen = sizeof(echoClientAddr);

      /* recvfrom */
      if ((recvMsgSize = recvfrom(sock, echoBuffer, RECEIVE_BUFFER_SIZE, 0, (struct sockaddr *) &echoClientAddr, &clientAddrLen)) < 0)
        {
          exit_with_error("recvfrom() failed");
        }

      printf("Handling client %s\n", inet_ntoa(echoClientAddr.sin_addr));

      /* sendto */
      if (sendto(sock, echoBuffer, recvMsgSize, 0, (struct sockaddr *) &echoClientAddr, sizeof(echoClientAddr)) != recvMsgSize)
        {
          exit_with_error("sendto() failed");
        }
    }
}
