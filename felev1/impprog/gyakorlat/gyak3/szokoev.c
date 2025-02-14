#include <stdio.h>

int main() {

    int ev;
    scanf("%d", &ev);

    if(ev % 4 == 0 && ev % 100 != 0 || ev % 400 == 0){
        printf("%d szokoev", ev);
    } else printf("%d nem szokoev", ev);

    // gcc -W -Wall -Wextra -pedantic -Werror

    return 0;
}