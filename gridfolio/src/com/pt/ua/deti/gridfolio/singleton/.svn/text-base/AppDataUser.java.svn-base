package com.pt.ua.deti.gridfolio.singleton;

public class AppDataUser {
	private static AppDataUser _instance;

	// Valores a serem guardados
	private int id = 0;
	private int numeventos = 0;
	private int numprojectos = 0;
	private String name = null;
	private String course = null;
	private String email = null;
	private String data = null;
	private String ano = null;

	private AppDataUser() {

	}

	public static AppDataUser getInstance() {
		if (_instance == null) {
			_instance = new AppDataUser();
		}
		return _instance;
	}

	public int getNumeventos() {
		return numeventos;
	}

	public void setNumeventos(int numeventos) {
		this.numeventos = numeventos;
	}

	public int getNumprojectos() {
		return numprojectos;
	}

	public void setNumprojectos(int numprojectos) {
		this.numprojectos = numprojectos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAno() {
		return ano;
	}
}
