
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Node {
    int data;
    Node next;
}

class GrabResult {

    public static Node add(Node ll) {
        int carry = addUtil(ll);
        if (carry != 0) {
            Node node = new Node();
            node.data = carry;
            node.next = ll;
            return node;
        }
        return ll;
    }

    private static int addUtil(Node head) {
        if (head == null) return 1;
        int carry = addUtil(head.next);
        int sum = head.data + carry;
        if (sum < 10) {
            head.data = sum;
            return 0;
        } else {
            head.data = 0;
            return 1;
        }
    }
}

public class Grab {
    public static void main(String[] args) {
    }
}

