#include <stdio.h>

int main() {
    int num;
    
    do {
        scanf("%d", &num);
    }
    while (num % 2 == 0);
    
    return 0;
}