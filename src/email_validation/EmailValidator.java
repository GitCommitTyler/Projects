package email_validation;


import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

//Checks if an email entered is in valid email address format and checks if it's in the array
public class EmailValidator {
    private String[] emailAddresses = { "tyler.nelson@gmail.com", "RobertBirch@yahoo.com", "bobbyshmurda@prison.org",
            "yerp@derp.net" };

    private String emailAdd2read;

    public void validateEmail() {
        try {
            checkEmailValid();
            System.out.println("Address is valid!");
            TimeUnit.SECONDS.sleep(3);
            this.checkAddress();
            System.out.println("Address was found!");
        } catch (InvalidEmailException ie) {
            System.err.println(ie.getMessage());
            return;
        }
        catch (InterruptedException ine){
            System.err.println(ine.getMessage());
        }
        catch (EmailNotFoundException enf) {
            System.err.println(enf.getMessage());
        }
        
    }

    //checks email against regular expression capturing the format of an email address
    private boolean checkEmailValid() throws InvalidEmailException{

        final String regex = "(([\\w]+)|([\\w]+[.]{0,1}[\\w]+)+)[@](([\\w]+[.]{1,}[\\w]+)+)$";
        final String string = this.emailAdd2read;
        

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);


        final boolean result = matcher.find();
        if(!result){
            System.out.println("Match result: " + result);
            throw new InvalidEmailException(this.emailAdd2read);
        }
        else
            System.out.println("Match result: " + result);
            return result;
    }

    //set email address to be checked
    public void setEmailAdd2Read(String emailAddress){
        this.emailAdd2read = emailAddress;
    }
    
    //checks if email address is in the array
    public boolean checkAddress() throws EmailNotFoundException{
        Arrays.sort(this.emailAddresses);
        if(Arrays.binarySearch(this.emailAddresses, this.emailAdd2read) < 0)
            throw new EmailNotFoundException(this.emailAdd2read);
        else  
            return(true);
    }

}

class Main2{

    //main loop 
    public static void main(String[] args){
        EmailValidator ev = new EmailValidator();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter an email address");
            
            while(sc.hasNextLine()){
                ev.setEmailAdd2Read(sc.nextLine());
                ev.validateEmail();
            }
            sc.close();
        }
    }
    
}
