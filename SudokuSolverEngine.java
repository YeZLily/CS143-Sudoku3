
public class SudokuSolverEngine {

   public static void main(String[] args) {
      SudokuBoard board = new SudokuBoard("boards/very-fast-solve.sdk");
      System.out.println("Initial board");
      System.out.println(board);
      System.out.println();

      if (!board.isValid()) {
         System.out.println("This board cannot be solved.");
      } else if (board.isSolved()) {
         System.out.println("This board is already solved.");
      } else {
         System.out.print("Solving board...");
         long start = System.currentTimeMillis();
         boolean solved = board.solve();
         long stop = System.currentTimeMillis();

         if (solved) {
            System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
            System.out.println();
            System.out.println(board);
         } else {
            System.out.println("This board cannot be solved.");
         }
      }
      
   }
}
