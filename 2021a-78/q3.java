//a.
public class AnyPair<E, V> {
    private E item1;
    private V item2;

    public AnyPair(E item1, V item2) throws Exception {
        super();
        if (item1.equals(null) || item2.equals(null))
            throw new Exception("One of the items is null.");
        this.item1 = item1;
        this.item2 = item2;
    }

    public AnyPair() {
        this.item1 = null;
        this.item2 = null;
    }

    public E getFirst() {
        return this.item1;
    }

    public V getSecond() {
        return this.item2;
    }

}

    // b.

public static <E extends Compareable<E>> AnyPair minmax(E[] arrOfItems){
    E minHolder = arrOfItems[0];
    E maxHolder = arrOfItems[0];

    if(arrOfItems == 0)
    return AnyPair();
    if(arrOfItems.length == 1)
    return new AnyPair<>(arrOfItems[0],arrOfItems[0]);

    try{
        for(E item :arrOfItems){
            if(minHolder.compareTo(item) < 0){
                minHolder = item;
            }
            if(maxHolder.compareTo(item) > 0){
                maxHolder = item;
            }
        }
        return new AnyPair<>(minHolder,maxHolder);
    }catch(Exception e){
        System.out.print(e);
    }
}