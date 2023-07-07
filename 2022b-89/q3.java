//a.

import java.util.ArrayList;
import java.util.Objects;

public interface GenLookup<T> {
    public T find(Integer T);
}

// b.

public class GenTable<T> implements GenLookup {
    private int[] keys;
    private ArrayList<T> objects;

    public GenTable(int[] keys, ArrayList<T> objects) throws IllegalSizeException {
        if (objects.size() != keys.length) {
            throw new IllegalSizeException(e);
        } else {
            this.keys = keys;
            this.objects = objects;
        }
    }

    public T find(Integer key) {
        for (int i = 0; i < keys.length; i++) {
            if (key.equals(new Integer(keys[i]))) // manual boxing
                return objects.get(i);
        }
        return null;
    }

}

class AnotherClass {
    public static <T extends Comparable<T>, E extends GenLookup<T>> T smallest(int id, E table1, E table2) {
        T findT1 = table1.find(id);
        T findT2 = table2.find(id);

        if (findT1 == null && findT2 == null)
            return null;
        if (findT1 == null)
            return findT2;
        if (findT2 == null)
            return findT1;

        return (findT1.compareTo(findT2) >= 0) ? findT1 : findT2;
    }
}
