import java.util.*;

public class NumberLocation extends SudokuSolver{
    private HashMap<Integer, Integer>  locations = new HashMap<>();
    private int num;
    private int[][] grid = getGrid();
    private boolean[][]avail = getAvail();
    private HashMap<Integer, Integer> availLocation = getAvailLocation();
    public NumberLocation(int number){
        num = number;
    }
    public void fillLocations(){
        int iter = 0;
        for (int[] rows : grid){
            for (int value : rows){
                if (value == num) locations.put(iter/9, iter%9);
                iter++;
            }
        }
    }
    public void checkGridSpot(){
        //checks the intersections of the grid spot to see if it can be filled
    }

}
