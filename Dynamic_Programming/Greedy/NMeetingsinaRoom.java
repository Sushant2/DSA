import java.util.*;
import java.io.*;

//! TIME COMPLEXITY : 
//O(n) to fill DS, // O(nlogn) to sort the DS, // O(n) to reiterate & find maximum meetings
// time comp : O(nlogn)
// space comp : O(n) for DS

public class NMeetingsinaRoom {

    public static class meetings {
        int start;
        int end;
        int pos;

        meetings(int start, int end, int pos) {
            this.start = start;
            this.end = end;
            this.pos = pos;
        }
    }

    public static class meetingComparator implements Comparator<meetings> {
        @Override
        public int compare(meetings o1, meetings o2) {
            if (o1.end < o2.end)
                return -1;
            else if (o1.end > o2.end)
                return 1;
            else if (o1.pos < o2.pos)
                return -1;
            return 1;
        }
    }

    public static int findMaxMeetings(int n, int[] S, int[] F) {
        ArrayList<meetings> meet = new ArrayList<>();
        for (int i = 0; i < n; i++)
            meet.add(new meetings(S[i], F[i], i + 1));
        meetingComparator MC = new meetingComparator();
        Collections.sort(meet, MC);
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(meet.get(0).pos);
        int limit = meet.get(0).end;
        for (int i = 1; i < n; i++) {
            if (S[i] > limit) {
                limit = meet.get(i).end;
                ans.add(meet.get(i).pos);
            }
        }
        return ans.size();
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] S = new int[n];
        int[] F = new int[n];
        for (int i = 0; i < n; i++)
            S[i] = scn.nextInt();
        for (int i = 0; i < n; i++)
            F[i] = scn.nextInt();
        int maxMeetings = findMaxMeetings(n, S, F);
        System.out.println(maxMeetings);
    }
}
