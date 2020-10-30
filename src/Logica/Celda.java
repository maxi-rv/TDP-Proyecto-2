package Logica;

public abstract class Celda 
{
	//ATRIBUTOS
	protected Integer numero;
	protected ContenedorGrafico grafico;
	
	//METODOS
	public abstract int getCantElementos();
	
	public abstract Integer getValor();
	
	public abstract void setValor(Integer numeroNuevo);
	
	public abstract ContenedorGrafico getGrafica();
	
	public abstract void setGrafica(ContenedorGrafico g);
}
