package com.example.tabhost;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressLint("NewApi")
public class MainActivity extends Activity 
{
	LocalActivityManager mlam;
	
	
	/***********************************************************************
	 * Function Name        : onCreate()
	 * Description          : Invoke to create the Activity
	 * Input parameters     : None
	 * output parameters    : None
	 * return values        : None
	 ***********************************************************************/ 

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mlam = new LocalActivityManager(this, false);
		mlam.dispatchCreate(savedInstanceState);
		TabHost th = (TabHost) findViewById(R.id.mytabhost);
		th.setup(mlam);

		/****************************** TAB 1 ******************************/

		/*Invoke to add the tab in TabHost.*/
		th.addTab(th.newTabSpec("TAB_1")

				/*Invoke to set the image on the tab.*/
				.setIndicator("", getResources().getDrawable(R.drawable.applications_system))

				/* Invoke to set the content on clicking the tab.*/
				.setContent
				(
				 new Intent(this, PhotosActivity.class)
				 .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
				));

		/****************************** TAB 2 ******************************/

		/*Invoke to add the tab in TabHost.*/
		th.addTab(th.newTabSpec("TAB_2")

				/*Invoke to set the image on the tab.*/
				.setIndicator("", getResources().getDrawable(R.drawable.contact))

				/* Invoke to set the content on clicking the tab.*/
				.setContent
				(
				 new Intent(this, SongsActivity.class)
				 .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
				));

		/****************************** TAB 3 ******************************/

		/*Invoke to add the tab in TabHost.*/
		th.addTab(th.newTabSpec("TAB_3")

				/*Invoke to set the image on the tab.*/
				.setIndicator("", getResources().getDrawable(R.drawable.folder_contacts_w))

				/* Invoke to set the content on clicking the tab.*/
				.setContent
				(
				 new Intent(this, VideosActivity.class)
				 .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
				));

		/****************************** TAB 4 ******************************/

		/*Invoke to add the tab in TabHost.*/
		th.addTab(th.newTabSpec("TAB_4")

				/*Invoke to set the image on the tab.*/
				.setIndicator("", getResources().getDrawable(R.drawable.speech_bubble))

				/* Invoke to set the content on clicking the tab.*/
				.setContent
				(
				 new Intent(this, ChatActivity.class)
				 .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
				));	
	}

	
	/***********************************************************************
	 * Function Name        : onCreateOptionsMenu()
	 * Description          : Invoke to create the items in menu
	 * Input parameters     : Menu
	 * output parameters    : 
	 * return values        : Create items in the Menu
	 ***********************************************************************/  

	@Override
		public boolean onCreateOptionsMenu(Menu menu) 
		{
			MenuInflater menuInflater = getMenuInflater();
			menuInflater.inflate(R.layout.menu, menu);
			return true;
		}



	/***********************************************************************
	 * Function Name        : onOptionsItemSelected()
	 * Description          : Invoke to create the events on selection of menuitem.
	 * Input parameters     : MenuItem
	 * output parameters    : 
	 * return values        : Specified event mention in the code.
	 ***********************************************************************/

	@Override 
		public boolean onOptionsItemSelected(MenuItem item)
		{
			switch (item.getItemId()) 
			{
				//Menu option bookmark
				case R.id.menu_bookmark: 
					Intent photointent=new Intent(getApplicationContext(),PhotosActivity.class);
					startActivity(photointent);
					return true;

					//Menu option save
				case R.id.menu_save:    
					Intent videointent=new Intent(getApplicationContext(),VideosActivity.class);
					startActivity(videointent);
					return true;

					//Menu option search
				case R.id.menu_search:  
					Intent songintent=new Intent(getApplicationContext(),SongsActivity.class);
					startActivity(songintent);
					return true;

					//Menu option Chat
				case R.id.chat:         
					Intent chatintent=new Intent(getApplicationContext(),ChatActivity.class);
					startActivity(chatintent);
					return true;

				default:
					return super.onOptionsItemSelected(item);
			}
		}    

	/*****************************************************************************************
	 * Function Name        : onResume()
	 * Description          : Invoke to hold activities in tabhost during switching of tabs .
	 * Input parameters     : None
	 * output parameters    : None
	 * return values        : None
	 *****************************************************************************************/

	@Override
		protected void onResume()
		{
			super.onResume();
			mlam.dispatchResume();
		}

	/*****************************************************************************************
	 * Function Name        : onPause()
	 * Description          : Invoke to hold activities in tabhost during switching of tabs .
	 * Input parameters     : None
	 * output parameters    : None
	 * return values        : None
	 *****************************************************************************************/

	@Override
		protected void onPause() 
		{
			super.onPause();
			mlam.dispatchPause(isFinishing());
		}
}
