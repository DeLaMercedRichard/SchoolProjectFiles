/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
import java.lang.Math;
/**
 *
 * @author Richard De La Merced
 * First Draft
 * 
 * @author Arielle Vachon
 * Finalized Draft
 */
public class Pawn extends Chess_Piece
{

  
     
    @Override
    public void CreatePiece(int xPosition_, int yPosition_, boolean colour_) {
        /*All Pieces*/
        xPosition = xPosition_;
        yPosition = yPosition_;
        colour = colour_;
        /*Pawn  & King Restricted Only*/
        firstTurn = true;
        /*Pawn Only*/
        reachedEnd = false;
        specialCase = false;
         isAlive = true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void move(int xPosition_, int yPosition_) {
      /*
      Applies to All Pieces  
      */
        xPosition = xPosition_;
        yPosition = yPosition_;
        /*
        Applies to Pawn and King
        */
        firstTurn = false;
        
         
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validMove(int xPosition_, int yPosition_, boolean attacking, boolean blockedPath, boolean specialCase) {
        
        if(!isAlive)
            return false;
        if(xPosition_ > 7 || xPosition_ < 0 || yPosition_> 7 || yPosition_<0)
        return false;
        
        conditionX = xPosition_ - xPosition;
        conditionY = yPosition_ - yPosition ;
        
        if(conditionY < 0 && colour || conditionY > 0 && !colour) 
            return false;
        
        conditionY = Math.abs(conditionY);
        
        //making a piece not move by selecting its original spot cancels action
        if(xPosition == xPosition_ && yPosition == yPosition_)
            return false;
        
        if(!attacking && blockedPath)
            return false;
        /*
        Checks if the input is outside of the scope of the board Applies to All Pieces
        */
        

        /*
         Restricted to Pawn Movements only
        */
         if(firstTurn)
        {
                maxMovement = 2; 
        }
        else
        {   
                maxMovement = 1;
        }
         
         /*
         Checks if the max movement of pawn is being overexceeded
         Only needed for pawn and king
         */        
         

        
        if(Math.abs(conditionY)>maxMovement) 
            return false;
        /*Helps write cconditional statements later*/

       /*Condition movement for white side Applicable to only Pawns*/
        //if(colour) //if(White)
        //{
          
         if(attacking) 
         {
             /*
             First and second condition makes sure it moves one space diagonally forward
             */
             if(Math.abs(conditionX)==1 && conditionY==1)
                 return true;
             else 
                 return false;
         }/*Checks if pawn is attacking properly*/
          //Pawn basic Movement   
         else//attacking conditions
         {
            //moves pawn "forward only" maxMovement is positive 
             //conditionY = new position - old position should return positive if white
            /*
             first condition checks for any sidemovement
             second condition checks for max movement
             third condition checks for direction
             */
             if(conditionX==0 && conditionY>0)
                return true;
             else
                return false;
         } //end if(attacking)else
             /*Checks if the pawn is moving forward properly*/
             
                 
        //}//end if(colour)
//        else//black conditions
//        {
//            /*Black starts from top*/
//         if(attacking) 
//             /*Checks if pawn is attacking properly*/
//             if(Math.abs(conditionX)==1 && conditionY== -1)
//                 return true;
//             else 
//                 return false;
//         else //not attacking condition
//         {
//            if(conditionX==0&&conditionY<0)
//               return true;
//            else
//               return false;
//         } //end attacking else
//              
//                 
//        } //end colour else
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Knight = Peter
    //nick = rook
    //king carol
    //lucca bishop
    //pawn_ , rook_, knight_ ,bishop_, king_, queen 

   
    
    
    

   
    
    
    
}
