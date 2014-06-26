package com.pt.ua.deti.gridfolio.singleton;

import java.util.HashMap;
import android.annotation.SuppressLint;

@SuppressLint("UseSparseArrays")
public class AppDataProject {
	private static AppDataProject _instance;
	private HashMap<Integer, Integer> idProjecto = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> professores = new HashMap<Integer, String>();
	private HashMap<Integer, String> titulo = new HashMap<Integer, String>();
	private HashMap<Integer, String> disciplina = new HashMap<Integer, String>();
	private HashMap<Integer, String> date = new HashMap<Integer, String>();
	private HashMap<Integer, String> descricao = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> flag = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> image = new HashMap<Integer, String>();
	
	private AppDataProject() {

	}

	public static AppDataProject getInstance() {
		if (_instance == null) {
			_instance = new AppDataProject();
		}
		return _instance;
	}
	
	public String getImageProject(int id){
		return this.image.get(id);
	}
	
	public void addImageProjet(String img, int id){
		this.image.put(id, img);
	}
	
	public int getFlag(int id) {
		return flag.get(id);
	}

	public void addFlag(int flag, int id) {
		this.flag.put(id, flag);
	}

	public int getIdProjecto(int id) {
		return idProjecto.get(id);
	}

	public void addIdProjecto(int idProjeto, int id) {
		this.idProjecto.put(id, idProjeto);
	}

	public String gettitulo(int id) {
		return titulo.get(id);
	}

	public void addtitulo(String titulo, int id) {
		this.titulo.put(id, titulo);
	}

	public String getProfessores(int id) {
		return professores.get(id);
	}

	public void addProfessores(String professores, int id) {
		this.professores.put(id, professores);
	}

	public String getDate(int id) {
		return this.date.get(id);
	}

	public void addDate(String date, int id) {
		this.date.put(id, date);
	}
	
	public String getDisciplina(int id) {
		return disciplina.get(id);
	}

	public void addDisciplina(String disciplina, int id) {
		this.disciplina.put(id, disciplina);
	}
	
	public String getDescricao(int id) {
		return descricao.get(id);
	}

	public void addDescricao(String descricao, int id) {
		this.descricao.put(id, descricao);
	}

	public int getNumbProjetos() {
		return this.titulo.size();
	}
}
