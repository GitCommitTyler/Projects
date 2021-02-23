package fix_the_bugs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.lang.Integer;

public class Main {
	
	// made arrays static instead of local to options selection
    private static List<Integer> expenses = new ArrayList<Integer>();
    
    static{
    	expenses.add(1000);
	    expenses.add(2300);
	    expenses.add(45000);
	    expenses.add(32000);
	    expenses.add(110);


	    
    }
    
    public static void main(String[] args) {
        /*System.out.println("Hello World!");*/
        System.out.println("\n**************************************\n");
        System.out.println("\tWelcome to TheDesk \n");
        System.out.println("**************************************");
        optionsSelection();
       
    }
    private static void optionsSelection() {
        String[] arr = {"1. I wish to review my expenditure",
                "2. I wish to add my expenditure",
                "3. I wish to delete my expenditure",
                "4. I wish to sort the expenditures",
                "5. I wish to search for a particular expenditure",
                "6. Close the application"
        };
        int[] arr1 = {1,2,3,4,5,6};
        int  slen = arr1.length;
        for(int i=0; i<slen;i++){
            System.out.println(arr[i]);
            // display the all the Strings mentioned in the String array
        }
        
        
        System.out.println("\nEnter your choice:\t");
        Scanner sc = new Scanner(System.in);
        try {
        int  options =  sc.nextInt();

                switch (options){
                    case 1:
                        System.out.println("Your saved expenses are listed below: \n");
                        System.out.println(expenses+"\n");
                        optionsSelection();
                        break;
                    case 2:
                        System.out.println("Enter the value to add your Expense: \n");
                        int value = sc.nextInt();
                        expenses.add(value);
                        System.out.println("Your value is updated\n");
                        //expenses.addAll(arrlist);
                        System.out.println(expenses+"\n");
                        optionsSelection();

                        break;
                    case 3:
                        System.out.println("You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
                        int con_choice = sc.nextInt();
                        if(con_choice==options){
                               expenses.clear();
                            System.out.println(expenses+"\n");
                            System.out.println("All your expenses are erased!\n");
                        } else {
                            System.out.println("Oops... try again!");
                        }
                        optionsSelection();
                        break;
                    case 4:
                        expenses = sortExpenses((ArrayList<Integer>) expenses);
                        optionsSelection();
                        break;
                    case 5:
                        searchExpenses((ArrayList<Integer>) expenses, sc);
                        optionsSelection();
                        break;
                    case 6:
                        closeApp();
                        break;
                    default:
                        System.out.println("You have made an invalid choice!");
                        optionsSelection();
                        break;
                }


        }
        catch(InputMismatchException ime) {
        	System.out.println("Input a number");
        	System.out.println("----------------------------------------------------------------------------------------");
            //ime.printStackTrace();
        	optionsSelection();	
        }

    }
    private static void closeApp() {
        System.out.println("Closing your application... \nThank you!");}
    
    // linear search
    private static void searchExpenses(ArrayList<Integer> arrayList, Scanner sc) {
		
		  int leng = arrayList.size();
		  
		  System.out.println("Enter the expense you need to search:\t"); //Complete the method 
		  try { 
			  int toFind = sc.nextInt();
			  Optional<Integer> a = expenses.stream().filter(f -> f == toFind)
					  .findFirst(); 
			  System.out.println(a.isPresent() ? "Found "+a.get() :
			  "Found nothing");  
		  }catch(InputMismatchException ime) {
			  sc.nextLine();
			  System.out.println("Input a number to search"); 
			  System.out.println("----------------------------------------------------------------------------------------");
			  searchExpenses((ArrayList<Integer>)expenses, sc);
		  }
		

    	return;
    	
    	
    }
    //merge sort
    private static ArrayList<Integer> sortExpenses(ArrayList<Integer> arrayList) {
    	if (arrayList.size()<2) {

    		return arrayList;}
    	List<Integer> sortedList = new ArrayList<Integer>();
        int arrlength =  arrayList.size();
       //Complete the method. The expenses should be sorted in ascending order.

        List<Integer> one = sortExpenses(new ArrayList<Integer>(arrayList.subList(0, arrlength/2)));
        List<Integer> two = sortExpenses(new ArrayList<Integer>(arrayList.subList(arrlength/2, arrlength)));

        int i =0, j = 0;
        for(;i<one.size() && j < two.size();) {
        	if(one.get(i)<two.get(j)) {
        		sortedList.add(one.get(i));
        		i++;
        	}
        	else {
        		sortedList.add(two.get(j));
        		j++;
        	}
        }
        while(i<one.size()) {
        	sortedList.add(one.get(i));
        	i++;
        	}
        while(j<two.size()) {
        	sortedList.add(two.get(j));
        	j++;
        }
        return (ArrayList<Integer>) sortedList;
    }
}
