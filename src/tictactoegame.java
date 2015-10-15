import java.util.Scanner;

public class tictactoegame
{
  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args)
  {

    final int SIZE = 3;
    char[][] board = new char[SIZE][SIZE]; 

    resetBoard(board);

  
    System.out.println("PLAY TIME WITH TIC-TAC-TOE\n");
    showBoard(board);

   
    System.out.print("  Which symbol do you want to play, \"x\" or \"o\"? ");
    char userSymbol = sc.next().toLowerCase().charAt(0);
    char compSymbol = (userSymbol == 'x') ? 'o' : 'x';


    System.out.println();
    System.out.print("  Do you want to go first (y/n)? ");
    char ans = sc.next().toLowerCase().charAt(0);

    int turn;  
    int remainCount = SIZE * SIZE; 

    
    if (ans == 'y') {
      turn = 0;
      userPlay(board, userSymbol); 
    }
    else {
      turn = 1;
      compPlay(board, compSymbol); 
    }
    
    showBoard(board);
    remainCount--;

    
    boolean done = false;
    int winner = -1;   

    while (!done && remainCount > 0) {
      
      done = isGameWon(board, turn, userSymbol, compSymbol); // Did the turn won?

      if (done)
        winner = turn; 
      else {
        
        turn = (turn + 1 ) % 2;

        if (turn == 0)
          userPlay(board, userSymbol);
        else
          compPlay(board, compSymbol);

        
        showBoard(board);
        remainCount--;
      }
    }

    
    if (winner == 0)
      System.out.println("\n** YOU WON.  CONGRATULATIONS!! **");
    else if (winner == 1)
      System.out.println("\n** YOU LOST..  Maybe next time :) **");
    else
      System.out.println("\n** DRAW... **");

  }

  public static void resetBoard(char[][] brd)
  {
    for (int i = 0; i < brd.length; i++)
      for (int j = 0; j < brd[0].length; j++)
        brd[i][j] = ' ';
  }

  public static void showBoard(char[][] brd)
  {
    int numRow = brd.length;
    int numCol = brd[0].length;

    System.out.println();

    
    System.out.print("    ");
    for (int i = 0; i < numCol; i++)
      System.out.print(i + "   ");
    System.out.print('\n');

    System.out.println(); 

   
    for (int i = 0; i < numRow; i++) {
      System.out.print(i + "  ");
      for (int j = 0; j < numCol; j++) {
        if (j != 0)
          System.out.print("|");
        System.out.print(" " + brd[i][j] + " ");
      }

      System.out.println();

      if (i != (numRow - 1)) {
       
        System.out.print("   ");
        for (int j = 0; j < numCol; j++) {
          if (j != 0)
            System.out.print("+");
          System.out.print("---");
        }
        System.out.println();
      }
    }
    System.out.println();
  }

  public static void userPlay(char[][] brd, char usym)
  {
    System.out.print("\nEnter the row and column indices: ");
    int rowIndex = sc.nextInt();
    int colIndex = sc.nextInt();

    while (brd[rowIndex][colIndex] != ' ') {
      System.out.print("\n!! The cell is already taken.\nEnter the row and column indices: ");
      rowIndex = sc.nextInt();
      colIndex = sc.nextInt();
    }

    brd[rowIndex][colIndex] = usym;
  }

  public static void compPlay(char[][] brd, char csym)
  {
   
    for (int i = 0; i < brd.length; i++) {
      for (int j = 0; j < brd[0].length; j++) {
        if (brd[i][j] == ' ') { 
          brd[i][j] = csym;
          return;
      }
      }
      }
      }

  public static boolean isGameWon(char[][] brd, int turn, char usym, char csym)
  {
    char sym;
    if (turn == 0)
      sym = usym;
    else
      sym = csym;

    int i, j;
    boolean win = false;

    for (i = 0; i < brd.length && !win; i++) {
      for (j = 0; j < brd[0].length; j++) {
        if (brd[i][j] != sym)
          break;
      }
      if (j == brd[0].length)
        win = true;
    }

    for (j = 0; j < brd[0].length && !win; j++) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][j] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }
   
    if (!win) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][i] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }

    if (!win) {
      for (i = 0; i < brd.length; i++) {
        if (brd[i][brd.length - 1 - i] != sym)
          break;
      }
      if (i == brd.length)
        win = true;
    }

    return win;
  }
}