package com.example.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.festafimdeano.R;
import com.example.festafimdeano.constants.FimDeAnoConstants;
import com.example.festafimdeano.util.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mViewHolder.textToDay = (TextView) findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = (TextView) findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = (Button) findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);
        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.textToDay.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDayLeftToEndOfYear()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);
        this.verifyPreference();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_confirm) {

            String preference = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.Presence);
            Intent intent = new Intent(this, DetailsActivity.class);

            intent.putExtra(FimDeAnoConstants.Presence, preference);

            startActivity(intent);
        }
    }

    private int getDayLeftToEndOfYear() {
        Calendar calendarToDay = Calendar.getInstance();
        int today = calendarToDay.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int dayDecember31 = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayDecember31 - today;
    }

    private void verifyPreference() {
        String preference = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.Presence);
        if (preference.equals("")) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        } else if (preference.equals(FimDeAnoConstants.Confirmed_Will_Go)) {
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        } else {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }

    private static class ViewHolder {
        TextView textToDay;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
