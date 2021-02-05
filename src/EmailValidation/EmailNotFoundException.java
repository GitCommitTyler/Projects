package EmailValidation;

public class EmailNotFoundException extends RuntimeException{
    EmailNotFoundException(String emailAddress){
        super("Email address \"" + emailAddress + "\" Not found");
    }
}
