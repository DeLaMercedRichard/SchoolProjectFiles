/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
import java.lang.Math;
/**
 *@author Arielle Vachon
 * Finalized Draft
 * @author Carol Aleman
 * First Draft
 */
public class King extends Chess_Piece
{
 public int xPosition;
    public int yPosition;
    public boolean colour, firstTurn, specialCase;
        //@Override
	public void CreatePiece(int xPosition_, int yPosition_, boolean colour_)
        {
         
            xPosition = xPosition_;
            yPosition = yPosition_;
            colour = colour_;
            firstTurn = true;
            specialCase = false;
             isAlive = true;
            
        }
        @Override
        public void move(int xPosition_, int yPosition_) 
        {
           xPosition = xPosition_;
           yPosition = yPosition_;
           ///firstTurn = false;
        
        }
        @Override
        public boolean validMove(int xPosition_, int yPosition_, boolean attacking, boolean blockedPath, boolean specialCase_)
        {
              
           if(!isAlive)
               return false;
            conditionX = Math.abs(xPosition - xPosition_);
            conditionY = Math.abs(yPosition - yPosition_);
            
            //Checks if GameBoard is letting this piece know if its path is blocked
            if (blockedPath) 
                return false; 
            
            if (xPosition_ < 0 || xPosition_ > 7) {
                return false;
            }
            
            if (yPosition_ < 0 || yPosition_ > 7) {
                return false;
            }
            
            //King moves in 1 direction any way
            if(conditionX > 1 || conditionY > 1)
            {
                //Castling
              if(firstTurn && specialCase_ && conditionX == 2 && conditionY == 0)
                  return true;
              
              else return false;
              
              
            }
            if(conditionX == 1 || conditionY == 1)
                 return true;
                        
             else return false;
        
        
        
        }    
}
