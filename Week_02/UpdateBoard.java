package week2;

import java.util.HashSet;
import java.util.Set;

//扫雷游戏
public class UpdateBoard {

    public static void main(String[] args) {
        char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        UpdateBoard updateBoard = new UpdateBoard();
        int[] click = {1, 2};
        char[][] res = updateBoard.updateBoard(board, click);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + ",");
            }
            System.out.println();
        }

    }

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            Set<Integer> si = new HashSet<>();
            si.add(getHash(x, y));
            dfs(board, x, y, si);
        }
        return board;
    }

    public void dfs(char[][] board, int x, int y, Set<Integer> si) {
        int res = 0;
        if (x < 0 || x >= board.length || y < 0 || y > board[0].length) {
            return;
        }
        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};
        for (int a : dx) {
            for (int b : dy) {
                if (x + a >= 0 && x + a < board.length && y + b >= 0 && y + b < board[0].length) {
                    if (a == 0 && b == 0) {
                        continue;
                    }
                    if (board[x + a][y + b] == 'M') {
                        res++;
                    }
                }
            }
        }
        if (res == 0) {
            board[x][y] = 'B';
            for (int a : dx) {
                for (int b : dy) {
                    if (x + a >= 0 && x + a < board.length && y + b >= 0 && y + b < board[0].length) {
                        if (!si.contains(getHash(x + a, y + b))) {
                            si.add(getHash(x + a, y + b));
                            dfs(board, x + a, y + b, si);
                        }
                    }
                }
            }
        } else {
            board[x][y] = (char) (res + '0');
        }
    }

    public int getHash(int x, int y) {
        return x * 51 + y;
    }
}

