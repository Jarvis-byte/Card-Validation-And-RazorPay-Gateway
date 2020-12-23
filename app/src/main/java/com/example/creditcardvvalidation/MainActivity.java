package com.example.creditcardvvalidation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    TextInputEditText inputtxtCardNumber, inputtxtExpirationDate, inputtxtSecurityCode, inputtxtFirstName, inputtxtLastName, inputtxtAmount;
    String CardNumber, Cvv, expiryDate, amount;
    int FirstDigit, SecondDigit;
    Button btnSubmit;
    InputMethodManager inputMethodManager1;
    Button btnPay;
    String TAG = "MainActivity Payment";

    private TextWatcher mDateEntryWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String working = s.toString();

            if (working.length() == 2 && before == 0) {

                working += "/";
                inputtxtExpirationDate.setText(working);
                inputtxtExpirationDate.setSelection(working.length());

            }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }


    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputtxtCardNumber = findViewById(R.id.inputtxtCardNumber);
        inputtxtExpirationDate = findViewById(R.id.inputtxtExpirationDate);
        inputtxtSecurityCode = findViewById(R.id.inputtxtSecurityCode);
        inputtxtFirstName = findViewById(R.id.inputtxtFirstName);
        inputtxtLastName = findViewById(R.id.inputtxtLastName);
        inputtxtAmount = findViewById(R.id.inputtxtAmount);
        inputtxtCardNumber.requestFocus();
        inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager1.showSoftInput(inputtxtCardNumber, inputMethodManager1.SHOW_IMPLICIT);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(inputtxtAmount.getText().toString())) {
                    inputtxtAmount.setError("This Field Can't be Empty!!!");
                    inputtxtAmount.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtAmount, inputMethodManager1.SHOW_IMPLICIT);
                }
                amount = inputtxtAmount.getText().toString().trim();
                int MainAmount = Math.round(Float.parseFloat(amount) * 100);
                Checkout checkout1 = new Checkout();
                checkout1.setKeyID("rzp_test_5HZySydZ73qHoN");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", "Arkoooo");
                    jsonObject.put("description", "Test Payment");
                    jsonObject.put("theme.color", "#0093DD");
                    jsonObject.put("currency", "INR");
                    jsonObject.put("amount", MainAmount);
                    checkout1.open(MainActivity.this, jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Checkout.preload(getApplicationContext());
        Date c = Calendar.getInstance().getTime();


        SimpleDateFormat dfMonth = new SimpleDateFormat("MM", Locale.getDefault());
        String formattedMonth = dfMonth.format(c);
        int todaysMonth = Integer.parseInt(formattedMonth);
        Log.i("Today's Month", formattedMonth);

        SimpleDateFormat dfYear = new SimpleDateFormat("yy", Locale.getDefault());
        String formattedYear = dfYear.format(c);
        int todaysYear = Integer.parseInt(formattedYear);
        Log.i("Today's Year", formattedYear);


        inputtxtExpirationDate.addTextChangedListener(mDateEntryWatcher);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                expiryDate = inputtxtExpirationDate.getText().toString().trim();
                String parts[] = expiryDate.split("/");

                String Month = parts[0];
                Log.i("Month", Month);
                int inputMonth = Integer.parseInt(Month);

                String Year = parts[1];
                Log.i("Year", Year);
                int inputYear = Integer.parseInt(Year);

                if (TextUtils.isEmpty(inputtxtCardNumber.getText().toString())) {
                    inputtxtCardNumber.setError("This Field Can't be Empty!!!");
                    inputtxtCardNumber.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtCardNumber, inputMethodManager1.SHOW_IMPLICIT);

                } else if (TextUtils.isEmpty(inputtxtExpirationDate.getText().toString())) {
                    inputtxtExpirationDate.setError("This Field Can't be Empty!!!");
                    inputtxtExpirationDate.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtExpirationDate, inputMethodManager1.SHOW_IMPLICIT);


                } else if (TextUtils.isEmpty(inputtxtSecurityCode.getText().toString())) {
                    inputtxtSecurityCode.setError("This Field Can't be Empty!!!");
                    inputtxtSecurityCode.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtSecurityCode, inputMethodManager1.SHOW_IMPLICIT);


                } else if (TextUtils.isEmpty(inputtxtFirstName.getText().toString())) {
                    inputtxtFirstName.setError("This Field Can't be Empty!!!");
                    inputtxtFirstName.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtFirstName, inputMethodManager1.SHOW_IMPLICIT);

                } else if (TextUtils.isEmpty(inputtxtLastName.getText().toString())) {
                    inputtxtLastName.setError("This Field Can't be Empty!!!");
                    inputtxtLastName.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtLastName, inputMethodManager1.SHOW_IMPLICIT);


                } else if ((inputMonth < todaysMonth && inputYear < todaysYear)) {
                    inputtxtExpirationDate.setError("This Card Has Expired");
                    inputtxtExpirationDate.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtExpirationDate, inputMethodManager1.SHOW_IMPLICIT);

                } else if (inputMonth < todaysMonth && inputYear == todaysYear) {
                    inputtxtExpirationDate.setError("This Card Has Expired");
                    inputtxtExpirationDate.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtExpirationDate, inputMethodManager1.SHOW_IMPLICIT);

                } else if (inputMonth > 13) {
                    inputtxtExpirationDate.setError("Enter Valid Month");
                    inputtxtExpirationDate.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtExpirationDate, inputMethodManager1.SHOW_IMPLICIT);
                } else {
                    Cvv = inputtxtSecurityCode.getText().toString().trim();
                    Log.i("Cvv Length", String.valueOf(Cvv.length()));
                    CardNumber = inputtxtCardNumber.getText().toString();
                    FirstDigit = inputtxtCardNumber.getText().toString().charAt(0) - '0';
                    SecondDigit = inputtxtCardNumber.getText().toString().charAt(1) - '0';

                    if (checkLuhn(CardNumber)) {
                        if (FirstDigit == 4 || FirstDigit == 5 || FirstDigit == 6) {
                            if (Cvv.length() == 3) {
                                Log.i("CVV", "Correct");
                                new AlertDialog.Builder(MainActivity.this)
                                        .setMessage("Payment Successful")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                inputtxtCardNumber.setText("");
                                                inputtxtExpirationDate.setText("");
                                                inputtxtSecurityCode.setText("");
                                                inputtxtFirstName.setText("");
                                                inputtxtLastName.setText("");
                                                inputtxtCardNumber.requestFocus();
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                                return;
                            } else {
                                inputtxtSecurityCode.setError("Incorrect CVV Number");
                                inputtxtSecurityCode.requestFocus();
                                inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                                inputMethodManager1.showSoftInput(inputtxtSecurityCode, inputMethodManager1.SHOW_IMPLICIT);
                                return;
                            }

                        } else if (FirstDigit == 3 && SecondDigit == 7) {
                            if (Cvv.length() == 4) {
                                Log.i("CVV", "American Card has Valid CVV");
                                new AlertDialog.Builder(MainActivity.this)
                                        .setMessage("Payment Successful!!!")
                                        .setPositiveButton("OK", null)
                                        .show();
                                return;
                            } else {
                                inputtxtSecurityCode.setError("Incorrect CVV Number");
                                inputtxtSecurityCode.requestFocus();
                                inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                                inputMethodManager1.showSoftInput(inputtxtSecurityCode, inputMethodManager1.SHOW_IMPLICIT);
                                Log.i("CVV", "InCorrect for American Express");
                                return;
                            }

                        }
                    } else

                        inputtxtCardNumber.setError("Invalid Card Number");
                    inputtxtCardNumber.requestFocus();
                    inputMethodManager1 = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager1.showSoftInput(inputtxtCardNumber, inputMethodManager1.SHOW_IMPLICIT);
                }
            }
        });

    }

    boolean checkLuhn(String cardNo) {
        int Digits = cardNo.length();

        int Sum = 0;
        boolean isSecond = false;

        for (int i = Digits - 1; i >= 0; i--) {

            int d = cardNo.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;
            Sum += d / 10;
            Sum += d % 10;

            isSecond = !isSecond;
        }
        return (Sum % 10 == 0);
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Error" + s, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnPaymentError" + s);

    }

}