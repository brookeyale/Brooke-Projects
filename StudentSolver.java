
public class StudentSolver {
    public static int solve(int[][] grid){
        int numOfColumns = grid.length;
        int numOfRows = grid[0].length;
        int totCoins = 0;
        int[][] tGrid;
        for (int i = 1; i < numOfRows * .85; i++){
            totCoins += fill(grid);
            tGrid = transpose(grid, numOfRows, numOfColumns);
            totCoins += fill(tGrid);
            grid = transpose(tGrid, numOfRows, numOfColumns); }
        print(grid);
        System.out.println(totCoins);
        return totCoins;
    }
    public static void print(int[][] grid){
        for (int[] x : grid){
            for( int y : x){
                System.out.print(y + " "); }
            System.out.println(); }
    }
    public static int fill(int[][] grid){
        //fills the rows in ascending and descending order
        int numOfColumns = grid.length;
        int numOfRows = grid[0].length;
        int totCoins = 0;
        boolean found = false;
        int prev;
        int iter = 0;
        boolean prevMod = false;
        for (int[] x: grid){
            prev = 0;
            int[] y = findMaxVal(x);
            boolean reiterated = false;
            for(int z: x){
                if (y[0] == z) found = true;
                //grid[i][j] for index of z
                int j = iter % numOfRows;
                int i = (iter - j) / numOfRows;
                int maxReit = maxReiterated(x, j);
                if(!found && z < prev && iter%numOfColumns != 0){//if on the left side and not the first element of the row
                    prevMod = true;
                    totCoins += (prev - z);
                    grid[i][j] = prev; }
                else if(found && (z >= prev) && (z != y[0])){//if on the right side, the current value is greater than the previous, and z is not the max
                    totCoins += z - prev;
                    if (j == 0){//the first index of the row
                        i = i-1;
                        j = numOfRows - 1;
                        grid[i][j] = z; }
                    else{
                        grid[i][j-1] = z; } }
                else if (found && y[0] > z && maxReit != -1){//if on the right side of the max, the max is greater than the current, and there exists a reiteration of the max
                    reiterated = true;
                    totCoins += prev - z;
                    grid[i][j] = prev;
                    z = prev; }
                if (!prevMod || reiterated)//if the previous value was modified or there was a reiteration
                    prev = z;
                iter += 1;
                prevMod = false; }
            found = false; }
        return totCoins; }
    public static int maxReiterated(int[] x, int index){
        //searches through a row backwards and returns the index number of the final iteration of the max integer if this index is different than the original
        int[] y = findMaxVal(x);
        for(int t = x.length - 1; t >= index; t--){
            if (x[t] == y[0] && t != y[1]) {
                if (t <= y[1])
                    return -1;
                else
                    return t; } }
        return -1; }
    public static int[] findMaxVal(int[] grid){
        //returns the maximum integer value in a row and its index
        int iter = 0;
        int max = 0;
        int index = 0;
        for (int x: grid) {
            if (x>max) {
                max = x;
                index = iter; }
            iter += 1; }
        return new int[]{max, index}; }
    public static int[][] transpose(int[][] grid,int numOfRows, int numOfColumns){
        //returns the transpose of a matrix
        int[][] temp = new int[numOfRows][numOfColumns];
        for(int i = 0; i < numOfRows; i++){
            for(int j =0; j < numOfColumns; j++){
                if (j>i) break;
                else if (i!=j){
                    temp[i][j]= grid[i][j];
                    grid[i][j]= grid[j][i];
                    grid[j][i]= temp[i][j];
                } } }
        return grid; }
    public static void main(String [] args){
        int[][] iGrid = new int[][]{
                {1,2,5,3,3},
                {2,4,1,5,1},
                {2,1,1,5,2},
                {1,1,5,1,3},
                {4,3,1,5,1}
        };
        solve(iGrid);
    }
}