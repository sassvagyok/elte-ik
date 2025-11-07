package data.structure;

import java.util.ArrayList;
import java.util.LinkedList;

public class RangedStack<E> {
    private LinkedList<E> elems;

    public RangedStack() {
        this.elems = new LinkedList<>();
    }

    public RangedStack(RangedStack<E> other) {
        this.elems = new LinkedList<>(other.elems);
    }

    public ArrayList<E> pop(int count) {
        ArrayList<E> popped = new ArrayList<>();

        for (int i = 0; i < count && !elems.isEmpty(); i++) {
            popped.add(elems.removeLast());
        }

        return popped;
    }

    @SuppressWarnings("unchecked") 
    public void push(E... elements) {
        for (E element : elements) {
            elems.add(element);
        }
    }
}