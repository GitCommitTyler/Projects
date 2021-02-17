package file_handling;
import java.util.Scanner;

public class Menu {
    //Allow user to input file name and options for what to do with desired file
    public static void runMenu(){
        System.out.print("Enter file name: ");
            Scanner in = new Scanner(System.in);
            if(in.hasNextLine()){
                System.out.print("What to do with file? Type \"read,\" \"write,\" or \"append\": ");
                Scanner option = new Scanner(System.in);
                switch (option.nextLine()) {
                    case "read": FileHandler.ReadFile(in.nextLine());
                        break;
                    case "write": FileHandler.WriteFile(in.nextLine());
                        break;
                    case "append": FileHandler.AppendToFile(in.nextLine());
                        break;
                    default: 
                        System.out.println("unknown command");
                        option.close();
                        in.close();
                        break;
                }
            }
    }
}
