package com.example.tabhost;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.inc247.ChatSDK;
import com.inc247.ChatSDKEventsListener;


public class ChatActivity extends Activity implements ChatSDKEventsListener 
{
	
	private static final String TAG=ChatActivity.class.getSimpleName();
	public Button btnChat;
	public TextView textStatus;

	/***********************************************************************
	 * Function Name        : onCreate()
	 * Description          : Invoke to create the Activity
	 * Input parameters     : None
	 * output parameters    : None
	 * return values        : None
	 ************************************************************************/

	@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.chat_layout);
			btnChat=(Button)findViewById(R.id.buttonChat);
			textStatus=(TextView)findViewById(R.id.textStatus);

			/*Invokes the method to create the ChatSDK object.*/
			final ChatSDK sdkObject=ChatSDK.initializeChat(this);
			sdkObject.setChatEventsListener(this);

			/*Invoke to check the availability of Agent.*/
			sdkObject.checkAgentAvailability();

			final JSONObject jsonObject=new JSONObject();        

			try 
			{
				jsonObject.put("username","value");
				jsonObject.put("email","value2");
				jsonObject.put("accountnumber","value3");

			}
			catch(Exception e)
			{
				Log.e(TAG,"Exception "+e);
			}

			/*Invoke the event on the click of the button.*/
			btnChat.setOnClickListener(new OnClickListener() 
					{
						@Override
				public void onClick(View v) 
			{
				/*Invoke to startchat*/
				sdkObject.startChat(jsonObject);
			}
			});
		}

	/***********************************************************************
	 * Function Name        : onChatStarted()
	 * Description          : Notifies application when chat has started.
	 * Input parameters     : JSONObject data
	 * output parameters    : None
	 * return values        : None
	 ***********************************************************************/ 

	@Override
		public void onChatStarted(JSONObject data) 
		{
			Log.e("OnChatStarted: JSON Data", ""+data);
			/* To be implemented further */
		}

	/***********************************************************************
	 * Function Name        : onAgentMessage()
	 * Description          : Notifies application when new agent message has been received in the chat.
	 * Input parameters     : JSONObject arg0
	 * output parameters    : None
	 * return values        : None
	 ***********************************************************************/ 

	@Override
		public void onAgentMessage(JSONObject arg0) 
		{ 
			System.out.println("Inside function OnAgentMessage");
			/* To be implemented further */
		}

	/***********************************************************************
	 * Function Name        : onChatAgentAvailabilty()
	 * Description          : Notifies the availability of agent for chat.
	 * Input parameters     : JSONObject data
	 * output parameters    : None
	 * return values        : None
	 ***********************************************************************/  

	@Override
		public void onChatAgentAvailability(JSONObject data) 
		{
			Log.e(TAG,"onChatAgentAvailability: JSON Data== "+data );        
			String agentStatusString=null;
			try 
			{
				agentStatusString = ((String)((JSONObject)(data.get("data"))).get("caStatus"));

			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}

			if(agentStatusString.equalsIgnoreCase("true")) 
			{
				/* agentStatusString is true i.e. Chat Agent is available*/

				/* btnChat (Chat Start Button) gets visible */
				btnChat.setVisibility(View.VISIBLE);

				/* Change the textStatus (No Agent Is Available) to invisible */
				textStatus.setVisibility(View.INVISIBLE);
			}

			else
			{
				/* agentStatusString is false i.e. Chat Agent is not available*/

				/* btnChat (Chat Start Button) gets invisible */
				btnChat.setVisibility(View.INVISIBLE);

				/* Change the textStatus (No Agent Is Available) to visible */
				textStatus.setText("No Agent Is Available");
				textStatus.setVisibility(View.VISIBLE);
			}
		}

	/***********************************************************************
	 * Function Name        : onChatEnded()
	 * Description          : Notifies application when chat is ended and the view has been closed.
	 * Input parameters     : JSONObject arg0
	 * output parameters    : None
	 * return values        : None
	 ***********************************************************************/

	@Override
		public void onChatEnded(JSONObject arg0) 
		{
			System.out.println("Inside function onChatEnded"); 
			/* To be implemented further */
		}

	/***********************************************************************
	 * Function Name        : onChatError()
	 * Description          : Notifies application developer when the error has occurred
	 * Input parameters     : JSONObject arg0
	 * output parameters    : None
	 * return values        : None
	 ***********************************************************************/  

	@Override
		public void onChatError(JSONObject arg0) 
		{
			System.out.println("Inside function OnChatError");    
			/* To be implemented further */
		}

	/***********************************************************************
	 * Function Name        : onChatMaximized()
	 * Description          : Notifies the application when the chatview has been maximized..
	 * Input parameters     : JSONObject arg0
	 * output parameters    : None
	 * return values        : None
	 ***********************************************************************/ 	

	@Override
		public void onChatMaximized(JSONObject arg0) 
		{
			System.out.println("Inside function onChatMaximized");        
			/* To be implemented further */
		}

	/***********************************************************************
	 * Function Name        : onChatMinimized()
	 * Description          : Notifies the application when the chatview has been minimized.
	 * Input parameters     : JSONObject arg0
	 * output parameters    : None
	 * return values        : None
	 ***********************************************************************/  

	@Override
		public void onChatMinimized(JSONObject arg0)
		{
			System.out.println("Inside function onChatMinimized");            
			/* To be implemented further */
		}
}
