 package com.pt.ua.deti.gridfolio.services;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class InsertImageProjeto extends AsyncTask<String, String, String> {

	private static final String SOAP_ACTION = "http://tempuri.org/InserirFotoProjecto";
	private static final String METHOD_NAME = "InserirFotoProjecto";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=InserirFotoProjecto";
	static String result = null;

	@Override
	protected String doInBackground(String... params) {
		Log.i("INSERT IMAGE", "entrei");
		try {

			
			Bitmap bMap = BitmapFactory.decodeFile("/sdcard/te.png");
			 ByteArrayOutputStream os=new ByteArrayOutputStream(); 
			 bMap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, 
			(OutputStream) os); 
			
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			PropertyInfo pi = new PropertyInfo();
			pi.setName("image");
			pi.setValue(os.toString());
			pi.setType(String.class);
			request.addProperty(pi);
			
			PropertyInfo piii = new PropertyInfo();
			piii.setName("id");
			piii.setValue(1);
			piii.setType(String.class);
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
			Log.i("SUCESS", result.toString());


		} catch (Exception e) {
			Log.i("EVENTS AMANHA", e.toString());
		}

		return result;
	}

}
