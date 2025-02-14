#include <stdio.h>

// -W -Wall -Wextra -pedantic

// int T[] -> int* T <= ugyanazt jelenti
int sum1(int* T, int size) {
    int result = 0;

    for (int i = 0; i < size; i++) {
        result += T[i];
    }
    return result;
}

// T[i] -> *(T + i)
int sum2(int* T, int size) {
    int result = 0;

    for (int i = 0; i < size; i++) {
        result += *(T + i); // &T + 4byte
    }
    return result;
}

// first, last
int sum3(int* f, int* l) {
    int result = 0;
    int i = 0;

    while (f+i < l) {
        result += *(f + (i++)); // i++ lehetne kulon is
    }
    return result;
}

float avg(int* f, int* l) {
    int result = 0;
    int i = 0;

    while (f+i < l) {
        result += *(f + (i++)); // i++ lehetne kulon is
    }
    return result / (float)(l - f);
}

int* local_variable() {
    int num = 5;
    int* p = &num;
    return p;
}

void plus1(int* p) {
    (*p)++;
}

/*
    Két azonos tömbben lévő mutatóról eldönti, hogy melyik mutat kisebb indexűre
*/
int* kisebb(int* elso, int* masodik) {
    return elso < masodik ? elso : masodik;
}

int main() {
    int* np = NULL;
    printf("np: %d", *np);

    int* lp = local_variable();
    printf("p: %d, *p: %d\n", lp, *lp);

    int n = 0;

    plus1(&n);
    printf("n+1: %d\n", n);

    int T[] = {1,2,3,4,5,6,7,8,9};

    int* less = kisebb(T+3, T+6);
    printf("Kisebb: %d, cime: %d\n", *less, less);

    // T = &(T[0])
    printf("Sum: %d\n", sum1(T, sizeof(T)/sizeof(T[0])));
    printf("Sum: %d\n", sum2(T, sizeof(T)/sizeof(T[0])));
    printf("Sum: %d\n", sum3(T, T+sizeof(T)/sizeof(T[0])));
    printf("Avg: %f\n", avg(T, T+sizeof(T)/sizeof(T[0])));

    return 0;
}