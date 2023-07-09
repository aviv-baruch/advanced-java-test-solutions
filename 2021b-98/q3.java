//a. 
public class Sentence implements Flipable<Sentence> {
    String[] strArr;

    public Sentence(String[] strArr) {
        this.strArr = strArr.clone();
    }

    public Sentence flip() {
        int length = strArr.length;
        String[] res = new String[length];
        int left = 0, right = length - 1;
        while (left <= right) {
            res[left] = strArr[right];
            res[right] = strArr[left];
            left++;
            right--;
        }
        return new Sentence(res);
    }
}

// b.
/*
 * Public class MyList <E extends A> extends List<E>
 */

// c.
/*
 * All of them are fine
 */