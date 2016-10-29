#include <stdio.h>
#include <unistd.h> /* for close() */
#include <sys/socket.h> /* for recv() and send() */

#define RECEIVE_BUFFER_SIZE 32

void exit_with_error(char *errorMessage);

void handle_tcp_client(int clientSocket)
{
  char echoBuffer[RECEIVE_BUFFER_SIZE];
  int recvMsgSize;
  char* resp = "server resp";

  /* recv */
  if ((recvMsgSize = recv(clientSocket, echoBuffer, RECEIVE_BUFFER_SIZE, 0)) < 0)
  {
    exit_with_error("recv() failed");
  }

  while (recvMsgSize > 0 )
  {

    printf("server recv: %s", echoBuffer);

    /* send */
    if (send(clientSocket, echoBuffer, recvMsgSize, 0) != recvMsgSize)
    {
      exit_with_error("send() failed");
    };

    /* recv */
    if ((recvMsgSize = recv(clientSocket, echoBuffer, RECEIVE_BUFFER_SIZE, 0)) < 0)
    {
      exit_with_error("while recv() failed");
    };
  }
  printf("\n");

  /* close */
  close(clientSocket);
}
