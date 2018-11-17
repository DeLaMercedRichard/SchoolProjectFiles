/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Arielle Vachon 
 * Finalized Draft
 */

/**
 *
 * @author Nicholas Melo
 * First Draft
 */
public class Rook extends Chess_Piece{
//select piece
//select move spot
//check if move type is valid
//check if piece is moving outside of board
//check if a piece already there
//check if piece is friend or foe
//Handle Conditions

//if friend invalid movement
//if foe delete foe


    

    @Override
    public void CreatePiece(int xPosition_, int yPosition_, boolean colour_){
    xPosition = xPosition_;
    yPosition = yPosition_;
    colour = colour_;
     isAlive = true;
    }
    
    @Override
    public void move(int xPosition_, int yPosition_) 
    {
        xPosition = xPosition_;
        xPosition = yPosition_;
    }
    
      //Colour false = black, true = white
    @Override
    public boolean validMove(int xPosition_, int yPosition_, boolean attacking, boolean blockedPath, boolean specialcase){
    // the rook goes in a straight line upwards/downwards and left/right like a cross
//        if (row == this.row || col == this.col) 
//            return true;
        
        if(!isAlive)
            return false;
        if (blockedPath) 
                return false; 
        
        if (xPosition_ < 0 || xPosition_ > 7) 
            return false;
        
        if (yPosition_ < 0 || yPosition_ > 7) 
            return false;
        
        if(xPosition == xPosition_ && yPosition == yPosition_)
                return false;
        
        // if the new x and y values are both different from the current position, it was a diagonal move and is invalid
        if (xPosition != xPosition_ && yPosition != yPosition_)
            return false;
        
        return true;
    }


    
   
    
}

