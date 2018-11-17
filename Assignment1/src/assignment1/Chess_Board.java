/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
import java.util.Scanner;
import java.util.InputMismatchException;

//FileHandler Library
import static assignment1.FileHandler.SaveFile;
import static assignment1.FileHandler.LoadFile;
import static assignment1.FileHandler.ApplyLoadedChanges;
import static assignment1.FileHandler.printUpdate;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author Richard De La Merced
 */
public class Chess_Board 
{
    //Piece variables
     private static int xposition, yposition, xselection, yselection,xpiece,ypiece, xmove, ymove , piecetype;
      private static boolean validSelection , colourPiece;
      
      //Reference to Other Pieces
      private static int otherxposition, otheryposition, otherpiecetype;
      private static boolean othercolour;
    public static String[][] boardPositions = new String[8][8];
    public static Chess_Piece[] pieces = new Chess_Piece[32];
    //Player Variables
    public static boolean turnOrder;
    private static boolean specialCase,isBlocked, attacking;
    public static boolean endGame;
    public static String save;
   //File Variables
    
    /*
    Order of Data:
    xposition, yposition, piecetype, isalive
    */
    
   private final static  Scanner scanner = new Scanner(System.in).useDelimiter("[,\\s+]");
    
   
/*
    boardPositions:
White vs Black 
    
♙ = pawn = ♟
♖ = rook = ♜
♘ = knight = ♞
♗ = bishop = ♝
♕ = queen = ♛
♔ = king = ♚ 
        
*/
void createBoard()
{
        
    validSelection = false;
    endGame=false;
    /*White Pawns*/
    xposition = 0;
    yposition = 1;
    for(int i = 0 ; i < 8 ; i ++)
    {
        pieces[i] = new Pawn();
        //White
        pieces[i].CreatePiece(xposition, yposition, true);
        
        boardPositions[xposition][yposition] = "|♙|";
        xposition++;
    }
    /*Black Pawns*/
    xposition = 0;
    yposition = 6;
    for(int i =  8 ; i < 16 ; i ++)
    {
        pieces[i] = new Pawn();
        //White
        pieces[i].CreatePiece(xposition, yposition, false);
        
        boardPositions[xposition][yposition] = "|♟|";
        xposition++;
    }
    xposition = 0;
    yposition = 0;
    /*White Pieces*/
    pieces[16] = new Rook();
    boardPositions[xposition++][yposition] ="|♖|";
    pieces[17] = new Knight();
     boardPositions[xposition++][yposition] ="|♘|";
      pieces[18] = new Bishop();
     boardPositions[xposition++][yposition] ="|♗|";
      pieces[19] = new Queen();
      boardPositions[xposition++][yposition] ="|♕|";
    pieces[20] = new King(); 
    boardPositions[xposition++][yposition] ="|♔|";
      pieces[21] = new Bishop();
      boardPositions[xposition++][yposition] ="|♗|";
    pieces[22] = new Knight();
    boardPositions[xposition++][yposition] ="|♘|";
    pieces[23] = new Rook();
    boardPositions[xposition++][yposition] ="|♖|";
        /*
boardPositions:
White vs Black 
    
♙ = pawn = ♟
♖ = rook = ♜
♘ = knight = ♞
♗ = bishop = ♝
♕ = queen = ♛
♔ = king = ♚ 
        
*/
    //resetting x and y position to update the piece position
    xposition = 0;
    yposition = 0;
    for(int i = 16; i < 24; i++)
    {
     pieces[i].CreatePiece(xposition, yposition, true);
     xposition++;

    }
    xposition = 0;
    yposition = 7;
    /*Black Pieces*/
    pieces[24] = new Rook();
    boardPositions[xposition++][yposition] = "|♜|";
    pieces[25] = new Knight();
    boardPositions[xposition++][yposition] = "|♞|";
      pieces[26] = new Bishop();
      boardPositions[xposition++][yposition] = "|♝|";
      pieces[27] = new Queen();
      boardPositions[xposition++][yposition] = "|♛|";
    pieces[28] = new King();
    boardPositions[xposition++][yposition] = "|♚|";
      pieces[29] = new Bishop();
      boardPositions[xposition++][yposition] = "|♝|";
    pieces[30] = new Knight();
    boardPositions[xposition++][yposition] = "|♞|";
    pieces[31] = new Rook();
    boardPositions[xposition++][yposition] = "|♜|";
    xposition = 0;
    yposition = 7;
    for(int i = 24; i < 32; i++)
    {
     pieces[i].CreatePiece(xposition, yposition, false);
     xposition++;
    }
/*
boardPositions:
White vs Black 
    
♙ = pawn = ♟
♖ = rook = ♜
♘ = knight = ♞
♗ = bishop = ♝
♕ = queen = ♛
♔ = king = ♚ 
▄    
        
*/

    for(int i = 0 ; i < 8; i++)
    {
        for(int j =0; j <8 ; j++)
        {
            if(boardPositions[i][j]==null)
            {
            boardPositions[i][j] ="|▄|";
            }
        }

    }
        
}
    
