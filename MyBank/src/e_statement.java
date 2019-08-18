import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;
import com.util.DataUtil;


public class e_statement extends JFrame {
	JTable jt;
	String row[][];
	String col[];
	ArrayList al;
	DataUtil d1;
	int l=0,i,j,rcount;
	JScrollPane jp;
	Statement st1;
	public e_statement() {
		super("STATEMENT");
		setLayout(null);
		table();
		
		setSize(700,1200);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	public void table()
	{
		getcol();
		getrowcount();
		retrvData();
		DefaultTableModel f=new DefaultTableModel();
		jt=new JTable(row,col);
		jp=new JScrollPane(jt);
		add(jp);
		jp.setBounds(250, 400, 1000, 250);
		
	}
	public static void main(String[] args) {
		new e_statement();
		
		

	}
	public void getcol()
	{
		Connection con;
		try{
			con=d1.getConnection();
			String str="SELECT name,credit,debit,balance from cusfinal_statement";
			st1=(Statement) con.createStatement();
			ResultSet rs=st1.executeQuery(str);
			ResultSetMetaData rd=rs.getMetaData();
			 l=rd.getColumnCount();
			col=new String[l];
			for(int i=1;i<=l;i++)
			{
				col[i-1]=rd.getColumnName(i);
			}
			System.out.println(col);
		}catch(Exception e){System.out.println(e.toString());}
	}
	public void getrowcount()
	{
		Connection con1;
		try{
			con1=d1.getConnection();
			String str="Select count(*) FROM cusfinal_statement";
			st1=(Statement) con1.createStatement();
			ResultSet rs=st1.executeQuery(str);
			rs.first();
			rcount=rs.getInt(1);
		}catch(Exception e){System.out.println(e.toString());}
}
	public void retrvData()
	{
		Connection con2;
		try{
			con2=d1.getConnection();
			String str1="SELECT name,credit,debit,balance from cusfinal_statement ";
			st1=(Statement) con2.createStatement();
			ResultSet rs=st1.executeQuery(str1);
			row=new String[rcount][l];
			for(i=0;i<rcount;i++)
				{rs.next();
				
					for(j=0;j<l;j++)
					{
						row[i][j]=rs.getString(j+1);			
					}
				}
		}
		catch(Exception e){System.out.println(e.toString());}
}
	

	

}
