#include "feladat.h"

void palindrom() {
    char str[128];
    printf("Give me smotehing: ");
    fgets(str, 128, stdin);

    char reverse_str[128];
    int first = 0;
    int last = strlen(str) - 1;
    char temp;

    while (first < last) {
        temp = str[first];
        reverse_str[first] = str[last];
        reverse_str[last] = temp;

        first++;
        last--;
    }
    
    printf("%s, %s", str, reverse_str);
    if(strcmp(str, reverse_str) != 0) {
        printf("Palindrom!\n");
    } else {
        printf("Nem palindrom...\n");
    }
}

int main(int argc, char** argv) {

    if(argc != 2) {
        printf("We need only one argument!\n");
        return 1;
    }

    int choice = atoi(argv[1]); // atoi: "123" -> 123

    switch (choice)
    {
        case 0:
            palindrom();
            break;
        default:
            printf("There is no such option!\n");
            break;
    }

    return 0;
}