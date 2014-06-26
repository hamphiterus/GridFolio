package com.pt.ua.deti.gridfolio.services;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.pt.ua.deti.gridfolio.singleton.AppDataUser;

import android.os.AsyncTask;
import android.util.Log;

public class ServiceGetInformationUser extends AsyncTask<String, String, String> {

	private static final String SOAP_ACTION = "http://tempuri.org/getUserGridFolio";
	private static final String METHOD_NAME = "getUserGridFolio";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=getUserGridFolio";
	static String result = null;
	static String nome;
	static String desc;
	static String inf;

	@Override
	protected String doInBackground(String... params) {
		
		try {
			
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			PropertyInfo pi = new PropertyInfo();
			 pi.setName("email");
			 pi.setValue(AppDataUser.getInstance().getEmail().toString());
			 pi.setType(String.class);
			 request.addProperty(pi);
			 
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;

			envelope.setOutputSoapObject(request);

			AndroidHttpTransport androidHttpTransporta = new AndroidHttpTransport(
					URL);
			androidHttpTransporta.call(SOAP_ACTION, envelope);

			SoapObject response = (SoapObject) envelope.getResponse();
			SoapObject pii = (SoapObject) response.getProperty(0);
		
			result = response.getProperty(0).toString();
			AppDataUser.getInstance().setEmail(pii.getProperty(3).toString());
			AppDataUser.getInstance().setName(pii.getProperty(2).toString());
			AppDataUser.getInstance().setCourse(pii.getProperty(4).toString());
			AppDataUser.getInstance().setId(Integer.parseInt(pii.getProperty(0).toString()));
			AppDataUser.getInstance().setAno(pii.getProperty(5).toString() );
			

		} catch (Exception e) {
			Log.i("USER", e.toString());
		}
    
		return result;
	}
	

}
