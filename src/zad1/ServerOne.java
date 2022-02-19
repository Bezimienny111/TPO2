package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ServerOne extends Thread {

	private String WordToTranslate;
	private String LangCode;
	private int SocketNumber;
	private ServerSocket ss = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private static Pattern inInfo = Pattern.compile(", ", 3); // podział informacji przychodzącej, trzy słowa rozdzielone spacjami.
	private ArrayList<String> ListOfLangs = new ArrayList<String>(); // lista dostępnych języków
	private Socket sock2 = null;
	private	PrintWriter out2 = null;
	private Socket[] tabSoc;
	private	PrintWriter[] tabPrin = null;
	private int portStarter;
	
	public volatile boolean isServerOn = true;  // kończenie wątku
	private String threadID;// id wątku
	
	public ServerOne(String id,ServerSocket ssIn,int portStart) throws UnknownHostException, IOException, InterruptedException {
		 this.threadID = id;
		 this.ss = ssIn;
		 this.portStarter = portStart;
		// this.ListOfLangs.add("EN");
		 this.ListOfLangs = HelpUtils.number();
		 System.out.println("Server "+threadID+ " started");
		 System.out.println("at port: " + ss.getLocalPort());
		 System.out.println("bind address: " + ss.getInetAddress());
		 
		 tabSoc = new Socket[HelpUtils.number().size()];
		 tabPrin =  new PrintWriter[HelpUtils.number().size()];;
		
		 
		// tabSoc[0] = new Socket("192.168.1.4", ++portStart);
		
		// sock2  = new Socket("192.168.1.4", 9091);
	//	 out2  = new PrintWriter(sock2.getOutputStream(), true);
		 
		//if(!ss.isClosed()) 
		 start();  // nasłuchiwanie połączeń
		 
		
	}

	public void run() {
		while (!ss.isClosed()) {
			try {
			Socket conn = ss.accept();	
			
			System.out.println("ServerOne connection on with thread: " + threadID);
			Requests(conn);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
		try {
			System.out.println("Zamknięce ss");
			ss.close(); // zamknięcie gniazda
			isServerOn = false;
		}catch (Exception e) {}
		
	}
		
		
		
		
		
		


	private void Requests(Socket conn) throws IOException {
		int tester = 0;
		int outreco = -1;
		try {
			//Dictonary dic = new Dictonary();
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			out = new PrintWriter(conn.getOutputStream(),true);
			
			for (String line; (line = in.readLine()) != null; ) {
				// out.println("szukam");
				String resp;
				String[] getIn = inInfo.split(line,3);
				//edycja
			//	if(getIn[0].equals("koncz")) {
			//
			//		break;
			//	}
				//----
				if (getIn.length != 3) {
					sendMess(0,"Złe dane wejściowe");
					tester = -1;
				} else {
				
				String adressOfCl = conn.getInetAddress().toString().replace("/", "");
				WordToTranslate = getIn[0].replace(",","");
				LangCode = getIn[1].replace(",","");
				SocketNumber = Integer.parseInt(getIn[2].replace(",",""));
				
				if (!(ListOfLangs.contains(LangCode))) {
					sendMess(3,"Brak kodu");
				
				}
				else {
			//		 dic = new Dictonary(1,LangCode);
					sendMess(0,"Poprawne dane wejsciowe");
					 tester = 1;
					// System.out.println(tester);
					 outreco = ListOfLangs.indexOf(LangCode);
					// System.out.println(portStarter);
					// System.out.println(ss.getInetAddress());
					 sock2  = new Socket(adressOfCl, (portStarter+ListOfLangs.indexOf(LangCode)+1));
					 out2  = new PrintWriter(sock2.getOutputStream(), true);
				//	 for (int i = 0; i < HelpUtils.number().size(); i++) {
				//		 tabSoc[i] = new Socket(ss.getInetAddress(), ++portStarter);
				//		 tabPrin[i] = new PrintWriter(tabSoc[i].getOutputStream(), true);
					// out2.flush();
					// out2.close();
				
					// in.close();
					 }
					 
					 
					
				
				if(tester == 1) {
				//	if (dic.getLangList().containsKey(WordToTranslate) == true) {
						  try {
  
							  out2.println(WordToTranslate +", "+adressOfCl+", "+ SocketNumber);
							  //tabPrin[outreco].println(WordToTranslate +", "+adressOfCl+", "+ SocketNumber);
						     // out2.println(WordToTranslate +" "+ SocketNumber);
						    //  System.out.print(WordToTranslate +", "+adressOfCl+", "+ SocketNumber);
						     // System.out.println();
						      //out2.flush();
						     // out2.close();
						   
						     // System.out.println(WordToTranslate +" "+ SocketNumber);
						      tester = -1;
						  }catch  (Exception  exc) {
						        exc.printStackTrace();
						        System.exit(4);
						    }
						
						
						
						
						
						
						// out.println("Znaleziono słowo");
				//		sendMess(1,dic.getWord(WordToTranslate));
				//		System.out.println(dic.getWord(WordToTranslate));
						tester = -1;
						//System.out.println(tester);
					
					
					
				}else {
					sendMess(3,"Cos poszlo nie tak");
					//System.out.println(tester);
					
				}
				}
			//	System.out.println("tester przed catch " + tester);
			//	isServerOn = !isServerOn;
				
			//	System.out.println("is serverON " + isServerOn);
			}
				
				//System.out.println("tester przed catch " + tester);
				
			} catch (Exception exc) {
		        exc.printStackTrace();	
		} finally {
		
		//if (tester == -1)

			try {          
				System.out.println("Zamykamy server Main");
				//System.out.println(tester);// zamknięcie strumieni
				out2.close();
				sock2.close();
				
		          in.close();                        // i gniazda
		          out.close();
		          conn.close();
		          conn = null;
		 
		        } catch (Exception exc) { }
		}
		}
		//}
	

	private void sendMess(int reco,String string)  {
		
	out.println(reco+"  " + string);	
	}
	
	
	public static void ServerOneStart(String ip, int port,int port2) throws UnknownHostException, IOException, InterruptedException {
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
		    
		    new ServerOne(Integer.toString(i),ss,port2);
		    }
		
		
	}
	
	
	 public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		 final int SERV_NUM = 5; 
		 ServerSocket ss = null;
		    try {

		      InetSocketAddress isa = new InetSocketAddress("192.168.1.4", 9090);

		      ss =  new ServerSocket();             // Utworzenie gniazda serwera
		      ss.bind(isa);                         // i związanie go z adresem

		    } catch(Exception exc) {
		        exc.printStackTrace();
		        System.exit(1);
		    }
		    for  (int i=1; i<=SERV_NUM;i++) {
		    
		    new ServerOne(Integer.toString(i),ss,9070);
		    }
		  }

		}
	

