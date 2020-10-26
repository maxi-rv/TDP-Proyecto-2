package Logica;
import javax.swing.ImageIcon;

public class ContenedorGraficoDeCasilla
{
	//ATRIBUTOS
	protected ImageIcon imagen;
	protected String[] rutaImagenes;
	
	/*
	 * Crea un Contenedor Grafico de imagenes.
	 */
	public ContenedorGraficoDeCasilla()
	{
		this.imagen = new ImageIcon();
		this.rutaImagenes = new String[]{"/img/Blocks_1.png", "/img/Blocks_2.png", 
				"/img/Blocks_3.png", "/img/Blocks_4.png", "/img/Blocks_5.png", 
				"/img/Blocks_6.png", "/img/Blocks_7.png", "/img/Blocks_8.png", 
				"/img/Blocks_9.png"};
	}
	
	//METODOS
	/*
	 * Actualiza la imagen correspondiente.
	 */
	public void actualizar(int numero) 
	{
		if (numero < this.rutaImagenes.length) 
		{
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.rutaImagenes[numero]));
			this.imagen.setImage(imageIcon.getImage());
		}
	}
	
	public ImageIcon getGrafico() 
	{
		return this.imagen;
	}
	
	public void setGrafico(ImageIcon imagen) 
	{
		this.imagen = imagen;
	}
	
	public String[] getImagenes() 
	{
		return this.rutaImagenes;
	}
	
	public void setImagenes(String[] imagenes) 
	{
		this.rutaImagenes = imagenes;
	}
}
