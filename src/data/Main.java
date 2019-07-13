package data;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class Main {

	private JFrame frmSimuladorDeCombate;
	private JTextField nFire;
	private JTextField nFireman;
	private JTextField nRefugee;
	private int nF, nFM, nR;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmSimuladorDeCombate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	public void begin() {
		try {
			if (nFire.getText().isEmpty()) {
				nF = 1;
			} else {
				nF = Integer.parseInt(nFire.getText());
				if (nF <= 0)
					nF = 1;
			}
			if (nFireman.getText().isEmpty()) {
				nFM = 1;
			} else {
				nFM = Integer.parseInt(nFireman.getText());
				if (nFM <= 0)
					nFM = 1;
			}
			if (nRefugee.getText().isEmpty()) {
				nR = 1;
			} else {
				nR = Integer.parseInt(nRefugee.getText());
				if (nR <= 0)
					nR = 1;
			}
			if ((nF + nFM + nR) < 100) {
				Boot boot = new Boot(nF, nFM, nR);
				boot.beginAll();
			} else {
				JOptionPane.showMessageDialog(null, "Soma de números deve ser menor de 100.");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		frmSimuladorDeCombate = new JFrame();
		frmSimuladorDeCombate.setType(Type.UTILITY);
		frmSimuladorDeCombate.setTitle("Simulador de combate ao fogo");
		frmSimuladorDeCombate.setBounds(100, 100, 450, 300);
		frmSimuladorDeCombate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimuladorDeCombate.getContentPane().setLayout(null);

		JButton btnComea = new JButton("Come\u00E7ar");
		btnComea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				begin();
			}
		});
		btnComea.setBounds(176, 214, 89, 23);
		frmSimuladorDeCombate.getContentPane().add(btnComea);

		nFire = new JTextField();
		nFire.setBounds(182, 143, 228, 20);
		frmSimuladorDeCombate.getContentPane().add(nFire);
		nFire.setColumns(10);

		JEditorPane txtFire = new JEditorPane();
		txtFire.setEditable(false);
		txtFire.setText("N\u00FAmero de Fogos:");
		txtFire.setBounds(24, 143, 148, 20);
		frmSimuladorDeCombate.getContentPane().add(txtFire);

		JEditorPane txtFireman = new JEditorPane();
		txtFireman.setEditable(false);
		txtFireman.setText("N\u00FAmero de Bombeiros:");
		txtFireman.setBounds(24, 112, 148, 20);
		frmSimuladorDeCombate.getContentPane().add(txtFireman);

		JEditorPane txtRefugee = new JEditorPane();
		txtRefugee.setEditable(false);
		txtRefugee.setText("N\u00FAmero de Refugiados:");
		txtRefugee.setBounds(24, 174, 148, 20);
		frmSimuladorDeCombate.getContentPane().add(txtRefugee);

		nFireman = new JTextField();
		nFireman.setBounds(182, 112, 228, 20);
		frmSimuladorDeCombate.getContentPane().add(nFireman);
		nFireman.setColumns(10);

		nRefugee = new JTextField();
		nRefugee.setBounds(182, 174, 228, 20);
		frmSimuladorDeCombate.getContentPane().add(nRefugee);
		nRefugee.setColumns(10);

		JLabel lblFire = new JLabel("");
		lblFire.setIcon(new ImageIcon(Main.class.getResource("/res/fire.png")));
		lblFire.setBounds(182, 23, 64, 63);
		frmSimuladorDeCombate.getContentPane().add(lblFire);

		JLabel lblRefugee = new JLabel("");
		lblRefugee.setIcon(new ImageIcon(Main.class.getResource("/res/r_21.png")));
		lblRefugee.setBounds(281, 23, 64, 63);
		frmSimuladorDeCombate.getContentPane().add(lblRefugee);

		JLabel lblFireman = new JLabel("");
		lblFireman.setIcon(new ImageIcon(Main.class.getResource("/res/f_11.png")));
		lblFireman.setBounds(81, 23, 64, 63);
		frmSimuladorDeCombate.getContentPane().add(lblFireman);
	}
}
