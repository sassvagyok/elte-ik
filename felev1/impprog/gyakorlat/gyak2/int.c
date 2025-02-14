#include <stdio.h>
// py: num = 10

int main() {
    const int num = 10;
    //num = 20;
    printf("A szám: %s\n", num % 2 == 0 ? "Páros" : "Páratlan");

    return 0;
}