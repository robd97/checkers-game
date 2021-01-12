import java.util.Random;
import java.lang.Math.*;


public class Game {
    public int[][] grid={{0,2,0,2,0,2,0,2},
                        {2,0,2,0,2,0,2,0},
                        {0,2,0,2,0,2,0,2},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {1,0,1,0,1,0,1,0},
                        {0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0}};
    private final Player[] players = new Player[2];
    private int turn;
    private int move = 0;
    private static int outOfBounds= 0;

    public Game(){
        for(int i = 1; i < 3; i++){
            players[i-1] = new Player(i, 12);
        }
        Random rand = new Random();
        turn = rand.nextInt(2) + 1;
    }
    private void switchTurn(){
        turn = turn == 1? 2: 1;
    }
    public boolean makeMove(int player, int x1, int y1, int x2, int y2) {
        if (turn == player && isLegitMove(x1, y1, x2, y2)) {

                switchTurn();
                move++;
                return true;
        }
        return false;
    }
    public int[] checkMove(int player, int x1, int y1, int x2, int y2) {
        if (isInGrid(x1, y1) && isInGrid(x1+x2, y1+y2)){
            if(grid[x1+x2][y1+y2] == 0 ){
                return new int[]{x2, y2};
            }
            else if(grid[x1+x2][y1+y2]%2 != player%2 && isInGrid(x1+x2*2, y1+y2*2)){
                if(grid[x1+x2*2][y1+y2*2] == 0) {
                    return new int[]{x2 * 2, y2 * 2};
                }
            }
        }
        return new int[]{outOfBounds,outOfBounds};
    }

    public int[][] checkMoves(int y, int x){
        // TODO: fix if statement.
        if(!isInGrid(y, x) || grid[y][x] == 0){
            return new int[][]{{outOfBounds, outOfBounds}, {outOfBounds, outOfBounds}};
        }

        switch(grid[y][x]){
            case 1:
                return new int[][]{
                checkMove(grid[y][x], y, x, 1, 1),
                checkMove(grid[y][x], y, x, -1, 1)};
            case 2:
                return new int[][]{
                checkMove(grid[y][x], y, x, 1, -1),
                checkMove(grid[y][x], y, x, -1, -1)};
            default:
                return new int[][]{
                checkMove(grid[y][x], y, x, 1, 1),
                checkMove(grid[y][x], y, x, -1, 1),
                checkMove(grid[y][x], y, x, 1, -1),
                checkMove(grid[y][x], y, x, -1, -1)};
        }
    }
    private boolean isInGrid(int y, int x){
        if (y <= 7 && y >= 0 && x >= 0 && x <= 7)
            return true;
        return false;
    }
    private boolean isLegitMove(int x1, int y1, int x2, int y2){
        if (isInGrid(x1, y1) && isInGrid(x2, y2))
            for(int[] cur: checkMoves(x1, y1))
                if (cur[0] == x2 && cur[1] == y2){
                    return true;
                }
        return false;
    }

}

