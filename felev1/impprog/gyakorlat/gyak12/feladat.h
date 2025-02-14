#ifndef FELADAT_H
#define FELADAT_H
#include <stdio.h>

struct _Box {
    int weight;
    struct _Box* next;
};

typedef struct _Box Box;

void initialize(Box** box);
int isEmpty(Box* box);
int peek(Box* box);
Box** last(Box* box);
void push(Box** box, int weight);

#endif // FELADAT_H