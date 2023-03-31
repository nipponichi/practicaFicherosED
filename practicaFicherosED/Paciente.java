package practicaFicherosED;

import java.util.Date;

public class Paciente extends Persona {
	private String calle;
	private String localidad;
	private String codPostal;
	private Date fecha;
	
	public Paciente () {
		
	}
	
	public Paciente (String nombre, int edad, String calle, String localidad,
			String codPostal) {
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.calle = calle;
		this.localidad = localidad;
		this.codPostal = codPostal;
		generarDni();
		comprobarSexo();
	}
	
	public Paciente (String nombre, int edad, char sexo, double peso,
			 double altura, String calle, String localidad, String codPostal,Date fecha) {
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.calle = calle;
		this.localidad = localidad;
		this.codPostal = codPostal;
		this.fecha = fecha;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	
}
