package Logica;

public class Casilla extends Celda
{
	//ATRIBUTOS
	ContenedorGraficoCasilla grafico;
	protected Boolean estado;
	protected Boolean bloqueado;
	protected int fila;
	protected int columna;
	
	/*
	 * Crea un Casilla.
	 */
	public Casilla(int fila, int columna)
	{
		this.numero = null;
		this.grafico = new ContenedorGraficoCasilla();
		this.estado = null;
		this.fila = fila;
		this.columna = columna;
	}
	
	//METODOS
	/*
	 * Actualiza el valor de la casilla, y su contenedorGrafico.
	 */
	public void actualizar() 
	{
		if (this.numero != null && this.numero + 1 < this.getCantElementos()) 
		{
			this.numero++;
		}
		else 
		{
			this.numero = 0;
		}
		
		grafico.actualizar(this.numero);
	}
	
	public Boolean getEstado()
	{
		return this.estado;
	}
	
	public void setEstado(boolean estado)
	{
		this.estado = estado;
	}
	
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
		if (numeroNuevo!=null && numeroNuevo < this.getCantElementos()) 
		{
			this.numero = numeroNuevo;
			this.grafico.actualizar(this.numero);
		}
		else 
		{
			this.numero = null;
		}
	}
	
	public ContenedorGrafico getGrafica() 
	{
		return this.grafico;
	}
	
	public void setGrafica(ContenedorGrafico g) 
	{
		this.grafico = (ContenedorGraficoCasilla) g;
	}
	
	public int getFila()
	{
		return this.fila;
	}
	
	public int getColumna()
	{
		return this.columna;
	}

	public void setTipoJugable() 
	{
		grafico.setTipoJugable();
	}
}
