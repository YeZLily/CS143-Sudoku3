import java.util.*;
import java.io.*;

public class SudokuEngine {
   public static void main(String[] args) {
      String fileName = "boards/very-fast-solve.sdk";
      SudokuBoard board = new SudokuBoard(fileName);  
      System.out.println(board); 
   }
}

/*
# PROGRAM OUTPUT

Initial board
+-------+-------+-------+
| - 3 4 | 6 7 8 | 9 1 2 |
| - 7 2 | 1 9 5 | 3 4 8 |
| 1 9 8 | 3 4 2 | 5 6 7 |
+-------+-------+-------+
| - - 9 | - 6 1 | 4 2 3 |
| - 2 6 | 8 5 3 | 7 9 1 |
| - 1 3 | 9 2 4 | - 5 6 |
+-------+-------+-------+
| - 6 1 | 5 3 7 | 2 8 4 |
| - 8 - | 4 1 9 | 6 3 5 |
| 3 4 5 | - 8 6 | 1 7 9 |
+-------+-------+-------+


Solving board...SOLVED in 0.004 seconds.

+-------+-------+-------+
| 5 3 4 | 6 7 8 | 9 1 2 |
| 6 7 2 | 1 9 5 | 3 4 8 |
| 1 9 8 | 3 4 2 | 5 6 7 |
+-------+-------+-------+
| 8 5 9 | 7 6 1 | 4 2 3 |
| 4 2 6 | 8 5 3 | 7 9 1 |
| 7 1 3 | 9 2 4 | 8 5 6 |
+-------+-------+-------+
| 9 6 1 | 5 3 7 | 2 8 4 |
| 2 8 7 | 4 1 9 | 6 3 5 |
| 3 4 5 | 2 8 6 | 1 7 9 |
+-------+-------+-------+

*/
