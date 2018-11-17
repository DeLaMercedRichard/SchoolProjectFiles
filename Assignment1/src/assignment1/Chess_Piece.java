/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Richard
 */
//select piece
//select move spot
//check if move type is valid
//check if piece is moving outside of board
//check if a piece already there
//check if piece is friend or foe
//Handle Conditions

//if friend invalid movement
//if foe delete foe

public abstract class Chess_Piece {
    
    protected boolean firstTurn, reachedEnd;
    protected int conditionX, conditionY;
    protected int maxMovement;
    public int xPosition, yPosition;
    public boolean colour,specialCase, isAlive;
    //Constructor
    public void CreatePiece(int xPosition_, int yPosition_, boolean Colour)
    {
    xPosition = xPosition_;
    yPosition = yPosition_;
    colour = Colour;
    isAlive = true;
    }
    //Colour true = black, false = white
    public void move(int x, int y)
    {
    xPosition = x;
    yPosition = y;
    
    }
   
    /*
    The same as canMove function, but 
    added additional parameters for convenience
    sake when checking conditions
    */
    
    public boolean validMove(int x, int y, boolean attacking, boolean blockedPath, boolean specialcase)
    {
    return true;
    }
    
    
}
