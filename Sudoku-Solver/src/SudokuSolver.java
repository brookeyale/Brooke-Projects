import java.util.*;
import java.io.*;

public class SudokuSolver {
    public static int[][] newPuzzle(){
        //test puzzle, will randomly generate puzzles in the future
        int[][] puzzle = new int[9][9];
        for (int[] row : puzzle){
            Arrays.fill(row,0);
        }
        puzzle[0][0] = 4;
        puzzle[0][1] = 2;
        puzzle[0][2] = 7;
        puzzle[0][5] = 5;
        puzzle[0][6] = 9;
        puzzle[0][8] = 3;
        puzzle[1][0] = 9;
        puzzle[1][2] = 5;
        puzzle[1][3] = 2;
        puzzle[1][4] = 4;
        puzzle[1][7] = 8;
        puzzle[2][1] = 8;
        puzzle[2][2] = 3;
        puzzle[2][4] = 9;
        puzzle[2][6] = 2;
        puzzle[3][1] = 7;
        puzzle[3][3] = 6;
        puzzle[3][4] = 2;
        puzzle[3][8] = 5;
        puzzle[4][0] = 3;
        puzzle[4][2] = 9;
        puzzle[4][6] = 7;
        puzzle[5][0] = 2;
        puzzle[5][1] = 5;
        puzzle[5][3] = 3;
        puzzle[5][5] = 4;
        puzzle[5][8] = 1;
        puzzle[6][0] = 7;
        puzzle[6][3] = 9;
        puzzle[6][7] = 3;
        puzzle[6][8] = 8;
        puzzle[7][0] = 5;
        puzzle[7][1] = 9;
        puzzle[7][3] = 4;
        puzzle[7][4] = 3;
        puzzle[7][5] = 7;
        puzzle[7][7] = 6;
        puzzle[8][0] = 1;
        puzzle[8][1] = 3;
        puzzle[8][2] = 2;
        puzzle[8][3] = 5;
        puzzle[8][7] = 7;
        return puzzle;
    }
    public static int[][] solve(int[][] original){
        // puzzle elements with 0 in the value are to be considered available
        // solving technique is the check number across rows and columns
        ArrayList<String> avail = new ArrayList<String>();
        int rowNum = 0;
        for (int[] row: original){
            int col = 0;
            for(int i = 0; i < 9; i++){
                if (row[i] == 0){
                    avail.add("row:" + rowNum + " col: " + col);
                }
                col++;
            }
            rowNum++;
        }
        //row locations of empty elements are now in an ArrayList called avail. Can check with //System.out.println(avail);
        return original;
    }
    public static void main(String[] args){
        int[][] puzzle = newPuzzle();
        for(int[] row : puzzle){
            System.out.println(Arrays.toString(row));
        }
    }
}