    void ManageBoard(boolean turnOrder_)throws IOException , FileNotFoundException
    {
      /*
        Stored Variables of Piece:
        xpiece
        ypiece
        colourPiece
        xmove
        ymove
      */
        //true = white
        //false = black
        turnOrder = turnOrder_;
         validSelection = false;
        //makes you select a proper thing before changing turns
    
      while(!validSelection)
     {
        
     Selection();
     if(validSelection)
     {
     CheckValidity();
     CheckPathing();
     isAttacking();
     //Not implementing Check or checkmate; just if you kill the king you win!
     CheckforWinCondition();
     /*
     HandleMovement uses piece[].checkValid then 
     */
     }
     
    
      }
      HandleMovement();
      
    } //end Manage Board
    
    void Selection() throws InputMismatchException, IOException , FileNotFoundException
    {
        
        /*
        Stored Variables of Piece:
        xpiece
        ypiece
        colourPiece
        xmove
        ymove
      */try
      {
      System.out.println("Please enter the x , y values of the piece you want to move");
      System.out.println("Or enter 's' to save or 'l' to load a save file.");
         xselection =  scanner.nextInt()-1;
         yselection = scanner.nextInt() -1;
         if(xselection > 7 ||xselection  <0 ||yselection > 7 ||yselection < 0)
             throw new InputMismatchException("Invalid Board Coordinates Selected");
      //  validSelection = false;
      //checks if selection is a valid selection
       for(int k = 0 ; k<32 ; k++)
         {
          if(pieces[k].xPosition == xselection && pieces[k].yPosition == yselection && pieces[k].colour == turnOrder)
                {
                    System.out.println("Valid Selection");
                    xpiece = xselection;
                    ypiece = yselection;
                    colourPiece = pieces[k].colour;
                    piecetype = k;
                    /*Selected Piece on the board*/
                   validSelection = true;
                   /*ends for loop early if piece is found before loop ends*/
                   break;
                  
                }
          
         }
      }
      catch(InputMismatchException e)
      {
          validSelection = false;
          
      save = scanner.next();
      //load = scanner.next();
      if(save.equalsIgnoreCase("s"))
              {
              SaveFile();
              System.out.println("You have saved the game");
        }
      else if(save.equalsIgnoreCase("l"))
      {
          System.out.println("Load Successful");
          LoadFile();
          ApplyLoadedChanges();
          printUpdate();
          printBoard();
      }
      else
      {
          System.out.println("Error");
      }
      System.out.println("Press Any Key To Continue");
          scanner.next();
      }
       
      
      
    
    } //End Selection
   //okay~!  
    void CheckValidity() throws InputMismatchException
    {
       
          //ask for move position\
        //move to main maybe?
       
       /*
        Stored Variables of Piece:
        xpiece
        ypiece
        colourPiece
        xmove
        ymove
      */
        if(validSelection)
        {
             System.out.println("Please enter the x , y values you wish to move the piece towards");
             xselection =  scanner.nextInt()-1;
             yselection = scanner.nextInt()-1;
             if(xselection > 7 ||xselection  <0 ||yselection > 7 ||yselection < 0)
             throw new InputMismatchException("Invalid Board Coordinates Selected");
             //nahh lets keep it here... seems okay.. I'll see if I change my mind later
             //Cancels Move by moving piece onto itself
             if(xselection == xpiece && yselection == ypiece)
             {
              validSelection=false;
              System.out.println("Invalid Selection");
             }
              for(int k = 0 ; k<32 ; k++)
             {
                 //cancels move if move is moved onto an ally
              if(pieces[k].xPosition == xselection && pieces[k].yPosition == yselection && pieces[k].colour == colourPiece)
                    {
                       validSelection = false;
                       System.out.println("Invalid Selection");
                       /*ends for loop early if piece is found before loop ends*/
                       break;

                    }
             }
              //if validSelection has remained true this entire time save move for further checking
             if(validSelection)
             {
             xmove = xselection;
             ymove = yselection;
             }
        }
            
        
       // boardPositions[xInput][yInput];
        
    } //end CheckValidity
    
