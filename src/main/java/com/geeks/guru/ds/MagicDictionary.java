package com.geeks.guru.ds;

import java.util.*;
import java.util.stream.Collectors;

class MagicDictionary {

    Set<String> dictionary;

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {
        dictionary = new HashSet<>();
    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        dictionary.addAll(Arrays.asList(dict));
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        Map<Character, Integer> searchMap = new HashMap<>();
        for (String dict : dictionary) {
            if (dict.length() != word.length()) continue;
            searchMap.clear();
            for (char c : dict.toCharArray()) {
                searchMap.merge(c, 1, Integer::sum);
            }
            List<Character> characterList = word.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
            for (int i = 0; i < characterList.size(); i++) {
                searchMap.merge(characterList.get(i), 1, (oldVal, newVal) -> oldVal - newVal > 0 ? oldVal - newVal : null);
                characterList.remove(i);
            }
            return characterList.size() == 1 && searchMap.size() == 1 && searchMap.values().stream().mapToInt(i -> i).sum() == 1;
        }
        return false;
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "hallo", "leetcode", "judge", "judgg"});
        System.out.println(magicDictionary.search("ggdge"));
    }
}
