package com.pt.ua.deti.gridfolio.threadsEvents;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.pt.ua.deti.gridfolio.singleton.AppDataProject;
import com.pt.ua.deti.gridfolio.singleton.AppDataUser;

import android.os.AsyncTask;
import android.util.Log;

public class ProjectsUserService extends AsyncTask<String, String, String> {

	private static final String SOAP_ACTION = "http://tempuri.org/getProjectsUserGridFolio";
	private static final String METHOD_NAME = "getProjectsUserGridFolio";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=getProjectsUserGridFolio";
	static String result = null;
	static String nome;
	static String desc;
	static String inf;
	
	@Override
	protected String doInBackground(String... params) {
		
		try {
			Log.i("PROJECTO", "PROJECTO");
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			PropertyInfo pi = new PropertyInfo();
			pi.setName("email");
			pi.setValue(AppDataUser.getInstance().getEmail().toString());
			pi.setType(String.class);
			request.addProperty(pi);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;

			envelope.setOutputSoapObject(request);

			AndroidHttpTransport androidHttpTransporta = new AndroidHttpTransport(URL);
			androidHttpTransporta.call(SOAP_ACTION, envelope);

			SoapObject response = (SoapObject) envelope.bodyIn;
			SoapObject Mresult = (SoapObject) envelope.getResponse();
			
			result = response.getProperty(0).toString();
			Log.i("PROJECTO FOR ", result.toString());
			Log.i("PROJECTO FOR ", "FORA");
			for(int i = 0; i < Mresult.getPropertyCount(); i++){
				SoapObject pii = (SoapObject)Mresult.getProperty(i);
				Log.i("PROJECTO FOR ", result.toString());
				AppDataProject.getInstance().addIdProjecto(Integer.parseInt(pii.getProperty(0).toString()), i);
				AppDataProject.getInstance().addDescricao(pii.getProperty(2).toString(), i);
				AppDataProject.getInstance().addtitulo(pii.getProperty(4).toString(), i);
				AppDataProject.getInstance().addDisciplina(pii.getProperty(3).toString(), i);
				AppDataProject.getInstance().addProfessores(pii.getProperty(5).toString(), i);
				AppDataProject.getInstance().addFlag(Integer.parseInt(pii.getProperty(1).toString()), i);
				
				//AppDataProject.getInstance().addImageProjet(pii.getProperty(6).toString(), i);
				
			}
		} 
		catch (Exception e) {
			Log.i("PROJECTO", e.toString());
		}
    
		return result;
	}
	

}
