import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;

import javax.swing.*;

import com.util.CheckEntries;
import com.util.DataUtil;


public class home extends JFrame implements ActionListener,FocusListener{
	
	JLabel head,lpin,lcard,vpin,vcard;
	JTextField tcard,tpin;
	JButton bsignin,bsignup,bclear;
	String h,card=null,pin=null;
	int status1=0,status2=0,status3=0,status4=0,y=0;
	DataUtil d1;
	
	int x=0;
	public home() {
		super("AUTOMATED TELLER MACHINE");
		setLayout(null);
		head=new JLabel("WELCOME TO ATM");
		Font fh=new Font("Arial Rounded MT Bold",Font.PLAIN,40);
		Font fh1=new Font("Arial Rounded MT Bold",Font.PLAIN,20);
		head.setFont(fh);
		lcard=new JLabel("CARD NO.:");
		lpin=new JLabel("PIN NO.:");
		lcard.setFont(fh1);
		lpin.setFont(fh1);
		tcard=new JTextField();
		tpin=new JTextField();
		bsignin=new JButton("SIGN IN");
		bsignup=new JButton("SIGN UP");
		bclear=new JButton("CLEAR");
		head.setBounds(400,150,1800,60);
		lcard.setBounds(380, 300, 120,40);
		tcard.setBounds(530, 300, 200, 35);
		lpin.setBounds(380, 380, 120, 40);
		tpin.setBounds(530, 380, 200, 35);
		bsignin.setBounds(530,450,100,30);
		bclear.setBounds(650,450,100,30);
		bsignup.setBounds(530,500,200,30);
		vpin=new JLabel("");
		vpin.setBounds(800,380,220,37);
		add(vpin);
		vcard=new JLabel("");
		vcard.setBounds(800,300,220,37);
		add(vcard);
		bsignup.addActionListener(this);
		add(head);
		add(lcard);
		add(tcard);
		add(lpin);
		add(tpin);
		add(bsignin);
		add(bsignup);
		add(bclear);
		tpin.addFocusListener(this);
		tcard.addFocusListener(this);
		vpin.setForeground(Color.RED);
		vcard.setForeground(Color.RED);
		bsignin.addActionListener(this);
		bclear.addActionListener(this);
		setSize(700,700);
		setVisible(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
}
	
	public static void main(String[] args) {
		new home();
		

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		card=tcard.getText().trim();
		pin=tpin.getText().trim();
		if(ae.getSource()==bsignup)
		{
			vcard.setText("");
			vpin.setText("");
			this.dispose();
			new personaldetails();
		}
		else if(ae.getSource()==bsignin){
			if(status1==1 && status2==1)
			{
				Connection con;
				String e=null,c=null,a=null;
				d1=new DataUtil();
				 con=d1.getConnection();
				 String real=null,real1=null;
				try
				{	 String str6="select appno,atmpin,cardno from cus_info";
						PreparedStatement pst6=con.prepareStatement(str6);
						System.out.println(pst6.toString());
						ResultSet rst6=pst6.executeQuery();
						System.out.println(rst6.toString());
						//rst6.first();
						while(rst6.next()){
						 e=rst6.getString(1);
						 a=rst6.getString(2);
						 c=rst6.getString(3);
						 if((card.equals(c)) && (pin.equals(a)))
							{
							 	x=1;
								JOptionPane.showMessageDialog(this, "WELCOME");
								String str2="select appno from cus_info where atmpin=? and cardno=?";
								PreparedStatement pst2=con.prepareStatement(str2);
								pst2.setString(1, a);
								pst2.setString(2, c);
								ResultSet rst2=pst2.executeQuery();
								System.out.println(pst2.toString());
								while(rst2.next())
								{
									real=rst2.getString(1);
								}
								ArrayList<String> data=new ArrayList<String>();
								String str3="select appno from cus_statement";
								PreparedStatement pst3=con.prepareStatement(str3);
								ResultSet rst3=pst3.executeQuery();
								while(rst3.next())
								{
									//real1=rst3.getString(1);
									if(real.equals(rst3.getString(1)))
									{
										y=1;
									}
								}
								if(y!=1)
								{
									data.add(real.toString());
									data.add(DateFormat.getDateTimeInstance().format(new java.util.Date()));
									data.add("0");
									data.add("0");
									data.add("0");
								}
								else if(y==1)
								{
									try{
										d1=new DataUtil();
										 con=d1.getConnection();
										String str4="select date,credit,debit,balance from cus_statement where appno=?";
										PreparedStatement pst4=con.prepareStatement(str4);
										pst4.setString(1, real);
										System.out.println(pst4.toString());
										ResultSet rst4=pst4.executeQuery();
										rst4.last();
										data.add(real);
										data.add(DateFormat.getDateTimeInstance().format(new java.util.Date()));
										data.add(rst4.getString(2));
										data.add(rst4.getString(3));
										data.add(rst4.getString(4));
									}
									catch(Exception asdd)
									{
										System.out.println(asdd.toString());
									}
								}
								
								String msg=d1.insert(con, "cus_statement", data);
								JOptionPane.showMessageDialog(getContentPane(), msg);
								this.dispose();
								new Trasaction();
								}
								
							
						}
						if(x!=1)
						{
							JOptionPane.showMessageDialog(this,"INVALID DETAILS");
						}
						
						
					
						
						
						System.out.println("asdfgcjfjdsjfjsehfjsfjsfsf");
						System.out.println(e);
						System.out.println(a);
						System.out.println(c);
						
				}
				catch(Exception er)
				{
					System.out.println(er.toString());
				}
				
			
				
			}
			else if(status1==2 || status2==2)
			{
				JOptionPane.showMessageDialog(this, "Complete the Validations");
			}
		}
		else if(ae.getSource()==bclear)
		{
			tpin.setText(null);
			tcard.setText(null);
		}
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent ae) {
		CheckEntries ce=new CheckEntries();
		if(ae.getSource()==tcard){
			String g=tcard.getText().trim();
			boolean bsave=ce.CheckCard(g);
			try{
				if(bsave==true){
					vcard.setText(" ");
					status1=1;
				}
				else if(bsave==false){
					
					vcard.setText("Invalid CARD Number");
					status1=2;
				}
			}
			catch(StringIndexOutOfBoundsException e){
			
				vcard.setText("Invalid GST Number");
			}
		}
		else if(ae.getSource()==tpin){
			String g=tpin.getText().trim();
			boolean bsave=ce.CheckPin(g);
			try{
				if(bsave==true){
					vpin.setText(" ");
					status2=1;
				}
				else if(bsave==false){
					
					vpin.setText("Invalid PIN");
					status2=2;
					
				}
			}
			catch(StringIndexOutOfBoundsException e){
			
				vpin.setText("Invalid PIN");
			}
		}
		
	}

}
