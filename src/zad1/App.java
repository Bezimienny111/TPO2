package zad1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class App {

	private JFrame frame;
	private JTextField LangField;
	private JTextField WordField;
	private JTextField IpField;
	private JTextField PortField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow,fill][grow,fill][grow,fill][grow,fill]", "[][][grow][][grow][grow]"));
		
		JLabel lblNewLabel_4 = new JLabel("Skrót języka");
		panel.add(lblNewLabel_4, "cell 0 0");
		
		JLabel lblNewLabel_5 = new JLabel("Słowo do tłumaczenia");
		panel.add(lblNewLabel_5, "cell 1 0");
		
		JLabel lblNewLabel = new JLabel("IP");
		panel.add(lblNewLabel, "cell 2 0");
		
		JLabel lblNewLabel_6 = new JLabel("Port");
		panel.add(lblNewLabel_6, "cell 3 0");
		
		LangField = new JTextField();
		panel.add(LangField, "cell 0 1,growx");
		LangField.setColumns(10);
		
		WordField = new JTextField();
		panel.add(WordField, "cell 1 1,growx");
		WordField.setColumns(10);
		
		IpField = new JTextField();
		IpField.setText("192.168.1.4");
		IpField.setEnabled(false);
		IpField.setEditable(false);
		panel.add(IpField, "cell 2 1,growx");
		IpField.setColumns(10);
		
		PortField = new JTextField();
		panel.add(PortField, "cell 3 1,growx");
		PortField.setColumns(10);
		
		JTextPane txtpnDostpneJzyki = new JTextPane();
		txtpnDostpneJzyki.setText(HelpUtils.printIt());
		txtpnDostpneJzyki.setEditable(false);
		panel.add(txtpnDostpneJzyki, "cell 0 2,grow");
		
		JButton btnNewButton = new JButton("Tłumacz");
		btnNewButton.setEnabled(false);
		panel.add(btnNewButton, "cell 1 2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!(PortField.getText() == null)) {
					
				Pattern regex = Pattern.compile("^[0-9]*$");
				Matcher m = regex.matcher(PortField.getText());
				boolean test = m.find();
				boolean test2 = (PortField.getText().length() == 0);
				System.out.println(test);
				System.out.println(test2);
				if (test == true) {
				
				
				if(!(WordField.getText() == null) && !(LangField.getText() == null) && !(WordField.getText().length() == 0) && !(LangField.getText().length() == 0) && !(PortField.getText().length() == 0)) {

				String ip = IpField.getText();
				int portMainServ = 9000;
				int  portListenClient = Integer.parseInt(PortField.getText());

				Client.appClient(
						WordField.getText(),
						ip, LangField.getText(), portListenClient, portMainServ);
				
				}
			}}}});
		
		JButton btnNewButton_1 = new JButton("Uruchom servery");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String ip = IpField.getText();
				int PortLangStart = 7000;
				int PortLangStartv = PortLangStart;
				int portMainServ = 9000;
				//int portListenClient = 9001;
				
				for (int i = 0; i<HelpUtils.number().size(); i++)
					
						ServerTwo.runServLang(ip,(++PortLangStart), HelpUtils.number().get(i));
					
				
				
				ServerOne.ServerOneStart(ip,portMainServ,PortLangStartv);
				btnNewButton.setEnabled(true);
				btnNewButton_1.setEnabled(false);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			}
		});
		panel.add(btnNewButton_1, "cell 2 2");
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1, "cell 1 3");
		
		JLabel label = new JLabel("");
		panel.add(label, "cell 2 3");
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2, "cell 3 3");
		
		JTextArea lblNewLabel_3 = new JTextArea("IP serwera gł: 9000;\r\nIP serwerwów\r\n językowych: 7000,7001...");
		lblNewLabel_3.setEditable(false);
		panel.add(lblNewLabel_3, "cell 1 4");
		
		
	}
	
	
	
	
}
