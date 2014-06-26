package com.pt.ua.deti.gridfolio.services;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import com.pt.ua.deti.gridfolio.singleton.AppDataEventsBreve;
import com.pt.ua.deti.gridfolio.singleton.AppDataUser;

import android.os.AsyncTask;
import android.util.Log;

public class ServiceGetEventsBreve extends AsyncTask<String, String, String> {

	private static final String SOAP_ACTION = "http://tempuri.org/getEventsBreveGridFolio";
	private static final String METHOD_NAME = "getAllEventsGridFolio";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=getEventsBreveGridFolio";
	static String result = null;

	@Override
	protected String doInBackground(String... params) {
		Log.i("EVENTS BREVE", "entrei");
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
			
			SoapObject response = (SoapObject) envelope.bodyIn;
			SoapObject Mresult = (SoapObject) envelope.getResponse();
			
			result = response.getProperty(0).toString();
			for (int i = 0; i < Mresult.getPropertyCount(); i++) {
				SoapObject pii = (SoapObject) Mresult.getProperty(i);
				
				AppDataEventsBreve.getInstance().addIDEvent(
						Integer.parseInt(pii.getProperty(0).toString()), i);
				AppDataEventsBreve.getInstance().addNome(
						pii.getProperty(3).toString(), i);
				AppDataEventsBreve.getInstance().addDescricao(
						pii.getProperty(4).toString(), i);
				AppDataEventsBreve.getInstance().addMaisinfo(
						pii.getProperty(5).toString(), i);
				AppDataEventsBreve.getInstance().addIDEvent(
						Integer.parseInt(pii.getProperty(0).toString()), i);
				AppDataEventsBreve.getInstance().addRefGroup(
						Integer.parseInt(pii.getProperty(1).toString()), i);
				AppDataEventsBreve.getInstance().addDate(pii.getProperty(6).toString(), i);

			}

		} catch (Exception e) {
			Log.i("EVENTS BREVE", e.toString());
		}

		return result;
	}

}
