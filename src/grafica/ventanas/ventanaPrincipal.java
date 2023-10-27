package grafica.ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class ventanaPrincipal {

	private JFrame frame;
	private ventanaPrincipal vista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal window = new ventanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public ventanaPrincipal() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 892, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNombre = new JLabel("Nombre ");
		lblNombre.setBounds(28, 17, 100, 34);
		frame.getContentPane().add(lblNombre);
		
		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(90, 17, 150, 34);
		frame.getContentPane().add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido ");
		lblApellido.setBounds(250, 17, 100, 34);
		frame.getContentPane().add(lblApellido);
		
		JTextField txtApellido = new JTextField();
		txtApellido.setBounds(315, 17, 150, 34);
		frame.getContentPane().add(txtApellido);
		
		frame.getContentPane().setLayout(null);
		
		frame.setVisible(true);
	}
}