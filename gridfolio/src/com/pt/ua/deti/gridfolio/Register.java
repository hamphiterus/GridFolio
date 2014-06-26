package com.pt.ua.deti.gridfolio;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.pt.ua.deti.gridfolio.singleton.AppDataUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.EditText;
import android.widget.ImageView;

public class Register extends Activity {

	// UI references.
	private static EditText mNome;
	private static EditText mCurso;
	private static EditText mEmail;
	private static EditText mPassword;
	private static EditText mCOnfPass;
	private static ImageView mPicture;

	// Data
	private static String nome = null;
	private static String curso = null;
	private static String email = null;
	private static String password = null;
	private static Bitmap bitmap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		mNome = (EditText) findViewById(R.id.editTextNome);
		mCurso = (EditText) findViewById(R.id.editTextCurso);
		mEmail = (EditText) findViewById(R.id.editTextEmail);
		mPassword = (EditText) findViewById(R.id.editTextPassword);
		mCOnfPass = (EditText) findViewById(R.id.editTextRetype);
		mPicture = (ImageView) findViewById(R.id.image_event);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	public void pictureEvent(View view) {
		String[] addPhoto = new String[] { "Camera", "Gallery" };
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		// dialog.setTitle(getResources().getString(R.string.method));
		dialog.setTitle("Profile Picture");
		dialog.setCancelable(true);

		dialog.setItems(addPhoto, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

				if (id == 0) {
					// with camera
					Intent takePicture = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(takePicture, 0);// zero can be
															// replced with any
															// action code
				}
				if (id == 1) {
					// from gallery
					Intent pickPhoto = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(pickPhoto, 1);
				}
			}
		});

		dialog.setNeutralButton("cancel", null);
		dialog.show();
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				Bundle extras = imageReturnedIntent.getExtras();
				bitmap = (Bitmap) extras.get("data");
				mPicture.setImageBitmap(bitmap);
			}

			break;
		case 1:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = imageReturnedIntent.getData();
				mPicture.setImageURI(selectedImage);
			}
			break;
		}
	}

	public void validateRegister(View view) {

		/* possivel c�digo para visualizar valores Toast msg = */
		// Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
		// msg.show();
		Pattern pattern = Patterns.EMAIL_ADDRESS;
		String errorValue = "";
		boolean error = false;

		nome = mNome.getText().toString();
		curso = mCurso.getText().toString();
		email = mEmail.getText().toString();
		password = mPassword.getText().toString();

		// verifica se os campos foram preenchidos
		if (nome.equals("") || curso.equals("") || email.equals("")
				|| password.equals("")) {
			error = true;
			errorValue = errorValue.concat(getString(R.string.missingAlert));
		} else {
			// verifica se o e-mail � valido
			if (!pattern.matcher(email).matches()) {
				email = null;
				errorValue = errorValue.concat(getString(R.string.mailAlert));
				error = true;
			} else {
				// verificar se o mail existe???

				// verifica se a password coincidem
				if (mPassword.getText().toString()
						.equals(mCOnfPass.getText().toString())) {
					password = mPassword.getText().toString();
				} else {
					errorValue = errorValue
							.concat(getString(R.string.passAlert));
					error = true;
				}
			}
		}
		// Verifica se h� erros se houver trata do assunto se n�o houver lan�a
		// uma task para criar registar
		if (error) {
			// 1. Instantiate an AlertDialog.Builder with its constructor
			// AlertDialog.Builder builder = new
			// AlertDialog.Builder(getActivity());
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			// 2. Chain together various setter methods to set the dialog
			// characteristics
			builder.setMessage(errorValue);

			// 3. Add buttons and listeners methods
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
							// Reset aos bot�es de campo password
							mEmail.setText(email);
							mPassword.setText(null);
							mCOnfPass.setText(null);
						}
					});

			// 4. Get the AlertDialog from create()
			AlertDialog dialog = builder.create();

			dialog.show();

		} else {
			
			String result = null;// = register(params);

			UserRegisterTask register = new UserRegisterTask();
			try {
				result = register.execute().get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			// dialog.setTitle(getResources().getString(R.string.method));
			if (result.equals("true")) {
				dialog.setNeutralButton("Ok",
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								Intent i = new Intent();
								startActivity(i);								
							}
						});
				dialog.setTitle("Registo Concluido");
				Intent gridFolio = new Intent(Register.this,
						LayerStack.class);
				startActivity(gridFolio);
			}else {
				dialog.setTitle(result);
				dialog.setNeutralButton("Ok",
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {

								dialog.dismiss();
							}
						});
			}
			
			dialog.show();
			Intent gridFolio = new Intent(Register.this,
					LayerStack.class);
			startActivity(gridFolio);
		}

	}

	public void setUserData() {

		// extrai o bitmap com a figura de profile (eu acho)
		mPicture.setDrawingCacheEnabled(true);
		mPicture.measure(
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		mPicture.layout(0, 0, mPicture.getMeasuredWidth(),
				mPicture.getMeasuredHeight());
		mPicture.buildDrawingCache(true);
		@SuppressWarnings("unused")
		Bitmap bmap = Bitmap.createBitmap(mPicture.getDrawingCache());
		mPicture.setDrawingCacheEnabled(false);

		AppDataUser.getInstance().setName(nome);
		AppDataUser.getInstance().setCourse(curso);
		AppDataUser.getInstance().setEmail(email);

	}

	public class UserRegisterTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			
			// Soap values
			final String SOAP_ACTION = "http://tempuri.org/RegistrarUser";
			final String METHOD_NAME = "RegistrarUser";
			final String NAMESPACE = "http://tempuri.org/";
			final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=RegistrarUser";
			
			String result = null;

			try {

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
				PropertyInfo pi;

				pi = new PropertyInfo();
				pi.setName("name");
				pi.setValue(nome);
				pi.setType(String.class);
				request.addProperty(pi);

				pi = new PropertyInfo();
				pi.setName("Curso");
				pi.setValue(curso);
				pi.setType(String.class);
				request.addProperty(pi);
				
				pi = new PropertyInfo();
				pi.setName("email");
				pi.setValue(email);
				pi.setType(String.class);
				request.addProperty(pi);
				
				pi = new PropertyInfo();
				pi.setName("login");
				pi.setValue(email);
				pi.setType(String.class);
				request.addProperty(pi);
				
				pi = new PropertyInfo();
				pi.setName("Pass");
				pi.setValue(password);
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
				
				result = response.getProperty(0).toString();

				
				
				
			} catch (Exception e) {
				Log.i("result", e.toString());
			}

			return result;
		}
	}
}
