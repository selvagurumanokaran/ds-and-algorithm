
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Supplier;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode list2 = splitList(head);
        reorderListUtil(head, list2);
    }

    private ListNode splitList(ListNode head) {
        ListNode slow = head, fast = slow.next;
        while (fast != null && fast.next != null) {
            if (slow != null) slow = slow.next;
            if (fast != null) fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        return temp;
    }

    private void reorderListUtil(ListNode head, ListNode list2) {
        if (list2 == null) return;
        reorderListUtil(head, list2.next);
        list2.next = head.next;
        head.next = list2;
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int rowStart = 0, result = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int[] cache = new int[h + 1];
        for (int horizontalCut : horizontalCuts) {
            int cakeHeight = horizontalCut - rowStart;
            if (cache[cakeHeight] != 0) {
                rowStart = horizontalCut;
                continue;
            }
            int colStart = 0;
            for (int col = 0; col < verticalCuts.length; col++) {
                int cakeWidth = verticalCuts[col] - colStart;
                result = Math.max(result, cakeHeight * cakeWidth);
                colStart = verticalCuts[col];
            }
            int cakeWidth = w - colStart;
            result = Math.max(result, cakeHeight * cakeWidth);
            rowStart = horizontalCut;
            cache[cakeHeight] = result;
        }
        int cakeHeight = h - rowStart;
        if (cache[cakeHeight] == 0) {
            int colStart = 0;
            for (int verticalCut : verticalCuts) {
                int cakeWidth = verticalCut - colStart;
                result = Math.max(result, cakeHeight * cakeWidth);
                colStart = verticalCut;
            }
            int cakeWidth = w - colStart;
            result = Math.max(result, cakeHeight * cakeWidth);
        }

        return result % ((int) Math.pow(10, 9) + 7);
    }

    public static void main(String[] args) {

        class Data implements Comparable<Data> {
            int id;
            String firstName;

            public Data(int id, String firstName) {
                this.id = id;
                this.firstName = firstName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Data data = (Data) o;

                return id == data.id;
            }

            @Override
            public int hashCode() {
                int result = 1;
                result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
                return result;
            }

            @Override
            public int compareTo(Data o) {
                return Integer.compare(this.id, o.id);
            }
        }
        /*Map<Data, String> map = new HashMap<>();
        map.put(new Data(1, "Guru"), "1->guru");
        map.put(new Data(2, "Guru"), "2->guru");
        map.put(new Data(3, "Guru"), "3->guru");
        map.put(new Data(4, "Guru"), "4->guru");
        map.put(new Data(5, "Guru"), "5->guru");

        System.out.println(map.get(new Data(1, "Guru")));*/

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 40; i++) {
            map.put(16 * i, Integer.toString(16 * i));
        }
//        map.put(0, "0");
//        map.put(16, "16");
//        map.put(16*2, "32");
//        map.put(16*3, "48");
//        map.put(16*4, "48");
//        map.put(16*5, "48");
//        map.put(16*6, "48");
//        map.put(16*7, "48");
//        map.put(16*8, "48");
//        map.put(16*9, "48");
//        map.put(16*10, "48");
//        map.put(16*11, "48");
//        map.put(16*12, "48");
//        map.put(16*13, "48");
//        map.put(16*14, "48");
//        map.put(16*15, "48");
//        map.put(16*16, "48");
        System.out.println(map.get(16 * 50));
//        Collections.unmodifiableCollection()


        class Request {
            private String method;
            private String body;

            public void setMethod(String method) {
                this.method = method;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public String fetch() {
                return body;
            }
        }

        Request request = new Request();
        request.setMethod("post");
        String first = request.fetch();
        request.setBody("body");
        String second = request.fetch();


        class Request1 {
            private String method;
            private String body;

            public Request1 setMethod(String method) {
                Request1 request1 = new Request1();
                request1.method = method;
                return request1;
            }

            public Request1 setBody(String body) {
                Request1 request1 = new Request1();
                request1.body = body;
                return request1;
            }

            public String fetch() {
                return body;
            }
        }

        Request1 request1 = new Request1().setMethod("body");
        String first1 = request1.fetch();
        String second1 = request1.setBody("post").fetch();
        String s = null;
        Optional.empty();
        Optional<String> s1 = Optional.of(s);
        Optional<String> s2 = Optional.ofNullable(s);
        System.out.println(s1.isPresent());
        System.out.println(s2.isPresent());
    }
}