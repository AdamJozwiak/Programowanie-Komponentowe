package com.sudokuGUI;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.*;
import java.io.IOException;


public class Loggery {


    public Loggery( )throws IOException{
        Logger logger = Logger.getLogger("Sudoku");
        try {
            FileInputStream configFile =  new FileInputStream("logConfig.txt");

            LogManager.getLogManager().readConfiguration(configFile);
        } catch (IOException ex){
            ex.printStackTrace();
        }


        //logger.setLevel(Level.FINEST);
        //fileTxt = new FileHandler("Logging.txt");
        //formatterTxt = new SimpleFormatter();
        //fileTxt.setFormatter(formatterTxt);
       // logger.addHandler(fileTxt);


    }
}
