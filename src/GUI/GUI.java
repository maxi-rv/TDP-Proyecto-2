package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import Logica.Casilla;
import Logica.Reloj;
import Logica.Sudoku;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {

	private JFrame frmSudoku;
	private Sudoku sudoku;
	private Reloj reloj;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmSudoku.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmSudoku = new JFrame();
		frmSudoku.setResizable(false);
		frmSudoku.setTitle("Sudoku");
		frmSudoku.getContentPane().setBackground(Color.DARK_GRAY);
		frmSudoku.setBounds(100, 100, 985, 700);
		frmSudoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSudoku.getContentPane().setLayout(null);
		
		JPanel clockPanel = new JPanel();
		clockPanel.setBackground(Color.BLACK);
		clockPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		clockPanel.setBounds(660, 10, 300, 70);
		frmSudoku.getContentPane().add(clockPanel);
		clockPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		gamePanel.setBackground(Color.GRAY);
		gamePanel.setBounds(10, 10, 640, 640);
		frmSudoku.getContentPane().add(gamePanel);
		gamePanel.setLayout(new GridLayout(9, 9, 1, 1));
		
		
		inicializarSudoku(gamePanel);
		inicializarReloj(clockPanel);
	}
	
	private void inicializarSudoku(JPanel gamePanel)
	{
		//Crea una instancia del juego Sudoku.
		try 
		{
			sudoku = new Sudoku();
		} 
		catch (Exception e1) 
		{
			JFrame f = new JFrame();  
			JOptionPane.showMessageDialog(f,e1.getMessage());
			System.exit(1);
		}
		
		//Toma todas las referencias a casillas de la instancia Sudoku, y las asigna a JLabel en paneles.
		for (int i = 0; i <sudoku.getCantFilas(); i++) 
		{
			for(int j =0; j<sudoku.getCantFilas(); j++) 
			{
				Casilla c = sudoku.getCasilla(i,j);
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				label.setOpaque(true);
				label.setBackground(Color.LIGHT_GRAY);
				
				gamePanel.add(label);
				
				label.addComponentListener(new ComponentAdapter() 
				{
					@Override
					public void componentResized(ComponentEvent e) 
					{
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});
				
				if(c.getValor()==null)
				{
					//Añade un Listener a el label para poder interactuar.
					label.addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mouseClicked(MouseEvent e) 
						{
							sudoku.accionar(c);
							reDimensionar(label,grafico);

							if(c.getEstado() == false)
							{
								Border border = BorderFactory.createLineBorder(Color.RED, 3);
								label.setBorder(border);
							}
							else
							{
								label.setBorder(null);
							}

							if(sudoku.ganoSudoku())
							{
								JFrame f = new JFrame();  
								JOptionPane.showMessageDialog(f,"¡VICTORIA!");
								System.exit(1);
							}
						}
					});
				}
				else
				{
					Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
					label.setBorder(border);
				}
			}
		}
	}
	
	private void inicializarReloj(JPanel clockPanel)
	{
		reloj = new Reloj();
		
		JLabel clock1 = new JLabel("");
		clock1.setIcon(new ImageIcon(GUI.class.getResource("/img/Number_0.png")));
		clock1.setHorizontalAlignment(SwingConstants.CENTER);
		clockPanel.add(clock1);
		
		JLabel clock2 = new JLabel("");
		clock2.setIcon(new ImageIcon(GUI.class.getResource("/img/Number_0.png")));
		clock2.setHorizontalAlignment(SwingConstants.CENTER);
		clockPanel.add(clock2);
		
		JLabel clock3 = new JLabel("");
		clock3.setIcon(new ImageIcon(GUI.class.getResource("/img/Number_0.png")));
		clock3.setHorizontalAlignment(SwingConstants.CENTER);
		clockPanel.add(clock3);
		
		JLabel clock4 = new JLabel("");
		clock4.setIcon(new ImageIcon(GUI.class.getResource("/img/Number_0.png")));
		clock4.setHorizontalAlignment(SwingConstants.CENTER);
		clockPanel.add(clock4);
		
		Timer timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                reloj.accionar();
            }
        });
	}
	
	private void reDimensionar(JLabel label, ImageIcon grafico) 
	{
		Image image = grafico.getImage();
		
		if (image != null) 
		{  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}
