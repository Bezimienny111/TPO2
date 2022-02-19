package zad1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class TranslateaApp {

	public JFrame frame;
	JTextArea Translated = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TranslateaApp window = new TranslateaApp();
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
	public TranslateaApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JLabel lblNewLabel = new JLabel("Przetłumaczone słowo");
		panel.add(lblNewLabel, "cell 0 0");
		
	
		Translated.setEditable(false);
		panel.add(Translated, "cell 0 1,grow");
	}
	
	public void clearField(){
		Translated.setText("");
	}
	void transleteInBox(String translatedWord) {
		Translated.setText(translatedWord);
	}

}
