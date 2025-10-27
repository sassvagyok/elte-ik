#include <stdio.h>

/*
    %d - int
    %ld - long int
    %c - char
    %s - string
*/

char S[40];

int main() {

    scanf("%s", &S);
    printf("Hello %s!\n, S");
    printf("Sum of these two nums %d, %d is %d", 10, 20, 10+20);
    printf("Máté");
    printf("\t%f", 10 / 3.0);
    return 0;
}