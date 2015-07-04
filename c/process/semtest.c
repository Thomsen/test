
#include <linux/sem.h>
#include <stdio.h>
#include <errno.h>

#include <stdlib.h>

#define SEM_PATH "/tmp/mysem"

#define max_tries 3

int semid;

main()
{
  int flag1, flag2, key, i, init_ok, tmperrno;
  struct semid_ds sem_info;
  struct seminfo sem_info2;
  union semun arg;
  struct sembuf askfor_res, free_res;
  flag1 = IPC_CREAT | IPC_EXCL | 00666;
  flag2 = IPC_CREAT | 00666;

  key = ftok(SEM_PATH, 'a');
  init_ok = 0;
  semid = semget(key, 1, flag1);
  if (semid < 0) {
    tmperrno = errno;
    perror("semget");
    if (tmperrno == EEXIST) {
      semid = semget(key, 1, flag2);
      arg.buf = &sem_info;
      for (i=0; i<max_tries; i++) {
        if (semctl(semid, 0, IPC_STAT, arg) == -1) {
          perror("semctl error");
          i = max_tries;
        } else {
          if (arg.buf->sem_otime != 0) {
            i = max_tries;
            init_ok = 1;
          } else {
            sleep(1);
          }
        }
      }
      if (!init_ok) {
        arg.val = 1;
        if (semctl(semid, 0, SETVAL, arg) == -1) {
          perror("semctl setval error");
        }
      } else {
        perror("semget error, process exit");
        exit(0);
      }
    } else {
      arg.val = 1;
      if (semctl(semid, 0, SETVAL, arg) == -1) {
        perror("semctl setval error");
      }
    }
  }
  arg.buf = &sem_info;
  if (semctl(semid, 0, IPC_STAT, arg) == -1) {
    perror("semctl IPC STAT");
  }

  printf("owner's uid is %d\n", arg.buf->sem_perm.uid);
  printf("owner's gid is %d\n", arg.buf->sem_perm.gid);
  printf("creater's uid is %d\n", arg.buf->sem_perm.cuid);
  printf("creater's gid is %d\n", arg.buf->sem_perm.cgid);

  arg.__buf = &sem_info2;
  if (semctl(semid, 0, IPC_INFO, arg) == -1) {
    perror("semctl IPC_INFO");
  }

  printf("the number of entries in semaphore map is %d\n", arg.__buf->semmap);
  printf("max number of semaphore indentifiers is %d\n", arg.__buf->semmni);
  printf("mas number of semaphores in system is %d\n", arg.__buf->semmns);
  printf("the number of undo structures system wide is %d\n", arg.__buf->semmnu);
  printf("max number of semaphores per semid is %d\n", arg.__buf->semmsl);
  printf("max number of ops per sempo call is %d\n", arg.__buf->semopm);
  printf("max number of undo entires per process is %d\n", arg.__buf->semume);
  printf("the sizeof struct sem_undo is %d\n", arg.__buf->semusz);
  printf("the maximum semaphore value is %d\n", arg.__buf->semvmx);

  askfor_res.sem_num = 0;
  askfor_res.sem_op = -1;
  askfor_res.sem_flg = SEM_UNDO;
  if (semop(semid, &askfor_res, 1) == -1) {
    perror("semop error");
  }

  sleep(3);
  printf("now free the resources\n");
  free_res.sem_num = 0;
  free_res.sem_op = 1;
  free_res.sem_flg = SEM_UNDO;
  if (semop(semid, &free_res, 1) == -1) {
    if (errno == EIDRM) {
      printf("the semaphore set was removed\n");
    }
  }
  if (semctl(semid, 0, IPC_RMID) == -1) {
    perror("semctl IPC_RMID");
  } else {
    printf("remove sem ok\n");
  }
}
