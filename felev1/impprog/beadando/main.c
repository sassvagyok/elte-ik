#include "color.h"

int main() {
    Gif* gif = malloc(sizeof(Gif));

    create_gif(gif);

    free(gif);

    return 0;
}