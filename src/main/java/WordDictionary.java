import java.util.HashMap;
import java.util.Map;

public class WordDictionary {

    Trie trie;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        trie = new Trie();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        Trie temp = trie;
        for (int i = 0; i < word.length(); i++) {
            temp = temp.children.computeIfAbsent(word.charAt(i), val -> new Trie());
        }
        temp.endOfWord = true;
    }


    private void addWordUtil(String word, int pos, Trie root) {
        if (pos == word.length()) {
            root.endOfWord = true;
            return;
        }
        Trie curr = root.children.get(word.charAt(pos));
        if (curr == null) {
            curr = new Trie();
            root.children.put(word.charAt(pos), curr);
        }
        addWordUtil(word, pos + 1, curr);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return searchUtil(word, 0, trie);
    }

    private boolean searchUtil(String word, int pos, Trie root) {
        if (pos == word.length()) return root.endOfWord;
        root = root.children.get(word.charAt(pos));
        if (root == null) return false;
        return searchUtil(word, pos + 1, root);
    }

    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("mad");
        System.out.println(dictionary.search("mad"));
    }
}

class Trie {
    boolean endOfWord;
    Map<Character, Trie> children;

    Trie() {
        endOfWord = false;
        children = new HashMap<>();
    }

    Trie(boolean endOfWord) {
        endOfWord = true;
        children = new HashMap<>();
    }
}