package com.prgguru.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

public class MainActivity extends Activity {
	// Create, automatically open (if applicable), save, and restore the 
	// Active Session in a way that is similar to Android UI lifecycles. 
	private UiLifecycleHelper uiHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// To maintain FB Login session
		uiHelper = new UiLifecycleHelper(this, null);
		uiHelper.onCreate(savedInstanceState);
	}
	
	// When Post Status Update button is clicked
	public void postStatusUpdate(View v){
		// Pass null as parameter for setLink method to post status update
		FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
        .setLink(null)
        .build();
		uiHelper.trackPendingDialogCall(shareDialog.present()); 
	}
	
	// When Share this tutorial on FB button is clicked
	public void shareTutorialonFB(View v){
		FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
        .setLink("http://programmerguru.com/android-tutorial/getting-started-with-the-facebook-sdk-for-android/")
        .build();
		uiHelper.trackPendingDialogCall(shareDialog.present());
	}
	// After Facebook Dialog is closed
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		uiHelper.onActivityResult(requestCode, resultCode, data,
				new FacebookDialog.Callback() {
			
					@Override
					public void onError(FacebookDialog.PendingCall pendingCall,
							Exception error, Bundle data) {
						Toast.makeText(getApplicationContext(), "Error Occured\nMost Common Errors:\n1. Device not connected to Internet\n2.Faceboook APP Id is not changed in Strings.xml", Toast.LENGTH_LONG).show();
					}

					@Override
					public void onComplete(
							FacebookDialog.PendingCall pendingCall, Bundle data) {						
						Toast.makeText(getApplicationContext(),"Done!!",Toast.LENGTH_SHORT).show();
					}
				});
	}

	@Override
	protected void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}
}
