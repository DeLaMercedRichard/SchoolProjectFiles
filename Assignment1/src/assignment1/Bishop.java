/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Lucca Lamacchia
 */
import java.lang.Math;
public class Bishop extends Chess_Piece{

    
   
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

                if (xPosition < 0 || xPosition > 7) {
                    return false;
                }

                if (yPosition < 0 || yPosition > 7) {
                    return false;
                }

                if (Math.abs (xPosition - xPosition_) == Math.abs (yPosition - yPosition_))
                    return true;
                else
                return false;
            
            
        
        }
}
