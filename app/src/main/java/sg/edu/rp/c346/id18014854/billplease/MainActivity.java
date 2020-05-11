package sg.edu.rp.c346.id18014854.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Step 1: Declare the field variable
    EditText etAmount;
    EditText etPax;
    ToggleButton tbsvs;
    ToggleButton tbgst;
    EditText etDiscount;
    TextView tvBill;
    TextView tvEachPays;
    Button btnSplit;
    Button btnReset;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: Link the field variables to UI components in layout
        etAmount = findViewById(R.id.editTextAmount);
        etPax = findViewById(R.id.editTextPax);
        tbsvs = findViewById(R.id.toggleButtonSVS);
        tbgst = findViewById(R.id.toggleButtonGST);
        etDiscount = findViewById(R.id.editTextDiscount);
        tvBill = findViewById(R.id.textViewDisplayBill);
        tvEachPays = findViewById(R.id.textViewDisplayEachPay);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);
        tvMessage = findViewById(R.id.textViewMessage);

        btnSplit.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                double svs = 0.0;
                double gst = 0.0;

                if (tbsvs.isChecked()) {
                    svs = 0.10;
                }
                if (tbgst.isChecked()) {
                    gst = 0.07;
                }
                double total = 1 + svs + gst; // percentage of total
                double disc = 0.0;
                if(etDiscount.getText().toString().isEmpty()){
                    disc = 1.0;
                } else {
                    disc = 1.0 - (Double.parseDouble(String.valueOf(etDiscount.getText()))/100);
                }

                int amount = Integer.parseInt(String.valueOf(etAmount.getText()));
                int pax = Integer.parseInt(String.valueOf(etPax.getText()));
                double finalBill = amount * total * disc;

                if(etAmount.getText().toString().isEmpty() || etPax.getText().toString().isEmpty()){
                    tvMessage.setText("Error: Input is Empty");
                } else if(Integer.parseInt(String.valueOf(etPax.getText())) < 1){
                    tvMessage.setText("Error: No of Pax less than 1, Pax has to be more than 0");
                } else {
                    tvBill.setText(String.format("Total Bill: $%.2f",(finalBill)));
                    tvEachPays.setText(String.format("Each Pays: $%.2f",(finalBill/pax)));
                }

            };

        });

        btnReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                etAmount.setText("");
                etPax.setText("");
                etDiscount.setText("");
                tvBill.setText("");
                tvEachPays.setText("");

                tbsvs.setChecked(false);
                tbgst.setChecked(false);
            }
        });

    }
}
