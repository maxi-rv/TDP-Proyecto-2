package Logica;
import javax.swing.ImageIcon;

public class ContenedorGraficoDigito extends ContenedorGrafico
{
	/*
	 * Crea un Contenedor Grafico de imagenes.
	 */
	public ContenedorGraficoDigito()
	{
		this.imagen = new ImageIcon();
		this.rutaImagenes = new String[]{"/img/Number_0.png", "/img/Number_1.png", "/img/Number_2.png",
				"/img/Number_3.png", "/img/Number_4.png", "/img/Number_5.png", "/img/Number_6.png", 
				"/img/Number_7.png", "/img/Number_8.png", "/img/Number_9.png"};
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
