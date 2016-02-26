package com.bill.shoppinglist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	TextView tvItems;
	Button bAdd;
	MediaPlayer soundOnClick;
	ArrayList<Item> mItems = new ArrayList<Item>();// Test thing
	private InformationShare sharer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sharer = new InformationShare("write");
		intialize(); // Fill our objects.
		mItems = sharer.getAllItems();
		String tempStringtemp = "";

		for (Item a : mItems) {

			tempStringtemp += a.toString() + "\n\n\n";
		}

		tvItems.setText(tempStringtemp);

		soundOnClick = MediaPlayer.create(MainActivity.this, R.raw.onclick);// Sound
																			// on
																			// click
	}

	private void intialize() {
		// TODO Fill vars
		bAdd = (Button) findViewById(R.id.bAddItem);
		tvItems = (TextView) findViewById(R.id.tvItems);

		bAdd.setOnClickListener(this); // Set on click action for bAdd
										// ("Add item").

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO What to do on click.
		soundOnClick.start();

		startActivityForResult(new Intent(MainActivity.this,
				AddItemActivity.class), 0);
		// Transfer to AddItemAcitivty class.
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {

			Bundle extras = data.getExtras();
			// mItems.add(new Item(extras.getString("name"), new Level(extras
			// .getInt("priority")))); // Take the name and
			// // levelPriority of
			// AddItem Activity
			String itemName = extras.getString("name");
			itemName = itemName.substring(0, 1).toUpperCase()+
						itemName.substring(1,itemName.length());//UpperCase the first letter
					
			sharer.addItem(itemName,
					Integer.toString(extras.getInt("priority")));

			mItems = sharer.getAllItems();
			String tempString = "";

			for (Item a : mItems) {

				tempString += a.toString() + "\n\n\n";
			}

			tvItems.setText(tempString);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		sharer.finishDb();
	}

}
