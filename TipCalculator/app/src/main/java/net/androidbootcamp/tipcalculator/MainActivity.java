package net.androidbootcamp.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //declare variables
    double billamount , tip , total;
    int percentage;
    //creates format
    final DecimalFormat money = new DecimalFormat("$#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billamount =0.00;
        tip = 0.00;
        total=0.00;
        percentage= 0;
        //declare button listener in case someone tries to use 0% tip
        Button calculate = (Button) findViewById(R.id.btnCalculate);
            calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText inputAmount = (EditText) findViewById(R.id.txtAmount);
                    TextView outputTotal = (TextView) findViewById(R.id.txtTotal);
                    String strbill = inputAmount.getText().toString();
                    billamount = Double.parseDouble(strbill);
                    outputTotal.setText(money.format(billamount));
                }
            });
        //seek bar reference
        SeekBar tipSeek = (SeekBar) findViewById(R.id.seekTip);
        //creates SeekBarChangeListener
        tipSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    TextView outputPercentage = (TextView) findViewById(R.id.txtTipPecent);
                    percentage = 5 * i;
                    String percentageText=percentage + "%";
                    outputPercentage.setText(percentageText);
                    //button reference
                    Button calculate = (Button) findViewById(R.id.btnCalculate);
                    //declare listener
                    calculate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //create references for outputs
                            EditText inputAmount = (EditText) findViewById(R.id.txtAmount);
                            TextView outputTip = (TextView) findViewById(R.id.txtTip);
                            TextView outputTotal = (TextView) findViewById(R.id.txtTotal);
                            //gets the bill amount value
                            String strbill = inputAmount.getText().toString();
                            billamount = Double.parseDouble(strbill);
                            //calculate output values
                            tip = (billamount * percentage) / 100;
                            total = billamount + tip;
                            //displays output values
                            outputTip.setText(money.format(tip));
                            outputTotal.setText(money.format(total));
                        }
                    });
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

    }




}
