#include <stdio.h>
#define MAX 123

int main() {
    printf("%ld\n", sizeof(int)); // 4 byte
    printf("%ld\n", sizeof(unsigned int)); // 4 byte
    printf("%ld\n", sizeof(char)); // 1 byte
    printf("%ld\n", sizeof(_Bool)); // 1 byte
    printf("%ld\n", sizeof(long)); // 4 byte
    printf("%ld\n", sizeof(float)); // 4 byte
    printf("%ld\n", sizeof(long int)); // 4 byte
    printf("%ld\n", sizeof(double)); // 8 byte
    printf("%ld\n", sizeof(long double)); // 16 byte

    int a = 2147483647;
    printf("%d %d", a, a + 1);

    return 0;
}