import java.util.Stack;


public class compute {
	
	static Stack<Character> OPTR = new Stack<Character>();	// 运算符栈
    static Stack<String> NUM = new Stack<String>();		//数字栈
    
	static char[] optr = {'+','-','*','/','(',')','='};
	static char[][] optrPriority ={
	{ '>','>','<','<','<','>','>' },
	{ '>','>','<','<','<','>','>' },
	{ '>','>','>','>','<','>','>' },
	{ '>','>','>','>','<','>','>' },
	{ '<','<','<','<','<','=','0' },
	{ '>','>','>','>','0','>','>' },
	{ '<','<','<','<','<','0','=' },
	};



	static int optrIndex(char ch)
	{
	    for(int i = 0;i < 7;i++)
	        if(ch == optr[i])
	            return i;
	    return -1;
	}

	static char priority(char optr1,char optr2) throws Exception
	{
	    int a = optrIndex(optr1);
	    int b = optrIndex(optr2);
	    if(a == -1 || b == -1)
	    	throw new Exception("表达式有误!");
	    return optrPriority[a][b];
	}

	static double calculate(double b, char theta, double a) throws Exception
	{
	    switch(theta)
	    {
	    case '+':
	        return a + b;
	    case '-':
	        return a - b;
	    case '*':
	        return a * b;
	    case '/':
	        return a / b;	
	    default:
        	throw new Exception("表达式有误!");
	    }
	}
	
	static boolean isdigit(char c) {
		if(c <= '9' && c >= '0')
			return true;
		return false;
	}

	static double answer(String str) throws Exception
	{
	    OPTR.push('=');
	    int flag = 0;        //实现多位数的运算
	    int strIndex = 0;
	    int point = 0;		//小数
	    int index = 1;
	    char c = str.charAt(strIndex++);
	    while(c != '=' || OPTR.peek() != '=')      //未录入所有数据或未运算完
	    {
	        if(isdigit(c))  //是数字
	        {
	            if(flag == 1 && point == 0)       //上一位也是数字
	            {
	                double t = Double.parseDouble(NUM.peek());
	                NUM.pop();
	                double temp = t * 10 + c - '0';
	                NUM.push(String.valueOf(temp));
	            }
	            else if(flag == 1 && point == 1){
	            	double t = Double.parseDouble(NUM.peek());
	            	NUM.pop();
	                double temp = t + (c - '0') * Math.pow(10, -index++);
	                NUM.push(String.valueOf(temp));
	            }
	            else if(flag == 0) {
	            	NUM.push(String.valueOf(c - '0'));
	            	flag = 1;
	            }
	            else 
	            	throw new Exception("表达式有误!");
	            c = str.charAt(strIndex++);
	        }
	        else if(c != '.'){
	            flag = 0;      //最新一位是运算符
	            index = 1;
	            point = 0;
	            switch(priority(OPTR.peek(),c))
	            {
	            case '<':
	                OPTR.push(c);
	                c = str.charAt(strIndex++);      //接收下一位
	                break;
	            case '=':
	                OPTR.pop();         //括号
	                c = str.charAt(strIndex++);
	                break;
	            case '>':               //进行运算并压入结果
	                double num1 = Double.parseDouble(NUM.peek());
	                NUM.pop();
	                double num2 = Double.parseDouble(NUM.peek());
	                NUM.pop();
	                char theta = OPTR.peek();
	                OPTR.pop();
	                NUM.push(String.valueOf(calculate(num1,theta,num2)));
	                break;
	            case 'e':
	            	throw new Exception("表达式有误!");
	            }
	        }
	        else if(point != 1){
	        	point = 1;
	        	c = str.charAt(strIndex++);
	        }
	        else
	        	throw new Exception("表达式有误!");
	    }
	    if(OPTR.firstElement() != '=') {
	    	throw new Exception("表达式有误!");
	    }
	    return Double.parseDouble(NUM.peek());
	}
	
	static public double calcul(final String s) throws Exception {
	    while (!OPTR.empty())OPTR.pop();
	    while (!NUM.empty())NUM.pop();
	    double a = answer(s);
		return a;	
	}
}
