/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
//ChessBoard Variables
import static assignment1.Chess_Board.pieces;
import static assignment1.Chess_Board.boardPositions;
import static assignment1.Chess_Board.turnOrder;
//File Streams
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
//Encapsulation
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */
public class FileHandler {
    //Not yet done (right now reading file)
    //SaveFile
    public static List<Integer> Data = new ArrayList<Integer>();
    public static List<String> DisplayData = new ArrayList<String>();
    public static File saveFile, savedPicture;
   /*
    Encapsulates the Data into a List then writes the encapsulated data to file
    */
    public static void SaveFile ()throws IOException , FileNotFoundException
    {
     /*
    Order of Data:
    xposition, yposition, piecetype, isalive
    */  saveFile = new File("SaveFile.txt");
        savedPicture = new File("SaveImage.txt");
        //Encapsulating Data
         System.out.println("Encapsulating Pieces...");
        for(int i = 0 ; i <32 ; i ++)
        {
            
             Data.add(pieces[i].xPosition);
             Data.add(pieces[i].yPosition);
             //First 16 pieces are white 
            
             if(i<16) 
             Data.add(1);
             //1 = white
             else
             Data.add(0);
             //0 = black
             //end if/else
             if(pieces[i].isAlive)
             Data.add(1);
             //1 the piece is alive
             else
             Data.add(0);
             //0 the piece is dead
             //end if/else
             
        }//end for loop
        if(turnOrder)
        Data.add(1);
        else
        Data.add(0);
      for(int j = 0 ; j<8;j++)
      {
          for (int k = 0 ; k < 8 ; k ++)
          {
               DisplayData.add(boardPositions[k][j]);
          }
      }
       try(RandomAccessFile rai = new RandomAccessFile(savedPicture, "rw"))
        {
             for(int i = 0 ; i < DisplayData.size() ; i ++)
                {
                 
                    rai.writeUTF(DisplayData.get(i));
                    
                }
         rai.seek(0);
            System.out.println("\nDisplay Saved\n");
         
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        catch(IOException e)
        {
            System.out.println("Input/Output Error");
        }
        try(RandomAccessFile raf = new RandomAccessFile(saveFile, "rw"))
        {
             for(int i = 0 ; i < Data.size() ; i ++)
                {
                   
                    raf.writeInt(Data.get(i));
                    
                }
         raf.seek(0);
            System.out.println("\nFile Saved\n");
         
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        catch(IOException e)
        {
            System.out.println("Input/Output Error");
        }
        
    } //end SaveFile
    /*
    Loads the data from file and saves it to a variable to be accessed.
    */
    public static void LoadFile()throws IOException , FileNotFoundException
    {
     try
        {
           
            
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
     FileInputStream fisp = null;
    BufferedInputStream bisp = null;
    DataInputStream disp = null;
 
 
    
      fis = new FileInputStream(saveFile);
 
      // Here BufferedInputStream is added for fast reading.
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);
       fisp = new FileInputStream(savedPicture);
 
      // Here BufferedInputStream is added for fast reading.
      bisp = new BufferedInputStream(fisp);
      disp = new DataInputStream(bisp);
      while (disp.available() != 0)
      {
        DisplayData.add(dis.readUTF());
      }
      // dis.available() returns 0 if the file does not have more lines.
      while (dis.available() != 0) {
 
      // this statement reads the line from the file and print it to
        // the console.
        //System.out.println(dis.readInt());
        Data.add(dis.readInt());
      }
      
 
      // dispose all the resources after using them.
      fis.close();
      bis.close();
      dis.close();
      fisp.close();
      bisp.close();
      disp.close();
 
        
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        catch(IOException e)
        {
            System.out.println("Input/Output Error");
        }//end trycatch
    }//end LoadFile
    
    /*
    Applies the Loaded Changes from File
    */
    public static void ApplyLoadedChanges()
    {
        int j = 0;
        int condition = 0;
      for(int i = 0 ; i <32 ; i ++)
        {
            //j increments by 4 each loop
            pieces[i].xPosition = Data.get(j);
            pieces[i].yPosition = Data.get(++j);
            condition =  Data.get(++j);
             if(condition == 1) 
              pieces[i].colour = true;
             //1 = white
             else
              pieces[i].colour = false;
             //0 = black
             //end if/else
             //Recycling condition
             condition =  Data.get(++j);
             if(condition == 1)
              pieces[i].isAlive = true;
             //1 the piece is alive
             else
              pieces[i].isAlive = false;
             //0 the piece is dead
             //end if/else
             ++j;
             
        }//end for loop
      if(Data.get(j) == 1)
      turnOrder = true;
      else
      turnOrder = false;
      
    }//end ApplyLoadedChanges
    
   public static void printUpdate()
    {
        int l = 0;
         for(int j = 0 ; j<8;j++)
      {
          for (int k = 0 ; k < 8 ; k ++)
          {
               boardPositions[k][j] = DisplayData.get(l++);
          }
      }
    }
}
