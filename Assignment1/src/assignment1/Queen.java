/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Owner
 */
import java.lang.Math;
public class Queen extends Chess_Piece{

    
    
        @Override
	public void CreatePiece(int xPosition_, int yPosition_, boolean colour_)
        {
         
            xPosition = xPosition_;
            yPosition = yPosition_;
            colour = colour_;
             isAlive = true;
        }
        @Override
        public void move(int xPosition_, int yPosition_) 
        {
           xPosition = xPosition_;
           yPosition = yPosition_;
        }
        @Override
        public boolean validMove(int xPosition_, int yPosition_, boolean attacking, boolean blockedPath, boolean specialCase)
        {  
            if(!isAlive)
                return false;
            if (blockedPath) 
                return false; 
            
            if (xPosition_ < 0 || xPosition_ > 7) {
                return false;
            }
           
            if(xPosition == xPosition_ && yPosition == yPosition_)
                return false;
            else if (Math.abs (xPosition - xPosition_) == Math.abs (yPosition - yPosition_))
            return true;
            else if (xPosition == xPosition_ || yPosition == yPosition_)
                return true;
            else
            return false;
        
        
        
        }
}

