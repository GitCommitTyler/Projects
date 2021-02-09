package FileHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.Stream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.util.Arrays;

//Reads writes and appends to a file
public class FileHandler {

    // Reads a file if it exists.
    // Catching that MalformedInputException doesn't work
    static void ReadFile(String fileToRead){
        try {
            File file = new File(fileToRead);
            if(file.exists()){
                Files.lines(Paths.get(fileToRead)).forEach(System.out::println);
                Files.lines(Paths.get(fileToRead)).close();
            }
            else{
                System.out.println("File not Found");
            }
        } catch (MalformedInputException m) {
            System.out.println(m.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Write to a file. Creates a file and writes a line of text to it.
    static void WriteFile(String fileName){
        System.out.print("Type text and press enter: ");
        try {
            Scanner textToWrite = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(fileName);
            Stream.of(textToWrite.nextLine()).forEach(pw::println);
            textToWrite.close();
            pw.close();
        }catch (IOException iex) {
            System.out.println(iex.getMessage());
            iex.printStackTrace();
        }
    }

    //Appends text to a file. Creates the file if it doesn't exist.
    static void AppendToFile(String fileName){
        System.out.print("Type text and press enter: ");
        Scanner textToAppend = new Scanner(System.in);
        try {
            FileWriter fw = new FileWriter(fileName, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(textToAppend.nextLine());
            pw.close();
            textToAppend.close();

        } catch (IOException iex) {
            System.out.println(iex.getMessage());
            iex.printStackTrace();
        }
    }
}

class Main4{
    public static void main(String[] args){
        Menu.runMenu();
        

        // FileHandler.ReadFile(in.nextLine());
        // FileHandler.WriteFile(scanner.nextLine());
        //FileHandler.AppendToFile(scanner.nextLine());

    }
}