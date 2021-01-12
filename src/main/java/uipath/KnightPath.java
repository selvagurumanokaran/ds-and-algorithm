package uipath;

import java.util.HashSet;
import java.util.Set;

public class KnightPath {

    // Given NxN chessboard, source and dest position, figure out min no of moves required for knight from source to dst.


    //source = [0,0] & dest = [7,7]
    static int findMinPath(int[] source, int[] dest, int N) {
        int[][] nextCells = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
        Set<String> visited = new HashSet<>();
        //    visited.add(source[0] +"" + source[1]);
        return findPath(source, dest, nextCells, N, visited);
    }

    static int findPath(int[] source, int[] dest, int[][] nextCells, int N, Set<String> visited) {
        //System.out.println("Source " + source[0] + " " + source[1]);
        if (source[0] < 0 || source[0] >= N || source[1] < 0 || source[1] >= N) return Integer.MAX_VALUE;
        if (visited.contains(source[0] + "" + source[1])) return Integer.MAX_VALUE;
        if (source[0] == dest[0] && source[1] == dest[1]) return 0;
        visited.add(source[0] + "" + source[1]);
        int currLength = Integer.MAX_VALUE;

        int[] temp = new int[2];
        for (int[] nextCell : nextCells) {
            temp[0] = source[0] + nextCell[0];
            temp[1] = source[1] + nextCell[1];
            currLength = Math.min(currLength, findPath(temp, dest, nextCells, N, visited));
        }
        System.out.println("Source " + source[0] + " " + source[1]);
        System.out.println("Length " + currLength);
        return currLength == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + currLength;
    }

    public static void main(String[] args) {
        int len = findMinPath(new int[]{0, 0}, new int[]{7, 7}, 8);
        System.out.println(len);
    }


}
