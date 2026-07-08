#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_LINE 1024
#define MAX_ARGS 64

// Function to parse the command line into arguments
void parse_command(char *line, char **args)
{
    int i = 0;

    args[i] = strtok(line, " \t\n");

    while (args[i] != NULL)
    {
        i++;
        args[i] = strtok(NULL, " \t\n");
    }
}

int main()
{
    char line[MAX_LINE];
    char *args[MAX_ARGS];
    pid_t pid;
    int status;

    while (1)
    {
        // Display prompt
        printf("tinysh> ");
        fflush(stdout);

        // Read input
        if (fgets(line, MAX_LINE, stdin) == NULL)
        {
            break;
        }

        // Parse command
        parse_command(line, args);

        // Empty command
        if (args[0] == NULL)
        {
            continue;
        }

        // Exit command
        if (strcmp(args[0], "exit") == 0)
        {
            break;
        }

        // Change directory
        if (strcmp(args[0], "cd") == 0)
        {
            if (args[1] == NULL)
            {
                printf("Usage: cd <directory>\n");
            }
            else
            {
                if (chdir(args[1]) != 0)
                {
                    perror("cd");
                }
            }
            continue;
        }

        // Create child process
        pid = fork();

        if (pid < 0)
        {
            perror("fork");
            continue;
        }

        if (pid == 0)
        {
            // Child process
            if (execvp(args[0], args) < 0)
            {
                perror("Command execution failed");
            }
            exit(EXIT_FAILURE);
        }
        else
        {
            // Parent waits for child
            waitpid(pid, &status, 0);
        }
    }

    printf("Exiting Tiny Shell...\n");

    return 0;
}

