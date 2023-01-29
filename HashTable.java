import java.util.Hashtable;

public class HashTable {
    public static String indexPart (int i) {
        i = i % 1000;
        String hashing;
        if (i < 10) {
            hashing = "00" + i;
        } else if (i < 100) {
            hashing = "0" + i;
        } else {
            hashing = "" + i;
        }
        return hashing;
    }

    public static int valuePart(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            int asciiVal = (int) ((Math.pow(31, i)) * (int) (s.charAt(i)));
            hash += asciiVal;

        }
        return hash;
    }
    public static String atcName(String s, int i) {
        String hashing = s+indexPart(i);
        return hashing;
    }
    public static int Indexing(String s){
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            int asciiVal = (int) ((Math.pow(31, i)) * (int) (s.charAt(i)));
            hash += asciiVal;
        }
        hash = hash%1000;
        return hash;



    }
}
