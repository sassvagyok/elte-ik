#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int generate_random_number(int min, int max) {
    return rand() % (max - min + 1) + min;
}

void function_name() {
    
}

int main() {

    srand(time(NULL));

    int max = 10;
    int min = 0;
    int szam = generate_random_number(0, 10);
    int bekert;

    while(szam != bekert){
        scanf("%d", &bekert);
    }

    printf("megvan");

    return 0;
}