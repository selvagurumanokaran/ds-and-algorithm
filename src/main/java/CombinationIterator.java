import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class CombinationIterator {

    private String characters;
    private StringBuilder currentString;
    private char[] map;

    public CombinationIterator(String characters, int combinationLength) {
        this.characters = characters;
        this.currentString = new StringBuilder(characters.substring(0, combinationLength));
        this.map = new char[26];
        for (int i = 0; i < characters.length(); i++) {
            this.map[characters.charAt(i) - 'a'] = 1;
        }
    }

    public String next() {
        String res = this.currentString.toString();
        int index = res.length() - 1;
        char nextCharacter = (char) ('z' + 1);
        for (; index >= 0; index--) {
            Character character = higherKey(this.currentString.charAt(index));
            if (character != null && character < nextCharacter) break;
            nextCharacter = this.currentString.charAt(index);
        }
        if (index < 0) {
            this.currentString = new StringBuilder();
            return res;
        }
        nextCharacter = this.currentString.charAt(index);
        for (; index < this.currentString.length(); index++) {
            this.currentString.setCharAt(index, higherKey(nextCharacter));
            nextCharacter = this.currentString.charAt(index);
        }

        return res;
    }

    public boolean hasNext() {
        return this.currentString.length() != 0;
    }

    private Character higherKey(char c) {
        System.out.println("==================");
        for (int i = c - 'a' + 1; i < 26; i++) {
            System.out.println("char " + c + " index " + i);
            if (this.map[i] == 1) return (char) ('a' + i);
        }
        return null;
    }


    public static void main(String[] args) {
        CombinationIterator iterator = new CombinationIterator("abcd", 4);
        System.out.println(iterator.hasNext() + " " + iterator.next());
        System.out.println(iterator.hasNext());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
//        System.out.println(iterator.hasNext() + " " + iterator.next());
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */