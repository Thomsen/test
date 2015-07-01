
#include <stdio.h>
#include <sys/types.h>
#include <sys/msg.h>
#include <unistd.h>
#include <errno.h> // find /usr/include -name errno.h

void msg_stat(int, struct msqid_ds);

main()
{
  int gflags, sflags, rflags;
  key_t key;
  int msgid;
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

  char* msgpath = "/tmp/msgqueue";
  //key = ftok(msgpath, 'a');

  struct timeval timenow;
  gettimeofday(&timenow, NULL);
  key = (key_t) timenow.tv_usec;
  gflags = IPC_CREAT | IPC_EXCL;
  msgid = msgget(key, gflags|0666);
  if (msgid == -1) {
    //printf("msg create error\n");
    fprintf(stderr, "msg create failed with error: %d\n", errno); // 17 EEXIST
    if (errno == EEXIST) {
      // ll /dev/mqueue
      reval = msgctl(msgid, IPC_RMID, NULL);
      if (reval == -1) {
        printf("unlink msg queue error\n");
        return ;
      }
    }
    return;
  }

  msg_stat(msgid, msg_ginfo);

  sflags = IPC_NOWAIT; // <bits/ipc.h>
  msg_sbuf.mtype = 10;
  msg_sbuf.mtext[0] = 'a';

  reval = msgsnd(msgid, &msg_sbuf, sizeof(msg_sbuf.mtext), sflags);
  if (reval == -1) {
    printf("message send error\n");
  }

  msg_stat(msgid, msg_ginfo);

  rflags = IPC_NOWAIT|MSG_NOERROR;
  reval = msgrcv(msgid, &msg_rbuf, 4, 10, rflags);

  if (reval == -1) {
    printf("read msg error\n");
  } else {
    printf("read from msg queue %d bytes \n", reval);
  }

  msg_stat(msgid, msg_ginfo);

  msg_sinfo.msg_perm.uid =8;
  msg_sinfo.msg_perm.gid = 8;
  msg_sinfo.msg_qbytes = 16388;

  reval = msgctl(msgid, IPC_SET, &msg_sinfo);
  if (reval == -1) {
    printf("msg set info error\n");
    return;
  }

  msg_stat(msgid, msg_ginfo);

  reval = msgctl(msgid, IPC_RMID, NULL);
  if (reval == -1) {
    printf("unlink msg queue error\n");
    return ;
  }
}

void msg_stat(int msgid, struct msqid_ds msg_info)
{
  int reval;
  sleep(1);
  reval = msgctl(msgid, IPC_STAT, &msg_info);
  if (reval == -1) {
    printf("get msg info error\n");
    return ;
  }

  printf("\n");
  printf("current number of bytes on queue is %d\n", msg_info.msg_cbytes);
  printf("number of messages in queue is %d\n", msg_info.msg_qnum);
  printf("max number of bytes on queue is %d\n", msg_info.msg_qbytes);

  printf("pid of last msgsnd is %d\n", msg_info.msg_lspid);
  printf("pid of last msgrcv is %d\n", msg_info.msg_lrpid);

  //printf("last msgsnd time is %s\n", ctime(&(msg_info.msg_stime)));
  //printf("last msgrcv time is %s\n", ctime(&(msg_info.msg_rtime)));
  printf("last change time is %s\n", ctime(&(msg_info.msg_ctime)));
  printf("msg uid is %d\n", msg_info.msg_perm.uid);
  printf("msg gid is %d\n", msg_info.msg_perm.gid);

}
