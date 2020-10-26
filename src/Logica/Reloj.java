package Logica;


public class Reloj 
{
	//ATRIBUTOS
	protected Digito[] digitos;
	protected int cantDigitos;
	protected long tiempoDeInicio;
	protected String tiempoTranscurrido;
	
	/*
	 * Crea un reloj y lo inicializa.
	 */
	public Reloj()
	{
		this.cantDigitos = 4;
		digitos = new Digito[this.cantDigitos];
		
		for (int j =0; j<cantDigitos; j++) 
		{
			digitos[j] = new Digito();
			digitos[j].setValor(0);
		}
		
		//Iniciar Reloj 
		tiempoDeInicio = System.currentTimeMillis();
	}
	
	//METODOS
	public void accionar() 
	{
		ActualizarReloj();
		
		digitos[3].setValor((tiempoTranscurrido.charAt(3)-'0'));
		digitos[2].setValor((tiempoTranscurrido.charAt(2)-'0'));
		digitos[1].setValor((tiempoTranscurrido.charAt(1)-'0'));
		digitos[0].setValor((tiempoTranscurrido.charAt(0)-'0'));
	}
	
	public Digito getCasilla(int i, int j) 
	{
		return this.digitos[j];
	}
	
	public int getCantDigitos() 
	{
		return this.cantDigitos;
	}
	
	protected void ActualizarReloj()
	{
		long tiempoActual = System.currentTimeMillis();
        tiempoTranscurrido = String.valueOf((tiempoActual - this.tiempoDeInicio) / 1000);
	}
}
