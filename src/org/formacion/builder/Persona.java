package org.formacion.builder;

public class Persona {

	private String nombre;
	private int edad;
	private String municipio;
	private String colegio;
	private String lugarTrabajo;
	
	private Persona() {}

	@Override
	public String toString(){
			String datosPersona = "";
			if (getEdad() >= 18){
				datosPersona = 	getNombre() + ", Edad: " + getEdad() + " Municipio: " + getMunicipio() + ", Trabajo: "+
						getLugarTrabajo();
			} else {
				datosPersona = 	getNombre() + ", Edad: " + getEdad() + " Municipio: " + getMunicipio() + ", Colegio: "+
						getColegio();
			}
		return datosPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getColegio() {
		return colegio;
	}

	public void setColegio(String colegio) {
		this.colegio = colegio;
	}

	public String getLugarTrabajo() {
		return lugarTrabajo;
	}

	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}

	//Builder
	public static class Builder {
		private Persona persona;
		
		public Builder(String nombre) {
			persona = new Persona();
			persona.nombre = nombre;
		}
		
		public Builder setMunicipio (String municipio) {
			persona.municipio = municipio;
			return this;
		}
		
		public BuilderdMayor setMayor(int edad) {
			if (edad < 18) throw new IllegalArgumentException("es menor de edad " + edad);
			persona.edad = edad;
			persona.colegio = null;
			return new BuilderdMayor(persona);
		}
		
		public BuilderMenor setMenor(int edad) {
			if (edad >= 18) throw new IllegalArgumentException("es mayor de edad " + edad);
			persona.edad = edad;
			persona.lugarTrabajo = null;
			return persona.new BuilderMenor(persona);
		}
	
		public Persona build() {
			return persona;
		}

	}

	//BuilderMayor inner
	public static class BuilderdMayor {
		private Persona persona = null;

		private BuilderdMayor(Persona persona){ this.persona = persona;}


		public BuilderdMayor setLugarTrabajo(String lugarTrabajo){
			persona.setLugarTrabajo(lugarTrabajo);
			return this;
		}

		public Persona build(){
			return persona;
		}
	}

	public class BuilderMenor {
		private Persona personaMenor = null;

		private BuilderMenor(Persona persona){
			personaMenor = persona;
		}

		public BuilderMenor setColegio(String colegio){
			personaMenor.setColegio(colegio);
			return this;
		}

		public Persona build(){
			return personaMenor;
		}

	}
	
}
