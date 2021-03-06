
#include <stdio.h>
#include <sys/types.h>
#include <sys/msg.h>
#include <unistd.h>
#include <errno.h> // find /usr/include -name errno.h

#include <stdlib.h>
#include <string.h>

#include <sys/stat.h>
#include <fcntl.h>

#include <time.h>  // assignment makes pointer from integer without a cast (str_time = ctime())

#define MAX_TEXT 4

void msg_stat(int, struct msqid_ds);

main(int argc, char *argv[])
{
  int gflags, sflags, rflags;
  key_t key;
  int msgid;
  int msgtype = 0;
  int reval;
  struct msgbuf {
    int mtype;
    char mtext[1];
  } msg_sbuf;

  struct msgmbuf {
    int mtype;
    char mtext[10];
  } msg_rbuf;

  struct msqid_ds msg_ginfo, msg_sinfo;

  if (argv == NULL) {
    printf("please enter message queue file !");
  }

  char* msgdir = "/tmp/msgqueue/"; // mkdir /tmp/msgqueue
  struct timeval timenow;
  gettimeofday(&timenow, NULL);
  char* msgfile = malloc(10);
  sprintf(msgfile, "%d", timenow.tv_usec);
  
  char* msgpath = malloc(strlen(msgdir) + strlen(msgfile) + 1);
  if (msgpath == NULL) {
    exit(1);
  }
  strcpy(msgpath, msgdir);
  strcat(msgpath, msgfile);
  int fid = open(msgpath, O_RDWR | O_CREAT | O_EXCL, 0666 | S_IWOTH);
  key = ftok(msgpath, 'a');

  gflags = IPC_CREAT | IPC_EXCL;
  msgid = msgget(key, gflags|0666);
  if (msgid == -1) {
    //printf("msg create error\n");
    fprintf(stderr, "msg create failed with error: %d\n", errno); // 17 EEXIST
    return;
  }

  printf("\nmsgget");
  msg_stat(msgid, msg_ginfo);

  sflags = IPC_NOWAIT; // <bits/ipc.h>
  msg_sbuf.mtype = 10;
  msg_sbuf.mtext[0] = 'a';

  reval = msgsnd(msgid, &msg_sbuf, sizeof(msg_sbuf.mtext), sflags);
  if (reval == -1) {
    fprintf(stderr, "message send failed with erro: %dr\n", errno);
  }

  printf("\nmsgsnd");
  msg_stat(msgid, msg_ginfo);

  rflags = IPC_NOWAIT|MSG_NOERROR;
  reval = msgrcv(msgid, &msg_rbuf, MAX_TEXT, msgtype, rflags);

  if (reval == -1) {
    fprintf(stderr, "read msg failed with error: %d\n", errno); // 43 NOMSG msgtyep error
  } else {
    printf("read from msg queue %d bytes \n", reval);
  }

  printf("\nmsgrcv");
  msg_stat(msgid, msg_ginfo);

  msg_sinfo.msg_perm.uid =8;
  msg_sinfo.msg_perm.gid = 8;
  msg_sinfo.msg_qbytes = 16388;

  reval = msgctl(msgid, IPC_SET, &msg_sinfo);
  if (reval == -1) {
    fprintf(stderr, "msg set info failed with error: %d\n", errno); // 1 EPERM $ sudo
    return;
  }

  printf("\nmsgctl IPC_SET");
  msg_stat(msgid, msg_ginfo);

  reval = msgctl(msgid, IPC_RMID, NULL);
  if (reval == -1) {
    fprintf(stderr, "unlink msg queue failed with error: %d\n", errno);
    return ;
  }
}

void msg_stat(int msgid, struct msqid_ds msg_info)
{
  int reval;
  sleep(1);
  reval = msgctl(msgid, IPC_STAT, &msg_info);
  if (reval == -1) {
    printf("get msg info failed with error: %d\n", errno);
    return ;
  }

  char* str_ctime;

  printf("\n-------------------start-----------------------\n");
  printf("current number of bytes on queue is %d\n", msg_info.msg_cbytes);
  printf("number of messages in queue is %d\n", msg_info.msg_qnum);
  printf("max number of bytes on queue is %d\n", msg_info.msg_qbytes);

  printf("pid of last msgsnd is %d\n", msg_info.msg_lspid);
  printf("pid of last msgrcv is %d\n", msg_info.msg_lrpid);

  printf("last msgsnd time is %s\n", ctime(&(msg_info.msg_stime)));
  printf("last msgrcv time is %s\n", ctime(&(msg_info.msg_rtime)));

  str_ctime = ctime(&(msg_info.msg_ctime));
  printf("last change time is %s\n", str_ctime);
  
  printf("msg uid is %d\n", msg_info.msg_perm.uid);
  printf("msg gid is %d\n", msg_info.msg_perm.gid);
  printf("---------------------end------------------------\n");

}
