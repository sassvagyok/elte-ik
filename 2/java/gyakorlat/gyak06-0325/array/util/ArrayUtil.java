package array.util;

public class ArrayUtil {
    public static int max(int[] nums) {
        int max;

        if (nums.length == 0) {
            max = 0;
        } else {
            max = Integer.MIN_VALUE;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > max) max = nums[i];
            }
        }

        return max;
    }

    public static int max2(int[] nums) {
        int max;

        if (nums.length == 0) {
            max = 0;
        } else {
            max = Integer.MIN_VALUE;

            for (int num : nums) {
                max = (num > max) ? num : max;
            }
        }

        return max;
    }

    public static int max3(int[] nums) {
        int max;
        if (nums.length == 0) {
            max = 0;
        } else {
            max = Integer.MIN_VALUE;

            for (int num : nums) {
                max = Math.max(num, max);
            }
        }

        return max;
    }

    public static int max4(int[] nums) {
        int max;

        if (nums.length == 0) {
            max = 0;
        } else {
            max = Integer.MIN_VALUE;

            for (int num : nums) {
                if (num > max) max = num;
            }
        }

        return max;
    }

    public static int minMax(int[] nums) {
        int[] minmax;
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            min = Math.min(num, min);
        }

        minmax.push(min);
        minmax.push(max(nums));
    }
}