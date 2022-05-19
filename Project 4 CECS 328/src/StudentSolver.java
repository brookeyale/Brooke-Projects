import java.util.*;

public class StudentSolver {
    private static int[][] bored;
    //can consider adding getters and setters for bored.
    // This will allow other functions within StudentSolver to access the private variable faster.
    // Can check with the timer function

    private static Pair[] corners = new Pair[4]; //new array to hold corner positions

    public static ArrayList<Pair<Integer,Integer>> solve(int[][] board){
        bored = board;
        System.out.println(Arrays.deepToString(board)); //shows the input

        //sets the corners
        corners[0] = new Pair<>(0,0);
        corners[1] = new Pair<>(0,bored[0].length - 1);
        corners[2] = new Pair<>(bored.length - 1, bored[0].length - 1);
        corners[3] = new Pair<>(bored.length - 1, 0);

        ArrayList<Pair<Integer,Integer>> sol = new ArrayList<>(); //best solution
        ArrayList<Integer> solInts = new ArrayList<>(); //an arraylist of the addends in the best solution
        HashMap<Pair<Integer, Integer>, Integer> hm = new HashMap<>(); //Integer key, Pair value

        //looking for the highest integer in the board
        int max = 0;
        Pair<Integer, Integer> maxPair = new Pair<>(0,0);
        //O(n^2) because n > m
        for(int m = 0; m < bored.length; m++){ //row iterator
            for(int n = 0; n  < bored[m].length; n++) { //col iterator
                hm.put(new Pair<>(m,n), board[m][n]); //hashmap to connect a pair of integers with a board location
                if (board[m][n] > max){
                    max = board[m][n];
                    maxPair = new Pair<>(m,n);
                }
            }
        }
        solInts.add(max);    sol.add(maxPair); //adding the max integer and pair for best solution
        hm = available(hm, maxPair); //a hashmap of all board pairs except for corners, and the max pair
        int iter = 0;

        //START HERE FUTURE BROOKE

        while (iter < 2000){ //2000 is an arbitrary number here, maybe pick a number of iterations related to n (like n^2) in the future
            maxPair = maxIterator(hm);
            if (maxPair.equals(new Pair<>(-1,-1))) break; //break condition in surroundingPoints method;
            ArrayList<Pair<Integer, Integer>> sp = surroundingPoints(maxPair, hm);
            HashMap<Pair<Integer,Integer>, Integer> mod = new HashMap<>(); //mod is the hashmap after pairs are selected for sp and removed from hm
            if (sp.size() > 0){
                for(int i = 0; i < sp.size(); i ++){
                    solInts.add(hm.get(sp.get(i))); //doesn't this always add the full size of sp with the values?
                    sol.add(sp.get(i));
                    mod = available(hm, sp.get(i)); //hashmapVariable.get(x) fetches the value mapped in a particular key
                }
            } else {
                solInts.add(hm.get(maxPair));
                sol.add(maxPair);
                mod = available(hm, maxPair);
            }
            iter += 1;
            hm = mod;
            }
        for (int m = 0; m < board.length; m++){
            for (int n = 0; n  < board[m].length; n++) {
                hm.put(new Pair<>(m,n), board[m][n]);
            }
        }
        return sol;
    }
    public static Pair<Integer, Integer> maxIterator(HashMap<Pair<Integer, Integer>, Integer> hm){
        int max = 0;
        Pair<Integer, Integer> maxPair = new Pair<>(-1,-1); //break condition for while loop in solve
        Pair<Integer, Integer> temp;
        for(int m = 0; m < bored.length; m++) {
            for (int n = 0; n < bored[m].length; n++) {
                temp = new Pair<>(m,n);
                if (hm.containsKey(temp) && bored[m][n] != 0 && max < hm.get(temp)) {
                    max = hm.get(temp);
                    maxPair = temp;
                }
            }
        }
        return maxPair;
    }
    public static ArrayList<Pair<Integer,Integer>> surroundingPoints(Pair<Integer, Integer> pair, HashMap<Pair<Integer, Integer>, Integer> hm){
        //input is maxPair from maxIterator and the hashmap
        //output is an arraylist of the top surrounding points with a higher sum than the max. if no such points are found, an empty set is returned
        //switch statements are not
        boolean isCorner = false;
        for (Pair p : corners){
            if (p.equals(pair)) {
                isCorner = true;
                break;
            }
        }
        ArrayList<Pair<Integer, Integer>> spAL = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> test = new ArrayList<>();
        int sum = 0;
        int testSum = 0;
        if (pair.first.equals(0) || pair.first.equals(bored.length - 1) || pair.second.equals(0) || pair.second.equals(bored[0].length - 1) ){
        if (hm.containsKey(new Pair<>(pair.first - 1, pair.second)) && pair.first - 1 >= 0) {
            spAL.add(new Pair<>(pair.first - 1, pair.second));
            sum += hm.get(new Pair<>(pair.first - 1, pair.second)); }
        if (hm.containsKey(new Pair<>(pair.first, pair.second - 1)) && pair.second - 1 >= 0) {
            spAL.add(new Pair<>(pair.first, pair.second - 1));
            sum += hm.get(new Pair<>(pair.first, pair.second - 1)); }
        if (hm.containsKey(new Pair<>(pair.first + 1, pair.second)) && pair.first < bored.length - 1) {
            spAL.add(new Pair<>(pair.first + 1, pair.second));
            sum += hm.get(new Pair<>(pair.first + 1, pair.second)); }
        if (hm.containsKey(new Pair<>(pair.first, pair.second + 1)) && pair.second < bored[0].length - 1){
            spAL.add(new Pair<>(pair.first, pair.second + 1));
            sum += hm.get(new Pair<>(pair.first, pair.second + 1)); }

        //For test comparison. This checks whether the selected surrounding pairs are the best option compared to the corner values
        test.add(pair);
        testSum += hm.get(pair);
        if (hm.containsKey(new Pair<>(pair.first - 1, pair.second - 1)) && pair.first - 1 >= 0 && pair.second - 1 >= 0){
            test.add(new Pair<>(pair.first - 1, pair.second - 1));
            testSum += hm.get(new Pair<>(pair.first - 1, pair.second - 1));
        }
        if (hm.containsKey(new Pair<>(pair.first - 1, pair.second + 1)) && pair.first - 1 >= 0 && pair.second < bored[0].length - 1){
                test.add(new Pair<>(pair.first - 1, pair.second + 1));
                testSum += hm.get(new Pair<>(pair.first - 1, pair.second + 1));
        }
        if (hm.containsKey(new Pair<>(pair.first + 1, pair.second + 1)) && pair.first < bored.length - 1 && pair.second < bored[0].length - 1){
                test.add(new Pair<>(pair.first + 1, pair.second + 1));
                testSum += hm.get(new Pair<>(pair.first + 1, pair.second + 1));
        }
        if (hm.containsKey(new Pair<>(pair.first + 1, pair.second - 1)) && pair.first < bored.length - 1 && pair.second - 1 >= 0){
                test.add(new Pair<>(pair.first + 1, pair.second - 1));
                testSum += hm.get(new Pair<>(pair.first + 1, pair.second - 1));
        }
        if (sum > hm.get(pair) && isCorner){
                return spAL;
        }
        else if (sum > testSum && !isCorner) {
            return spAL;
        }
        else return test;}

        return new ArrayList<>();

    }
    public static HashMap<Pair<Integer, Integer>, Integer> available(HashMap<Pair<Integer,Integer>, Integer> hm, Pair<Integer, Integer> pair){
        //updates which pair values are still available based on their board position
        //switch statements are not used because multiple available pairs can be removed in one iteration
        if (hm.containsKey(new Pair<>(pair.first - 1, pair.second)) && pair.first - 1 >= 0){
            hm.remove(new Pair<>(pair.first - 1, pair.second));
            bored[pair.first-1][pair.second] = 0;}
        if (hm.containsKey(new Pair<>(pair.first, pair.second - 1)) && pair.second - 1 >= 0){
            hm.remove(new Pair<>(pair.first, pair.second - 1));
            bored[pair.first][pair.second -1] = 0;}
        if (hm.containsKey(new Pair<>(pair.first + 1, pair.second)) && pair.first < bored.length - 1){
            hm.remove(new Pair<>(pair.first + 1, pair.second));
            bored[pair.first+1][pair.second] = 0;}
        if (hm.containsKey(new Pair<>(pair.first, pair.second + 1)) && pair.second < bored[0].length - 1){
            hm.remove(new Pair<>(pair.first, pair.second + 1));
            bored[pair.first][pair.second +1] = 0;}
        hm.remove(pair);
        bored[pair.first][pair.second]=0;
        return hm;
    }
    public static void main(String[] args){
        int[][]board = new int[7][100];
        Random rand = new Random();
        for(int m = 0; m < board.length; m++){
            for(int n = 0; n  < board[m].length; n++) {
                board[m][n] = rand.nextInt(100) + 1;
            }
        }
        ArrayList<Pair<Integer,Integer>> sol = solve(board);
        System.out.println(sol);
    }
}
