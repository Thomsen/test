#include <stdio.h> /* for printf() and fprintf() */
#include <stdlib.h> /* for atoi() and exit() */
#include <string.h> /* for memset() */
#include <unistd.h> /* for close() */

#include <sys/socket.h> /* for socket, bind(), connect() and recv() */
#include <arpa/inet.h> /* for sockaddr_in and inet_addr() */

#define RECEIVE_BUFFER_SIZE 32

void exit_with_error(char *errorMessage);

int main(int argc, char *argv[])
{
  int sock;
  struct sockaddr_in echoServerAddr;
  unsigned short echoServerPort;

  char *serverIp;
  char *echoString;

  char echoBuffer[RECEIVE_BUFFER_SIZE];

  unsigned int echoStringLen;
  int bytesRcvd, totalBytesRcvd;

  if ((argc < 3) || (argc > 4))
  {
    fprintf(stderr, "Usage: %s <Server IP><Echo Word> [<Echo Port>]\n", argv[0]);
    exit(1);
  }

  serverIp = argv[1];
  echoString = argv[2];

  if (argc == 4)
  {
    echoServerPort = atoi(argv[3]);
  }
  else
  {
    echoServerPort = 7;
  }

  /* create socket */
  if ((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
  {
    exit_with_error("socket() failed");
  }

  memset(&echoServerAddr, 0, sizeof(echoServerAddr));
  echoServerAddr.sin_family = AF_INET;
  echoServerAddr.sin_addr.s_addr = inet_addr(serverIp);
  echoServerAddr.sin_port = htons(echoServerPort);

  /* connect */
  if (connect(sock, (struct sockaddr *) &echoServerAddr, sizeof(echoServerAddr)) < 0)
  {
    exit_with_error("connect() failed");
  }

  echoStringLen = strlen(echoString);

  /* send */
  if (send(sock, echoString, echoStringLen, 0) != echoStringLen)
  {
    exit_with_error("send() send a different number of bytes than expected");
  }

  totalBytesRcvd = 0;
  printf("client recv: ");

  while (totalBytesRcvd < echoStringLen)
  {
    /* recv */
    if ((bytesRcvd = recv(sock, echoBuffer, RECEIVE_BUFFER_SIZE - 1, 0)) <= 0)
    {
      exit_with_error("client recv() failed or connection close prematurely");
    }
    totalBytesRcvd += bytesRcvd;
    echoBuffer[bytesRcvd] = '\0';
    printf("%s", echoBuffer);
  }

  printf("\n");

  /* close */
  close(sock);
  exit(0);
}
