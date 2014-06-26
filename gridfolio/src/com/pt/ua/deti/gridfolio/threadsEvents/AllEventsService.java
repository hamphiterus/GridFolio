package com.pt.ua.deti.gridfolio.threadsEvents;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.pt.ua.deti.gridfolio.ShowMyEventsActivity;
import com.pt.ua.deti.gridfolio.singleton.AppDataAllEvents;
import android.os.AsyncTask;
import android.util.Log;

public class AllEventsService extends AsyncTask<String, Boolean, Boolean> {

	private static final String SOAP_ACTION = "http://tempuri.org/getAllEventsGridFolio";
	private static final String METHOD_NAME = "getAllEventsGridFolio";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=getAllEventsGridFolio";
	static String result = null;
	private final ShowMyEventsActivity sme;
	
	public AllEventsService(ShowMyEventsActivity sme) {
		this.sme = sme;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		Log.i("EVENTS", "entrei");
		try {

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;

			envelope.setOutputSoapObject(request);

			AndroidHttpTransport androidHttpTransporta = new AndroidHttpTransport(URL);
			androidHttpTransporta.call(SOAP_ACTION, envelope);

			SoapObject response = (SoapObject) envelope.bodyIn;
			SoapObject Mresult = (SoapObject) envelope.getResponse();

			result = response.getProperty(0).toString();
			for (int i = 0; i < Mresult.getPropertyCount(); i++) {
				SoapObject pii = (SoapObject) Mresult.getProperty(i);
				AppDataAllEvents.getInstance().addIDEvent(Integer.parseInt(pii.getProperty(0).toString()), i);
				AppDataAllEvents.getInstance().addNome(pii.getProperty(3).toString(), i);
				AppDataAllEvents.getInstance().addDescricao(pii.getProperty(4).toString(), i);
				AppDataAllEvents.getInstance().addMaisinfo(pii.getProperty(5).toString(), i);
				AppDataAllEvents.getInstance().addIDEvent(Integer.parseInt(pii.getProperty(0).toString()), i);
				AppDataAllEvents.getInstance().addRefGroup(Integer.parseInt(pii.getProperty(1).toString()), i);
				AppDataAllEvents.getInstance().addLatitude(pii.getProperty(9).toString(), i);
				AppDataAllEvents.getInstance().addLongitude(pii.getProperty(10).toString(), i);
				AppDataAllEvents.getInstance().addDate(pii.getProperty(6).toString(), i);
			}
			return true;
		} 
		catch (Exception e) {
			Log.i("EVENTS", e.toString());
			return false;
		}
	}
	
	@Override
	public void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		if(sme.pd != null) {
			sme.pd.dismiss();
		}
	}
}
