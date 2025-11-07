#include <stdio.h>
#include <stdbool.h> //ha kell logikai

int main() {
    int a = 10;
    int b = 3.14;
    printf("%d %f\n", b, b);

    int c = 'a';
    printf("%d %c\n", c, c);

    /*
    int d = "a";
    printf("%d %s\n", d, d);
    */

    // 0: hamis barmi mas: igaz
    int e = true;
    printf("%d\n", e);
    e = false;
    printf("%d\n", e);

    bool val = true;

    return 0;
}