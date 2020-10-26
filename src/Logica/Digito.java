package Logica;

public class Digito
{
	//ATRIBUTOS
	protected Integer numero;
	protected ContenedorGraficoDeCasilla grafico;
	protected Boolean estado;
	protected int posicion;
	
	/*
	 * Crea un Digito.
	 */
	public Digito()
	{
		this.numero = null;
		this.grafico = new ContenedorGraficoDeCasilla();
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
		
		public ContenedorGraficoDeCasilla getEntidadGrafica() 
		{
			return this.grafico;
		}
		
		public void setGrafica(ContenedorGraficoDeCasilla g) 
		{
			this.grafico = g;
		}
		
		public int gePosicion()
		{
			return this.posicion;
		}
}
