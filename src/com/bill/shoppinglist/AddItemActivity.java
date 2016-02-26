package com.bill.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class AddItemActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	// The stuff from xml... Button,texts,etc!
	Button bAddIt, bCancel;
	EditText etName;
	RadioGroup rgPriority;
	RadioButton rbLvl1, rbLvl2, rbLvl3;
	//
	MediaPlayer soundOnClick; // Just a sound when the user clicks a button
	int level = 2;// The priority of each item instance.
	// 2 is because the radio button 2 is checked
	static int counter = 0;// To know how much instances of Item we have

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additem);
		intialize();
		soundOnClick = MediaPlayer.create(AddItemActivity.this, R.raw.onclick);

	}

	private void intialize() {
		// TODO Auto-generated method stub
		bAddIt = (Button) findViewById(R.id.bAddIt);
		bCancel = (Button) findViewById(R.id.bCancel);

		rgPriority = (RadioGroup) findViewById(R.id.rgPriority);
		rbLvl1 = (RadioButton) findViewById(R.id.rbLevel1);
		rbLvl2 = (RadioButton) findViewById(R.id.rbLevel2);
		rbLvl3 = (RadioButton) findViewById(R.id.rbLevel3);

		etName = (EditText) findViewById(R.id.etNameOfItem);

		// Set on click
		bAddIt.setOnClickListener(this);
		bCancel.setOnClickListener(this);

		// Set on checked
		rgPriority.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(RadioGroup rg, int v) {
		// TODO Auto-generated method stub
		switch (v) {

		case R.id.rbLevel1:

			level = 1;

			break;
		case R.id.rbLevel2:

			level = 2;

			break;
		case R.id.rbLevel3:

			level = 3;

			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bAddIt:
			// Display an error if the user doesn't give a name!
			soundOnClick.start();
			if (etName.getText().toString().equals("")
					|| etName.getText().toString() == null)
				startActivity(new Intent(AddItemActivity.this,
						NotNameFound.class));
			else {
				Intent i = new Intent(AddItemActivity.this, FirstActivity.class);
				i.putExtra("name", etName.getText().toString());// Take the name
																// of the item.
				i.putExtra("priority", level);// The priority level
				i.putExtra("counter", counter);// The counter
				setResult(RESULT_OK, i);// Set the result is OK and give the
										// intend .
				counter++;
				finish();
			}

			break;

		case R.id.bCancel:
			soundOnClick.start();
			// Finish the addItemActivity and go back.
			finish();

		}

	}

}
