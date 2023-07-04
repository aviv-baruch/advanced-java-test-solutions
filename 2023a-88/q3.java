
import java.util.*;
//a.
public static <E extends Shape> String returnLargestShapeKey(HashMap<String, E> map){
    if(map.isEmpty())
        return null;
    String largestKey = map.keySet().iterator().next();
    for(String key: map.keySet()){
        if(map.get(key).getArea() > map.get(largestKey).getArea())
            largestKey = key;
    }
    return largestKey;
}

//b.
public static String returnLargestShapeKey(HashMap<String, ? extends Shape> map);


//c.
//1,2 valid, 3 לא תקין בגלל שצורה עלולה להיות משהו שהוא לא ריבוע
// שניהם לא תקינים, בגלל שהקלאס העוטף לא ממש את הקלאס העוטף השני
