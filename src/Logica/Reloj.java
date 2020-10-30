package Logica;

import java.time.Duration;
import java.time.LocalTime;

public class Reloj 
{
	//ATRIBUTOS
	protected Digito[] digitos;
	protected int cantDigitos;
	protected LocalTime tiempoDeInicio;
	protected Duration tiempoTranscurrido;
	
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
		}
		
		//Iniciar Reloj 
		tiempoDeInicio = LocalTime.now();
	}
	
	//METODOS
	public void actualizar() 
	{
		ActualizarReloj();
		
		String cadena = null;
		
		for (int j =0; j<cantDigitos; j++) 
		{
			String cadenaMinutos = String.format("%02d", tiempoTranscurrido.toMinutes());
			String cadenaSegundos = String.format("%06d", tiempoTranscurrido.getSeconds());
			
			Integer minuto = Integer.parseInt(cadenaMinutos);
			Integer segundo = Integer.parseInt(cadenaSegundos);
			
			String cadenaSegundosAux = null;
			
			if(minuto > 0 && segundo>59)
			{
				segundo = segundo - (60*minuto);
			}
			
			if(segundo<10)
			{
				cadenaSegundosAux = '0'+segundo.toString();
			}
			else
			{
				cadenaSegundosAux = segundo.toString();
			}
			
			cadena = cadenaMinutos+cadenaSegundosAux;
			
			digitos[j].setValor(cadena.charAt(j)-'0');
		}
	}
	
	public Digito getDigito(int j) 
	{
		return this.digitos[j];
	}
	
	public int getCantDigitos() 
	{
		return this.cantDigitos;
	}
	
	protected void ActualizarReloj()
	{
		LocalTime tiempoActual = LocalTime.now( );
		tiempoTranscurrido = Duration.between(tiempoDeInicio , tiempoActual);
	}
}
