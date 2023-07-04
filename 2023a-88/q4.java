//a.
public class Storage {
    private String[] stringsToEncode;
    private boolean[] idStringsToEncode;
    private int completed;

    public Storage(String[] strArr) {
        this.stringsToEncode = strArr;
        this.idStringsToEncode = new boolean[strArr.length];
        this.completed = 0;

    }

    public synchronized Data getData(Data data) {
        Data retData = null;
        for (int i = 0; i < stringsToEncode.length; i++) {
            if (idStringsToEncode[i] == false) {
                retData = new Data(stringsToEncode[i], i);
                idStringsToEncode[i] = true;
                return retData;
            }
        }
        return null;
    }

    public synchronized void setData(Data data) {
        idStringsToEncode[data.pos] = true;
        stringsToEncode[data.pos] = data.text;
        completed++;
        if (completed == stringsToEncode.length)
            notifyAll();
    }

    public synchronized String[] getResult(Data data) {
        while (completed < stringsToEncode.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return stringsToEncode;
    }
}

// b.
public class Coder implements Runnable {
    private Storage storage;
    private int id;

    public Coder(Storage storage, int id) {
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void run() {
        Data data = storage.getData();
        while (data != null) {
            data.text = encode(data.text);
            storage.setData(data);
            data = storage.getData();
        }
    }

    private String encode(String text) {
        StringBuilder encoded = new StringBuilder(text);

        for (int i = 0; i < text.length() / 2; i++) {
            char tempChar = encoded.charAt(i);
            encoded.setCharAt(i, encoded.charAt(text.length() - 1 - i));
            encoded.setCharAt(text.length() - 1 - i, tempChar);
        }

        return encoded.toString();
    }
}
