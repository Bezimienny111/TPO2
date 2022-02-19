package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ServerTwo extends Thread {

	private String WordToTranslate;
	private String LangCode;
	private int SocketNumber;
	private ServerSocket ss = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private static Pattern inInfo = Pattern.compile(", ", 2); // podział informacji przychodzącej, trzy słowa rozdzielone spacjami.
	Dictonary dic = new Dictonary();
	private String langOfDict;
	private Socket sock2 = new Socket();
	private	PrintWriter out2 = null;
	private String adressOfClient;
	
	public  boolean isServerOn = true;  // kończenie wątku
	private String threadID;// id wątku
	
	public ServerTwo(String id,ServerSocket ssIn, String lang) throws IOException, InterruptedException {
		 this.threadID = id;
		 this.ss = ssIn;
		 dic = new Dictonary(1,lang);
		 this.langOfDict = lang;
		 System.out.println("Server "+ " "+ lang+" "+threadID+ " started");
		 System.out.println("at port: " + ss.getLocalPort());
		 System.out.println("bind address: " + ss.getInetAddress());
		// sock2.setSoTimeout(200000);
		// sock2.setKeepAlive(true);
	/*	 while(!sock2.isConnected())
			 try {
		 sock2  = new Socket("192.168.1.4", 9092);
		 out2  = new PrintWriter(sock2.getOutputStream(), true);
			 } catch(Exception e) {}				 
		*/	   // to działa ale w innym miejscu powinno być
		 start();  // nasłuchiwanie połączeń
		
		
	}

	public void run() {
		while (!ss.isClosed()) {
			try {
			Socket conn = ss.accept();	
			
			System.out.println("ServerLang "+langOfDict+" connection on with thread: " + threadID);
			Requests(conn);
			sock2.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
		try {
			ss.close(); // zamknięcie gniazda
		}catch (Exception e) {}
			
}

	private void Requests(Socket conn) {
		int tester = 0;
		try {
			
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			out = new PrintWriter(conn.getOutputStream(),true);
			
			
			for (String line; (line = in.readLine()) != null; ) {
				
				String resp;
				String[] getIn = inInfo.split(line,3);
				//in.close();
				System.out.print(" linia wejściowa = " + getIn[0]);
				if (getIn.length != 3) {
					System.out.println("Złe dane wejściowe");
				} else {
				
				
				WordToTranslate = getIn[0];//.replace(",","");
				adressOfClient = getIn[1].replace(",","");
				SocketNumber = Integer.parseInt(getIn[2].replace(",",""));
			//	System.out.println("-----------------");
			//	System.out.println(WordToTranslate);
			//	System.out.println(adressOfClient);
			//	System.out.println(SocketNumber);
				
				System.out.println("czy połączono " + sock2.isConnected());
				 while(!sock2.isConnected())
					 try {
				 sock2  = new Socket(adressOfClient, SocketNumber);
				 out2  = new PrintWriter(sock2.getOutputStream(), true);
				 System.out.println(" Połączono z klientem: " + sock2.isConnected());
				 
					 } catch(Exception e) {}				 

				
				//System.out.println("-----------------");
						tester = 1;
				//	 System.out.println(tester);
				
				if(tester == 1) {
					if (dic.getLangList().containsKey(WordToTranslate) == true) {
						//out.println("Znaleziono słowo");
						sendMess(0,dic.getWord(WordToTranslate));
						 System.out.println(0 +"  "+ dic.getWord(WordToTranslate));
						 // Socket sock = new Socket("192.168.1.4", SocketNumber);
					   //   out = new PrintWriter(sock.getOutputStream(), true);
					   //   out.println(0 +"  "+ dic.getWord(WordToTranslate));
						// System.out.println(0 +"  "+ dic.getWord(WordToTranslate));
						// sock2.close();
						
						
						//System.out.println(dic.getWord(WordToTranslate));
						tester = -1;
						//System.out.println(tester);
					}else {
						sendMess(0,"Brak tłumaczenia na liscie");
					}
					
					
				}else {
					sendMess(0,"Coś poszło nie tak");
				//	System.out.println(tester);
				}
				}
			}
				
				
			} catch (Exception exc) {
		        exc.printStackTrace();	
		} 
		//finally {
		
		//if (tester == -1)
		{
			try {           
				System.out.println("Zamykamy server LANG");
				
				out2.close();
				sock2.close();
				sock2 = new Socket();
				//sock2 = null;
			//	System.out.println(tester);// zamknięcie strumieni
		          in.close();                        // i gniazda
		          out.close();
		          conn.close();
		          conn = null;
		        } catch (Exception exc) { }
		    }
		}
	//	}
	

	private void sendMess(int reco,String string)  {
		
	out2.println(string);	
	}
	
	public static void runServLang(String ip, int port, String langShort) throws IOException, InterruptedException {
		 final int SERV_NUM = 5; 
		 ServerSocket ss = null;
		    try {

		      InetSocketAddress isa = new InetSocketAddress(ip, port);

		      ss =  new ServerSocket();             // Utworzenie gniazda serwera
		      ss.bind(isa);                         // i związanie go z adresem

		    } catch(Exception exc) {
		        exc.printStackTrace();
		        System.exit(1);
		    }
		    for  (int i=1; i<=SERV_NUM;i++) {
		    
		    new ServerTwo(Integer.toString(i),ss,langShort);
		    }
		
		
	}
	
	 public static void main(String[] args) throws IOException, InterruptedException {
		 final int SERV_NUM = 5; 
		 ServerSocket ss = null;
		    try {

		      InetSocketAddress isa = new InetSocketAddress("192.168.1.4", 9091);

		      ss =  new ServerSocket();             // Utworzenie gniazda serwera
		      ss.bind(isa);                         // i związanie go z adresem

		    } catch(Exception exc) {
		        exc.printStackTrace();
		        System.exit(1);
		    }
		    for  (int i=1; i<=SERV_NUM;i++) {
		    
		    new ServerTwo(Integer.toString(i),ss,"EN");
		    }
		  }

		}
	

