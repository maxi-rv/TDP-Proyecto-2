package Logica;

import javax.swing.ImageIcon;

public abstract class ContenedorGrafico 
{
	//ATRIBUTOS
	protected ImageIcon imagen;
	protected String[] rutaImagenes;
		
		
	//METODOS
	public abstract void actualizar(int numero);
	
	public abstract ImageIcon getGrafico();
	
	public abstract void setGrafico(ImageIcon imagen);
	
	public abstract String[] getImagenes();
	
	public abstract void setImagenes(String[] imagenes);
}
