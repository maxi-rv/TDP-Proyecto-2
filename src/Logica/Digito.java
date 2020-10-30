package Logica;

public class Digito extends Celda
{
	//ATRIBUTOS
	protected int posicion;
	
	/*
	 * Crea un Digito.
	 */
	public Digito()
	{
		this.numero = null;
		this.grafico = new ContenedorGraficoDigito();
	}
	
	//METODOS
	public int getCantElementos() 
	{
		return this.grafico.getImagenes().length;
	}
	
	public Integer getValor() 
	{
		return this.numero;
	}
	
	public void setValor(Integer numeroNuevo) 
	{
		if (numeroNuevo != null && numeroNuevo < this.getCantElementos()) 
		{
			this.numero = numeroNuevo;
		}
		
		grafico.actualizar(this.numero);
	}
	
	public ContenedorGrafico getGrafica() 
	{
		return this.grafico;
	}
	
	public void setGrafica(ContenedorGrafico g) 
	{
		this.grafico = g;
	}
	
	public int gePosicion()
	{
		return this.posicion;
	}
}
