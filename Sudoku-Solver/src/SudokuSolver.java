import java.util.*;
import java.io.*;

public class SudokuSolver {
    private static boolean[][] avail = new boolean[9][9];
    private static int[][] grid = new int[9][9];
    private static NumberLocation ones = new NumberLocation(1);
    private static NumberLocation twos = new NumberLocation(2);
    private static NumberLocation threes = new NumberLocation(3);
    private static NumberLocation fours = new NumberLocation(4);
    private static NumberLocation fives = new NumberLocation(5);
    private static NumberLocation sixes = new NumberLocation(6);
    private static NumberLocation sevens = new NumberLocation(7);
    private static NumberLocation eights = new NumberLocation(8);
    private static NumberLocation nines = new NumberLocation(9);


    public static int[][] getGrid(){
        return grid;
    }
    public static boolean[][] getAvail(){
        return avail;
    }
    public static void fillAvail(){
        //marks grid elements as true if te value is null and false if there is a number
        //will also search for all locations of a number and return an arraylist of grid spots that contains the number.
        //probably used once
        int iter = 0;
        for (int[] rows : grid){
            for (int value : rows){
                if (value == 0) avail[iter / 9][iter % 9] = true;
                else avail[iter/9][iter%9] = false;
                System.out.println(avail[iter/9][iter%9]);
                iter++;
            }
        }
    }
    public static void main(String[] args){
        grid = newPuzzle();
        fillAvail();
    }
    public static int[][] newPuzzle(){
        //test grid, will randomly generate grids in the future
        for (int[] row : grid){
            Arrays.fill(row,0);
        }
        grid[0][0] = 4;
        grid[0][1] = 2;
        grid[0][2] = 7;
        grid[0][5] = 5;
        grid[0][6] = 9;
        grid[0][8] = 3;
        grid[1][0] = 9;
        grid[1][2] = 5;
        grid[1][3] = 2;
        grid[1][4] = 4;
        grid[1][7] = 8;
        grid[2][1] = 8;
        grid[2][2] = 3;
        grid[2][4] = 9;
        grid[2][6] = 2;
        grid[3][1] = 7;
        grid[3][3] = 6;
        grid[3][4] = 2;
        grid[3][8] = 5;
        grid[4][0] = 3;
        grid[4][2] = 9;
        grid[4][6] = 7;
        grid[5][0] = 2;
        grid[5][1] = 5;
        grid[5][3] = 3;
        grid[5][5] = 4;
        grid[5][8] = 1;
        grid[6][0] = 7;
        grid[6][3] = 9;
        grid[6][7] = 3;
        grid[6][8] = 8;
        grid[7][0] = 5;
        grid[7][1] = 9;
        grid[7][3] = 4;
        grid[7][4] = 3;
        grid[7][5] = 7;
        grid[7][7] = 6;
        grid[8][0] = 1;
        grid[8][1] = 3;
        grid[8][2] = 2;
        grid[8][3] = 5;
        grid[8][7] = 7;
        return grid;
    }
}
