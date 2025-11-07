#include <stdio.h>

int sum(int T[], int size) {
    int n = 0;
    for (int i = 0; i < size; i++) {
        n += T[i];
    }
    return n;
}

int max(int T[], int size) {
    int n = 0;
    for (int i = 0; i < size; i++) {
        if (T[i] > n) {
            n = T[i];
        }
    }
    return n;
}

int min2(int T[], int size) {
    if (size < 2) {
        return -1;
    }
    int minimum = T[0] < T[1] ? T[0] : T[1];
    int sec_min = T[0] > T[1] ? T[0] : T[1];

    for (int i = 2; i < size; i++) {
        if (minimum > T[i]) {
            sec_min = minimum;
            minimum = T[i];
        } else if (sec_min > T[i]) {
            sec_min = T[i];
        }
    }
    return sec_min;
}

int suly(int T[], int S[], int size) {
    int n = 0;
    for (int i = 0; i < size; i++) {
        n += T[i] * S[i];
    }
    return n;
}

double atlag(int T[], int size) {
    double n = 0;
    for (int i = 0; i < size; i++) {
        n += (double)T[i];
    }
    n = n / (double)size;

    return n;
}

int words(char s1[], int s1_size, char s2[], int s2_size) {
    for (int i = 0; i < (s1_size < s2_size ? s1_size : s2_size); i++) {
        if (s1[i] < s2[i]) {
            return 1;
        } else {
            return 2;
        }
        return s1_size == s2_size ? 0 : (s1_size < s2_size ? 1 : 2);
    }
}

int length(char S[]) {
    int n = 0;
    
    while (S[n] != '\0') {
        n += 1;
    }
    return n;
}

int main() {

    // int T[] = {1,2,3,4,5};
    // int S[] = {6,7,8,9,10};
    // char G[] = "asd";

    // printf("Sum: %d\n", sum(T, sizeof(T) / sizeof(T[0])));

    // printf("Max: %d\n", max(T, sizeof(T) / sizeof(T[0])));

    // printf("Suly: %d\n", suly(T, S, sizeof(T) / sizeof(T[0])));

    // printf("Atlag: %ld\n", atlag(T, sizeof(T) / sizeof(T[0])));

    // printf("Karakterek: %d\n", length(G));

    char Text[100];
    char c = getchar();
    int size = 0;

    while (c != '\n') {
        Text[size] = c;
        size++;
        c = getchar();
    }
    Text[size] = '\0';
    printf("%s\n", Text);

    return 0;
}