package practicaFicherosED;

public class Persona {
	
	/**
	 * Declaración de constante para definir Sexo H como predefinido
	 */
	private final static char SEXO_DEF = 'H';
	
	/**
	 * Declaración de constante para definir el valor de IMC del objeto Persona
	 */
	public static final int INFRAPESO = -1, PESO_IDEAL = 0, 
			 SOBREPESO = 1;
	
	/**
	 * Declaración de variables protected para permitir acceso desde
	 * clase heredera Paciente.
	 * Todos los atributos tienen valor por defecto excepto el DNI
	 */
	 protected String nombre;
	 protected int edad;
	 protected String DNI = "";
	 protected char sexo;
	 protected double peso;
	 protected double altura;

	 /**
	  * Constructor Persona por defecto.
	  */
	 public Persona() {
		this("", 0, SEXO_DEF, 0, 0);
	 }
	 
	 /**
	  * Constructor Persona con atributos: nombre, edad y sexo como<br>
	  * parámetros. Resto por defecto.
	  * @param nombre
	  * @param edad
	  * @param sexo
	  */
	 public Persona(String nombre, int edad, char sexo) {
		this(nombre, edad, sexo, 0, 0);
	 }
	 
	 /**
	  * Constructor Persona con todos los atributos como parámetro.
	  * @param nombre
	  * @param edad
	  * @param sexo
	  * @param peso
	  * @param altura
	  */
	 public Persona(String nombre, int edad, char sexo, double peso,
			 double altura) {
		 this.nombre = nombre;
		 this.edad = edad;
		 this.peso = peso;
		 this.altura = altura;
		 this.sexo = sexo;
		 comprobarSexo();
		 generarDni();
	 }
	 
	 /**
	  * Método de comprobacion de Sexo, otorga sexo H por defecto<br>
	  * si el caracter introducido es diferente de 'H' o 'M'
	  */
	 protected void comprobarSexo() {
		 if (sexo != 'H' && sexo != 'M') {
			 this.sexo = SEXO_DEF;
		 	}
	 }
	 
	 /**
	  * Método con algoritmo de generación automática de DNI
	  */
	 protected void generarDni() {
		 final int divisor = 23;
		 
		 // Generamos un número aleatorio entre 10 y 100 millones. (8 cifras).
		 int numDNI = ((int) Math.floor(Math.random() * (100000000 -
				 10000000) + 10000000));
		 //Con el residuo de esta operacion generamos la letra del DNI
		 int res = numDNI % divisor; //- (numDNI / divisor * divisor);
		 char letraDNI = generaLetraDNI(res);
		 DNI = Integer.toString(numDNI) + letraDNI;
	 }
	 
	 /**
	  * Metodo de optencion de letra de DNI Segun el resultado<br>
	  * de resto de división entre numDni y divisor
	  * @param res
	  * @return letra para DNI según resto de división.
	  */
	 private char generaLetraDNI(int res) {
		 char letras[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y',
			 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
			 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
		 return letras[res];
	}

	 /**
	  * //Método SET para atributo nombre.
	  * @param nombre
	  */
	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	}
	 
	 /**
	  * //Método SET para atributo edad.
	  * @param edad
	  */
	 public void setEdad(int edad) {
		 this.edad = edad;
	}

	 /**
	  * //Método SET para atributo sexo.
	  * @param sexo
	  */
	 public void setSexo(char sexo) {
		 this.sexo = sexo;
	}

	 /**
	  * //Método SET para atributo peso.
	  * @param peso
	  */
	 public void setPeso(double peso) {
		 this.peso = peso;
	}

	 /**
	  * //Método SET para atributo altura.
	  * @param altura
	  */
	 public void setAltura(double altura) {
		 this.altura = altura;
	}
	 
	public String getDNI() {
		return DNI;
	}

	public static char getSexoDef() {
		return SEXO_DEF;
	}

	public static int getInfrapeso() {
		return INFRAPESO;
	}

	public static int getPesoIdeal() {
		return PESO_IDEAL;
	}

	public static int getSobrepeso() {
		return SOBREPESO;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public char getSexo() {
		return sexo;
	}

	public double getPeso() {
		return peso;
	}

	public double getAltura() {
		return altura;
	}
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	/**
	  * Método de cálculo  de IMC
	  * @return Resultado IMC
	  */
	//Contemplar Static para no pasar objeto persona, solo necesitamos peso y altura
	 public int calcularIMC() {
		 //Calculamos el peso de la persona
		 double pesoActual = peso / (Math.pow(altura, 2));
		 //Segun el peso, devuelve un codigo
		 if (pesoActual >= 20 && pesoActual <= 25) {
			 return PESO_IDEAL;
			 	}
		 		else if (pesoActual < 20) {
		 			return INFRAPESO;
		 		} 
		 		else {
		 			return SOBREPESO;
		 		}
	 }
	 
	 /**
	  * Método booleano de reconocimiento de mayor o menor de edad.
	  * @return true si la persona tiene 18 años o mas.
	  */
	 public boolean esMayorDeEdad() {
		 return (this.edad >=18);
	 }
	 
	 /**
	  * Método para representar los parámetros de los atributos del objeto Persona.
	  * sin este método obtendriamos el nombre del objeto y su dirección en memoria.
	  */
	 public String toString() {
		 //Convertimos el caracter de sexo 'H' o 'M'
		 //en String Hombre o Mujer
		 String sexo;
		 if (this.sexo == 'H') {
			 sexo = "Hombre";
		 }
		 else {
			 sexo = "Mujer";
		 }
		 return "Informacion de la persona:\n"
		 + "Nombre: " + nombre + "\n"
		 + "Sexo: " + sexo + "\n"
		 + "Edad: " + edad + "\n"  
		 + "DNI: " + DNI + "\n"
		 + "Peso: " + peso + " kg\n"
		 + "Altura: " + altura + " metros\n";
	 }
}
