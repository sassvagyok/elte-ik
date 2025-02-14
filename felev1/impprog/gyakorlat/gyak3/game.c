#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int greater(int num, int guess);
int generate_random_number(int max);
int assessment(int guesses);
int menu();

int main() {

    srand(time(NULL));

    menu();

    int guess;
    int num = generate_random_number(menu());
    int guesses = 0;

    while(guess != num){
        scanf("%d", &guess);
        greater(num, guess);
        guesses += 1;
    }

    printf("Eltalaltad %d tippbol\n", guesses);
    assessment(guesses);
    menu();

    return 0;
}

int greater(int num, int guess){
    if(guess < num){
        return printf("Kisebb a szamod\n");
    }
    if(guess > num){
        return printf("Nagyobb a szamod\n");
    }
}

int generate_random_number(int max) {
    return rand() % (max - 0 + 1) + 0;
}

int assessment(int guesses){
    if(guesses == 1) return printf("Csodás\n");
    if(guesses < 3) return printf("Profi\n");
    if(guesses < 6) return printf("Nem rossz\n");
    else return printf("Gatya\n");
}

int menu(){
    int option;

    do {
        printf("1. Easy (0 - 10)\n2. Medium (0 - 100)\n3. Hard (0 - 1000)\n4. Exit\n");
        scanf("%d", &option);
    } while (option < 1 || option > 4);
    
    printf("%d. kivalasztva\n", option);
    if(option == 1) return 10;
    if(option == 2) return 100;
    if(option == 3) return 1000;
}