package com.example.apphome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView detailGameName,detailGameClassification ,detailGameDeveloper,detailDownloadButton,detailGameDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailGameName = findViewById(R.id.detailGameName);
        detailGameClassification = findViewById(R.id.detailClassification);
        detailGameDeveloper = findViewById(R.id.detailCompanyName);
        detailDownloadButton = findViewById(R.id.detailLink);
        detailGameDescription = findViewById(R.id.detailDescription);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailGameName.setText(bundle.getString("GameName"));
            detailGameClassification.setText(bundle.getString("GameCassification"));
            detailGameDeveloper.setText(bundle.getString("gameDeveloper"));
            detailDownloadButton.setText(bundle.getString("DownloadButtom"));
            detailGameDescription.setText(bundle.getString("GameDescription"));
        }
    }
}