class MyHashSet {

    class Node {
        int key;
        Node next;
    }

    /**
     * Initialize your data structure here.
     */
    Node[] arr;

    public MyHashSet() {
        arr = new Node[10000];
    }

    public void add(int key) {
        int index = key % arr.length;
        Node node;
        if (arr[index] == null) {
            node = new Node();
            node.key = key;
            arr[index] = node;
        } else {
            Node temp = arr[index];
            if (temp.key != key) {
                while (temp.next != null && temp.next.key != key) {
                    temp = temp.next;
                }
                if (temp.next == null) {
                    node = new Node();
                    node.key = key;
                    temp.next = node;
                }
            }

        }

    }

    public void remove(int key) {
        int index = key % arr.length;
        Node temp = arr[index];
        if (temp != null) {
            if (temp.key != key) {
                while (temp.next != null && temp.next.key != key) {
                    temp = temp.next;
                }
                if (temp.next != null) {
                    Node temp1 = temp.next;
                    temp.next = temp1.next;
                    temp1.next = null;
                }
            } else {
                Node temp1 = temp.next;
                temp.next = null;
                arr[index] = temp1;
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int index = key % arr.length;
        Node temp = arr[index];
        while (temp != null && temp.key != key) temp = temp.next;
        return temp != null;
    }

    public static void main(String[] args) {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));
        System.out.println(hashSet.contains(3));
        hashSet.add(2);
        System.out.println(hashSet.contains(2));
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));
        hashSet.add(10001);
        hashSet.remove(10001);
    }
}