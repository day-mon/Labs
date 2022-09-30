#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>


int main(int argc, char **argv) {
    // read in command line arg for a, b, or c then put it into a char named arg
    char arg = 'n';
    for (int i = 0; i < argc; i++) {
        if (strcmp(argv[i], "a") == 0) {
             arg = 'a';
        } else if (strcmp(argv[i], "b") == 0) {
             arg = 'b';
        } else if (strcmp(argv[i], "c") == 0) {
             arg = 'c';
        }
    }
    // check if arg is still n and if so print usage and exit
    if (arg == 'n') {
        printf("Usage: %s -a|-b|-c", argv[0]);
        exit(1);
    }


    int pid = getpid();
    printf("PID for this process: %d\n", pid);


    switch (arg) {
        case 'a':
            for (int i = 0; i < 3; i++) {
                int p = fork();
                if (p == 0) {
                    // in child process print and then exit the process
                    printf("Parent PID: %d, PID: %d \n", getppid(), getpid());
                    break;
                }
            }
            break;
        case 'b':
            for (int i = 0; i < 2; i++) {
                if (pid != getpid()) {
                    break;
                }
                if (i == 0)
                {
                    if (fork() == 0) {
                        printf("Parent PID: %d, PID: %d \n", getppid(), getpid());
                    }
                }
                else if (i == 1)
                {
                    if (fork() == 0) {
                        printf("Parent PID: %d, PID: %d \n", getppid(), getpid());
                        for (int k = 0; k < 2; k++) {
                            if (fork() == 0) {
                                printf("Parent PID: %d, PID: %d \n", getppid(), getpid());
                            }
                        }
                    }
                }
            }
            break;
        case 'c':
            for (int i  = 0; i < 2; i++) {
                if (pid != getpid()) {
                    break;
                }
                if (fork() == 0) {
                    printf("Parent PID: %d, PID: %d \n", getppid(), getpid());
                    if (fork() == 0) {
                        printf("Parent PID: %d, PID: %d \n", getppid(), getpid());
                    }
                }
            }
            break;
        default:
            exit(1);
    }

    sleep(60);
    return 0;
}

