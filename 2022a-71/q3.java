//a.

import java.util.*;public static<E extends Comparable<E>,T>HashMap<E,T>grep(HashMap<E,T>map,E element){HashMap<E,T>res=new HasMap<E,T>for(E key:map.keySet()){if(key.compareTo(element)>0){res.put(key,map.get(key));}}return res;}

public interface Countable {
    public int Count();
}

public class CountableStack <V extends Countable>{
    Stack<V> stk = new Stack<>();

    public V find(int item){
        Stack<V> tempStk = new Stack<>();
        boolean found = false;
        V res;
        while(!stk.isEmpty() && !found){
            if(V.count() == tempStk.peek()){
                found = true;
                res = tempStk.peek();
            }else{
                tempStk.push(stk.pop());
            }
        }
        while(!tempStk.isEmpty())
            stk.push(tempStk.pop());

        return found ? res : null;
    }

}