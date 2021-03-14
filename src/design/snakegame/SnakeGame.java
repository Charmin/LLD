package design.snakegame;

import java.util.Arrays;
import java.util.LinkedList;

public class SnakeGame {

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */

    /*
    * Game over
    * hits the wall
    * hits itself
    *
    * GameStart(board size, food positions, (start position of snake (0,0)))
    *
    * int MakeMove(U, L, D, R) (-1 indicates game over, length of the snake)
    *
    * */
    int height;
    int width;
    int snakeLength;

    LinkedList<int[]> foodPositions = new LinkedList<>();
    LinkedList<int[]> snakePositions = new LinkedList<>();

    public SnakeGame(int width, int height, int[][] food) {
        foodPositions.addAll(Arrays.asList(food));
        snakeLength = 1;
        snakePositions.add(new int[] {0, 0});
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] curSnakePos = snakePositions.peek();
        int[] curFoodPos = foodPositions.peek();

        int[] newPos = new int[2];
         switch (direction) {
            case "U":
                newPos[0] = curSnakePos[0] - 1;
                newPos[1] = curFoodPos[1];
                break;
             case "D":
                 newPos[0] = curSnakePos[0] + 1;
                 newPos[1] = curSnakePos[1];
                 break;
             case "L":
                 newPos[0] = curFoodPos[0];
                 newPos[1] = curFoodPos[1] - 1;
                 break;
             case "R":
                 newPos[0] = curFoodPos[0];
                 newPos[1] = curFoodPos[1] + 1;
                 break;
        }
        //check if new pos is
        // 1. food
        // 2. itself
        // 3. wall
        if (isBumpingItself(newPos) || isWall(newPos)) {
            System.exit(-1);
        }

        snakePositions.add(newPos);
        if (!isFood(newPos)) {
            snakePositions.remove(); //removes from last
        }

        return snakePositions.size();
    }

    private boolean isFood(int[] newPos) {
        return false;
    }

    private boolean isWall(int[] newPos) {
        return false;
    }

    private boolean isBumpingItself(int[] newPos) {
        return false;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */