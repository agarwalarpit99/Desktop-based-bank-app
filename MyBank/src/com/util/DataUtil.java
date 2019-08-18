package com.util;
import java.io.FileReader;


import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;
//import com.mysql.jdbc.ResultSetMetaData;
//import com.mysql.jdbc.Statement;
public class DataUtil<rs> {
	Connection con;
	java.sql.Statement st;
	
	ResultSet rs;
	PreparedStatement pst;
	String retst1,retst;
	String q=null;
	FileReader fr;
	Properties pr;
	/*This function is uesd to get connection and load Driver
*/	
	public Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/automatedteller","root","root");
		}
		catch (Exception e)
		{
			e.toString();
		}
		return con;
	}
	public String insert(Connection c, String ta, ArrayList al){
			try{
			int b=al.size();
			b=b-1;
			String s="?";
			for(int i=0; i<b; i++){
				s=s.concat(",?");
			}
			System.out.println(c.toString());
			q="INSERT INTO "+ta+" VALUES("+s+")";
			System.out.println(q);
			PreparedStatement ps=c.prepareStatement(q);
			int m=0;
			for(int i=0;i<al.size();i++){
				m=m+1;
				System.err.println(al.get(i));
				ps.setString(m, (String)al.get(i));
			}
			System.out.println(ps.toString());
			int f=ps.executeUpdate();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			String msg="Saved";
			return msg;

}
	public String AllId(String st1)
	
	{
		String retst=null;
		try{
			
			
				int x=Integer.parseInt(st1);
				x=x+1;
				for(int i=1;i<100;i++){
				if(x<10)
				{
					 retst=String.valueOf(x)+i;
					 break;
				}
				else if(x>=10 && x<100)
				{
					 retst=String.valueOf(x)+"00";
				}
				else if(x>=100 && x<1000)
				{
					 retst=String.valueOf(x)+"0";
				}
				}
			}
			
		
	
		catch(Exception ee)
		{
			System.out.println(ee.toString());
		}
		return retst=retst+1;	
	}
}