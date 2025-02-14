#include <stdio.h>

void elsoFeladat() {
    int first;
    int second;

    scanf("%d %d", &first, &second);

    printf("Elso:\n");
    printf("%d\n", first + second);
    printf("%d\n", first - second);
    printf("%d\n", first / second);
    printf("%d\n", first * second);
}

void masodikFeladat() {
    int r;

    scanf("%d", &r);

    printf("Masodik:\n");
    printf("Atmero: %d\n", r * 2);
    printf("Terulet: %f\n", r * r * 3.14);
    printf("Kerulet: %f\n", 2 * r * 3.14);
}

void harmadikFeladat() {
    int datum, ev, ho, nap;
    scanf("%d", &datum);

    printf("Harmadik:\n");
    ev = datum / 10000;
    ho = (datum / 100) % 100;
    nap = datum % 100;

    printf("%d.%d.%d.\n", ev, ho, nap);
}

void otodikFeladat() {
    int a;
    int b;
    int kulonbseg;

    scanf("%d %d", &a, &b);

    if(a > b) {
        kulonbseg = a - b;
        a -= kulonbseg;
        b += kulonbseg;
    } else {
        kulonbseg = b - a;
        b -= kulonbseg;
        a += kulonbseg;
    }

    printf("Otodik:\n");
    printf("%d %d", a, b);
}

int main() {
    elsoFeladat();
    masodikFeladat();
    harmadikFeladat();
    otodikFeladat();

    return 0;
}