package zad1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;
import javafx.stage.FileChooser;

public class WindowApp2 {

	private JFrame frame;
	private JTextField Tlumaczone;
	private JTextField inValueWord;
	private JTextField txtTlumaczenie;
	private JTextField inKeyWord;
	private JTextPane outList;
	private JLabel lblNewLabel_1;
	private JButton saveButton;
	private JButton loadButton;
	private JButton btnNewButton;
	private File file;
	private JTextField first;
	private JTextField second;
	private JButton langAddButt;
	private String pathout = "out";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowApp2 window = new WindowApp2();
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
	public WindowApp2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dictonary[] wordList = new Dictonary[1];
		wordList[0] = new Dictonary(); 
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][1px][][][grow][grow][]", "[1px][][][][grow][][][][][][]"));

		lblNewLabel_1 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_1, "flowx,cell 1 0,alignx left,aligny top");

		Tlumaczone = new JTextField();
		Tlumaczone.setText("Tlumaczone");
		Tlumaczone.setEditable(false);
		frame.getContentPane().add(Tlumaczone, "cell 2 0,grow");
		Tlumaczone.setColumns(10);
		
		txtTlumaczenie = new JTextField();
		txtTlumaczenie.setText("Tlumaczenie");
		txtTlumaczenie.setEditable(false);
		frame.getContentPane().add(txtTlumaczenie, "cell 3 0,grow");
		txtTlumaczenie.setColumns(10);
		
		inKeyWord = new JTextField();
		frame.getContentPane().add(inKeyWord, "cell 0 1 3 1,grow");
		inKeyWord.setColumns(10);
		
		inValueWord = new JTextField();
		inValueWord.setHorizontalAlignment(SwingConstants.TRAILING);
		frame.getContentPane().add(inValueWord, "cell 3 1 2 1,grow");
		inValueWord.setColumns(10);
		
		JButton btnDodajSlowo = new JButton("Dodaj Slowo");
		frame.getContentPane().add(btnDodajSlowo, "cell 3 2,grow");
		
		btnDodajSlowo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!(inKeyWord == null)|| !(inKeyWord.getText().equals(null)) || !(inKeyWord.getText().equals("")))
				{
					//System.out.print(wordList[0]);
					if (wordList[0] == null) {
						wordList[0] = new Dictonary(inKeyWord.getText(),inValueWord.getText());
						
					}else {
					
					wordList[0].addWord(inKeyWord.getText(),inValueWord.getText());
					}
				//outList.setText(wordList[0].toString());
			
				Json json = new Json();
				outList.setText(json.prettyPrint(wordList[0]));
				
				//wordList[0] = json.fromJson(Dictonary.class, everything);
			
				//json.prettyPrint(wordList[0]);
				}
			}});
		
		
		saveButton = new JButton("Zapisz");
		frame.getContentPane().add(saveButton, "cell 4 3,grow");
		
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						Json json = new Json();
						if (!(pathout.equals("out"))) {
						FileOutputStream outputStream;
						try {
							outputStream = new FileOutputStream(pathout);
						
					    byte[] strToBytes = json.prettyPrint(wordList[0]).getBytes();
					    outputStream.write(strToBytes);
					    outputStream.flush();
					    outputStream.close();
						
						//file.writeString(wordList[0].toString(), false);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}}});
		
		loadButton = new JButton("Wczytaj");
		frame.getContentPane().add(loadButton, "cell 5 3,grow");
		
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				BufferedReader br;
				
				
				
				
				try {
					String path = null;
					
					
					if (first.getText()!="" && second.getText()!="") {
						path = "data/"+first.getText()+"-"+second.getText()+".json";
						//System.out.println(path);
						pathout = path;
						}
					
					if (!(path == null)) {
					br = new BufferedReader(new FileReader(path));
				    StringBuilder sb = new StringBuilder();
				    String line = br.readLine();

				    while (line != null) {
				        sb.append(line);
				        sb.append(System.lineSeparator());
				        line = br.readLine();
				    }
				    String everything = sb.toString();
				System.out.println(everything);
				    br.close();
					Json json = new Json();
					//json.setIgnoreUnknownFields(true);
					
					wordList[0] = json.fromJson(Dictonary.class, everything);
					//System.out.println(wordList[0]);
					json.prettyPrint(wordList[0]);
					outList.setText(json.prettyPrint(wordList[0]));
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			//	FileHandle file =  Gdx.files.internal("data/ENG.json");
				//String in = file.readString();
			
			}});
		
		first = new JTextField();
		frame.getContentPane().add(first, "cell 4 4,grow");
		
		second = new JTextField();
		frame.getContentPane().add(second, "cell 5 4,grow");
		
		langAddButt = new JButton("zatwierdz nazwe");
		frame.getContentPane().add(langAddButt, "cell 4 5");
		
	
		
		outList = new JTextPane();
		outList.setEditable(false);
		frame.getContentPane().add(outList, "cell 0 6 7 5,grow");
		
		
	}

}