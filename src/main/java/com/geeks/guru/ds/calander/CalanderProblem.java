package com.geeks.guru.ds.calander;

import java.util.*;

class Meeting {
    Date startTime;
    Date endTime;

    public Meeting(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

public class CalanderProblem {

    public static void main(String[] args) {
        Meeting meeting1 = new Meeting(new Date(2019, 10, 21, 8, 00, 00), new Date(2019, 10, 21, 8, 30, 00));
        Meeting meeting2 = new Meeting(new Date(2019, 10, 21, 8, 15, 00), new Date(2019, 10, 21, 8, 45, 00));
        Meeting meeting3 = new Meeting(new Date(2019, 10, 21, 9, 00, 00), new Date(2019, 10, 21, 10, 30, 00));
        Meeting meeting4 = new Meeting(new Date(2019, 10, 21, 11, 00, 00), new Date(2019, 10, 21, 11, 30, 00));
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(meeting1);
        meetings.add(meeting2);
        meetings.add(meeting3);
        meetings.add(meeting4);
        arrangeMeeting(meetings);
    }


    public static int arrangeMeeting(List<Meeting> meetings) {
        //int cnt = 0;
        List<Meeting> result = new ArrayList<>();
        //Collections.copy(meetingsCopy, meetings);
        Collections.sort(meetings,
                new Comparator<Meeting>() {
                    public int compare(Meeting meeting1, Meeting meeting2) {
                        return (int) (meeting1.endTime.getTime() - meeting2.endTime.getTime());
                    }
                });
        Meeting currMeeting = meetings.get(0);
        while (currMeeting != null) {
            result.add(currMeeting);
            Meeting finalCurrMeeting = currMeeting;
            Optional<Meeting> first = meetings.stream().filter(m ->
                    m.startTime.getTime() > finalCurrMeeting.endTime.getTime()).findFirst();
            currMeeting = first.isPresent() ? first.get() : null;
        }
        return result.size();
    }

}