    void isAttacking()
    {
    /*Only Applies to pawn and only checks for Pawn only
        
        */   
       attacking = false;
         if(piecetype < 16 && Math.abs(xmove-xpiece)==1 && Math.abs(ymove-ypiece )==1)
             attacking = true;
         else
             attacking = false;
         /*
         All other events don't matter
         */
         for(int j = 0 ; j < 32; j++)
         {
              otherxposition = pieces[j].xPosition;
              otheryposition = pieces[j].yPosition;
              //checks if there is a piece in the move position
            if(otherxposition == xmove && otheryposition == ymove)
            {
                //redundent check to skip selected piece
             if(otherxposition != xpiece && otheryposition != ypiece)
             {
               attacking = true;
               //will be used to disable the killed piece
               otherpiecetype = j;
             }
            }
         }
         
       /*
          try
        {

        }
        catch (Exception e)
        {
        
        }
        finally{}
         */
        
    } //end isAttacking
    /**
    CheckPathing saves the last piece invalid OTHER piece
    not really valid information XD just a note to keep track of
    */
    void CheckPathing()
    {
        isBlocked = false;
        specialCase = false;
        /**
        Most annoying thing EVER!!!!
        Recursive checking is a pain
        Assume not attacking
        */
       /**
        All this function will do is check if path is blocked
        */
        /**
        Stored Variables of Piece:
        xpiece
        ypiece
        colourPiece
        xmove
        ymove
      */
         /**
        piecetype:
        0-15 = pawn
        16 -23 = white back pieces 
        24 - 31 = black back pieces
        back pieces order:
        rook, knight, bishop, queen, king, bishop, knight , rook
        */
        /*Lets start with a pond first*/
       for(int i = 0 ; i < 32 ; i ++)
       {
            otherxposition = pieces[i].xPosition;
            otheryposition = pieces[i].yPosition;
            othercolour = pieces[i].colour;
            if(xmove == otherxposition && ymove == otheryposition && colourPiece == othercolour)
            {
             isBlocked = true;
             break;
            }
            int referenceX, referenceY,piecetopieceX,piecetopieceY;
            referenceX = Math.abs(xpiece - xmove);
            referenceY = Math.abs(ypiece - ymove);
            piecetopieceX = Math.abs(otherxposition - xpiece);
            piecetopieceY = Math.abs(otheryposition - ypiece);
            //originX = xpiece;
            //originY = ypiece;
            if(piecetype < 16)
            {
                if(colourPiece && otherxposition-xpiece== 1)
                {
                    isBlocked = true;
                    break;
                }
                else if(!colourPiece && otherxposition-xpiece== -1)
                {
                //if black pond
                    isBlocked = true;
                    break;
                }
                else 
                {
                    isBlocked = false;
                    break;
                }
                
            }
             /*
        piecetype:
        0-15 = pawn
        16 -23 = white back pieces 
        24 - 31 = black back pieces
        back pieces order:
        rook, knight, bishop, queen, king, bishop, knight , rook
        */
            else if(piecetype==16||piecetype==23||piecetype==24||piecetype==31)
            {
                //Rooks: 
                //16 and 23 are white
                //24 and 31 are black
                //checks if the piece is on the same axis
              
               if(piecetopieceX==0 || piecetopieceY==0)
                if(piecetopieceX < referenceX && piecetopieceY < referenceY)
                {
                 //if other piece's x,y inside the origin piece-move range 
                 //return isBlocked true
                    isBlocked = true;
                    break;
                }
               
            }
            else if(piecetype==18||piecetype==21||piecetype==26||piecetype==29)
            {
                //Knights can be ignored  17/22/25/30
                //Bishops:
                /*
                 if (Math.abs (xPosition - xPosition_) == Math.abs (yPosition - yPosition_))
                    return true;
                */
                if( piecetopieceY == piecetopieceX )//checks if it is on the right axis
                if(piecetopieceX < referenceX && piecetopieceY < referenceY)
                {
                 //if other piece's x,y inside the origin piece-move range 
                 //return isBlocked true
                    isBlocked = true;
                    break;
                }
            }
            else if(piecetype==19 ||piecetype ==27)
            {
                //king can be ignored 20/28
                //Queens:
                if(piecetopieceX==0 || piecetopieceY==0|| piecetopieceY == piecetopieceX)
                if(piecetopieceX < referenceX && piecetopieceY < referenceY)
                {
                 //if other piece's x,y inside the origin piece-move range 
                 //return isBlocked true
                    isBlocked = true;
                    break;
                }
               
            }
        
       }
    
    } //End CheckPathing()
    /*Assume all movement has been validated :D */
   void HandleMovement()
   {
       /*After checking the move has been 
       validated by the board the piece checks
       if it's a valid move for itself*/
      if(pieces[piecetype].validMove(xmove, ymove, attacking, isBlocked, specialCase))
      {
          if(attacking)
          {
              //grabs otherpiece selection from isAttacking()
              pieces[otherpiecetype].isAlive = false; 
              //isAlive is for WinCondition
              //Remove the piece from the board
              pieces[otherpiecetype].xPosition = 100;
              pieces[otherpiecetype].yPosition = 100;
              //Makes piece unselectable
          }
          
              pieces[piecetype].move(xmove,ymove);
              boardPositions[xmove][ymove] =  boardPositions[xpiece][ypiece];
              boardPositions[xpiece][ypiece] = "|▄|";
             // turnOrder= !turnOrder;
              //next turn
      }
      else
      {
          System.out.println("Invalid Selection");
          //redo turn
      }
        
       
       /*
       This function replaces the pieces original position with ▄ 
       char and the selected spot with the corresponding piece char
       and updates the new piece's position. Also handles any attacks
       */
       /*
boardPositions:
White vs Black 
    
♙ = pawn = ♟
♖ = rook = ♜
♘ = knight = ♞
♗ = bishop = ♝
♕ = queen = ♛
♔ = king = ♚ 
▄  = blank space  
        
*/
        
       
   }
    
