import java.util.*;
import java.io.*;

public class SudokuBoard {
    private int[][] board;

    // Creates a blank Sudoku board.
    // It makes a 9 by 9 int array, where 0 means the spot is empty.
    public SudokuBoard() {
        board = new int[9][9];
    }

    // Creates a Sudoku board from a file.
    // It reads each row from the file and stores digits in the board array.
    // A dot in the file is stored as 0 because that means the spot is empty.
    public SudokuBoard(String fileName) {
        this();
        try {
            Scanner console = new Scanner(new File(fileName));
            for (int r = 0; r < board.length; r++) {
                if (console.hasNext()) {
                    String line = console.next();
                    for (int c = 0; c < board[0].length; c++) {
                        char value = line.charAt(c);
                        if (value == '.') {
                            board[r][c] = 0;
                        } else {
                            board[r][c] = value - '0';
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Checks if the current board is a valid Sudoku board.
    // It first checks that all values are allowed numbers.
    // Then it checks rows, columns, and 3x3 boxes for repeated numbers.
    public boolean isValid() {
        if (!checkNumbers())
            return false;
        if (!checkRow())
            return false;
        if (!checkCol())
            return false;
        if (!checkBox())
            return false;

        return true;
    }

    // Checks that every value on the board is between 0 and 9.
    // It looks at every cell and returns false if any value is outside that range.
    private boolean checkNumbers() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] < 0 || board[r][c] > 9) {
                    return false;
                }
            }
        }

        return true;
    }

    // Checks every row for repeated numbers.
    // Ignores 0 because empty spaces shouldn't count as duplicates.
    private boolean checkRow() {
        for (int r = 0; r < board.length; r++) {
            Set < Integer > set = new HashSet < > ();
            for (int c = 0; c < board[0].length; c++) {
                int val = board[r][c];
                if (val != 0) {
                    if (set.contains(val))
                        return false;
                    set.add(val);
                }
            }
        }


        return true;
    }

    // Checks every column for repeated numbers.
    // Uses a new set for each column and ignores empty spaces.
    private boolean checkCol() {
        for (int r = 0; r < board.length; r++) {
            Set < Integer > set = new HashSet < > ();
            for (int c = 0; c < board[0].length; c++) {
                int val = board[c][r];
                if (val != 0) {
                    if (set.contains(val))
                        return false;
                    set.add(val);
                }
            }
        }

        return true;
    }

    // Checks each 3x3 mini square for repeated numbers.
    // It goes through each box and uses a set to remember which numbers were found.
    private boolean checkBox() {
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol <
                3; boxCol++) { // checks the first, second, and third box
                Set < Integer > set = new HashSet < > ();
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c <
                        3; c++) { // checks the first row, then first column of the box
                        int val = board[boxRow * 3 + r][boxCol * 3 +
                        c]; // adds the value of the board at the location of [(if in the case box row 0 and row 1 of the box then (0*3+1))]
                        if (val != 0) {
                            if (set.contains(val))
                                return false;
                            set.add(val);
                        }
                    }
                }
            }
        }

        return true;
    }

    // Checks if the board is completely solved.
    // The board must first be valid. 
    // Then each number from 1 to 9 must appear exactly 9 times.
    public boolean isSolved() {
            if (!isValid())
                return false;

        Map < Integer, Integer > map = new HashMap < > ();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                int val = board[r][c];

                if (val != 0) {
                    if (map.containsKey(val)) {
                        map.put(val, map.get(val) + 1);
                    } else {
                        map.put(val, 1);
                    }
                }
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (!map.containsKey(num) || map.get(num) !=
                9) { // this loop goes from 1-9 and checks if the map contains the number and the occurrences the number came up
                return false;
            }
        }

        return true;
    }

    // Attempts to solve the Sudoku board using recursive backtracking.
    // First checks if the board is invalid or already solved.
    // If the board is incomplete but valid, it finds an empty spot,
    // tries numbers 1 through 9, and recursively checks if that choice can lead to a solution.
    // If a number doesn't work, it resets the spot back to 0 and tries another number.
    
    public boolean solve() {
        if(!isValid()) {
            return false;
        }
        if(isSolved()) {
            return true;
        }

        for (int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(board[r][c] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        board[r][c] = num;

                        if (isValid() && solve()) {
                            return true;
                        }
                        board[r][c] = 0;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    // Turns the Sudoku board into a printable String.
    // It adds grid lines and prints empty spots as dashes.
    public String toString() {
        String result = "";
        String line = "+-------+-------+-------+\n";

        for (int r = 0; r < board.length; r++) {
            if (r % 3 == 0) {
                result = result + line;
            }
            for (int c = 0; c < board[0].length; c++) {
                if (c % 3 == 0) {
                    result = result + "| ";
                }
                if (board[r][c] == 0) {
                    result = result + "- ";
                } else {
                    result = result + board[r][c] + " ";
                }
            }
            result = result + "|\n";
        }
        result = result + line;
        return result;
    }
}
