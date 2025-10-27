/*
print("Hello World: " + str(2))
print(f"Hello World: {2}")
*/

#include <stdio.h>

/*
    %d - egész számok - int
    %ld - nagy egész számok - long int
    %c - karakter - char
    %s - szöveg
    %f - lebegőpontos szám
*/

int main() {
    char name[30];
    int age;

    // char c = 'a'
    printf("Hogy hivnak? ");
    scanf("%s", name);
    char c;
    while ((c = getchar()) != '\n' && c != EOF) { }
    printf("Hany eves vagy? ");
    scanf("%d", &age);
    printf("Szia %d eves %s!\n", age, name);
    
    return 0;
}