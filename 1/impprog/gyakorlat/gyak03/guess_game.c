#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

int generate_random_number(int min, int max);
void print_menu();
int select_menu();
void print_message(int guesses, int max);
void guessing_game(int level);
void game();

int main() {
    srand(time(NULL));
    game();
    return 0;
}

int generate_random_number(int min, int max) {
    return rand() % (max - min) + min;
}

void print_menu() {
    printf("<----------------------------->\n");
    printf(" Welcome to the guessing game!\n");
    printf("<----------------------------->\n");
    printf("1. Easy\n2. Medium\n3. Hard\n4. Exit\nPlease select: ");
}

int select_menu() {
    int choice;
    do {
        print_menu();
        scanf("%d", &choice);
    } while (choice < 1 || choice > 4);
    return choice;
}

void print_message(int guesses, int max) {
    if (guesses == 1) {
        printf("Bullseye!\n");
    }
    if (guesses > 1 && guesses < max * 0.2) {
        printf("You are good, brother!\n");
    }
    if (guesses > max * 0.2 && guesses < max * 0.4) {
        printf("Great job!\n");
    }
    if (guesses > max * 0.4 && guesses < max * 0.6) {
        printf("Not bad but not as good as I expected!\n");
    }
    if (guesses > max * 0.6 && guesses < max * 0.8) {
        printf("Ehh, be better next time!\n");
    }
    if (guesses > max * 0.8 && guesses < max) {
        printf("You are dumb!\n");
    }
}

void guessing_game(int level) {
    int max = pow(10, level);
    int target = generate_random_number(0, max);
    int guess = -1;
    int num_of_guesses = 0;
    do {
        printf("Guess: ");
        scanf("%d", &guess);
        if (guess > target) {
            printf("The number is lower!\n");
        }
        if (guess < target) {
            printf("The number is greater!\n");
        }
        num_of_guesses ++;
    } while (guess != target);
    printf("You got it!\nNumber of guesses: %d\n", num_of_guesses);
    print_message(num_of_guesses, max);
}

void game() {
    int choice;
    do {
        choice = select_menu();
        if (choice != 4) {
            guessing_game(choice);
        }
    } while (choice != 4);
}