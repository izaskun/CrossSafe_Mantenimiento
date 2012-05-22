package idek.solutions.c2dm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
	private static String KEY = "c2dmPref";
	private static String REGISTRATION_KEY = "registrationKey";

	private Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		if (intent.getAction().equals(
				"com.google.android.c2dm.intent.REGISTRATION")) {
			handleRegistration(context, intent);
		} else if (intent.getAction().equals(
				"com.google.android.c2dm.intent.RECEIVE")) {
			handleMessage(context, intent);
		}
	}

	private void handleRegistration(Context context, Intent intent) {
		// handles registeration
	}

	private void handleMessage(Context context, Intent intent) {

		String title = intent.getStringExtra("title");
		String message = intent.getStringExtra("msg");
		Toast.makeText(context, "title : " + title + "\n message : " + message,
				1).show();
		// Do whatever you want with the message
	}
}