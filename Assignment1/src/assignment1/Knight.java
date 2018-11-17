
package assignment1;

/*
@author Peter Saccoia-Adams
First Draft
@author Richard De La Merced
Final Draft
*/
//Peter Saccoia-Adams (n01050057)

import java.lang.Math;
public class Knight extends Chess_Piece{

    
    
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
           
            if(
                    Math.abs(xPosition - xPosition_) == 2 && Math.abs (yPosition - yPosition_) == 1 
                    || //or
                    Math.abs (yPosition - yPosition_) == 1 && Math.abs(xPosition - xPosition_) == 2
              )
                return true;
        else
        return false;
        
        
        
        }
}

