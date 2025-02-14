#ifndef COLOR_H
#define COLOR_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

#define RESET "\033[0m"
#define BG_BLACK "\033[40m"
#define BG_RED "\033[41m"
#define BG_GREEN "\033[42m"
#define BG_YELLOW "\033[43m"
#define BG_BLUE "\033[44m"
#define BG_MAGENTA "\033[45m"
#define BG_CYAN "\033[46m"
#define BG_WHITE "\033[47m"
#define TERMINAL_CLEAR "\033[2J" 
#define TERMINAL_HOME "\033[2H"
#define MAX_SIZE 30

enum color {
    fekete,
    piros,
    zold,
    sarga,
    kek,
    magenta,
    cian,
    feher
};

struct _Image {
    int width;
    int height;
    int** color;
};
typedef struct _Image Image;

struct _Gif {
    Image imgs[10];
};
typedef struct _Gif Gif;

void color_print(enum color c);
void read_image(Gif* gif, char* file, int index);
void image_print(Image img);
void create_gif(Gif* gif);
void print_gif(Gif gif);
void free_images(Gif* gif);

#endif // COLOR_H