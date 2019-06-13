import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//计算器界面
public class mainGUI {
	public static JFrame cal = new JFrame();
	static JPanel butP = new JPanel();
	static JPanel textP = new JPanel();
	static GridLayout grid = new GridLayout(5,4);
	static BorderLayout border = new BorderLayout();
	//按钮
	static JButton b1 = new JButton("1");
	static JButton b2 = new JButton("2");
	static JButton b3 = new JButton("3");
	static JButton b4 = new JButton("4");
	static JButton b5 = new JButton("5");
	static JButton b6 = new JButton("6");
	static JButton b7 = new JButton("7");
	static JButton b8 = new JButton("8");
	static JButton b9 = new JButton("9");
	static JButton b0 = new JButton("0");
	static JButton bplus = new JButton("+");
	static JButton bsub = new JButton("-");
	static JButton bmulti = new JButton("*");
	static JButton bdiv = new JButton("/");
	static JButton bequal = new JButton("=");
	static JButton bleft = new JButton("(");
	static JButton bright = new JButton(")");
	static JButton bpoint = new JButton(".");
	static JButton bclear = new JButton("C");
	static JButton bcancel = new JButton("<-");
	static JTextField text = new JTextField(35);
	
	public static void main(String[] args) {
		cal.setSize(550,300);
		cal.setResizable(false);
		cal.setLocationRelativeTo(null);
		cal.setTitle("简易计算器");
		Container container = cal.getContentPane();
		container.setLayout(border);
		//文本框
		text.setHorizontalAlignment(JTextField.RIGHT);
		textP.add(text);
		container.add(textP,BorderLayout.NORTH);
		//按钮
		ButtonInitial();
		container.add(butP,BorderLayout.CENTER);
		cal.setVisible(true);
	}
	//按钮初始化
	static void ButtonInitial() {
		butP.setLayout(grid);
		butP.add(bleft);
		butP.add(bright);
		butP.add(bcancel);
		butP.add(bclear);
		butP.add(b7);
		butP.add(b8);
		butP.add(b9);
		butP.add(bdiv);
		butP.add(b4);
		butP.add(b5);
		butP.add(b6);
		butP.add(bmulti);
		butP.add(b1);
		butP.add(b2);
		butP.add(b3);
		butP.add(bsub);
		butP.add(b0);
		butP.add(bpoint);
		butP.add(bequal);
		butP.add(bplus);
		bleft.addActionListener(new ButtonListener1());
		b1.addActionListener(new ButtonListener1());
		b2.addActionListener(new ButtonListener1());
		b3.addActionListener(new ButtonListener1());
		b4.addActionListener(new ButtonListener1());
		b5.addActionListener(new ButtonListener1());
		b6.addActionListener(new ButtonListener1());
		b7.addActionListener(new ButtonListener1());
		b8.addActionListener(new ButtonListener1());
		b9.addActionListener(new ButtonListener1());
		b0.addActionListener(new ButtonListener1());
		bright.addActionListener(new ButtonListener1());
		bpoint.addActionListener(new ButtonListener1());
		bplus.addActionListener(new ButtonListener1());
		bdiv.addActionListener(new ButtonListener1());
		bsub.addActionListener(new ButtonListener1());
		bmulti.addActionListener(new ButtonListener1());
		bcancel.addActionListener(new ButtonListener2());
		bclear.addActionListener(new ButtonListener3());
		bequal.addActionListener(new ButtonListener4());
	}
	//普通按钮
	static class ButtonListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String ch =  e.getActionCommand();
			text.setText(text.getText() + ch);
		}
	}
	//删除按钮
	static class ButtonListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!text.toString().equals("")) {
				String s = text.getText();
				s = s.substring(0,s.length()- 1);
				text.setText(s);
			}
		}
	}
	//清除按钮
	static class ButtonListener3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			text.setText("");
		}
	}
	//等于按钮
	static class ButtonListener4 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String str = text.getText() + '=';
			double ans = 0;
			try {
				ans = compute.calcul(str);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog( cal , "表达式有误!" ,"消息" , JOptionPane.ERROR_MESSAGE) ;
			}
			text.setText(String.valueOf(ans));
		}
	}
}
