package client;
import java.io.*;// library
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.*;
public class Client extends JFrame implements ActionListener// class decalration
{
	private JTextField jtf = new JTextField();//object declaration
	private JTextField jtf1 = new JTextField();
	private JTextArea jta = new JTextArea(30,30);//object declaration
	private JButton b = new JButton("Show");
	private DataOutputStream toServer;//object declaration
	private DataInputStream fromServer;
	JLabel l1,l2,l3 ;
	public static void main(String args[]) throws NullPointerException// main class
	{
		new Client();
	}
	
	public Client()// constructor
	{
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
                                        
		p4.add(b);
				
		p2.add(jta);
		Font f = new Font("Areal", Font.BOLD, 12);//object declaration
	    l1 = new JLabel("\nArea received from the server is ");
		l1.setFont(f);
	        l2 = new JLabel("Enter Radius: ");
		l3 = new JLabel("Enter Shape: ");
		p1.setLayout(new GridLayout(3,2,5,5));
		p1.add(l2);
		p1.add(jtf);
		p1.add(l3);
		p1.add(jtf1);
		p3.setLayout(new GridLayout(3,1));
		p3.add(p1);
		p3.add(p2);
		p3.add(p4);
	add(p3);

		b.addActionListener(this);
		//	jtf1.addActionListener(new ButtonListener());
		setTitle("Client");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try// try catch block
		{
			Socket socket = new Socket("192.168.92.1", 60000);// socket object
			
			fromServer = new DataInputStream(socket.getInputStream());
			
			toServer = new DataOutputStream(socket.getOutputStream());
			
		}
		
		catch(IOException ex)
		{
			jta.append(ex.toString() + '\n');
			
		}
	}
	
		public void actionPerformed(ActionEvent e)// action method
		{
			if(e.getSource()==b){
			
			try
			{
				double radius = Double.parseDouble(jtf.getText().trim());
				int n = Integer.parseInt(jtf1.getText().trim());
				toServer.writeDouble(radius);
				toServer.flush();
				double area = fromServer.readDouble();
				if(n==1){
				jta.append("\nRadius is: " + radius );
				jta.append("\n\nCircle : " + area );
				}
				if(n==2){
				jta.append("\nRadius is: " + radius);
				jta.append("\n\nCylinder: " + area);
				}
					
			}
						catch(IOException ex)
			{
				System.err.println(ex);
				
			}
		}
		}
}