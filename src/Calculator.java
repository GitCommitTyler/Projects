import java.io.*;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calculator{
	
    
    private int priority(char operator){
       switch(operator) 
       {
       case '+': return 1;
       case '-': return 1;
       case '*': return 2;
       case '/': return 2;
       case '^': return 3;
       case '(': return 4;
       }
       return 0;
    }  

    private boolean parseDouble(String chr)
    {
    	try {
    		Double.parseDouble(chr);
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }

   
    public void toPostfix(String expression)
    {
    	Stack<Character> opstack = new Stack<Character>();
    	List<String> postfix = new ArrayList<String>();
    	String curr = "";
    	
    	
    	for(char chr : expression.toCharArray()) {
    		

    		if( Character.isDigit(chr) || chr == '.')
    			curr += chr;
    		else
    		{
    			if (curr.length() > 0)
    			{
    				postfix.add(curr);
    				curr = "";
    			}
    			if (opstack.size() == 0 && chr != ')')
    			{
    				opstack.push(chr);
    			}
    			else if (chr == ')') {
    				while (opstack.size() > 0)
    				{
    					if(opstack.peek() == '(')
    					{
    						opstack.pop();
    						break;
    					}
    					postfix.add(opstack.pop().toString());
    				}
    			}
    			else
    			{
    				
    				if(priority(chr) > priority(opstack.peek()))
    				{
    					opstack.push(chr);
    				}
    				else
    				{
    					while (opstack.size() > 0 && priority(opstack.peek())>= priority(chr))
                        {
                            if (opstack.peek() == '(')
                            {
                                //opstack.pop();
                                break;
                            }
                            postfix.add(opstack.pop().toString());
                        }
                            
                        opstack.push(chr);
    				}
    			}
    		}
    		
    	}
    	if (curr.length() > 0)
        {
            postfix.add(curr);
            curr = "";
        }

        while (opstack.size() > 0)
            postfix.add(opstack.pop().toString());
        
        System.out.println("Answer: " + eval(postfix));
    }

    private double eval(List<String> postfix)
    {
    	Stack<Double> opstack = new Stack<Double>();
    	for (String item : postfix) {
    		
    		if(parseDouble(item)) {
    			opstack.push(Double.parseDouble(item));
    		}
    		else
    		{
    			double result = 0;
    			double op2 = opstack.pop();
    			double op1 = opstack.pop();
    			if (item.equals("+"))
                    result = op1 + op2;
                else if (item.equals("-"))
                    result = op1 - op2;
                else if (item.equals("*"))
                    result = op1 * op2;
                else if (item.equals("/"))
                    result = op1 / op2;
                else if (item.equals("^"))
                    result = Math.pow(op1, op2);
                opstack.push(result);
            }
    	}
    	return opstack.pop();
    }
}

class Main{
    public static void main(String[] args){
    	Calculator calc = new Calculator();
        while(true){
        	System.out.print("Type Expression: ");
        	boolean validityFlag = true;
            String expression = "";
            try{
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                expression = input.readLine();
                for(int i = 0; i < expression.length(); i++) {
                	if(!(Character.isDigit(expression.charAt(i))|| expression.charAt(i) == '+' || 
                			expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/' || 
                			expression.charAt(i) == '^' || expression.charAt(i) == '(' || expression.charAt(i) == ')' || 
                			expression.charAt(i) == '.'))
                	{
                		validityFlag = false;
                	}
                }
                if(validityFlag == false)
                {
                	System.out.println("invalid expression");
                	continue;
                }
                else {
                	calc.toPostfix(expression);
                }
            }catch(Exception e){
                System.out.print("input error\n");
            }  
        }
        
    }
} 