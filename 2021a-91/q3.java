//a.
public class EvalCode extends Code implements Evaluateable {
    public int evaluate() {
        return super.getTxt().length() * super.getIndex();
    }

}

    // b.
    public static <E extends Evaluateable> E smallest(ArrayList<E> list) {
        if (list.isEmpty())
            return null;
        E smallest = list.get(0);
        for (E item : list) {
            if (smallest.evaluate() > item.evaluate())
                smallest = item;
        }
        return smallest;
    }

    // c.
public int calculate(ArrayList<? extends Evaluateable> list){
    int sum = 0;
    if(!list.isEmpty()){
    for(int i =0;i< list.size();i++)
        sum+= list.get(i).evaluate();
    }
    return sum / list.size();;
}