import jdk.jfr.Unsigned;

import java.sql.Array;
import java.util.*;
import java.util.stream.*;
import java.util.Dictionary;

public class Main {

    public static int MaxOperations(int[] nums, int k) {
        int[] available = Arrays.stream(nums).filter(x -> x <= k).sorted().toArray();
        var count = 0;
        int left = 0;
        int right = available.length - 1;
        while (left < right) {
            if (available[left] + available[right] == k) {
                count++;
                left++;
                right--;
            } else if (available[left] + available[right] < k)
                left++;
            else if (available[left] + available[right] > k)
                right--;
        }
        return count;
    }

    public double averageWaitingTime(int[][] customers) {
        if (customers.length == 1) return customers[0][1];
        var waitTime = customers[0][1];
        var whenChefIsIdle = customers[0][0] + customers[0][1];
        for (int i = 1; i < customers.length; i++) {
            if (customers[i][0] >= whenChefIsIdle) {
                waitTime += customers[i][1];
                whenChefIsIdle = customers[i][0] + customers[i][1];
            } else {
                waitTime += customers[i][1] + whenChefIsIdle - customers[i][0];
                whenChefIsIdle = whenChefIsIdle + customers[i][1];
            }
        }
        return (double) waitTime / customers.length;
    }

    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        var result = new ArrayList<Boolean>();
        for (int i = 0; i < l.length; i++) {
            var sub = Arrays.stream(nums, l[i], r[i] == nums.length - 1 ? nums.length : r[i] + 1).sorted().toArray();
            int diff = sub[1] - sub[0];
            var isBroken = false;
            for (int j = 0; j < sub.length - 1; j++) {
                if (sub[j + 1] - sub[j] != diff) {
                    result.add(false);
                    isBroken = true;
                    break;
                }
            }
            if (!isBroken)
                result.add(true);
        }
        return result;
    }

    public static int[] getSumAbsoluteDifferences(int[] nums) {
        var prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        var suffixSum = new int[nums.length];
        suffixSum[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        }
        var result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = nums[i] * (2 * i - result.length + 1) - prefixSum[i] + suffixSum[i];
        }
        return result;
    }

    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        var result = 0;
        var max = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            if (releaseTimes[i] - releaseTimes[i - 1] > max
                    || (releaseTimes[i] - releaseTimes[i - 1] == max && keysPressed.charAt(i) > keysPressed.charAt(result))) {
                max = releaseTimes[i] - releaseTimes[i - 1];
                result = i;
            }
        }
        return keysPressed.charAt(result);
    }

    public static int addRungs(int[] rungs, int dist) {
        var counter = rungs[0] % dist == 0 ? rungs[0] / dist - 1 : rungs[0] / dist;
        for (int i = 0; i < rungs.length - 1; i++) {
            if (rungs[i + 1] - rungs[i] > dist) {
                var up = (rungs[i + 1] - rungs[i]) / dist;
                counter += (rungs[i + 1] - rungs[i]) % dist == 0 ? up - 1 : up;
            }
        }
        return counter;
    }

    public static String kthLargestNumber(String[] nums, int k) {
        List<String>[] arrayOfLists = new ArrayList[101];

        for (int i = 0; i < nums.length; i++) {
            if (arrayOfLists[nums[i].length()] == null)
                arrayOfLists[nums[i].length()] = new ArrayList<>();
            arrayOfLists[nums[i].length()].add(nums[i]);
        }

        for (int i = 100; i >= 0; i--) {
            if (arrayOfLists[i] != null) {
                if (arrayOfLists[i].size() < k) {
                    k -= arrayOfLists[i].size();
                } else {
                    Collections.sort(arrayOfLists[i]);
                    return arrayOfLists[i].get(arrayOfLists[i].size() - k);
                }
            }
        }
        return ";";
    }

    public static void main(String[] args)
    {

    }
}
