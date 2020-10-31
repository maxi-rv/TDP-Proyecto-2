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
import Logica.Digito;
import Logica.Reloj;
import Logica.Sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {

	protected JFrame frmSudoku;
	protected Sudoku sudoku;
	protected Reloj reloj;
	protected JLabel[][] jLabels;
	Timer[] timers;
	

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
		frmSudoku.setBounds(100, 100, 1005, 700);
		frmSudoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSudoku.getContentPane().setLayout(null);
		
		JPanel clockPanel = new JPanel();
		clockPanel.setBackground(Color.LIGHT_GRAY);
		clockPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		clockPanel.setBounds(660, 10, 320, 70);
		frmSudoku.getContentPane().add(clockPanel);
		clockPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
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
			JFrame f = new JFrame();  
	        String ruta = JOptionPane.showInputDialog(f, "Ingrese la ruta del archivo solucion:");
			sudoku = new Sudoku(ruta);
		} 
		catch (Exception e1) 
		{
			JFrame f = new JFrame();  
			JOptionPane.showMessageDialog(f,e1.getMessage());
			System.exit(1);
		}
		
		jLabels = new JLabel[sudoku.getCantFilas()][sudoku.getCantFilas()];
		
		//Toma todas las referencias a casillas de la instancia Sudoku, y las asigna a JLabel en paneles.
		for (int i = 0; i <sudoku.getCantFilas(); i++) 
		{
			for(int j =0; j<sudoku.getCantFilas(); j++) 
			{
				Casilla c = sudoku.getCasilla(i,j);
				ImageIcon grafico = c.getGrafica().getGrafico();
				JLabel label = new JLabel();
				label.setOpaque(true);
				label.setBackground(Color.LIGHT_GRAY);
				
				jLabels[i][j] = label;
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
							
							for (int k = 0; k < jLabels.length; k++) 
							{
								for(int l = 0; l< jLabels.length; l++) 
								{
									Boolean estado = sudoku.getCasilla(k, l).getEstado();
									
									if(estado != null)
									{
										if(estado == false)
										{
											Border border = BorderFactory.createLineBorder(Color.RED, 3);
											jLabels[k][l].setBorder(border);
										}
										else if(estado == true)
										{
											jLabels[k][l].setBorder(null);
										}
									}
								}
							}

							if(sudoku.ganoSudoku())
							{
								for (int i=0; i<timers.length; i++)
								{
									timers[i].stop();
								}
								
								JFrame f = new JFrame();  
								JOptionPane.showMessageDialog(f,"¡VICTORIA!");
								
								System.exit(1);
							}
						}
					});
				}
			}
		}
	}
	
	private void inicializarReloj(JPanel clockPanel)
	{
		reloj = new Reloj();
		
		timers = new Timer[reloj.getCantDigitos()];
		
		for(int j=0; j<reloj.getCantDigitos(); j++) 
		{
			Digito d = reloj.getDigito(j);
			
			ImageIcon grafico = d.getGrafica().getGrafico();
			JLabel label = new JLabel();
			
			clockPanel.add(label);
			
			label.addComponentListener(new ComponentAdapter() 
			{
				@Override
				public void componentResized(ComponentEvent e) 
				{
					reDimensionar(label, grafico);
					label.setIcon(grafico);
				}
			});
			
			Timer timer = new Timer(1000, new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                reloj.actualizar();
	                reDimensionar(label, grafico);
	            }
	        });
			
			timer.start();
			timers[j]=timer;
			
			if(j==1)
			{
				JLabel lblSeparador = new JLabel(":");
				lblSeparador.setHorizontalAlignment(SwingConstants.CENTER);
				label.setOpaque(true);
				label.setBackground(Color.LIGHT_GRAY);
				lblSeparador.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 48));
				lblSeparador.setForeground(Color.WHITE);
				lblSeparador.setSize(64, 64);
				clockPanel.add(lblSeparador);
			}
		}
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
