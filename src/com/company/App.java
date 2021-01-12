package com.company;

public class App {
    public static void main(String args[]){
        Game mainGame = new Game();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(mainGame.grid[i][j] != 0){
                    System.out.printf("%d, %d:", i, j);
                    for(int[] cur: mainGame.checkMoves(i,j)) {
                        for(int k : cur){
                            System.out.print(k);
                        }
                        System.out.print(" ");
                    }
                    System.out.println();
                }
            }
        }

    }
}
