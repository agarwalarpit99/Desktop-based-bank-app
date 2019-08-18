import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.nio.Buffer;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.omg.CORBA.Current;

import sun.awt.WindowClosingListener;




public class NewCompo extends JFrame implements ActionListener{
	private static final Object BorderLayout_North = null;
	JPopupMenu pm;
	JToolBar jt, jt1;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
	JMenuBar mb;
	JMenu jm1,jm2,jm3;
	JMenuItem m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13;
	JTextArea ta;
	JFileChooser jf,jf1;
	String st,st1;
	Font f,f1,fo;
	JColorChooser cc,cc1;
	JComboBox cb,cb1;
	int i,n=0,g=20,u=20, v=0;
	String s[],r,r1;
	GraphicsEnvironment ge;
	Highlighter h;
	JScrollPane sh, sv;
	JSlider jsd;
	JLabel ls1,ls2,ls3;
	public NewCompo() {
		super("NOTEPAD v2.0");
		pm=new JPopupMenu("edit");
		jf=new JFileChooser();
		jf1=new JFileChooser();
		jt=new JToolBar();
		jt.setRollover(true);
		jt1=new JToolBar();
		jt1.setRollover(true);
		cb=new JComboBox();
		jsd=new JSlider();
		jsd.setValue(8);
		jsd.setMaximum(100);
		jsd.setMinimum(0);
		ls1=new JLabel("Zoom Out");
		ls2=new JLabel("Zoom In ");
		v=jsd.getValue();
		ls3=new JLabel(v+"%");
		jt1.add(ls1);
		jt1.add(jsd);
		jt1.add(ls2);
		jsd.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				v=jsd.getValue();
				ls3.setText(String.valueOf(v));
				String n=ta.getFont().getName();
				int sa=ta.getFont().getStyle();
				int c=jsd.getValue();
				ta.setFont(new Font(n,sa,c));
			}
		});
		jt1.add(ls3);
		for(i=8;i<72;i=i+2)
		{
			cb.addItem(i);
		}
		
		cb1=new JComboBox();
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String sp[] = ge.getAvailableFontFamilyNames();
        for(int i=0;i<sp.length;i++)
        {
        cb1.addItem(sp[i]);
        }
		
		cc=new JColorChooser();
		cc1=new JColorChooser();
		b1=new JButton("N");
		b1.setToolTipText("New");
		b1.setMnemonic(KeyEvent.VK_N);
		b2=new JButton("O");
		b2.setToolTipText("Open");
		b2.setMnemonic(KeyEvent.VK_O);
		b3=new JButton("B");
		b2.setToolTipText("Bold");
		b4=new JButton("I");
		b4.setToolTipText("Italic");
		b5=new JButton("U");
		b5.setToolTipText("Underline");
		b6=new JButton("Find");
		b6.setToolTipText("Find Word");
		b7=new JButton("Replace");
		b7.setToolTipText("Replace Word");
		b8=new JButton("+");
		b8.setToolTipText("Zoom In");
		b9=new JButton("-");
		b9.setToolTipText("Zoom Out");
		b10=new JButton("UC");
		b10.setToolTipText("Upper Case");
		b11=new JButton("LC");
		b11.setToolTipText("Lower Case");
		mb=new JMenuBar();
		jm1=new JMenu("File");
		jm1.setMnemonic(KeyEvent.VK_F);
		jm2=new JMenu("Edit");
		jm2.setMnemonic(KeyEvent.VK_E);	
		jm3=new JMenu("Format");//Shortcut keys
		m1=new JMenuItem("New ALT+N");
		m1.setMnemonic(KeyEvent.VK_N);//we add shortcut key in JMenuItem directly
		m2=new JMenuItem("Open ALT+O",KeyEvent.VK_O);
		m2.setMnemonic(KeyEvent.VK_O);
		m3=new JMenuItem("Save ALT+S",KeyEvent.VK_S);
		m3.setMnemonic(KeyEvent.VK_S);
		m4=new JMenuItem("Exit ALT+T",KeyEvent.VK_T);
		m4.setMnemonic(KeyEvent.VK_T);
		m5=new JMenuItem("Cut ALT+X",KeyEvent.VK_X);
		m5.setMnemonic(KeyEvent.VK_X);
		m6=new JMenuItem("Copy ALT+C",KeyEvent.VK_C);
		m6.setMnemonic(KeyEvent.VK_C);
		m7=new JMenuItem("Paste ALT+V",KeyEvent.VK_V);
		m7.setMnemonic(KeyEvent.VK_V);
		m8=new JMenuItem("SelectALL ALT+A",KeyEvent.VK_A);
		m8.setMnemonic(KeyEvent.VK_A);
		m9=new JMenuItem("Delete");
		m10=new JMenuItem("BackGround Color ALT+L");
		m10.setMnemonic(KeyEvent.VK_L);
		m11=new JMenuItem("ForeGround Color ALT+U");
		m11.setMnemonic(KeyEvent.VK_U);
		m12=new JMenuItem("Print ALT+P");
		m12.setMnemonic(KeyEvent.VK_P);
		m13=new JMenuItem("Delete ALT+DEL");
		m13.setMnemonic(KeyEvent.VK_DELETE);
		ta=new JTextArea();
		sh=new JScrollPane(ta);
		sv=new JScrollPane(ta);
		sv.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sh.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//cb.setLayout(null);
		add(sh);
		add(sv);
		cb.setBounds(200,100,30,30);
		jm1.add(m1);
		jm1.add(m2);
		jm1.add(m3);
		jm1.add(m12);
		jm1.add(m4);
		jm2.add(m5);
		jm2.add(m6);
		jm2.add(m7);
		jm2.add(m8);
		jm2.add(m13);
		jm3.add(m9);
		jm3.add(m10);
		jm3.add(m11);
		mb.add(jm1);
		mb.add(jm2);
		mb.add(jm3);
		setJMenuBar(mb);
		jt.add(b1);
		jt.add(b2);
		jt.add(b3);
		jt.add(b4);
		//jt.add(b5);
		jt.add(b6);
		jt.add(b7);
		jt.add(b8);
		jt.add(b9);
		jt.add(b10);
		jt.add(b11);
		pm.add(m13);
		add(jt,BorderLayout.NORTH);
		add(jt1,BorderLayout.SOUTH);
		ta.setBounds(0,50,600,600);
		jt.add(cb);
		jt.add(cb1);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		m1.addActionListener(this);
		m2.addActionListener(this);
		m3.addActionListener(this);
		m4.addActionListener(this);
		m5.addActionListener(this);
		m6.addActionListener(this);
		m7.addActionListener(this);
		m8.addActionListener(this);
		m9.addActionListener(this);
		m10.addActionListener(this);
		m11.addActionListener(this);
		m12.addActionListener(this);
		cb.addActionListener(this);
		cb1.addActionListener(this);
		ta.setInheritsPopupMenu(true);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent ae) {
				 ta.setInheritsPopupMenu(true);
				 //ta.getComponentPopupMenu();
				pm.show(getContentPane(),ae.getX(),ae.getY());
				
				
			}
		});
		
		addWindowListener(new WindowListener() {
			
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
			
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				int en=JOptionPane.showConfirmDialog(getContentPane(), "Are you sure..??");
				if(en==JOptionPane.YES_OPTION)
				{
					System.exit(1);
				}
				else if(en==JOptionPane.CANCEL_OPTION) 
				{
					//String yn=ta.getText();
					ta.setText(String.valueOf(ta.getText()));
				}
				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
			

		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setVisible(true);
		
	
		
	}	
	public static void main(String[] args) {
		NewCompo obj=new NewCompo();

	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==m1 || ae.getSource()==b1)
		{
			int x=JOptionPane.showConfirmDialog(getContentPane(), "Do you want to save the existing file ??");
			if(x==JOptionPane.YES_OPTION)
			{

				int y=jf1.showSaveDialog(getContentPane());
				if(y==JFileChooser.APPROVE_OPTION)
				{
					try
					{
						File f1=jf1.getSelectedFile();
						String b=f1.getAbsolutePath();
					FileOutputStream fout=new FileOutputStream(f1);
					fout.write(ta.getText().getBytes());
					
					}	
					
					catch(Exception e){}
				}
			}
				else if(x==JOptionPane.NO_OPTION)
				{
					ta.setText(" ");
				}
			}
		
			
		
		else if(ae.getSource()==m2 || ae.getSource()==b2)
		{
			File f;
			int x=jf.showOpenDialog(this);
			if(x==JFileChooser.APPROVE_OPTION)
			{
				f=jf.getSelectedFile();
				String p=f.getPath();
				try
				{
					BufferedReader br=new BufferedReader(new FileReader(p));
					String s1=null;
					String s2=null;
					while((s1=br.readLine())!=null)
					{
						s2=s2+s1+"\n";
					}
					ta.setText(s2);
					br.close();
				}
				catch(Exception e){}
			}
		}
			else if(ae.getSource()==m3)
			{
				int y=jf1.showSaveDialog(getContentPane());
				if(y==JFileChooser.APPROVE_OPTION)
				{
					try
					{
						File f1=jf1.getSelectedFile();
						String b=f1.getAbsolutePath();
					FileOutputStream fout=new FileOutputStream(f1);
					fout.write(ta.getText().getBytes());
					
					}	
					catch(Exception e){}
				}
	
			
		}
		
			else if(ae.getSource()==m4)
			{
				int en=JOptionPane.showConfirmDialog(getContentPane(), "Are you sure..??");
				if(en==JOptionPane.YES_OPTION)
				{
					System.exit(1);
				}
				else if(en==JOptionPane.CANCEL_OPTION) 
				{
					//String yn=ta.getText();
					ta.setText(String.valueOf(ta.getText()));
				}
			}
			else if(ae.getSource()==m5)
			{
			
				ta.cut();
			}
			else if(ae.getSource()==m6)
			{
				ta.copy();
				
				
			}
			else if(ae.getSource()==m7)
			{
				ta.paste();
			}
			else if(ae.getSource()==m8)
			{
				ta.selectAll();
				
			}
			else if(ae.getSource()==m9)
			{
				
				String de=ta.getSelectedText();
				if(de==null)
				{
					JOptionPane.showMessageDialog(getContentPane(), "Please select");
				}
				else{
				int dc=JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to delete..??");
				if(dc==JOptionPane.YES_OPTION)
				{
					ta.setText(ta.getText().replace(ta.getSelectedText(),""));
					
				}
				else
				{
					ta.setText(String.valueOf(ta.getText()));
				}
				}
				
				
			}
			else if(ae.getSource()==m10)
			{
				Color c=cc.showDialog(getContentPane(),"Change Color",Color.RED);
				ta.setBackground(c);
				
			}
			else if(ae.getSource()==m11)
			{
				Color c1=cc1.showDialog(getContentPane(),"Change Color",Color.RED);
				ta.setForeground(c1);
				
			}
			else if(ae.getSource()==b3)
			{
				int f=ta.getFont().getStyle();
				if(f==2){
					ta.setFont(ta.getFont().deriveFont(Font.BOLD | Font.ITALIC));
				}
				else{
					ta.setFont(ta.getFont().deriveFont(Font.BOLD));
				}
			}
			else if(ae.getSource()==b4)
			{
				int f=ta.getFont().getStyle();
				if(f==1){
					ta.setFont(ta.getFont().deriveFont(Font.BOLD | Font.ITALIC));
				}
				else{
					ta.setFont(ta.getFont().deriveFont(Font.ITALIC));
				}
			}
			else if(ae.getSource()==cb)
			{
				int jsd1=jsd.getValue();
				int g=(int)cb.getSelectedItem();
				g=g+jsd1;
				int f=ta.getFont().getStyle();
				if(f==3){
					ta.setFont(ta.getFont().deriveFont(Font.BOLD | Font.ITALIC,g));
				}
				else if(f==2){
					ta.setFont(ta.getFont().deriveFont(Font.ITALIC, g));
				}
				else if(f==1){
					ta.setFont(ta.getFont().deriveFont(Font.BOLD,g));
				}
				else if(f==0){
					ta.setFont(ta.getFont().deriveFont(Font.PLAIN,g));
				}
			}
			else if(ae.getSource()==cb1)
			{
				
				//int q=cb1.getSelectedIndex();
				int h=(int)cb.getSelectedItem();
				String u=(String) cb1.getSelectedItem();//we use tostring because for string represention of an object.
				int f=ta.getFont().getStyle();
				int h1=ta.getFont().getSize();
				ta.setFont(new Font(u,f,h1));
			}
			else if(ae.getSource()==b6)
			{
				
				String x=JOptionPane.showInputDialog("Enter word");
					String y=ta.getText();
					if(y.contains(x))
					{
					try {
				        Scanner scan = new Scanner(y);
				        while (scan.hasNext())
				        {
				            String str = scan.next();
				            if (str.equals(x))
				                n++;
				        }
					scan.close();}
					catch(Exception e){}
					}
					else
					{
						JOptionPane.showMessageDialog(getContentPane(), "Not Found");
					}
					if(n>0)
					{
					JOptionPane.showMessageDialog(getContentPane(), +n+"TIMES USED");
					}
		
	}
			else if(ae.getSource()==b7)
			{
				
				String x1=JOptionPane.showInputDialog("Enter new word");
				String x2=JOptionPane.showInputDialog("Enter word to replace with");
				String y1=ta.getText();
					if(y1.contains(x2))
					{
						x2=y1.replace(x2, x1);
						ta.setText(x2);
					}
					else
					{
						JOptionPane.showMessageDialog(getContentPane(), "Word not found");
					}
		
		
			}
			else if(ae.getSource()==b8)
			{
				
				fo=ta.getFont();
				u=u+10;
				f=new Font(String.valueOf(fo),Font.PLAIN,u);
				ta.setFont(f);
			}
			else if(ae.getSource()==b9)
			{
				fo=ta.getFont();
				u=u-10;
				f=new Font(String.valueOf(fo),Font.PLAIN,u);
				ta.setFont(f);
			}
			else if(ae.getSource()==b10)
			{
				String y1=ta.getText();
				r=y1.toUpperCase();
				ta.setText(r);
				
			}
			else if(ae.getSource()==b11)
			{
				String y2=ta.getText();
				r1=y2.toLowerCase();
				ta.setText(r1);
				
			}
			else if(ae.getSource()==m12)
			{
				PrinterJob pj=PrinterJob.getPrinterJob();
				if(pj.printDialog()){
					try{
						pj.print();
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			}
}

		
		
		

	



