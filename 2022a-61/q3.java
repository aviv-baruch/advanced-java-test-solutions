//a.

public class Text implements Codeable<Text> {
    private String st = "";

    public Text(String st) {
        this.st = st;
    }

    public String getString() {
        return this.st;
    }

    // a.
    public Text code() throws NonCodeable {
        StringBuilder str = new StringBuilder();

        if (st.length() < 2)
            throw new NonCodeable("String is shorter than 2 chars");

        char temp = str.charAt(str.length() - 1);
        for (int i = 0; i < str.length() - 2; i++)
            str.append(st.charAt(i));

        str.insert(0, temp);

        return str.toString();
    }

    // b.
    public static <E extends Codeable<E>> ArrayList<E> coder(ArrayList<E> arrList) {
        ArrayList<E> res = new ArrayList<>();

        for (E item : arrList) {
            try {
                res.add(item.code());
            } catch (NonCodeable e) {
                res.add(item);
            }
        }
        return res;
    }

    // c.
    /*
     * 1. לא תקין, Codeable<?> is not subtype of Codeable<Object>, unless ? is
     * Object, but the complier cannot know that.
     * 2. תקין
     * 3. לא תקין Codeable<Text> is not subtype of Codeable<Object>
     */
}