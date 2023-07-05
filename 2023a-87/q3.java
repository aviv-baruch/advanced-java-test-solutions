
import java.util.*;
//a.
public static <E extends Comparable<E>> E reduceStack (Stack<E> stack, E element){
    if(stack.isEmpty()) return null;

    Stack<E> temp = new Stack<E>();
    E lowestElement = stack.peek();
    while(!stack.isEmpty()){
        if(stack.peek().compareTo(element) > 0){
            if(stack.peek().compareTo(lowestElement) < 0){
                lowestElement = stack.pop();
            }
        }else{
            temp.push(stack.pop());
        }
    }
    while(!temp.isEmpty()){
        stack.push(temp.pop());
    }
    return lowestElement;
}

//b.
public static Computable calculate (ArrayList<? extends Computable> arr, Computable obj){
 if(arr.isEmpty()) return null;
    Computable res = null;
    for(Computable elem : arr){
        if(elem.compute().equals(obj.compute())){
             return res;
        }
    }
    return null;
}

//c.
public static <E extends Computable> calculate (ArrayList<E> arr, E obj);
//לא ניתן לאכוף בחתימת הפונקציה, אבל אפשר לאכוף במימוש הפונקציה, לדוגמה, באמצעות בדיקת instanceof
