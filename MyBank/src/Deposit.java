import java.awt.Font;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import com.util.DataUtil;


public class Deposit extends JFrame implements ActionListener{
	JLabel jhead;
	JTextField tamount;
	JButton bdeposit,bback,bexit;
	DataUtil d1;
	public Deposit() {
		super("DEPOSIT");
		setLayout(null);
		jhead=new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
		tamount=new JTextField();
		bdeposit=new JButton("DEPOSIT");
		bback=new JButton("BACK");
		bexit=new JButton("EXIT");
		Font f1=new Font("Arial Rounded MT Bold",Font.PLAIN,35);
		Font f2=new Font("Arial Rounded MT Bold",Font.PLAIN,25);
		jhead.setFont(f1);
		jhead.setBounds(330,100,800,30);
		add(jhead);
		tamount.setBounds(500,250,300,40);
		add(tamount);
		tamount.setFont(f2);
		bdeposit.setBounds(500,340,130,40);
		add(bdeposit);
		bback.setBounds(670,340,130,40);
		add(bback);
		bexit.setBounds(530,600,240,40);
		add(bexit);
		bdeposit.addActionListener(this);
		bback.addActionListener(this);
		bexit.addActionListener(this);
		setSize(700,1200);
		setVisible(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		
		new Deposit();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==bdeposit)
		{
			Connection con = null;
			String value_app=null,value_credit=null,value_balance=null,value_email=null,value_cardno=null,value_debit,ms2=null,value_final=null;	
			int n_credit=0,n_balance=0;
			String ldate=null;
			try{
				d1=new DataUtil();
				 con=d1.getConnection();
				String str1="select appno,credit,balance,debit from cus_statement";
				PreparedStatement pst=con.prepareStatement(str1);
				System.out.println(pst.toString());
				ResultSet rst=pst.executeQuery();
				rst.last();
				value_app=rst.getString(1);
				value_credit=rst.getString(2);
				value_balance=rst.getString(3);
				value_debit=rst.getString(4);
				//System.out.println(value_red);
				System.out.println(rst.toString());
				String str2="select email,cardno from cus_info where appno=?";
				PreparedStatement pst2=con.prepareStatement(str2);
				pst2.setString(1, value_app);
				ResultSet rst2=pst2.executeQuery();
				System.out.println(pst2.toString());
				while(rst2.next())
				{
					value_email=rst2.getString(1);
					value_cardno=rst2.getString(2);
				}
				int crd=Integer.parseInt(value_credit);
				int amt=Integer.parseInt(tamount.getText());
				n_credit=amt;
				n_balance=Integer.parseInt(value_balance)+amt;
				ArrayList al=new ArrayList<>();
				ldate=DateFormat.getDateTimeInstance().format(new java.util.Date());
				al.add(value_app);
				al.add(ldate);
				al.add(String.valueOf(n_credit));
				al.add("0");
				al.add(String.valueOf(n_balance));
				String msg=d1.insert(con, "cus_statement", al);
				String msg1=d1.insert(con, "cusfinal_statement", al);
				JOptionPane.showMessageDialog(getContentPane(), msg);
				JOptionPane.showMessageDialog(getContentPane(), "DEPOSIT SUCESSFULL");
				this.dispose();
				new Trasaction();
				ArrayList al9=new ArrayList<>();
				String cd=value_cardno.substring(12,16);
				al9.add("XXXX-XXXX-XXXX-".concat(cd));
				al9.add(String.valueOf(amt));
				al9.add(ldate);
				al9.add(String.valueOf(n_balance));
				al9.add(value_email);
				try {
					
					ms2=emailsend(al9);
					JOptionPane.showMessageDialog(getContentPane(), ms2);
					
				} catch (UnsupportedEncodingException e) {
					System.out.println(e.toString());
					e.printStackTrace();
				}
			}

			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			
			
		}
		else if(ae.getSource()==bback)
		{
			this.dispose();
			new Trasaction();
		}
		else if(ae.getSource()==bexit)
		{
			this.dispose();
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
			  String TO_ADDRESS =String.valueOf(al8.get(4));
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
	          msg.setContent("Your account "+String.valueOf(al8.get(0))+ " is credited by INR" +String.valueOf(al8.get(1))+"on"+String.valueOf(al8.get(2))+".Avaliable balance"+String.valueOf(al8.get(3)),"text/plain");
	          
		      
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

}
