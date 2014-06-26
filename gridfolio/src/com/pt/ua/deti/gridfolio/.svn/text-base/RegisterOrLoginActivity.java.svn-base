package com.pt.ua.deti.gridfolio;

import java.util.concurrent.ExecutionException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.pt.ua.deti.gridfolio.singleton.AppDataUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


@SuppressWarnings("deprecation")
public class RegisterOrLoginActivity extends Activity {

	private Dialog login = null;
	private Context activityContext = this;
	private String username, password;
	// Your Facebook APP ID
	private static String APP_ID = "288919834576756";

	// Instance of Facebook Class
	private Facebook facebook;
	@SuppressWarnings("unused")
	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;
	private AlertDialog.Builder dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_or_login);
		
		if(!isOnline()) {
			dialog = new AlertDialog.Builder(this);
			dialog.setTitle(R.string.without_internet);
			dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface dialog, int id) {
					 startActivity(new Intent(Settings.ACTION_SETTINGS));
		         }
		    });
			
			AlertDialog xpto = dialog.create();
			xpto.show();
		}
		
		facebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(facebook);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_or_login, menu);
		return true;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(!isOnline()) {
			dialog = new AlertDialog.Builder(this);
			dialog.setTitle(R.string.without_internet);
			dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface dialog, int id) {
					 startActivity(new Intent(Settings.ACTION_SETTINGS));
		         }
		    });
			
			AlertDialog xpto = dialog.create();
			xpto.show();
		}
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}

	public void goToLogin(View view) {
		LayoutInflater factory = LayoutInflater.from(this);
		final View loginView = factory.inflate(R.layout.dialog_signin, null);

		Builder dialog = new AlertDialog.Builder(this);

		dialog.setCancelable(true);

		dialog.setView(loginView)
				.setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						boolean succeded = false;
						dialog.dismiss();
						
						EditText edtext_username = (EditText)loginView.findViewById(R.id.username);
						EditText edtext_password = (EditText)loginView.findViewById(R.id.password);
						
						username = edtext_username.getText().toString();
						password = edtext_password.getText().toString();
						 
//						ProgressDialog mDialog = new ProgressDialog(RegisterOrLoginActivity.this);
//			   	        mDialog.setMessage("A verificar dados...");
//			   	        mDialog.show();
//			   	        mDialog.setCancelable(true);
														 
						try {
							succeded = new UserLoginTask(RegisterOrLoginActivity.this).execute().get();
						} catch (InterruptedException e) {
							Log.i("error", e.toString());
						 	e.printStackTrace();
						} catch (ExecutionException e) {
							Log.i("error", e.toString());
							e.printStackTrace();
						}
						Log.i("succed", "" + succeded);

						if (succeded) {
							//mDialog.dismiss();
							AppDataUser.getInstance().setEmail(username);
							startActivity(new Intent(activityContext, ShowMyEventsActivity.class));
						} 
//						else {
//							AppDataUser.getInstance().setEmail("lfso@ua.pt");
//							startActivity(new Intent(activityContext, ShowMyEventsActivity.class););
//						}
					}
				}).setNegativeButton(R.string.cancel, null).create();
		dialog.show();
	}

	public void goToRegister(View view) {
		Intent tmp = new Intent(this, Register.class);
		startActivity(tmp);
	}

	public void goToFacebook(View view) {	
		loginToFacebook();
	}

	public void loginToFacebook() {
		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {

			facebook.authorize(this,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();
							Intent gridFolio = new Intent(activityContext,
									LayerStack.class);
							startActivity(gridFolio);
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
	}

	public void lostPass(View view) {
		login.cancel();
		System.out.print("forgotPassMethod");
		Log.d("Login", "Foi pela pass");
	}
	
	@Override
	public void onBackPressed(){
		//Do Nothing
	}

	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		Context context;
		private ProgressDialog mDialog;
		
		public UserLoginTask(Context context) {
			this.context = context;
		}
	      
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();

	         mDialog = new ProgressDialog(context);
	         mDialog.setMessage("A verificar dados...");
	         mDialog.show();
	    }

		@Override
		protected Boolean doInBackground(Void... params) {
			// Soap values
			final String SOAP_ACTION = "http://tempuri.org/Authenticate";
			final String METHOD_NAME = "Authenticate";
			final String NAMESPACE = "http://tempuri.org/";
			final String URL = "http://conquerua.meligaletiko.tk/WebServicesConquerUA.asmx?op=Authenticate";

			boolean registered = false;

			Log.i("username", username);
			Log.i("pass", password);

			try {

				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

				PropertyInfo pi = new PropertyInfo();
				pi.setName("login");
				pi.setValue(username);
				pi.setType(String.class);
				request.addProperty(pi);

				PropertyInfo pi2 = new PropertyInfo();
				pi2.setName("Password");
				pi2.setValue(password);
				pi2.setType(String.class);
				request.addProperty(pi2);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);

				AndroidHttpTransport androidHttpTransporta = new AndroidHttpTransport(
						URL);
				androidHttpTransporta.call(SOAP_ACTION, envelope);

				SoapObject response = (SoapObject) envelope.bodyIn;
				Log.i("result", "1");
				String result = response.getProperty(0).toString();

				Log.i("result", result.toString());
				
				if (result.equals("true")) {
					Log.i("result", "1");
					registered = true;
				}

			} catch (Exception e) {
				Log.i("result", e.toString());
			}
			return registered;
		}
		
		@Override
	    protected void onPostExecute(Boolean result) {
	        super.onPostExecute(result);
	        mDialog.dismiss();
		}
	}
}
