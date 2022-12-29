package tictactoe;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // we create an array of chars where we will save the TicTacToe ( 3 x 3 )
        String[][] array = new String[3][3];
        // number of X and O nrChars[0] = nr. of X, nrChars[1] = nr. of O
        int nrChars[] = new int[2];
        String winners[] = new String[9];
        // create TicTacToe pattern
        boolean addC = false;
        int intA = 0;
        int intB = 0;
        boolean endGame = false;
        String currentPlayer = "X";

        createTicTacToe(array, null, nrChars);
        showTicTacToe(array);

        while (!endGame) {
            while (!addC) {
                String posA = scanner.next();
                String posB = scanner.next();
                if (!posA.matches("\\d+") || !posB.matches("\\d+")) {
                    System.out.println("You should enter numbers!");
                    return;
                }
                intA = Integer.parseInt(posA);
                intB = Integer.parseInt(posB);
                if ((intA < 1 || intA > 3) || (intB < 1 || intB > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    break;
                }
                if (array[intA - 1][intB - 1].equals("X") || array[intA - 1][intB - 1].equals("O")) {
                    System.out.println("This cell is occupied! Choose another one!");
                    break;
                }
                addC = true;
                if ("X".equals(currentPlayer)) {
                    array[intA - 1][intB - 1] = "X";
                    currentPlayer = "O";
                } else {
                    array[intA - 1][intB - 1] = "O";
                    currentPlayer = "X";
                }
                break;
            }
            showTicTacToe(array);
            checkWinner(array, winners);
            addC = false;
            if (checkGame(nrChars, winners)) {
                break;
            }
        }
    }

    public static boolean checkGame(int[] nrChars, String[] winners) {
        int dif = 0;
        if (nrChars[0] > nrChars[1]) {
            dif = nrChars[0] - nrChars[1];
        } else {
            dif = nrChars[1] - nrChars[0];
        }
        if (dif > 1) {
            System.out.println("Impossible");
            return false;
        }
        if (!Objects.equals(winners[1], null) && !Objects.equals(winners[0], null)) {
            System.out.println("Impossible");
            return false;
        }
        // if there is no winner, check what happens
            if (nrChars[0] + nrChars[1] == 9) {
                System.out.println("Draw");
                return true;
            }
            // there is a winner.
            if (!Objects.equals(winners[0], null)) {
                System.out.printf("%s wins", winners[0]);
                return true;
            } else if (!Objects.equals(winners[1], null)) {
                System.out.printf("%s wins", winners[1]);
                return true;
            }
        return false;
    }


    public static void showTicTacToe(String[][] array) {
        System.out.println("---------");
        // parsing the lines based on the length of the array
        for (int i = 0; i < array.length; i++) {
            // parsing the columns based on the length of the array
            for (int j = 0; j < array.length; j++) {
                // if j is 0, then we put | in front of the column foreach line
                // to set the TicTacToe structure
                if (j == 0) {
                    System.out.print("| ");
                }
                System.out.print(array[i][j]+" ");
                // if j is the last number of the column, we put | before the column foreach line
                // to set the TicTacToe structure
                if (j == array.length - 1) {
                    System.out.print("|");
                }
            }
            // output a new line after each line of the TicTacToe, to make the structure.
            System.out.println();
        }
        System.out.println("---------");
    }

    public static void createTicTacToe(String[][] array, String symbol, int[] nrChars) {
        int nrX = 0;
        int nrO = 0;
        // parsing the lines (3 lines)
        for (int i = 0; i < 3; i++) {
            // parsing the columns (3 columns per line)
            for (int j = 0; j < 3; j++) {
                if (!Objects.equals(symbol, null)) {
                    array[i][j] = symbol;
                    // counting the symbol X
                    if (symbol.equals("X")) {
                        nrX++;
                        // counting the symbol O
                    } else if (symbol.equals("O")) {
                        nrO++;
                    }
                } else {
                    array[i][j] = "_";
                }
            }
        }
        // saving number of X and O
        nrChars[0] = nrX;
        nrChars[1] = nrO;
    }
    public static void checkWinner(String[][] array, String[] winners) {
        String chars = "X O";
        int count = 0;
        // parsing the String chars by splitting it with ""
        for (String c : chars.split(" ")) {
            for (int i = 0; i < array.length; i++) {
                // if there are the same X or O on the lines, save the winner
                if (c.equals(array[i][0]) && c.equals(array[i][1]) && c.equals(array[i][2])) {
                    winners[count] = c;
                }
                // if there are the same X or on one of the columns, save the winner
                if (c.equals(array[0][i]) && c.equals(array[1][i]) && c.equals(array[2][i])) {
                    winners[count] = c;
                }
            }
            // if no winner found on each line or each column, we check on diagonals
            if (c.equals(array[0][0]) && c.equals(array[1][1]) && c.equals(array[2][2])) {
                winners[count] = c;
            } else if (c.equals(array[0][2]) && c.equals(array[1][1]) && c.equals(array[2][0])) {
                winners[count] = c;
            }
            count++;
        }
    }
}