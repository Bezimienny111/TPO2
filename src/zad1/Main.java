package zad1;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String ip = "192.168.1.4";
		int PortLangStart = 7000;
		int PortLangStartv = PortLangStart;
		int portMainServ = 9000;
		int portListenClient = 9001;
		
		for (int i = 0; i<HelpUtils.number().size(); i++)
		ServerTwo.runServLang(ip,(++PortLangStart), HelpUtils.number().get(i));
		
		
		ServerOne.ServerOneStart(ip,portMainServ,PortLangStartv);
		
	//	Client.appClient("kupowac", ip, "JP", portListenClient, portMainServ);
	//	++portListenClient;
	//	Client.appClient("isc", ip, "JP", portListenClient, portMainServ);
	//	++portListenClient;
	//	Client.appClient("mowic", ip, "JP", portListenClient, portMainServ);
	//	++portListenClient;
	///	Client.appClient("kupowac", ip, "EN", portListenClient, portMainServ);
	//	++portListenClient;
	//	Client.appClient("czekac", ip, "JP", portListenClient, portMainServ);
	//	++portListenClient;
	//	Client.appClient("kupowac", ip, "EN", portListenClient, portMainServ);
	//	++portListenClient;
	//	if(!(HelpUtils.test == null))
	//	System.out.println("------ "+ HelpUtils.test+" ----------" );
		
	}

}
