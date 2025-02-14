#include <stdio.h>

int main() {
    
    for (int i = 1; i<=10; i++){
        for (int n = 1; n<=10; n++){
            printf("%d\t", i*n);
        }
        printf("\n");
    }

    return 0;
}