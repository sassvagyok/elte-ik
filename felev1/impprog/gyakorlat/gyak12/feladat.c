#include "feladat.h"

void initialize(Box** box) { // **, mert módosítjuk, ezért kell a memóriacímet ismerni / milyen címet? xd
    (*box) == NULL;
}

int isEmpty(Box* box) {
    return box == NULL;
}

Box** last(Box* box) {
    if (box == NULL) {
        return NULL;
    }

    Box* bp = box;

    while (bp->next != NULL) {
        bp = bp->next;
    }

    return &bp;
}

int peek(Box* box) {
    return (*last(box))->weight;
}

void push(Box** box, int weight) {
    if (box == NULL) {
        *box = malloc(sizeof(Box*));
        (*box)->weight = weight;
        (*box)->next = NULL;
    } else {
        Box** last_box = last(*box);
        *last_box = malloc(sizeof(Box*));
        (*last_box)->weight = weight;
        (*last_box)->next = NULL;
    }
}