package email_validation;



public class InvalidEmailException extends RuntimeException{
    InvalidEmailException(String emailAddress){
        super("Email \""+emailAddress+"\" is invalid");
    }
}
