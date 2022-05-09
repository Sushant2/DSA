import java.util.*;
import java.io.*;

//! TIME COMPL : O(nlogn) + SPACE COMPL : O(m*n)
//nlognn for sorting , & m*n - iterating n jobs & for each job iterating from last deadline
public class jobSequencingProblem {

    public static class Job {
        int id, deadline, profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static int[] findMaxProfit(Job[] arr, int n) {
        Arrays.sort(arr, (a, b) -> (b.profit - a.profit));
        // now find the maxm deadline
        int maxDead = 0;
        for (int i = 0; i < n; i++)
            if (arr[i].deadline > maxDead)
                maxDead = arr[i].deadline;

        // make an array of size maxDead
        int[] maxDeadDays = new int[maxDead + 1];
        Arrays.fill(maxDeadDays, -1);

        int countJobs = 0, totalProfit = 0;
        for (int i = 0; i < n; i++) {
            for (int j = arr[i].deadline; j >= 1; j--) {
                if (maxDeadDays[j] == -1) {
                    countJobs++;
                    totalProfit += arr[i].profit;
                    maxDeadDays[j] = i;
                    break;
                }
            }
        }
        // now return answer inform of an array
        int[] ans = new int[2];
        ans[0] = countJobs;
        ans[1] = totalProfit;
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        Job[] arr = new Job[n];
        for (int i = 1; i <= n; i++) {
            int deadline = scn.nextInt();
            int profit = scn.nextInt();
            arr[i - 1] = new Job(i, deadline, profit);
        }
        int[] ans = findMaxProfit(arr, n);
        System.out.println("Total Jobs: " + ans[0] + " Total Profit: " + ans[1]);
    }
}
