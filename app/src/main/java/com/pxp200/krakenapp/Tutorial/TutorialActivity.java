package com.pxp200.krakenapp.Tutorial;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pxp200.krakenapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorialActivity extends AppCompatActivity {

    MediaPlayer musicPlayer;
    int soundVolume = 50;

    String[] dialog = {"Let's begin with some basics this world is built on trading within your community. Unlike most games you are only able to trade with those nearby.",
            "You can also go and harvest some food, mining, or go cut some wood!",
    "Try your skill and go on an adventure! go try and slay some goblins? Monster's in this world when killed drop gems you turn into the guild for gold that you then use to stock back up for your next journey!",
    ""};
    int counter = 0;

    @BindView(R.id.tutorial_text)
    TextView tutorialText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);


        musicPlayer = MediaPlayer.create(this, R.raw.flyawayinstrument);
        musicPlayer.setVolume(100, soundVolume);
        musicPlayer.start();
        musicPlayer.setLooping(true);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.tutorial_root)
    public void handleClick() {
        tutorialText.setText(dialog[counter]);
        counter++;
        if(counter >= dialog.length){
            musicPlayer.stop();
            finish();
        }
    }
}
