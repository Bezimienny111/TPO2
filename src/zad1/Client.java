package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;


public class Client extends Thread {

	private Socket clietnSocket = null;
	private Socket sock2 = null;
	private PrintWriter clientOutMess = null;
	private BufferedReader toClietnInput = null;
	private BufferedReader translatedInput= null;
	private String host;// = "192.168.1.4";
	private int clietnPort;// = 9090;
	private int clientListenPort;
	private String whatIwant;
	private ServerSocket ss = null;
	private String threadID;// id wątku
	public volatile boolean isServerOn = true;  // kończenie wątku
	private boolean isTranslate = false;
	private boolean correctData = false;
	private String Log = "Client log: ";
	private String translation ="";
	private TranslateaApp translatewindow;
	
	public String getLog() {
		return Log;
	}

	public void setLog(String log) {
		Log = log;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	private static Pattern inInfo = Pattern.compile(" +", 2);
	  //public Client(String stringIN, String id,ServerSocket ssIn) {
	  public Client(String stringIN,String hostIP,int portTalk, int portListen) {
		  translatewindow = new TranslateaApp();
		  
		
			//	window.getSetButton().addActionListener(arg0);
		 
		this.host = hostIP;
		  this.clietnPort = portTalk;
		  this.clientListenPort = portListen;
		//  threadID = id;
		//  this.ss = ssIn;
		    try {
		      clietnSocket = new Socket(host, clietnPort);
		   
		      clientOutMess = new PrintWriter(clietnSocket.getOutputStream(), true);
		      toClietnInput = new BufferedReader(
		               new InputStreamReader(
		                   clietnSocket.getInputStream()));
		      whatIwant = stringIN;
		      
		      
		  //  sock2 = new Socket(host, port2); 
		   //   in2 = new BufferedReader(
		   //            new InputStreamReader(
		   //                sock2.getInputStream()));
		            
		  
		    		}
	  catch (Exception  exc) {
	        exc.printStackTrace();
	        System.exit(4);
	    }
		    start();
	  }
	  
	  public void run() {
		  try {
			ss = new ServerSocket(clientListenPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	  while (isServerOn) {
		  
	//	try {
	//		Socket conn = ss.accept();
		
	//		
	//		System.out.println("ServerOne connection on with thread: " + threadID);
	//		
	//		BufferedReader inF = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	//		String resp;
	//		for (String line; (line = inF.readLine()) != null; ) {
	//			 resp = line;
	//		 System.out.println(resp.replaceAll(".\s\s", ""));
	//		}
	//	} catch (IOException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}	
		  
		  try {
			 
				  IWantLang(whatIwant);
				  Thread.sleep(500);
				  clientOutMess.flush();
				  clientOutMess.close();
				  toClietnInput.close();
				  clietnSocket.close();
				  ss.close();
			 // clientOutMess.println("bye");
		  }catch (Exception exc) {
			  exc.printStackTrace();
		  }
		//  try {
			  //clientOutMess.close();
		//	clietnSocket.close();
			//ss.close();
		//} catch (IOException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		  }  
	//  }
	  
	  public void ListeningClient() {
		  Runnable run = new Runnable() {

			@Override
			public void run() {
			while (!isTranslate) {
				try {
					System.out.println("uruchomiono");
					if(!(ss == null)) {
					Socket socketOfServ = ss.accept();
					socketOfServ.setSoTimeout(5000);
					translatedInput = new BufferedReader(
							             new InputStreamReader(
							            		 socketOfServ.getInputStream()));
					String translatedWord = translatedInput.readLine();
					System.out.println("linia z tłumaczenia: "+ translatedWord);
					getOutWithTranslation(translatedWord);
					 translatewindow.frame.setVisible(true);
					//System.out.println(translatedWord);
					//translation = translatedWord;
					//HelpUtils tmp = new HelpUtils();
					//System.out.println(tmp.PrintThisString(translation));
					 translatedInput.close();
					socketOfServ.close();
					ss.close();
					}
				  } catch (IOException e) {
                      //e.printStackTrace();
                  }
				isTranslate =true;
				
				
			}
				
			}
			  
		  };
		  Thread thread = new Thread(run);
	        thread.start();
		  
	  }
	  
	  
	  public void IWantLang(String lang) throws IOException {
		  System.out.println("Request: " + lang);
		  clientOutMess.println(lang);
		  // edycja
		// clientOutMess.println("koncz, a, a");
	//	  clientOutMess.flush();
		 // clientOutMess.close();
		  //------------
		  String resp = toClietnInput.readLine();
		  if (resp.startsWith("3")) {
		//	System.out.println(resp);
		//	System.out.println(toClietnInput.readLine());
		//	toClietnInput.close();
		//	  clietnSocket.close();
		//		ss.close();
		//		clientOutMess.close();
		  }
		  if (resp.startsWith("0")) {
			  correctData =true;
			  if (correctData)
				    ListeningClient();
		// System.out.println(resp.replaceAll(".\s\s", ""));
	//	 System.out.println(toClietnInput.readLine().replaceAll(".\s", ""));
		//  clietnSocket.close();
		 // toClietnInput.close();
		//	ss.close();
		//	clientOutMess.close();
		  
		  }
		  }
	  
	  public void getOutWithTranslation(String translation) {
		  translatewindow.transleteInBox(translation);
		  
	  }
public static void main(String[] args) {
	//String[] req = { "kupowac, EN, 9092"};
	// ServerSocket ss = null;
	//    try {

	 //     InetSocketAddress isa = new InetSocketAddress("192.168.1.4", 9092);
	//
	 //     ss =  new ServerSocket();             // Utworzenie gniazda serwera
	 //     ss.bind(isa);                         // i związanie go z adresem
	//
	 //   } catch(Exception exc) {
	 //       exc.printStackTrace();
	 //       System.exit(1);
	//    }
	//  for (int i=0; i<req.length; i++)
	  //    new Client(req[i],Integer.toString(i),ss);
	 // new Client(req[0]);
	}

public static void appClient(String word,String ip,String lang, int portListen, int portTalk) {
	String[] req = { word+", "+lang+", "+portListen};
	// Client cl = 
			 new Client(req[0],ip,portTalk,portListen);
	//return cl.getTranslation();
}
}
				
