package practicaFicherosED;

public class Paciente extends Persona {
	private String calle;
	private String localidad;
	private String codPostal;
	private String fecha;
	private String hora;
	private String dni;	
	
	public Paciente () {
		
	}
	
	public Paciente (String nombre, int edad, String calle, String localidad,
			String codPostal) {
		this.nombre = nombre;
		this.edad = edad;
		this.calle = calle;
		this.localidad = localidad;
		this.codPostal = codPostal;
		generarDni();
	}
	
	public Paciente (String nombre, int edad, String calle, String localidad,
			String codPostal, String dni) {
		this.nombre = nombre;
		this.edad = edad;
		this.calle = calle;
		this.localidad = localidad;
		this.codPostal = codPostal;
		this.dni = dni;
	}
	
	public Paciente (String nombre, double peso,
			 double altura, String fecha, String hora, String dni) {
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.fecha = fecha;
		this.hora = hora;
		//this.imc = calcularIMC();
		this.dni = dni;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getDniTeclado() {
		return dni;
	}

	public void setDniTeclado(String dni) {
		this.dni = dni;
	}
	
}
