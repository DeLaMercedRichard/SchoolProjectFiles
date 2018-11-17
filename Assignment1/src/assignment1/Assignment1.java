/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * BLUB BLUB BLUB BLUB!
 */
package assignment1;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Group Members in Alphabetical Order 
 * @author Arielle Vachon       (n01220095)
 * @author Carol Aleman         (n00480006)
 *
 * @author Peter Saccoia-Adams  (n01050057)
 * @author Richard De La Merced (n01007309)
 * 
 * 
 */
public class Assignment1 {

    /**
     * For Simplicity sakes we took out the check and checkmate factor.
     * We settled for a win condition of if the king dies you win!
     * @param args
     * @throws java.io.IOException
     */
    
    
    public static void main(String[] args) throws InputMismatchException, IOException
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("[,\\s+]");
        boolean turnOrder;
        boolean exit;
        int selection;
        exit= false;
        Chess_Board game = new Chess_Board();
            
        javax.swing.JFrame frame = new javax.swing.JFrame("Chess");
       
        
        
       while(!exit)
       {
             turnOrder = true;
             
              /*
             
             */
             
             game.createBoard();
 frame.setContentPane(new Game_Board(game.boardPositions));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 600, 600);
             game.printBoard();
            while(!game.endGame)
            {
               if(turnOrder)
               {
               System.out.println("White's Move");
               }
               else
               {
               System.out.println("Black's Move");
               }
               game.ManageBoard(turnOrder);

               game.printBoard();

               turnOrder = !turnOrder;

            }
            System.out.println("Enter 1 to play again or 2 to exit");
            try
            {
            selection = scanner.nextInt();
            if(selection ==1)
            {
             //exit = false;
            }
            else if (selection ==2)
            {
             exit = true;
            }
            else
                throw new InputMismatchException("No Known Selection");
            }
            catch(InputMismatchException e)
            {
            System.out.println("No Known Selection");
            }
            
            
       }
       
     
    }
    
}
