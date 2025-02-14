#include <stdio.h>

int main() {

    int T[10];
    int S[] = {1,2,3,4}; // ilyenkor nem kell megadni hány elemű
    int U[10] = {0};

    int size = sizeof(T) / sizeof(T[0]);

    for (int i = 0; i < 4; i++) {
        printf("%d\n", S[i]);
    }

    for (int i = 0; i < 10; i++) {
        T[i] = i;
    }

    return 0;
}