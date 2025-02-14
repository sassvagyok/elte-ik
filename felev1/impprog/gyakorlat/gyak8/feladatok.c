#include <stdio.h>

void factorial(int a) {
    static int count = 0;

    if(a < 1){
        count += 1;
        printf("%d\n", faktorialis);
    }
}

int masodik(int a, int b) {
    int osszeg = a + b;
    int* p = &osszeg;

    return *p;
}

int main() {
    factorial(0);

    printf("%d\n", masodik(3,4));

    return 0;
}