package com.example.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.festafimdeano.R;
import com.example.festafimdeano.constants.FimDeAnoConstants;
import com.example.festafimdeano.util.SecurityPreferences;

import java.security.Security;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.checkParticipate = (CheckBox) findViewById(R.id.check_participate);

        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.check_participate) {
            if (this.mViewHolder.checkParticipate.isChecked()) {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.Presence, FimDeAnoConstants.Confirmed_Will_Go);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.Presence, FimDeAnoConstants.Confirmed_Wont_Go);
            }

        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String precense = extras.getString(FimDeAnoConstants.Presence);
            if (precense.equals(FimDeAnoConstants.Confirmed_Will_Go)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkParticipate;
    }
}
