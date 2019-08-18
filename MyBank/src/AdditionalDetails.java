import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.util.CheckEntries;
import com.util.DataUtil;


public class AdditionalDetails extends JFrame implements ActionListener,FocusListener {
	JLabel head,lreligion,lcategory,lincome,lqual,loccup,lpan,laadhaar,lcitizen,lexisacc,vpan,vaadhaar;
	JComboBox jreli,jcate,jincome,jqual,joccup;
	JTextField tpan,taadhaar;
	ButtonGroup bg1,bg2;
	JRadioButton r1yes,r1no,r2yes,r2no;
	JButton bnext;
	int status1=0,status2=0;
	DataUtil d1;
	public AdditionalDetails() {
		super("NEW ACCOUNT APPILICATION FORM-PAGE 2");
		setLayout(null);
		head=new JLabel("Page 2: Additional Details");
		Font f1=new Font("Arial Rounded MT Bold",Font.PLAIN,25);
		Font f2=new Font("Arial Rounded MT Bold",Font.PLAIN,15);
		head.setFont(f1);
		lreligion=new JLabel("Religion :");
		lcategory=new JLabel("Category :");
		lincome=new JLabel("Income :");
		lqual=new JLabel("Qualification :");
		loccup=new JLabel("Occupation :");
		lpan=new JLabel("PAN Number :");
		laadhaar=new JLabel("AADHAAR Number :");
		lcitizen=new JLabel("Senior Citizen :");
		lexisacc=new JLabel("Existing Account :");
		bnext=new JButton("NEXT");
		jreli=new JComboBox();
		jreli.addItem("Hindu");
		jreli.addItem("Muslim");
		jreli.addItem("Sikh");
		jreli.addItem("Cristhan");
		jreli.addItem("Other");
		jcate=new JComboBox();
		jcate.addItem("GEN");
		jcate.addItem("OBC");
		jcate.addItem("SC");
		jcate.addItem("ST");
		jcate.addItem("OTHER");
		jincome=new JComboBox();
		jincome.addItem("null");
		jincome.addItem("< 1,50,000");
		jincome.addItem("> 1,50,000");
		jincome.addItem("< 5,00,000");
		jincome.addItem("upto 10 lakhs");
		jincome.addItem("upto 50 lakhs");
		jincome.addItem("> 50 lakhs");
		jqual=new JComboBox();
		jqual.addItem("Graduate");
		jqual.addItem("Post Graduate");
		jqual.addItem("Non-Graduate");
		jqual.addItem("Doctrate");
		jqual.addItem("Other");
		joccup=new JComboBox();
		joccup.addItem("Salaried");
		joccup.addItem("Self-Employed");
		joccup.addItem("Business");
		joccup.addItem("Student");
		joccup.addItem("Retired");
		joccup.addItem("Other");
		tpan=new JTextField();
		taadhaar=new JTextField();
		bg1=new ButtonGroup();
		r1yes=new JRadioButton("Yes");
		r1no=new JRadioButton("No",true);
		bg1.add(r1yes);
		bg1.add(r1no);
		bg2=new ButtonGroup();
		r2yes=new JRadioButton("Yes");
		r2no=new JRadioButton("No",true);
		bg2.add(r2yes);
		bg2.add(r2no);
		head.setBounds(500,30,500,30);
		lreligion.setBounds(100,110,100,30);
		jreli.setBounds(250,110,200,30);
		lreligion.setFont(f2);
		add(lreligion);
		add(jreli);
		lcategory.setBounds(100,200,100,30);
		jcate.setBounds(250,200,200,30);
		lcategory.setFont(f2);
		add(lcategory);
		add(jcate);
		lincome.setBounds(100,280,100,30);
		jincome.setBounds(250,280,200,30);
		lincome.setFont(f2);
		add(lincome);
		add(jincome);
		lqual.setBounds(100,360,120,30);
		jqual.setBounds(250,360,200,30);
		lqual.setFont(f2);
		add(lqual);
		add(jqual);
		loccup.setBounds(100,440,100,30);
		joccup.setBounds(250,440,200,30);
		loccup.setFont(f2);
		add(loccup);
		add(joccup);
		lpan.setBounds(100,520,120,30);
		tpan.setBounds(250,520,200,30);
		lpan.setFont(f2);
		add(lpan);
		add(tpan);
		laadhaar.setBounds(700,110,150,30);
		taadhaar.setBounds(900,110,200,30);
		laadhaar.setFont(f2);
		add(taadhaar);
		add(laadhaar);
		lcitizen.setBounds(700,200,130,30);
		r1yes.setBounds(900,200,120,35);
		r1no.setBounds(1060,200,120,35);
		lcitizen.setFont(f2);
		add(lcitizen);
		add(r1yes);
		add(r1no);
		lexisacc.setBounds(700,290,150,30);
		r2yes.setBounds(900,290,120,35);
		r2no.setBounds(1060,290,120,35);
		lexisacc.setFont(f2);
		bnext.setBounds(1000,600,120,30);
		add(bnext);
		add(lexisacc);
		add(r2yes);
		add(r2no);
		add(head);
		vpan=new JLabel("");
		vpan.setBounds(500,520,150,38);
		add(vpan);
		vpan.setForeground(Color.RED);
		vaadhaar=new JLabel("");
		vaadhaar.setBounds(1150,110,150,38);
		add(vaadhaar);
		vaadhaar.setForeground(Color.RED);
		bnext.addActionListener(this);
		tpan.addFocusListener(this);
		taadhaar.addFocusListener(this);
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setSize(700,1200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	public static void main(String[] args) {
		new AdditionalDetails();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(status1==1 && status2==1)
		{Connection con = null;
			
			String value_red=null;
			
			try{
				d1=new DataUtil();
				 con=d1.getConnection();
				String str1="select appno from personaldetail";
				PreparedStatement pst=con.prepareStatement(str1);
				System.out.println(pst.toString());
				ResultSet rst=pst.executeQuery();
				rst.last();
				//while(rst.next()){
				value_red=rst.getString(1);
				//}
				System.out.println(value_red);
				
				System.out.println(rst.toString());
			}

			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			
			ArrayList<String> data=new ArrayList<String>();
			
			data.add(value_red.toString());
			data.add(jreli.getSelectedItem().toString());
			data.add(jcate.getSelectedItem().toString());
			data.add(jincome.getSelectedItem().toString());
			data.add(jqual.getSelectedItem().toString());
			data.add(joccup.getSelectedItem().toString());
			data.add(tpan.getText().toString());
			data.add(taadhaar.getText().toString());
			if(r1yes.isSelected())
			{
				data.add("Y".toString());
			}
			else if(r1no.isSelected())
			{
				data.add("N".toString());
			}
			
		if(r2yes.isSelected())
			{
				data.add("Y".toString());
			}
			else if(r2no.isSelected())
			{
				data.add("N".toString());
			}
			System.out.println(data);
			String msg=d1.insert(con, "tempaddtional", data);
			JOptionPane.showMessageDialog(getContentPane(), msg);
			this.dispose();
			
		new AccountDetails();
		
		
		}
		else if(status1==2 || status2==2)
		{
			JOptionPane.showMessageDialog(this, "Please Complete Validation");
		}
		else if(tpan.getText().equals("") || taadhaar.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this, "Please Fill");
		}
	}
	private Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent ae) {
		CheckEntries ce=new CheckEntries();
		if(ae.getSource()==tpan)
		{
			String g=tpan.getText();
			boolean b=ce.CheckPAN(g);
			try
			{
				if(b==true)
				{
					vpan.setText("");
					status1=1;
				}
				else if(b==false)
				{
					vpan.setText("Invalid PAN No.");
					status1=2;
				}
			}
			catch(StringIndexOutOfBoundsException e){
				
				vpan.setText("Invalid PAN Number");
			}
		}
		else if(ae.getSource()==taadhaar)
		{
			String g=taadhaar.getText();
			boolean b=ce.CheckAadhaar(g);
			try
			{
				if(b==true)
				{
					vaadhaar.setText("");
					status2=1;
				}
				else if(b==false)
				{
					vaadhaar.setText("Invalid Aadhaar No.");
					status2=2;
				}
			}
			catch(StringIndexOutOfBoundsException e){
				
				vaadhaar.setText("Invalid Aadhaar Number");
			}
		}
		
	}

}
