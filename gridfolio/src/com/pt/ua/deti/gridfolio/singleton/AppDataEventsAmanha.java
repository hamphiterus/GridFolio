package com.pt.ua.deti.gridfolio.singleton;

import java.util.HashMap;
import java.util.Set;

import android.util.Log;

public class AppDataEventsAmanha {
	private static AppDataEventsAmanha _instance;
	private HashMap<Integer, String> categoria = new HashMap<Integer, String>();
	private HashMap<Integer, String> nome = new HashMap<Integer, String>();
	private HashMap<Integer, String> descricao = new HashMap<Integer, String>();
	private HashMap<Integer, String> local = new HashMap<Integer, String>();
	private HashMap<Integer, String> date = new HashMap<Integer, String>();
	private HashMap<Integer, String> maisinfo = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> refGroup = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> idevent = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> latitude = new HashMap<Integer, String>();
	private HashMap<Integer, String> longitude = new HashMap<Integer, String>();

	private AppDataEventsAmanha() {

	}

	public static AppDataEventsAmanha getInstance() {
		if (_instance == null) {
			_instance = new AppDataEventsAmanha();
		}
		return _instance;
	}
	
	public String getDescricao(int id) {
		
		return descricao.get(id);
	}

	public void addDescricao(String descricao, int id) {
		Log.i("AMANHA SINGLETON", "");
		this.descricao.put(id, descricao);
	}

	public String getNome(int id) {
		return nome.get(id);
	}

	public void addNome(String nome, int id) {
		this.nome.put(id, nome);
	}
	public String getCategoria(int id) {
		return categoria.get(id);
	}

	public void addCategoria(String categoria, int id) {
		this.categoria.put(id, categoria);
	}

	public String getLocal(int id) {
		return local.get(id);
	}

	public void addLocal(String local, int id) {
		this.local.put(id, local);
	}

	public String getDate(int id) {
		return date.get(id);
	}

	public void addDate(String date, int id) {
		this.date.put(id, date);
	}

	public String getMaisinfo(int id) {
		return maisinfo.get(id);
	}

	public void addMaisinfo(String maisinfo, int id) {
		this.maisinfo.put(id, maisinfo);
	}

	public int getRefGroup(int id) {
		return refGroup.get(id);
	}

	public void addRefGroup(int refGroup, int id) {
		this.refGroup.put(id, refGroup);
	}

	public String getLatitude(int id) {
		return latitude.get(id);
	}

	public void addLatitude(String latitude, int id) {
		this.latitude.put(id, latitude);
	}

	public String getLongitude(int id) {
		return longitude.get(id);
	}

	public int getIDEvent(int id) {
		return idevent.get(id);
	}

	public void addIDEvent(int IDEvent, int id) {
		this.idevent.put(id, IDEvent);
	}
	public void addLongitude(String longitude, int id) {
		this.longitude.put(id, longitude);
	}

	public int getNumbEvents(){
		return this.nome.size();
	}
	
	public String[] getAllNames() {
		String[] allNames = new String[getNumbEvents()];
		int i = 0;
		Set<Integer> keys = nome.keySet();  
        for (Integer key : keys)  
        {  
        	Log.i("AppData", nome.get(key));
            allNames[i] = nome.get(key);
            i++;
        } 
		return allNames;
	}
}
