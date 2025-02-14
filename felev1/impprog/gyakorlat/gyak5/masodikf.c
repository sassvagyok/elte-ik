#include <stdio.h>

int main () {
    int T[] = {1,2,3,4,5};

    int min = T[0];
    int max = T[0];
    int mindInd = 0;
    int maxInd = 0;

    for (int i = 0; i < 5; i++) {
        if (T[i] < min) {
            min = T[i];
            mindInd = i;
        }
        if (T[i] > max) {
            max = T[i];
            maxInd = i;
        }
    }
    printf("%d", maxInd);
    T[mindInd] = max;
    T[maxInd] = min;

    for (int i = 0; i < sizeof(T) / sizeof(T[0]); i++) {
        printf("%d\n", T[i]);
    }
}