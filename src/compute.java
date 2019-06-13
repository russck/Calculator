import java.util.Stack;


public class compute {
	
	static Stack<Character> OPTR = new Stack<Character>();	// �����ջ
    static Stack<String> NUM = new Stack<String>();		//����ջ
    
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
	    	throw new Exception("���ʽ����!");
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
        	throw new Exception("���ʽ����!");
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
	    int flag = 0;        //ʵ�ֶ�λ��������
	    int strIndex = 0;
	    int point = 0;		//С��
	    int index = 1;
	    char c = str.charAt(strIndex++);
	    while(c != '=' || OPTR.peek() != '=')      //δ¼���������ݻ�δ������
	    {
	        if(isdigit(c))  //������
	        {
	            if(flag == 1 && point == 0)       //��һλҲ������
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
	            	throw new Exception("���ʽ����!");
	            c = str.charAt(strIndex++);
	        }
	        else if(c != '.'){
	            flag = 0;      //����һλ�������
	            index = 1;
	            point = 0;
	            switch(priority(OPTR.peek(),c))
	            {
	            case '<':
	                OPTR.push(c);
	                c = str.charAt(strIndex++);      //������һλ
	                break;
	            case '=':
	                OPTR.pop();         //����
	                c = str.charAt(strIndex++);
	                break;
	            case '>':               //�������㲢ѹ����
	                double num1 = Double.parseDouble(NUM.peek());
	                NUM.pop();
	                double num2 = Double.parseDouble(NUM.peek());
	                NUM.pop();
	                char theta = OPTR.peek();
	                OPTR.pop();
	                NUM.push(String.valueOf(calculate(num1,theta,num2)));
	                break;
	            case 'e':
	            	throw new Exception("���ʽ����!");
	            }
	        }
	        else if(point != 1){
	        	point = 1;
	        	c = str.charAt(strIndex++);
	        }
	        else
	        	throw new Exception("���ʽ����!");
	    }
	    if(OPTR.firstElement() != '=') {
	    	throw new Exception("���ʽ����!");
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
