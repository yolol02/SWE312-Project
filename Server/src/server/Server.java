package server;
import java.awt.*;//abstract window tool
import java.applet.*;
import java.net.*;//NET
import java.util.*;//UNTILITY TOOLS
import javax.swing.*;// J RELATED OBJECTS
import java.io.*;//
import java.rmi.*;//REMOTE

//Server Class declaration 
public class Server extends JFrame
{
	private JTextArea jta = new JTextArea();//object declaration
	
	public static void main(String args[]) throws NullPointerException //main method
	{
		new Server();
	}
	
	public Server()// constructor
	{
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);
		setTitle("Sever");
		setSize(500,300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		try // try catch block
		{
			ServerSocket serverSocket = new ServerSocket(60000);
			jta.append("Server started at" + new Date() + '\n');
			
			Socket socket = serverSocket.accept(); // sockect object
			
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
			
			while(true) // while loop
			{
				double n = inputFromClient.readDouble();
				double area = n * n * Math.PI;
				
				outputToClient.writeDouble(area);
				if(n==1){
				
				jta.append("Radius received from client: " + n + '\n' );// display out put on textarea
				jta.append("Circle Area found:" + area + '\n');
				}
				if(n==2){
				
				jta.append("Radius received from client: " + n + '\n' );// display out put on textarea
				jta.append("Cylender Area found:" + area*3 + '\n');
				}
				
			}
		}
		
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
}