   void CheckforCheck()
   {
       /*
       This function checks if there is a check currently and after the move.
       If so validSelection = false
       */
       /*
       For Future Implementation
       */
   }
   void CheckforWinCondition()
   {
     /*
        piecetype:
        0-15 = pond
        16 -23 = white back pieces 
        24 - 31 = black back pieces
        back pieces order:
        rook, knight, bishop, queen, king, bishop, knight , rook
        */
       //king are 20/28
       
       if(!pieces[20].isAlive)
       {
       System.out.println("Black Wins!");
       endGame = true;
       //Clearing Up Memory (scannerpointer = null)
       scanner.close();
       }
       else if (!pieces[28].isAlive)
       {
       System.out.println("White Wins!");
       endGame = true;
       //Clearing Up Memory (scannerpointer = null)
       scanner.close();
       }
           
   }
   
   
    
    void printBoard()
    {
        
     for(int i = 0 ; i < 8; i++)
        {
            System.out.format("%-3d" , 8-i);
            for(int j =0; j <8 ; j++)
            {
                
                System.out.print(boardPositions[j][7-i]);
            }
            System.out.println(" ");
        }   
      System.out.format("%-4s%-4d%-3d%-4d%-3d%-4d%-3d%-4d%-3d" ,' ',1,2,3,4,5,6,7,8);
                        //s    1   2   3   4   5  6   7
                        System.out.println(" ");
    }
    
}
