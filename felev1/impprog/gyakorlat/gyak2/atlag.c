#include <stdio.h>

int main() {
    float elso;
    float masodik;
    //int elso, masodik;

    // scanf("%d", &elso);
    scanf("%f\n", &elso);
    // scanf("%d", &masodik);
    scanf("%f", &masodik);

    printf("Atlag: %f", (elso + masodik) / 2);
    //printf("Atlag: %f", (float)(elso + masodik) / 2);

    return 0;
}