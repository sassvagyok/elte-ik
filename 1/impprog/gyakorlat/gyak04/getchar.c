#include <stdio.h>

char toUpper(char letter) {
    if (letter >= 'a' && letter <= 'z') {
        return letter - 32;
    }
    return letter;
}

char toLower(char letter) {
    if (letter >= 'A' && letter <= 'Z') {
        return letter + 32;
    }
    return letter;
}

char getInput() {
    char c = getchar();
    while (c == '\n') {
        c = getchar();
    }
    return c;
}

int main() {
    // char c = getInput();
    // printf("%c\n", toUpper(c));
    // char d = getInput();
    // printf("%c\n", toUpper(d));

    int n = 10;
    char c;
    int i;
    for (i = 0; i < n; i++) {
        c = getInput();
        printf("%c", c);
    }
    
    char c2;
    do {
        c2 = getchar();
        printf("%c", c2);
    } while (c2 != EOF); // CTRL Z + enter


    char ch = getInput();
    int num = ch - '0';
    printf("%d", num); // char int-té alakítása

    return 0;
}