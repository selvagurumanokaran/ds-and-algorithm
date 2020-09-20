import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class MainClass {
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {
            if (!stack.isEmpty() && asteroids[i] < 0 && stack.peek() > 0) {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroids[i])) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    if (stack.peek() < 0)
                        stack.push(asteroids[i]);
                    else if (stack.peek() == Math.abs(asteroids[i])) {
                        stack.pop();
                    }
                } else {
                    stack.push(asteroids[i]);
                }
            } else {
                stack.push(asteroids[i]);
            }
        }
        int[] output = new int[stack.size()];
        int i = output.length - 1;
        while (!stack.isEmpty()) {
            output[i--] = stack.pop();
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(MainClass.asteroidCollision(new int[]{8, -8}));
    }
}