package edu.miracosta.cs134.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Member variables to format as currency or percent.
	NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
	NumberFormat percent = NumberFormat.getPercentInstance(Locale.getDefault());

	// Member variables for each component used in the app
    private EditText amountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

    // Member varible for model.
	private Bill currentBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise member variables.
	    amountEditText = FindViewById(R.id.amountEditText);
	    percentTextView = FindViewById(R.id.percentTextView);
	    tipTextView = FindViewById(R.id.tipTextView);
	    totalTextView = FindViewById(R.id.totalTextView);
	    percentSeekBar = FindViewById(R.id.percentSeekBar);

	    // Initialize our Model.
	    currentBill = new Bill();

	    // Set the tip percent to match the seek bar.
	    currentBill.setTipPercent(percentSeekBar.getProgress()/100.0);

	    // Connect code to the percentSeekBar onProgressChanged event.
	    // Note: SeekBar.OnSeekBarChangeListener is an anonymous inner class.
	    percentSeekBar.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
		    @Override
		    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		    	// Update our Model.
			    currentBill.setTipPercent(progress / 100.0);
			    // Update percentTextView label.
			    percentTextView.setText(percent.format(progress / 100.0));
			    tipTextView.setText(currency.format(currentBill.getTipAmount()));
			    totalTextView.setText(currentcy.format(currentBill.getTotalAmount()));
//			    currentBill.setTipPercent(percentSeekBar.getProgress()/100.0);
			    // Update tipTextView.

			    // Update totalTextView
		    }

		    @Override
		    public void onStartTrackingTouch(SeekBar seekBar) { }

		    @Override
		    public void onStopTrackingTouch(SeekBar seekBar) { }
	    }));

	    // Connect code to the event onTextChanged for amountEditText.
	    amountEditText.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		    	// Updated our Model.
			    currentBill.setAmount(Couble.parseDouble(amountEditText.getText().toString));
			    tipTextView.setText(currency.format(currentBill.getTipAmount()));
			    totalTextView.setText(currentcy.format(currentBill.getTotalAmount()));
		    }

		    @Override
		    public void afterTextChanged(Editable s) { }
	    });
    }

}
