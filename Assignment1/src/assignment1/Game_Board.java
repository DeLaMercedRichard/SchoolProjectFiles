
package assignment1;
//import static assignment1.Chess_Board.boardPositions;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Richard n01007309
 */

public class Game_Board extends javax.swing.JPanel {
    //<editor-fold defaultstate = "collapsed" desc="Variables">
    /*Chess_Tiles Text = boardPositions[][]*/
    public static javax.swing.JButton Chess_Tiles[][] = new javax.swing.JButton[8][8];
    public static javax.swing.JLabel Chess_Tile_Labels[] = new javax.swing.JLabel[8];
    public static boolean hasSelected;
    public static int[] selection = new int[2]; //[] = new int[2] returns the x and y selection
    public static String[][] tileText;
    //</editor-fold>
    /**
     * Creates new form Game_Board
     */
    public Game_Board(String  boardpositions[][]) {
        hasSelected = false;
        tileText = boardpositions;
        
        initComponents();
        
    }

    
  //<editor-fold defaultstate="collapsed" desc="All Button/Tile Codes for GameBoard">
    public void setGameBoardTiles()
    {
        /*Updates the Game UI with the appropriate texts based on the boardPositions*/
        for(int j = 0 ; j < 8; j++)
        {
            for(int i = 0 ; i < 8 ; i ++)
            {
                Chess_Tiles[i][j] = new javax.swing.JButton(tileText[i][j]);
               Chess_Tiles[i][j].setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));
                //Chess_Tiles[i][j].setText(boardPositions[i][j]);
                Chess_Tiles[i][j].addActionListener
                    (
                        (java.awt.event.ActionEvent evt) -> 
                            {
                                updateSelectedTile(evt);
                            }
                    );
                
            }
        }
    }
    public void updateTiles(String  boardpositions[][])
    {
     tileText = boardpositions;
        for(int j = 0 ; j < 8; j++)
        {
            for(int i = 0 ; i < 8 ; i ++)
            {
                Chess_Tiles[i][j].setText(tileText[i][j]);
            }
        }
    }
    public void updateSelectedTile(java.awt.event.ActionEvent evt)
    {
        /*
        Lets Game_Board know if the person has selected something or not.
        The Game_Board then can handle the selection process
        */
        hasSelected = true;
        javax.swing.JButton currentButton = (javax.swing.JButton)evt.getSource();
        for(int j = 0; j < 8 ; j ++)
        {
            for(int i = 0 ; i <8 ; i ++)
            {
                if(currentButton == Chess_Tiles[i][j])
                {
                 selection[0] = i;
                 selection[1] = j;
                         
                }
            }
        }
        //xSelection = currentButtonx?
        //ySelection = currentButtony?
    }
    
    //</editor-fold>
      @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Initializing Board">                
    
    private void initComponents() {

        java.awt.GridLayout layout = new java.awt.GridLayout(8,8);
        this.setLayout(layout);
        setGameBoardTiles();
        for(int j = 0 ; j < 8; j++)
        {
            for(int i = 0 ; i < 8; i ++)
            {
                //Because of how the grid layout adds objects
                //The Y axis needs to be inverted
             this.add(Chess_Tiles[i][7-j]);
            }
        }
       
         
       // pack();
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
