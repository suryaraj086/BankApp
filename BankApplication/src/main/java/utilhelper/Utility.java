package utilhelper;
import java.util.Map;
import myexception.*;

public class Utility {

	   public static String nullChecker(String input) throws CustomException
	    {
	        if (input == null ||input.isEmpty()) 
	        {

	                throw new CustomException("Object can't be null");
	       
	        }
			return input;
	    }
	   
	   public static Object nullChecker(Object input) throws CustomException
	    {
	        if (input == null) 
	        {

	                throw new CustomException("Object can't be null");
	       
	        }
			return input;
	    }
	   
	   public static void hashNullChecker(Map<Object, Object> input) throws CustomException
	    {
	        if (input == null) 
	        {

	                throw new CustomException("Map can't be null");
	       
	        }
	    }
	   
	   public static int getLength(String input) throws CustomException
	    {
		   nullChecker(input);
		   return input.length();
	    }
	   
	   public static int getLength(Map<Object, Object> input) throws CustomException
	    {
		   nullChecker(input);
		   return input.size();
	    }
	   
}
