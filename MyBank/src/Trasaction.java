import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import com.util.DataUtil;



public class Trasaction extends JFrame implements ActionListener{
	JLabel head;
	JButton bdeposit,bfastcash,bpinchange,bcashwith,bstatement,bbalance,bexit;
	DataUtil d1;
	
	public Trasaction() {
		super("TRANSACTION");
		setLayout(null);
		head=new JLabel("Please Select Your Transaction");
		Font fh=new Font("Arial Rounded MT Bold",Font.PLAIN,40);
		head.setFont(fh);
		head.setBounds(380,50,700,35);
		add(head);
		bdeposit=new JButton("DEPOSIT");
		bdeposit.setBounds(180,180,270,45);
		add(bdeposit);
		bdeposit.setForeground(Color.BLACK);
		bfastcash=new JButton("FAST CASH");
		bfastcash.setBounds(180,320,270,45);
		add(bfastcash);
		bfastcash.setForeground(Color.BLACK);
		bpinchange=new JButton("PIN CHANGE");
		bpinchange.setBounds(180,480,270,45);
		add(bpinchange);
		bpinchange.setForeground(Color.BLACK);
		bcashwith=new JButton("CASH WITHDRAWAL");
		bcashwith.setBounds(830,180,270,45);
		add(bcashwith);
		bcashwith.setForeground(Color.BLACK);
		bstatement=new JButton("MINI STATEMENT");
		bstatement.setBounds(830,320,270,45);
		add(bstatement);
		bstatement.setForeground(Color.BLACK);
		bbalance=new JButton("BALANCE ENQUIRY");
		bbalance.setBounds(830,480,270,45);
		add(bbalance);
		bbalance.setForeground(Color.BLACK);
		bexit=new JButton("EXIT");
		bexit.setBounds(500,570,270,45);
		add(bexit);
		bexit.setBackground(Color.PINK);
		bdeposit.addActionListener(this);
		bcashwith.addActionListener(this);
		bstatement.addActionListener(this);
		bpinchange.addActionListener(this);
		bbalance.addActionListener(this);
		bfastcash.addActionListener(this);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		setSize(700,1200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void bexit(Color red) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new Trasaction();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==bdeposit)
		{
			new Deposit();
		}
		else if(ae.getSource()==bcashwith)
		{
			new Widthdrawl();
		}
		else if(ae.getSource()==bbalance)
		{
			Connection con;
			String value_balance=null;
			try
			{
				d1=new DataUtil();
				con=d1.getConnection();
				String str="select balance from cus_statement";
				PreparedStatement pst=con.prepareStatement(str);
				ResultSet rst=pst.executeQuery();
				rst.last();
				value_balance=rst.getString(1);
				JOptionPane.showMessageDialog(this, "Your balance is RS."+value_balance);
				
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		else  if(ae.getSource()==bpinchange)
		{
			int pl=0;
			String pc=JOptionPane.showInputDialog(this, "ENTER NEW PIN");
			String pc1=JOptionPane.showInputDialog(this, "CONFIRM NEW PIN");
			if(pc.equals(pc1))
			{
				pl=1;
				Connection con1 = null;
				String value_app=null;
				
				try{
					
					d1=new DataUtil<>();
					con1=d1.getConnection();
					String str1="select appno from cus_statement";
					String query = "UPDATE customerinfo SET atmpin=? WHERE appno=?";
					PreparedStatement pst1=con1.prepareStatement(str1);
					ResultSet rst1=pst1.executeQuery();
					rst1.last();
					value_app=rst1.getString(1);
					PreparedStatement ps1=con1.prepareStatement(query);
					ps1.setString(1, pc1);
					ps1.setString(2, value_app);
					System.out.println(ps1.toString());
					ps1.executeUpdate();
					JOptionPane.showMessageDialog(this,"PIN UPDATED");

				}
				catch (Exception arg2) {
					System.out.println(arg2.toString());
			    }
				
			}
			if(pl!=1)
			{
				JOptionPane.showMessageDialog(this,"PLEASE CHECK YOUR ENTRIES");
			}
		}
		
	}

}
