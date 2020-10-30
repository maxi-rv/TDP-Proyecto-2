package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Sudoku 
{
	//ATRIBUTOS
	protected Casilla[][] tablero;
	protected int cantFilas;
	
	/*
	 * Crea una sesion de Sudoku.
	 */
	public Sudoku() throws Exception
	{
		this.cantFilas = 9;
		tablero = new Casilla[this.cantFilas][this.cantFilas];
		
		if(!cargarArchivo())
		{
			throw new Exception("Error: El formato del archivo es incorrecto.");
		}
		
		if(!verificarTabla())
		{
			throw new Exception("Error: El archivo de inicializacion no contiene una solucion correcta!");
		}
		
		eliminarCasillas();
	}
	
	
	//METODOS
	/*
	 * 
	 */
	public void accionar(Casilla c) 
	{
		c.actualizar();
		
		//Verifica Fila, Columna, y Panel. 
		if(this.chequearColumnaCompleta(c) & this.chequearFilaCompleta(c) & this.chequearPanelCompleta(c))
		{
			c.setEstado(true);
		}
		else
		{
			c.setEstado(false);
		}
	}
	
	/*
	 * 
	 */
	public Casilla getCasilla(int i, int j) 
	{
		return this.tablero[i][j];
	}
	
	/*
	 * 
	 */
	public int getCantFilas() 
	{
		return this.cantFilas;
	}
	
	/*
	 * 
	 */
	public boolean ganoSudoku()
	{
		boolean toReturn = false;
		
		if(verificarTabla())
			toReturn = true;
		
		return toReturn;
	}
	
	/*
	 * 
	 */
	private boolean chequearFila(Casilla c)
	{
		boolean toReturn = true;
		int fila = c.getFila();
		
		for(int indiceColumna=0; indiceColumna<cantFilas; indiceColumna++)
		{
			if(tablero[fila][indiceColumna]!=c && tablero[fila][indiceColumna].getValor()!=null)
			{
				if(tablero[fila][indiceColumna].getValor().equals(c.getValor()))
				{
					toReturn = false;
				}
			}
		}
		
		return toReturn;
	}
	
	/*
	 * 
	 */
	private boolean chequearColumna(Casilla c)
	{
		boolean toReturn = true;
		int columna = c.getColumna();
		
		for(int indiceFila=0; indiceFila<cantFilas; indiceFila++)
		{
			if(tablero[indiceFila][columna]!=c && tablero[indiceFila][columna].getValor()!=null)
			{
				if(tablero[indiceFila][columna].getValor().equals(c.getValor()))
				{
					toReturn = false;
				}
			}
		}
		
		return toReturn;
	}
	
	/*
	 * 
	 */
	private boolean chequearPanel(Casilla c)
	{
		boolean toReturn = true;
		
		int primerFila = encontrarPrimerIndiceDePanel(c.getFila());
        int primerColumna = encontrarPrimerIndiceDePanel(c.getColumna());
        int indiceFila = primerFila;
        int indiceColumna = primerColumna;
        
        while(toReturn && indiceFila<primerFila+3) 
        {
            while(toReturn && indiceColumna<primerColumna+3)
            {
            	if(tablero[indiceFila][indiceColumna]!=c && tablero[indiceFila][indiceColumna].getValor()!=null)
	    		{
            		if(tablero[indiceFila][indiceColumna].getValor().equals(c.getValor()))
	            	{
	            		toReturn = false;
	            	}
	    		}
            	indiceColumna++;
            }
            indiceFila++;
        }
        
        return toReturn;
	}
	
	/*
	 * 
	 */
	private boolean chequearFilaCompleta(Casilla c)
	{
		boolean toReturn = true;
		int fila = c.getFila();
		
		for(int indiceColumna=0; indiceColumna<cantFilas; indiceColumna++)
		{
			if(tablero[fila][indiceColumna]!=c && tablero[fila][indiceColumna].getValor()!=null)
			{
				if(tablero[fila][indiceColumna].getValor().equals(c.getValor()))
				{
					tablero[fila][indiceColumna].setEstado(false);
					toReturn = false;
				}
				else
        		{
        			tablero[fila][indiceColumna].setEstado(true);
        		}
			}
		}
		
		return toReturn;
	}
	
	/*
	 * 
	 */
	private boolean chequearColumnaCompleta(Casilla c)
	{
		boolean toReturn = true;
		int columna = c.getColumna();
		
		for(int indiceFila=0; indiceFila<cantFilas; indiceFila++)
		{
			if(tablero[indiceFila][columna]!=c && tablero[indiceFila][columna].getValor()!=null)
			{
				if(tablero[indiceFila][columna].getValor().equals(c.getValor()))
				{
					tablero[indiceFila][columna].setEstado(false);
					toReturn = false;
				}
				else
        		{
        			tablero[indiceFila][columna].setEstado(true);
        		}
			}
		}
		
		return toReturn;
	}
	
	/*
	 * 
	 */
	private boolean chequearPanelCompleta(Casilla c)
	{
		boolean toReturn = true;
		
		int primerFila = encontrarPrimerIndiceDePanel(c.getFila());
        int primerColumna = encontrarPrimerIndiceDePanel(c.getColumna());
        int indiceFila = primerFila;
        int indiceColumna = primerColumna;
        
        while(toReturn && indiceFila<primerFila+3) 
        {
            while(toReturn && indiceColumna<primerColumna+3)
            {
            	if(tablero[indiceFila][indiceColumna]!=c && tablero[indiceFila][indiceColumna].getValor()!=null)
	    		{
            		if(tablero[indiceFila][indiceColumna].getValor().equals(c.getValor()))
	            	{
            			tablero[indiceFila][indiceColumna].setEstado(false);
            			toReturn = false;
	            	}
            		else
            		{
            			tablero[indiceFila][indiceColumna].setEstado(true);
            		}
	    		}
            	indiceColumna++;
            }
            indiceFila++;
        }
        
        return toReturn;
	}
	
	/*
	 * 
	 */
    private int encontrarPrimerIndiceDePanel(int indice) 
    {
        if(indice%3 != 0) 
        {
            indice=encontrarPrimerIndiceDePanel(indice-1);
        }
        
        return indice;
    }
    
    /*
     * 
     */
    private boolean cargarArchivo() throws Exception 
	{
    	boolean toReturn = true;
    	
    	File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
		
        JFrame f = new JFrame();  
        String ruta = JOptionPane.showInputDialog(f, "Ingrese la ruta del archivo solucion:");
        
        if (ruta==null)
        {
        	throw new Exception("Error: La ruta del archivo no puede ser vacia.");
        }
        
		try 
		{
			archivo = new File(ruta);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
		} 
		catch (FileNotFoundException e) 
		{
			throw new Exception("Error: La ruta del archivo es incorrecta.");
		}
		
        
		try 
		{
			for (int i=0; i<cantFilas && toReturn; i++) 
			{
				String linea = br.readLine();
				int k = 0;
				
				for (int j=0; j<cantFilas && toReturn; j++) 
				{
					if(linea.charAt(k)>='1' && linea.charAt(k)<='9')
					{
						if(k<16 && linea.charAt(k+1)==' ')
						{
							tablero[i][j] = new Casilla(i,j);
							tablero[i][j].setValor((linea.charAt(k)-'0')-1);
							k = k + 2;
						}
						else if(k==16)
						{
							tablero[i][j] = new Casilla(i,j);
							tablero[i][j].setValor((linea.charAt(k)-'0')-1);
						}
						else
						{
							toReturn = false;
						}	
					}
					else
					{
						toReturn = false;
					}
				}
			}
	    }
        catch(Exception e1)
		{
             e1.printStackTrace();
        }
		finally
		{
	        try
	         {                    
	            if(null != fr)
	            {   
	               fr.close();     
	            }                  
	         }
	         catch (Exception e2)
	         { 
	            e2.printStackTrace();
	         }
		}
		
		return toReturn;
	}
    
    /*
     * 
     */
    private boolean verificarTabla()
	{
		boolean toReturn = true;
    	
    	for (int i=0; i<cantFilas && toReturn; i++) 
		{
			for (int j=0; j<cantFilas && toReturn; j++)
			{
				
				Casilla c = tablero[i][j];
				
				if(c.getValor() != null)
				{
					if(!this.chequearColumna(c) || !this.chequearFila(c) || !this.chequearPanel(c))
					{
						toReturn = false;
					}
				}
				else
				{
					toReturn = false;
				}
			}
				
		}
    	
    	return toReturn;
	}
    
    /*
     * Elimina algunas casillas aleatoriamente.
     */
    private void eliminarCasillas() 
    {
    	Random rand = new Random();
		
		for (int i=0; i<cantFilas; i++) 
		{
			for (int j=0; j<cantFilas; j++) 
			{
				int valor = rand.nextInt(10);
				if(valor==0)
				{
					tablero[i][j] = new Casilla(i,j);
					tablero[i][j].setTipoJugable();
				}
			}
		}
	}

}


