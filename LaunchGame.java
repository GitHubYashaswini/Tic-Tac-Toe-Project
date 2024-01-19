import java.util.*;
import java.util.Random;

class TicTacToe 
{
    static char[][] board;

    public TicTacToe()
    {
      board = new char[3][3];
      initBoard();
    }

    void initBoard()
    {
       for(int i=0; i<3; i++)
       {
        for(int j=0; j<3; j++)
        {
          board[i][j]= ' ';
        }
       } 
    }

    static void dispBoard()
    {
      System.out.println("-------------");
      for(int i=0; i<board.length; i++)
       {
        System.out.print("| ");
        for(int j=0; j<board[i].length; j++)
        {
          System.out.print(board[i][j] + " | ");        
        }
        System.out.println();
        System.out.println("-------------");
       } 
       
    }

    static void placeMark(int row, int col, char mark)
    {
        if(row >=0 && row<=2 && 
        col>=0 && col<=2)
        {
             board[row][col] = mark;
        }
        else
        {
            System.out.println("Invalid Position");
        }
    }

    static boolean checkColWin()
    {
        for(int j=0; j<=2; j++)
        {
            if(board[0][j]!=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
            {
                return true;
            }
        }
        return false;

    }

    static boolean checkRowWin()
    {
        for(int i=0; i<=2; i++)
        {
            if(board[i][0]!= ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
            {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagWin()
    {
        if(board[0][0]!= ' ' && board[0][0] == board[1][1]
         && board[1][1] == board[2][2] 
         ||board[0][2]!= ' ' && board[0][2] == board[1][1] 
         && board[1][1] == board[2][0])
        {
             return true;
        }
         else
        {
             return false;
        }
    }

    static boolean checkDraw()
    {
        for(int i=0; i<=2; i++)
        {
            for(int j=0; j<=2; j++)
            {
                if(board[i][j] == ' ')
                {
                    return false;
                }
            }
       }
       return true;
    }    
}
 
 
abstract class Player
{
    String name;
    char mark;
    abstract void makeMove();

    boolean isValidMove(int row, int col)
    {
       if(row >= 0 && row <=2 &&
       col >=0 && col<=2)
       {
        if(TicTacToe.board[row][col] == ' ')
        {
            return true;
        }
       }
          return false;
    }
}

class HumanPlayer extends Player
{
    HumanPlayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    void makeMove()
    {
       Scanner scan = new Scanner(System.in);
       int row;
       int col;
       do
       {
         System.out.println("Enter the row and col");
         row = scan.nextInt();
         col = scan.nextInt();
        }while(! isValidMove(row,col));

       TicTacToe.placeMark(row, col, mark);
    }
}


class AIPlayer extends Player
{
    AIPlayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    void makeMove()
    {
       Scanner scan = new Scanner(System.in);
       int row;
       int col;
       do
       {
         Random  r = new Random();
         row = r.nextInt(3);
         col = r.nextInt(3);
        }while(! isValidMove(row,col));

       TicTacToe.placeMark(row, col, mark);
    }
}
public class LaunchGame 
{
    public static void main(String[] args) 
    {
        TicTacToe t = new TicTacToe();

        System.out.println("Hi, WELCOME TO TIC TAC TOE GAME");
        System.out.println("1.Want to play with friend");
        System.out.println("2.Want to play with AI");
        System.out.println("3.Exit");
        System.out.println("Enter your choice");
        int ip;
        Scanner s = new Scanner(System.in);
        ip = s.nextInt();
        if(ip == 1)
        {
            System.out.println("Enter Player1 name");
            Scanner n1 = new Scanner(System.in);
            String name1 = n1.nextLine();

            System.out.println("Enter Player2 name");
            Scanner n2  = new Scanner(System.in);
            String name2 = n2.nextLine();

            HumanPlayer p1 = new HumanPlayer(name1,'X');
            HumanPlayer p2 = new HumanPlayer(name2,'O');
            Player cp;
            cp = p1;
           while(true)
           {
                System.out.println(cp.name + " Turn");
                cp.makeMove();

                TicTacToe.dispBoard();

                if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin())
               { 
                      System.out.println(cp.name +" Has Won");
                      break;
               }
               else if(TicTacToe.checkDraw())
               {
                      System.out.println("Game is draw");
                      break;
               } 
               else
               {
                      if(cp == p1)
                      {
                          cp = p2;
                      }
                      else
                      {
                           cp = p1;
                      }
               }
           }
        }

        else if(ip == 2)
        {
            System.out.println("Enter Your name");
            Scanner n1 = new Scanner(System.in);
            String name1 = n1.nextLine();

            HumanPlayer p1 = new HumanPlayer(name1,'X');
            AIPlayer p2 = new AIPlayer("AI",'O');
            Player cp;
            cp = p1;
           while(true)
           {

               System.out.println(cp.name + " Turn");
               cp.makeMove();

               TicTacToe.dispBoard();

               if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin())
               {
                    System.out.println(cp.name +" Has Won");
                    break;
               }
               else if(TicTacToe.checkDraw())
               {
                     System.out.println("Game is draw");
                     break;
               }
              else
               {
                    if(cp == p1)
                    {
                         cp = p2;
                    }
                   else
                   {
                         cp = p1;
                   }
               }
            }
        }

        else if(ip == 3)
        {
            System.out.println("Your are leaving");
        }
        else
        {
            System.out.println("Enter only 1 , 2 or 3");
        }

    }
}

