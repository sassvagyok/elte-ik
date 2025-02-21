#include "color.h"

void color_print(enum color c) {
    char choosenColor[8];

    switch (c)
    {
    case 0:
        strcpy(choosenColor, BG_BLACK);
        break;
    case 1:
        strcpy(choosenColor, BG_RED);
        break;
    case 2:
        strcpy(choosenColor, BG_GREEN);
        break;
    case 3:
        strcpy(choosenColor, BG_YELLOW);
        break;
    case 4:
        strcpy(choosenColor, BG_BLUE);
        break;
    case 5:
        strcpy(choosenColor, BG_MAGENTA);
        break;
    case 6:
        strcpy(choosenColor, BG_CYAN);
        break;
    case 7:
        strcpy(choosenColor, BG_WHITE);
        break;
    default:
        break;
    }

    printf("%s %s", choosenColor, RESET);
}

void image_print(Image img) {
    for (int i = 0; i < img.height; i++) {
        for (int j = 0; j < img.width; j++) {
            color_print(img.color[i][j]);
        }
        printf("\n");
    }
}

void read_image(Gif* gif, char* file, int index) {
    FILE* f = fopen(file, "r");
    if (f == NULL) {
        printf("Nem talalhato fajl: %s\n", file);
        exit(1);
    }

    fscanf(f, "%d", &gif->imgs[index].width);
    fscanf(f, "%d", &gif->imgs[index].height);

    gif->imgs[index].color = malloc(gif->imgs[index].height * sizeof(int*));
    for (int i = 0; i < gif->imgs[index].height; i++) {
        gif->imgs[index].color[i] = malloc(gif->imgs[index].width * sizeof(int));
    }

    for (int i = 0; i < gif->imgs[index].height; i++) {
        for (int j = 0; j < gif->imgs[index].width; j++) {
            fscanf(f, "%d", &gif->imgs[index].color[i][j]);
        }
    }

    fclose(f);
}

void create_gif(Gif* gif) {
    char buffer[20];
    char extension[4] = ".bg";

    printf("Add meg a bemeneti fajlok nevet: ");
    fgets(buffer, 20, stdin);
    buffer[strcspn(buffer, "\n")] = '\0';
    strcat(buffer, extension);

    for (int i = 0; i < 10; i++) {
        char indexStr[2];
        sprintf(indexStr, "%d", i);

        char* fileName = malloc(sizeof(char) * (strlen(buffer) + strlen(indexStr)));
        strcpy(fileName, buffer);
        strcat(fileName, indexStr);

        read_image(gif, fileName, i);

        free(fileName);
    }
    
    print_gif(*gif);
}

void print_gif(Gif gif) {
    for (int i = 0; i < 10; i++) {
        printf("%s %s", TERMINAL_CLEAR, TERMINAL_HOME);
        image_print(gif.imgs[i]);
        Sleep(500);
    }
    
    free_images(&gif);
}

void free_images(Gif* gif) {
    for (int n = 0; n < 10; n++) {
        for (int i = 0; i < gif->imgs[n].height; i++) {
            free(gif->imgs[n].color[i]);
        }
        free(gif->imgs[n].color);
    }
}