package line;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class MyCode {

    public static void main(String[] args) throws java.lang.Exception {
        Scanner scanner = new Scanner(System.in);
        String line;
        LRU lru = new LRU();
        while ((line = scanner.next()) != null) {
            if (line.equals("exit")) break;
            String[] segments = line.split(" ");
            if (segments[0].equals("add")) lru.add(Integer.parseInt(segments[1]), Integer.parseInt(segments[2]));
            if (segments[0].equals("get")) lru.get(Integer.parseInt(segments[1]));
            if (segments[0].equals("evict")) lru.evict();
            if (segments[0].equals("remove")) lru.remove(Integer.parseInt(segments[1]));
        }

        //generateParenthesis(Integer.parseInt(input), 0, 0, "");
    }

    //Quest 1
    private static void generateParenthesis(int n, int open, int close, String s) {
        if (s.length() == n * 2) {
            System.out.println(s);
        }

        if (open != n) {
            String s1 = s + "(";
            generateParenthesis(n, open + 1, close, s1);
        }

        if (close < open) {
            String s1 = s + ")";
            generateParenthesis(n, open, close + 1, s1);
        }
    }

    //Ques 2
    private static int getRunningProcess(BufferedReader br, int t) throws IOException {
        Set<Character> runningProcesses = new HashSet<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] segments = line.split(" ");
            int currTime = Integer.parseInt(segments[0]);
            if (currTime > t) break;
            if (segments[2].equals("running")) {
                runningProcesses.add(segments[1].charAt(0));
            } else {
                runningProcesses.remove(segments[1].charAt(0));
            }
        }

        if (runningProcesses.size() == 1) runningProcesses.iterator().next();
        return -1;
    }

    //Question 3
    // https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/


    class Node {
        int key;
        int val;
        Node next;
        Node prev;
    }

    static class LRU {
        Map<Integer, Node> map;
        Node head;
        Node tail;

        LRU() {
            map = new HashMap<>();
        }

        public void add(int key, int val) {
            Node node = map.get(key);
            if (node != null) {
                node.val = val;
            } else {
               /* node = new Node();
                node.key = key;
                node.val = val;*/
            }
            map.put(key, node);
            removeFromList(node);
            addFirst(node);
        }

        public int get(int key) throws Exception {
            Node node = map.get(key);
            if (node == null) throw new Exception("No item found!");
            addFirst(node);
            return node.val;
        }

        public int remove(int key) throws Exception {
            Node node = map.get(key);
            if (node == null) throw new Exception("No item found!");
            map.remove(node.key);
            removeFromList(node);
            return node.val;
        }

        public void evict() {
            if (tail != null) {
                map.remove(tail.key);
                removeFromList(tail);
            }
        }


        private void addFirst(Node node) {
            node.next = head;
            node.prev = null;
            if (head != null) head.prev = node;
            head = node;

            if (tail == null) tail = head;
        }

        private void removeFromList(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }
        }
    }

}
