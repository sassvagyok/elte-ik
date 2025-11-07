package data.structure;

import java.util.HashMap;

public class MultiSet<E> {
    private HashMap<E, Integer> elemToCount;

    @SafeVarargs
    public MultiSet(E... elems) {
        elemToCount = new HashMap<>();

        for (E elem : elems) {
            add(elem);
        }
    }

    public int add(E elem) {
        int count = elemToCount.getOrDefault(elem, 0);

        elemToCount.put(elem, count + 1);

        return count + 1;
    }

    public int getCount(E elem) {
        return elemToCount.getOrDefault(elem, 0);
    }

    public MultiSet<E> intersect(MultiSet<E> that) {
        MultiSet<E> intersection = new MultiSet<>();

        for (E elem : elemToCount.keySet()) {
            int counter = Math.min(this.getCount(elem), that.getCount(elem));

            if (counter > 0) {
                intersection.elemToCount.put(elem, counter);
            }

            return intersection;
        }
    }
}