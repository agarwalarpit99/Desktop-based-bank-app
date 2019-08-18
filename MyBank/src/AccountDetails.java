import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.util.DataUtil;


public class AccountDetails extends JFrame implements ActionListener{
	JLabel head,lacctype,lcardno,lpin,l1,lxx,lx,l2,lservice;
	ButtonGroup bg;
	JRadioButton rsav,rcurr,rfix,rrda;
	JCheckBox jatm,jmobile,jcheque,jnet,jemail,jstatement,jdeclare;
	JButton bsubmit,bcancel;
	DataUtil d1,d2;
	String ms2=null,em,at,ca;
	ArrayList al3,al9,al2;
	int x=0,x1=0,hg2=0;
	
	String y1=null,y2=null,xc=null;
	String satm=null,smobile=null,scheque=null,snet=null,semail=null,sstatement=null;
	public AccountDetails() {
		super("NEW ACCOUNT APPLICATION FORM-PAGE 3");
		setLayout(null);
		head=new JLabel("Page 3 : Account Details");
		Font f1=new Font("Arial Rounded MT Bold",Font.PLAIN,25);
		Font f2=new Font("Arial Rounded MT Bold",Font.PLAIN,18);
		Font f3=new Font("Arial Rounded MT Bold",Font.PLAIN,14);
		Font f4=new Font("Arial",Font.PLAIN,13);
		Font f5=new Font("Arial Rounded MT Bold",Font.PLAIN,20);
		head.setFont(f1);
		head.setBounds(500,10,400,40);
		add(head);
		lacctype=new JLabel("Account Type :");
		lacctype.setBounds(100,80,150,35);
		add(lacctype);
		lacctype.setFont(f2);
		bg=new ButtonGroup();
		rsav=new JRadioButton("Saving Account",true);
		rcurr=new JRadioButton("Current Account");
		rfix=new JRadioButton("Fixed Deposit Account");
		rrda=new JRadioButton("Recurring Deposit Account");
		bg.add(rsav);
		bg.add(rcurr);
		bg.add(rfix);
		bg.add(rrda);
		rsav.setBounds(100,125,150,35);
		rfix.setBounds(280,125,180,35);
		rcurr.setBounds(100,170,150,35);
		rrda.setBounds(280,170,220,35);
		add(rsav);
		add(rfix);
		add(rcurr);
		add(rrda);
		rsav.setFont(f3);
		rfix.setFont(f3);
		rcurr.setFont(f3);
		rrda.setFont(f3);
		lcardno=new JLabel("CARD NUMBER :");
		lcardno.setBounds(100,245,150,35);
		add(lcardno);
		lcardno.setFont(f2);
		l1=new JLabel("(Your 16-digit Card Number)");
		l1.setBounds(95,283,270,15);
		add(l1);
		l1.setFont(f4);
		lxx=new JLabel("XXXX-XXXX-XXXX-XXXX");
		lxx.setBounds(330,245,480,35);
		add(lxx);
		lxx.setFont(f5);
		l2=new JLabel("It would appear on ATM CARD/Cheque Book and Statement");
		l2.setBounds(330,285,400,18);
		add(l2);
		l2.setFont(f4);
		lpin=new JLabel("PIN :");
		lpin.setBounds(100,350,80,35);
		add(lpin);
		lpin.setFont(f2);
		l2=new JLabel("(4-digit Password)");
		l2.setBounds(100,390,200,15);
		add(l2);
		l2.setFont(f4);
		lx=new JLabel("XXXX");
		lx.setBounds(330,350,150,35);
		add(lx);
		lx.setFont(f5);
		lservice=new JLabel("Services Required :");
		lservice.setBounds(850,78,180,38);
		lservice.setFont(f2);
		add(lservice);
		jatm=new JCheckBox("ATM CARD");
		jmobile=new JCheckBox("Mobile Banking");
		jcheque=new JCheckBox("Cheque Book");
		jnet=new JCheckBox("Internet Banking");
		jemail=new JCheckBox("EMAIL Alerts");
		jstatement=new JCheckBox("E-Statement");
		jatm.setBounds(850,140,150,35);
		jmobile.setBounds(850,200,150,35);
		jcheque.setBounds(850,270,150,35);
		jnet.setBounds(1100,140,150,35);
		jemail.setBounds(1100,200,150,35);
		jstatement.setBounds(1100,270,150,35);
		add(jatm);
		add(jcheque);
		add(jnet);
		add(jemail);
		add(jstatement);
		add(jmobile);
		jatm.setFont(f3);
		jcheque.setFont(f3);
		jnet.setFont(f3);
		jemail.setFont(f3);
		jstatement.setFont(f3);
		jmobile.setFont(f3);
		jdeclare=new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.");
		jdeclare.setBounds(420,480,600,35);
		add(jdeclare);
		jdeclare.setFont(f4);
		bsubmit=new JButton("SUBMIT");
		bsubmit.setBounds(510,560,100,40);
		add(bsubmit);
		bsubmit.setEnabled(false);
		bcancel=new JButton("CANCEL");
		bcancel.setBounds(680,560,100,40);
		add(bcancel);
		jdeclare.addActionListener(this);
		bsubmit.addActionListener(this);
		setSize(700,1200);
		setVisible(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new AccountDetails();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==bsubmit)
		{
			Connection con = null;
			
			String value_red=null;
			
			al3=new ArrayList<>();
			 al9=new ArrayList<>();
			try{
				d1=new DataUtil();
				 con=d1.getConnection();
				String str1="select * from personaldetail";
				PreparedStatement pst=con.prepareStatement(str1);
				System.out.println(pst.toString());
				ResultSet rst=pst.executeQuery();
				System.out.println(rst.toString());
				rst.last();
				al3.add(rst.getString(1));
				al3.add(rst.getString(2));
				al3.add(rst.getString(3));
				al3.add(rst.getString(4));
				al3.add(rst.getString(5));
				al3.add(rst.getString(6));
				al3.add(rst.getString(7));
				al3.add(rst.getString(8));
				al3.add(rst.getString(9));
				al3.add(rst.getString(10));
				al3.add(rst.getString(11));
				System.out.println(rst.toString());
				String str2="select * from tempaddtional";
				PreparedStatement pst1=con.prepareStatement(str2);
				System.out.println(pst1.toString());
				ResultSet rst1=pst1.executeQuery();
				rst1.last();
				//while(rst1.next()){
				al3.add(rst1.getString(2));
				al3.add(rst1.getString(3));
				al3.add(rst1.getString(4));
				al3.add(rst1.getString(5));
				al3.add(rst1.getString(6));
				al3.add(rst1.getString(7));
				al3.add(rst1.getString(8));
				al3.add(rst1.getString(9));
				al3.add(rst1.getString(10));
				if(rsav.isSelected())
				{
					al3.add("Saving".toString());
				}
				else if(rcurr.isSelected())
				{
					al3.add("Current".toString());
				}
				
				else if(rfix.isSelected())
				{
					al3.add("FixedDeposit".toString());
				}
				else if(rrda.isSelected())
				{
					al3.add("Recurring".toString());
				}
				if(jatm.isSelected())
				{
				satm="Y";
					
					
				}
				if(jmobile.isSelected())
				{
					 
					smobile="Y";
				}
				if(jcheque.isSelected())
				{
					scheque="Y";
				}
				 if(jnet.isSelected())
				{
					 snet="Y";
				}
				if(jemail.isSelected())
				{
					semail="Y";
					
				}
				if(jstatement.isSelected())
				{
					 sstatement="Y";
				}
				al3.add(satm);
				al3.add(smobile);
				al3.add(scheque);
				al3.add(snet);
				al3.add(semail);
				al3.add(sstatement);
				al2=increment();
				x1=Integer.parseInt((String) al2.get(0));
				System.out.println("zncjsnjsjdhsjhdjwdjjn");
				System.out.println(x1);
				x=x1+134;
				al3.add(String.valueOf(x));	
				xc=String.valueOf(al2.get(1));
				int nbv=2345;
				int fgh=Integer.parseInt(xc.substring(11,15));
				System.out.println(fgh);
				fgh=fgh+567;
				System.out.println(nbv);
				//al3.add("1234");
				//al3.add("9967343487981234");
				//al3.add("1234");
				al3.add("184524445323".concat(String.valueOf(fgh)));
				al3.add(DateFormat.getDateTimeInstance().format(new java.util.Date()));
				
				
			}

			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			System.out.println(al3);
			String msg=d1.insert(con, "cus_info", al3);
			JOptionPane.showMessageDialog(getContentPane(), msg.concat(".Form Submitted Sucessfully"));
			System.out.println("sdadsffffffffffffffffffffffffffffff");
			//System.out.println(em);
			try
			{
				d2=new DataUtil();
				 con=d2.getConnection();
				
				 String str6="select email,atmpin,cardno from cus_info";
					PreparedStatement pst6=con.prepareStatement(str6);
					System.out.println(pst6.toString());
					ResultSet rst6=pst6.executeQuery();
					System.out.println(rst6.toString());
					rst6.last();
					al9.add(rst6.getString(1));
					al9.add(rst6.getString(2));
					al9.add(rst6.getString(3));
					System.out.println("asdfgcjfjdsjfjsehfjsfjsfsf");
					System.out.println(al9);
					 
					
					
			}
			catch(Exception er)
			{
				System.out.println(er.toString());
			}
		
			try {
			
				ms2=emailsend(al9);
				JOptionPane.showMessageDialog(getContentPane(), ms2);
				
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
			
			this.dispose();
		}
		if(jdeclare.isSelected())
		{
			bsubmit.setEnabled(true);
		}
		
		else
		{
			bsubmit.setEnabled(false);
		}
		
		
	}
	public String emailsend(ArrayList al8) throws UnsupportedEncodingException 
	{
		String str3=null;
		try{
			  String SMTP_HOST = "smtp.gmail.com";
			  String FROM_ADDRESS = "arpitagarwal916.aa@gmail.com";
			  String PASSWORD = "arpit4u1@";
			  String FROM_NAME = "ICICI BANK";
			  String TO_NAME = "Arpithjhjhj";
			  String TO_ADDRESS =String.valueOf(al8.get(0));
			  Properties props = new Properties();  
			  props.put("mail.smtp.host", SMTP_HOST);
	          props.put("mail.smtp.auth", "true");
	          props.put("mail.debug", "false");
	          props.put("mail.smtp.ssl.enable", "true");
	          Session session = Session.getInstance(props, new SocialAuth());
	          Message msg = new MimeMessage(session);

	          InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
	          msg.setFrom(from);

	          InternetAddress to = new InternetAddress(TO_ADDRESS, TO_NAME);
	          msg.setRecipient(Message.RecipientType.TO,to);

	          msg.setSubject("Your Bank Details..");
	          msg.setContent("Your CARDNO IS...... "+String.valueOf(al8.get(2))+ "     ATM  PIN IS........" +String.valueOf(al8.get(1)),"text/plain");
	          
		      
		         // Send message
		         Transport.send(msg);
		         System.out.println("Sent message successfully....");
		       
		      }
				catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		  return str3="Collect your CARDNO and ATMPIN from your registered email..";
			}
	class SocialAuth extends Authenticator {

	    @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication("arpitagarwal916.aa@gmail.com","arpit4u1@");
	    }
	}
	public ArrayList increment()
	{
		Connection con1;
		 ArrayList al5=new ArrayList<>();
		
		try
		{
			d2=new DataUtil();
			 con1=d2.getConnection();
			
			
			 String str7="select atmpin,cardno from cus_info";
				PreparedStatement pst7=con1.prepareStatement(str7);
				System.out.println(pst7.toString());
				ResultSet rst7=pst7.executeQuery();
				System.out.println(rst7.toString());
				rst7.last();
				al5.add(rst7.getString(1));
				al5.add(rst7.getString(2));
				System.out.println("ssssssssssssssss");
				 System.out.println(y1);
				System.out.println(y2);
				
				
				
		}
		catch(Exception er)
		{
			System.out.println(er.toString());
		}
		return al5;
	
	}
}
	
		

	


