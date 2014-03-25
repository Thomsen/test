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
  struct sockaddr_in fromAddr;

  unsigned short echoServerPort;
  unsigned int fromSize;

  char *serverIp;
  char *echoString;
  char echoBuffer[RECEIVE_BUFFER_SIZE+1];
  int echoStringLen;
  int respStringLen;


  if ((argc < 3) || (argc > 4))
    {
      fprintf(stderr, "Usage: %s <Server IP> <Echo Word> [<Echo Port>]\n", argv[0]);
    }

  serverIp = argv[1];
  echoString = argv[2];

  if ((echoStringLen = strlen(echoString)) > RECEIVE_BUFFER_SIZE)
    {
      exit_with_error("echo word too lng");
    }

  if (argc == 4)
    {
      echoServerPort = atoi(argv[3]);
    }
  else
    {
      echoServerPort = 7;
    }

  /* create socket */
  if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
    {
      exit_with_error("socket() failed");
    }

  memset(&echoServerAddr, 0, sizeof(echoServerAddr));
  echoServerAddr.sin_family = AF_INET;
  echoServerAddr.sin_addr.s_addr = inet_addr(serverIp);
  echoServerAddr.sin_port = htons(echoServerPort);

  /* sendto */
  if (sendto(sock, echoString, echoStringLen, 0, (struct sockaddr *) &echoServerAddr, sizeof(echoServerAddr)) != echoStringLen)
    {
      exit_with_error("sendto() send a different number of bytes than expected");
    }

  /* recvfrom() */
  fromSize = sizeof(fromAddr);
  if ((respStringLen = recvfrom(sock, echoBuffer, RECEIVE_BUFFER_SIZE, 0, (struct sockaddr *) &fromAddr, &fromSize)) != echoStringLen)
    {
      exit_with_error("recvfrom() failed");
    }

  if (echoServerAddr.sin_addr.s_addr != fromAddr.sin_addr.s_addr)
    {
      fprintf(stderr, "Error: received a packet from unknown source.\n");
      exit(1);
    }

  echoBuffer[respStringLen] = '\0';
  printf("Received: %s\n", echoBuffer);

  /* close */
  close(sock);
  exit(0);

}
