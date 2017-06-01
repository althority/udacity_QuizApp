package com.example.althority.aq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int correctAnswers;
    Button submitBtn;
    Button startOverBtn;

    /**
     * This method is called when the Submit button is tapped.
     */
    final View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {

        public void onClick(final View v) {

            //Hide soft keyboard when the user taps the Submit btn
            InputMethodManager inputManager = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    (null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            //Calculate the Quiz results
            calculateQuizResults();

            //Show a Toast
            String toastString = "";

            if (correctAnswers == 0) {
                toastString = getString(R.string.oopsToast1) + getString(R.string.oopsToast2);

            } else if (correctAnswers == 7) {
                toastString = getString(R.string.wowToast1) + getString(R.string.wowToast2);

            } else if (correctAnswers == 1) {
                toastString = getString(R.string.toast1) + getString(R.string.toast2) + " "
                        + correctAnswers + " " + getString(R.string.toast3ifcorrectAnswersIsOne);
            } else {
                toastString = getString(R.string.toast1) + getString(R.string.toast2) + " "
                        + correctAnswers + " " + getString(R.string.toast3);
            }

            Toast toast = Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


            //Avoid double submission
            correctAnswers = 0;

        }
    };

    /**
     * This method is called when the Start Over button is tapped.
     */
    private View.OnClickListener startOverBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prevents showing soft keyboard on resume
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        TextView intro = (TextView) findViewById(R.id.introTextView);
        intro.setTypeface(null, Typeface.BOLD);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        startOverBtn = (Button) findViewById(R.id.startOverBtn);

        submitBtn.setOnClickListener(submitBtnOnClickListener);
        startOverBtn.setOnClickListener(startOverBtnOnClickListener);
    }

    private void checkQuestion1() {
        RadioButton q1_answer2 = (RadioButton) findViewById(R.id.q1_answer2);
        boolean q1_answer2IsChecked = q1_answer2.isChecked();
        if (q1_answer2IsChecked) {
            correctAnswers++;
        }

    }

    private void checkQuestion2() {
        CheckBox q2_answer1 = (CheckBox) findViewById(R.id.q2_answer1);
        CheckBox q2_answer2 = (CheckBox) findViewById(R.id.q2_answer2);
        CheckBox q2_answer3 = (CheckBox) findViewById(R.id.q2_answer3);
        CheckBox q2_answer4 = (CheckBox) findViewById(R.id.q2_answer4);
        boolean q2_answer1IsChecked = q2_answer1.isChecked();
        boolean q2_answer2IsChecked = q2_answer2.isChecked();
        boolean q2_answer3IsChecked = q2_answer3.isChecked();
        boolean q2_answer4IsChecked = q2_answer4.isChecked();

        if (q2_answer1IsChecked && q2_answer2IsChecked && q2_answer3IsChecked
                && !q2_answer4IsChecked) {
            correctAnswers++;
        }

    }

    private void checkQuestion3() {
        CheckBox q3_answer1 = (CheckBox) findViewById(R.id.q3_answer1);
        CheckBox q3_answer2 = (CheckBox) findViewById(R.id.q3_answer2);
        CheckBox q3_answer3 = (CheckBox) findViewById(R.id.q3_answer3);
        CheckBox q3_answer4 = (CheckBox) findViewById(R.id.q3_answer4);
        boolean q3_answer1IsChecked = q3_answer1.isChecked();
        boolean q3_answer2IsChecked = q3_answer2.isChecked();
        boolean q3_answer3IsChecked = q3_answer3.isChecked();
        boolean q3_answer4IsChecked = q3_answer4.isChecked();

        if (!q3_answer1IsChecked && q3_answer2IsChecked && q3_answer3IsChecked
                && q3_answer4IsChecked) {
            correctAnswers++;
        }
    }

    private void checkQuestion4() {
        RadioButton q4_answer4 = (RadioButton) findViewById(R.id.q4_answer4);
        boolean q4_answer4IsChecked = q4_answer4.isChecked();
        if (q4_answer4IsChecked) {
            correctAnswers++;
        }

    }

    private void checkQuestion5() {
        RadioButton q5_answer2 = (RadioButton) findViewById(R.id.q5_answer2);
        boolean q5_answer2IsChecked = q5_answer2.isChecked();
        if (q5_answer2IsChecked) {
            correctAnswers++;
        }

    }

    private void checkQuestion6() {
        RadioButton q6_answer1 = (RadioButton) findViewById(R.id.q6_answer1);
        boolean q6_answer1IsChecked = q6_answer1.isChecked();
        if (q6_answer1IsChecked) {
            correctAnswers++;
        }

    }

    private void checkQuestion7() {
        EditText q7_editTextBox = (EditText) findViewById(R.id.q7_editTextBox);
        String q7_answer = q7_editTextBox.getText().toString();
        String q7_correctAnswer = getString(R.string.q7_correctAnswer);
        if (q7_answer.equals(q7_correctAnswer)) {
            correctAnswers++;
        }


    }

    private void calculateQuizResults() {
        checkQuestion1();
        checkQuestion2();
        checkQuestion3();
        checkQuestion4();
        checkQuestion5();
        checkQuestion6();
        checkQuestion7();
    }


}
