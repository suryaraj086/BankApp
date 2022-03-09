package myexception;

@SuppressWarnings("serial")
public class CustomException extends Exception {  
    public CustomException(String errorMessage) {  
    super(errorMessage);  
    }
    
    public CustomException(Exception e) {  
        super(e);  
        }
    
}  
