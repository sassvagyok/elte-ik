#include <stdio.h>

int main() {
    int a, b;

    printf("Elso szam: ");
    scanf("%d", &a);
    printf("Masodik szam: ");
    scanf("%d", &b);
    printf("A ket szam hanyadosa %f\n", (float)a / 0);

    return 0;
}