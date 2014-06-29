/**
 * Created by Gleb on 6/27/2014.
 */
import lib.InputNumbers.Input;
public class TicTacToeBot {

    public static void boardShow(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == null) arr[i][j] = " ";

                if (j == 1) System.out.print(" | " + arr[i][j] + " | ");
                else System.out.print(arr[i][j]);
            }

            if (i == 2) break;

            System.out.println();
            System.out.println("---------");

        }
    }

    public static String playerChoose () {
        String sign;
        while (true) {
            System.out.println("Which plaeyr do you want to play for (only capital letter) ? (X or O)");
            sign = Input.readLine();
            if ("X".equals(sign) || "O".equals(sign)) break;
            else System.out.println("Error. Try again");
        }

        return sign;
    }

    public static void playerTurn (String[][] arr , String sign) {

        int row , column ;

            while (true) {
                System.out.println("Please, enter row[1-3]");
                row = Input.readInt();
                System.out.println("And column");
                column = Input.readInt();


                if ((row >= 1 && row <= 3) && (column >= 1 && column <= 3)) {
                    if ("X".equals(arr[row - 1][column - 1]) || "O".equals(arr[row - 1][column - 1]))
                        System.out.println("Error! Incorrect move! Try again");
                    else {
                        arr[row - 1][column - 1] = sign;
                        break;
                    }
                } else System.out.println("Error! Incorrect move! Try again");
            }
    }

    public static void botTurn (String[][] arr , String sign_b) {
        int row_b , column_b;
        int count = 0;

        while (true) {
            row_b = Input.random(0 , 2);
            column_b = Input.random(0 , 2);
            count++;
            if (count == 50) break;
            if("X".equals(arr[row_b][column_b]) || "O".equals(arr[row_b][column_b])) continue;
            else {
                arr[row_b][column_b] = sign_b;
                break;
            }
        }
    }

    public  static String comparison (String[][] arr) { // Who has won(comparison)
        String sign = "X";
        String sign_ret = "";
        int count = 0;

        for (int a = 0; a <= 1; a++) {

            if (sign.equals(arr[0][0]) && sign.equals(arr[1][1]) && sign.equals(arr[2][2]) ||
                sign.equals(arr[0][2]) && sign.equals(arr[1][1]) && sign.equals(arr[2][0])) {
                sign_ret = sign;
            }

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < 1; j++) {
                    if (sign.equals(arr[i][j]) && sign.equals(arr[i][j + 1]) && sign.equals(arr[i][j + 2]) ||
                        sign.equals(arr[j][i]) && sign.equals(arr[j + 1][i]) && sign.equals(arr[j + 2][i])) {
                        sign_ret = sign;
                    }
                }
            }

            sign = "O";
        }
        return sign_ret;
    }
    public static void main(String[] args) {
        String arr[][] = new String[3][3];
        String sign = playerChoose();
        String sign_b;

        if("X".equals(sign)) sign_b = "O";
        else sign_b = "X";

        int count = 1;

        while (true) {
            boardShow(arr);
            System.out.println();
            if ("X".equals(sign)) {
                playerTurn(arr , sign);
                botTurn(arr , sign_b);
                if (count == 5) {
                    System.out.println();
                    System.out.println("Draw!");
                    break;
                }
            }
            else {
                botTurn(arr , sign_b);
                boardShow(arr);
                System.out.println();
                if (count == 5) {
                    System.out.println();
                    System.out.println("Draw!");
                    break;
                }
                playerTurn(arr , sign);
            }

            comparison(arr);
            if("X".equals(comparison(arr)) || "O".equals(comparison(arr))) {
                boardShow(arr);
                System.out.println();
                System.out.println("Player " + comparison(arr) + " has won!");
                break;
            }
            count++;

        }



    }

}
