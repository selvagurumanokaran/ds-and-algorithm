package com.geeks.guru.ds;

class Pair {
    int start;
    int end;
    Pair next;

    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class MyCalendar {

    Pair list;

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        end = end - 1;
        Pair newPair = new Pair(start, end);
        if (list == null) {
            list = newPair;
            return true;
        }
        if (end < list.start) {
            newPair.next = list;
            list = newPair;
            return true;
        }
        Pair first = list;
        Pair second = list.next;
        while (second != null) {
            if (start > first.end && end < second.start) {
                newPair.next = second;
                first.next = newPair;
                return true;
            }
           /* if((start >= first.start && start <= first.end) || (end >= first.start && end <= first.end)
              || (start < first.start && end > first.end) ) return false;
            if((start >= second.start && start <= second.end) || (end >= second.start && end <= second.end)
              || (start < second.start && end > second.end)) return false;*/
            first = second;
            second = second.next;
        }
        /*if((start >= first.start && start <= first.end) || (end >= first.start && end <= first.end)
          || (start < first.start && end > first.end)) return false;*/
        if (start > first.end) {
            first.next = newPair;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MyCalendar calander = new MyCalendar();
        calander.book(47, 50);
        calander.book(33, 41);
        calander.book(39, 45);
        calander.book(33, 42);
        calander.book(25, 32);
        calander.book(26, 35);
        calander.book(19, 25);
        calander.book(3, 8);
        calander.book(8, 13);
        calander.book(18, 27);
    }
}
