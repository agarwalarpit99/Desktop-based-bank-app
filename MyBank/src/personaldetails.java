import java.awt.Color;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;

import com.util.*;

public class personaldetails extends JFrame implements ActionListener,FocusListener{
	JLabel head1,head2,lname,lfather,ldob,lgen,lemail,lmartial,ladd,lcity,lpin,lstate,vemail,vpin,lformno;
	JTextField tname,tfather,tdob,tgen,temail,tmartial,tadd,tcity,tpin,tstate;
	JButton bnext;
	JComboBox jdate,jmonth,jyear,jcity,jstate;
	ButtonGroup bg,bg2;
	JRadioButton rm,rf,rmarried,runmarried,rother;
	int status1=0,status2=0;
	 int formno=2000;
	DataUtil d1,d2;
	String value_red=null;
	String refer=null;
	int x=0,y=0;
	
	public personaldetails() {
		super("NEW ACCOUNT APPLICATION FORM");
		setLayout(null);
		Font f1=new Font("Arial Rounded MT Bold",Font.PLAIN,35);
		Font f2=new Font("Arial Rounded MT Bold",Font.PLAIN,22);
		Font f3=new Font("Arial Rounded MT Bold",Font.PLAIN,15);
		head1=new JLabel("APPICATION FORM NO.");
		head2=new JLabel("Page 1:Personal Details");
		refer=increment();
		x=Integer.parseInt(refer);
		y=x+1;
		lformno=new JLabel(String.valueOf(y));
		lformno.setBounds(850,10,250,40);
		add(lformno);

		lformno.setFont(f1);
		head1.setFont(f1);
		head2.setFont(f2);
		lname=new JLabel("Name :");
		lfather=new JLabel("Father's Name :");
		ldob=new JLabel("Date of Birth :");
		lgen=new JLabel("Gender :");
		lemail=new JLabel("Email Address :");
		lmartial=new JLabel("Martial Status :");
		ladd=new JLabel("Address :");
		lcity=new JLabel("City :");
		lpin=new JLabel("Pin :");
		lstate=new JLabel("State :");
		tname=new JTextField();
		tfather=new JTextField();
		temail=new JTextField();
		tadd=new JTextField();
		tpin=new JTextField();
		bnext=new JButton("NEXT");
		jdate=new JComboBox();
		for(int i=1;i<32;i++)
		{
			jdate.addItem(i);
		}
		jmonth=new JComboBox();
		jmonth.addItem("JANUARY");
		jmonth.addItem("FEBUARY");
		jmonth.addItem("MAR");
		jmonth.addItem("APRIL");
		jmonth.addItem("MAY");
		jmonth.addItem("JUNE");
		jmonth.addItem("JULY");
		jmonth.addItem("AUGUST");
		jmonth.addItem("SEPTEMBER");
		jmonth.addItem("OCTOBER");
		jmonth.addItem("NOVEMBER");
		jmonth.addItem("DECEMBER");
		jyear=new JComboBox();
		for(int j=1930;j<2001;j++)
		{
			jyear.addItem(j);
		}
		jcity=new JComboBox();
		jcity.addItem("AGRA");
		jcity.addItem("DELHI");
		jcity.addItem("PUNE");
		jcity.addItem("MUMBAI");
		jcity.addItem("CHENNAI");
		jstate=new JComboBox();
		jstate.addItem("U.P");
		jstate.addItem("DELHI");
		jstate.addItem("MAHARASTRA");
		jstate.addItem("TAMIL NADU");
		jstate.addItem("RAJASTHAN");
		bg2=new ButtonGroup();
		rmarried=new JRadioButton("Married");
		runmarried=new JRadioButton("Unmarried",true);
		rother=new JRadioButton("other");
		bg2.add(rmarried);
		bg2.add(runmarried);
		bg2.add(rother);
		bg=new ButtonGroup();
		rm=new JRadioButton("Male",true);
		rf=new JRadioButton("Female");
		bg.add(rm);
		bg.add(rf);
		head1.setBounds(420,10,450,40);
		
		head2.setBounds(510,60,300,30);
		lname.setBounds(60,110,80,30);
		lname.setFont(f3);
		lfather.setFont(f3);
		tname.setBounds(220,110,200,30);
		lfather.setBounds(60,170,130,30);
		tfather.setBounds(220,170,200,30);
		ldob.setBounds(60,225,130,30);
		ldob.setFont(f3);
		jdate.setBounds(220,225,40,30);
		jmonth.setBounds(270,225,80,30);
		jyear.setBounds(360,225,60,30);
		lgen.setBounds(60,280,130,30);
		rm.setBounds(220,280,100,35);
		rf.setBounds(370,280,100,35);
		lgen.setFont(f3);
		lemail.setBounds(60,350,130,30);
		temail.setBounds(220,350,200,30);
		lemail.setFont(f3);
		lmartial.setBounds(60,420,130,30);
		rmarried.setBounds(220,420,100,35);
		runmarried.setBounds(350,420,100,35);
		rother.setBounds(480,420,100,35);
		lmartial.setFont(f3);
		ladd.setBounds(60,510,130,30);
		tadd.setBounds(220,510,200,30);
		lcity.setBounds(60,580,130,30);
		jcity.setBounds(220,580,130,30);
		ladd.setFont(f3);
		lmartial.setFont(f3);
		lcity.setFont(f3);
		lpin.setBounds(700,110,130,30);
		tpin.setBounds(860,110,200,30);
		lpin.setFont(f3);
		lstate.setFont(f3);
		lstate.setBounds(700,190,130,30);
		jstate.setBounds(860,190,100,30);
		bnext.setBounds(1000,600,120,30);
		vemail=new JLabel("");
		vemail.setBounds(450,350,200,32);
		add(vemail);
		vemail.setForeground(Color.RED);
		vpin=new JLabel("");
		vpin.setBounds(1090,110,120,32);
		add(vpin);
		vpin.setForeground(Color.RED);
		temail.addFocusListener(this);
		tpin.addFocusListener(this);
		add(bnext);
		add(head1);
		
		add(head2);
		add(lname);
		add(tname);
		add(lfather);
		add(tfather);
		add(ldob);
		add(jdate);
		add(jmonth);
		add(jyear);
		add(lgen);
		add(rm);
		add(rf);
		add(lemail);
		add(temail);
		add(lmartial);
		add(rmarried);
		add(runmarried);
		add(rother);
		add(ladd);
		add(tadd);
		add(lcity);
		add(jcity);
		add(lpin);
		add(tpin);
		add(lstate);
		add(jstate);
		//co=d1.increment(formno);
				bnext.addActionListener(this);
		setSize(700,1200);
		setVisible(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new personaldetails();
	
		

	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String id;
		Connection con;
		
		if(tname.getText().equals("") || tfather.getText().equals("") || temail.getText().equals("") || tpin.getText().equals("") || tadd.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this, "Please Fill");
		}
		else if(status1==1 && status2==1 ){
			d1=new DataUtil();
			Connection c=d1.getConnection();
			
			ArrayList<String> data=new ArrayList<String>();
			
			data.add(String.valueOf(y).toString());
			data.add(tname.getText().toString());
			data.add(tfather.getText().toString());
			String d=jdate.getSelectedItem().toString();
			String m=jmonth.getSelectedItem().toString();
			String y=jyear.getSelectedItem().toString();
			data.add(d.concat(m).concat(y));
			if(rm.isSelected())
			{
				data.add("M".toString());
			}
			else if(rf.isSelected())
			{
				data.add("F".toString());
			}
			data.add(temail.getText().toString());
		if(rmarried.isSelected())
			{
				data.add("Married".toString());
			}
			else if(runmarried.isSelected())
			{
				data.add("Unmarried".toString());
			}
			else if(rother.isSelected())
			{
				data.add("other".toString());
			}
			data.add(tadd.getText().toString());
			data.add(jcity.getSelectedItem().toString());
			data.add(tpin.getText().toString());
			data.add(jstate.getSelectedItem().toString());
			
			System.out.println(data);
			String msg=d1.insert(c, "personaldetail", data);
			JOptionPane.showMessageDialog(getContentPane(), msg);
			
			
			this.dispose();
			new AdditionalDetails();
			
		}
			
		else if(status1==2 || status2==2)
		{
			JOptionPane.showMessageDialog(this, "Please Complete Validation");
		}
		
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent ae) {
		CheckEntries ce=new CheckEntries();
		if(ae.getSource()==temail){
			String g=temail.getText();
			boolean bsave=ce.CheckEmail(g);
			try{
				if(bsave==true){
					vemail.setText(" ");
					status1=1;
				}
				else if(bsave==false){
					
					vemail.setText("Invalid E-MAIL");
					status1=2;
				}
			}
			catch(StringIndexOutOfBoundsException e){
			
				vemail.setText("Invalid E-MAIL");
			}
		}
		else if(ae.getSource()==tpin){
			String g=tpin.getText();
			boolean bsave=ce.CheckHomePin(g);
			try{
				if(bsave==true){
					vpin.setText(" ");
					status2=1;
				}
				else if(bsave==false){
					
					vpin.setText("Invalid Pin");
					status2=2;
				}
			}
			catch(StringIndexOutOfBoundsException e){
			
				vpin.setText("Invalid Pin");
			}
		}
		
		
	}
	public String increment()
	{
		Connection con1;
		try{
			d1=new DataUtil();
			 con1=d1.getConnection();
			String str2="select appno from personaldetail";
			PreparedStatement pst2=con1.prepareStatement(str2);
			System.out.println(pst2.toString());
			ResultSet rst2=pst2.executeQuery();
			rst2.last();
			//while(rst.next()){
			value_red=rst2.getString(1);
			//}
			System.out.println(value_red);
			
			System.out.println(rst2.toString());
			
		}

		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return value_red;
		
	}

}
