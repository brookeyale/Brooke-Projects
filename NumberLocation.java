import java.util.*;

public class NumberLocation extends SudokuSolver{
    private HashMap<Integer, Integer>  locations = new HashMap<>(); //Key is row, Value is column. Contains the coordinates of values with requested number
    private HashMap<Integer, Integer>  unavail = new HashMap<>();
    private boolean[][] option = new boolean[9][9]; //false by default, true when the spot is an option for the number
    private final int num;
    private HashMap<Integer, Integer> avail = new HashMap<>();
    private static final int[][] grid = getGrid();
    public static int[][] returnGrid(){
        return grid;
    }
    public enum Square {
        // Keeps track of squares w/o the number
        // 3x3 grids, starting top left, going right, then skipping the left most to come back
        //  0  1  2
        //  3  4  5
        //  6  7  8
        SZERO, SONE, STWO, STHREE, SFOUR, SFIVE, SSIX, SSEVEN, SEIGHT
    }
    public List<Enum> squares = new ArrayList<>();

    public NumberLocation(int number){
        num = number;
    }
    private void nIS(Square square){
        //nIS = not In Square
        squares.add(square);
    }
    public void fillLoc(){
        // general fill function
        // marks where num is located |locations.put(row,col)|
        // blocks where num cannot be |searchRow()/ searchCol()|
        // marks avail |nIS(sq)|
        int iter = 0;
        boolean squareAdded = false;
        for (int[] rows : grid){
            for (int value : rows){
                if (value == num) {
                    locations.put(iter/9, iter%9);
                    searchRow(iter/9);
                    searchCol(iter%9);
                }
                if (!squareAdded ) {
                    if (iter / 9 < 3 && iter % 9 < 3 && !squares.contains(Square.SZERO)) nIS(Square.SZERO);
                    else if (iter / 9 < 3 && iter % 9 < 6 && !squares.contains(Square.SONE)) nIS(Square.SONE);
                    else if (iter / 9 < 3 && !squares.contains(Square.STWO)) nIS(Square.STWO);
                    else if (iter / 9 < 6 && iter % 9 < 3 && !squares.contains(Square.STHREE)) nIS(Square.STHREE);
                    else if (iter / 9 < 6 && iter % 9 < 6 && !squares.contains(Square.SFOUR)) nIS(Square.SFOUR);
                    else if (iter / 9 < 6 && !squares.contains(Square.SFIVE)) nIS(Square.SFIVE);
                    else if (iter / 9 < 9 && iter % 9 < 3 && !squares.contains(Square.SSIX)) nIS(Square.SSIX);
                    else if (iter / 9 < 9 && iter % 9 < 6 && !squares.contains(Square.SSEVEN)) nIS(Square.SSEVEN);
                    else if (iter / 9 < 9 && !squares.contains(Square.SEIGHT)) nIS(Square.SEIGHT);
                    squareAdded = true;

                }
                iter++;
            }
            squareAdded = false;
        }
    }
    //below search functions used to fill available spots
    public void searchRow(int rowNum){
        for (int i = 0; i < 9; i++){
            if (grid[rowNum][i] == 0){
                avail.put(rowNum, i);
            }
        }
    }
    public void searchCol(int colNum){
        for (int i = 0; i < 9; i++){
            if (grid[i][colNum] == 0 ){
                avail.put(i, colNum);
            }
        }
    }
    public int sqToInt(Enum sq){
        if (sq.equals(Square.SZERO)) return 0;
        if (sq.equals(Square.SONE)) return 1;
        if (sq.equals(Square.STWO)) return 2;
        if (sq.equals(Square.STHREE)) return 3;
        if (sq.equals(Square.SFOUR)) return 4;
        if (sq.equals(Square.SFIVE)) return 5;
        if (sq.equals(Square.SSIX)) return 6;
        if (sq.equals(Square.SSEVEN)) return 7;
        if (sq.equals(Square.SEIGHT)) return 8;
return -1;
    }
    public void checkGridSpot(){
        //checks the intersections of the grid spot to see if it can be filled
        int[][] temp = new int[9][9];
        //instead of below lines, want to make a new function that parses through the grid spots that num is not in
        //this will allow me to check if certain grid spots can be filled by values I have
       if (squares.size() < 9){
           for (Enum sq: Square.values()){
               int sqNum = sqToInt(sq);
               switch (sqNum) {
                   case 0:
                       //gonna write some ugly ass code for just the first case here
                       //will make into a function once I have my life together
                       int optionFound = 0;
                       int row = -1;
                       int col = -1;
                       for (int i = 0; i < 3; i++){
                           for (int j = 0; j < 3; j++){
                               if (avail.containsKey(i) && avail.containsValue(j)){
                                   option[i][j] = true;
                                   optionFound ++;
                                   if (optionFound == 1){
                                       row = i;
                                       col = j;
                                   }
                               }
                           }
                       }
                       if (optionFound == 0){
                           System.out.println("You fucked up something when assigning available locations or squares");
                       }
                       else if (optionFound == 1){
                           System.out.println("You added " + num + "to row" + row + "and to col" + col);
                           locations.put(row, col);
                           squares.remove(Square.SZERO);
                           option[row][col] = false;
                           grid[row][col] = num;
                           System.out.println("Below is what your grid looks like now:");
                           printGrid();
                       }
                       break;
                   case 1: break;
                   case 2: break;
                   case 3: break;
                   case 4: break;
                   case 5: break;
                   case 6: break;
                   case 7: break;
                   case 8: break;

               }
           }
       }
    }

}
