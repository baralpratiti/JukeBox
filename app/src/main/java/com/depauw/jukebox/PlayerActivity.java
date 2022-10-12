package com.depauw.jukebox;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.graphics.Color;

import com.depauw.jukebox.databinding.ActivityPlayerBinding;


public class PlayerActivity extends AppCompatActivity {


    private ActivityPlayerBinding binding;
    private int red;
    private int green;
    private int blue;
    private int myColor;
    private MediaPlayer myPlayer = new MediaPlayer();



    private SeekBar.OnSeekBarChangeListener seekBar_red_changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            String redText = String.valueOf(i);
            binding.textviewRed.setText(redText);
            red = i;
            myColor = Color.rgb(red,green,blue);
            binding.constraintlayout.setBackgroundColor(myColor);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener seekBar_green_changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            String greenText = String.valueOf(i);
            binding.textviewGreen.setText(greenText);
            green = i;
            int myColor = Color.rgb(red,green,blue);
            binding.constraintlayout.setBackgroundColor(myColor);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener seekBar_blue_changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            String blueText = String.valueOf(i);
            binding.textviewBlue.setText(blueText);
            blue = i;
            int myColor = Color.rgb(red,green,blue);
            binding.constraintlayout.setBackgroundColor(myColor);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    //part 2
    private RadioGroup.OnCheckedChangeListener radiogroup_songs_CheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            binding.seekbarSongPosition.setProgress(0);
            if(binding.radioSong1.isChecked()) {
                if (myPlayer.isPlaying())
                {
                    myPlayer.stop();
                }
                myPlayer = MediaPlayer.create(PlayerActivity.this, R.raw.track1);
                myPlayer.start();
                play(R.raw.track1,getResources().getDrawable(R.drawable.track1));
            }

            else if(binding.radioSong2.isChecked()) {
                if (myPlayer.isPlaying())
                {
                            myPlayer.stop();
                }
                myPlayer = MediaPlayer.create(PlayerActivity.this, R.raw.track2);
                myPlayer.start();
                play(R.raw.track2,getResources().getDrawable(R.drawable.track2));
            }
            else {
                if (myPlayer.isPlaying())
                {
                    myPlayer.stop();
                }
                myPlayer = MediaPlayer.create(PlayerActivity.this, R.raw.track3);
                myPlayer.start();
                play(R.raw.track3,getResources().getDrawable(R.drawable.track3));
            }
        }
    };

    public void play(@RawRes int sound, Drawable drawable){

        binding.imageviewAlbumCover.setImageDrawable(drawable);
    }
    

    private SeekBar.OnSeekBarChangeListener seekbar_song_position_changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                int msec = myPlayer.getDuration() * i/100;
                myPlayer.seekTo(msec);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    //part 3
    int votes1 = 0;
    int votes2 = 0;
    int votes3 = 0;
    int rating1 = 0;
    int rating2 = 0;
    int rating3 = 0;
    int averageRating = 0;
    private View.OnClickListener button_cast_vote_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(binding.radioSong1.isChecked())
            {
                votes1 = votes1 + 1;
                rating1 = rating1 + (int)binding.ratingbarVoterRating.getRating();
                binding.textviewNumVotes1.setText(String.valueOf(votes1));
                averageRating = rating1 / votes1;
                binding.progressbarAverageRating1.setProgress(averageRating);
                binding.ratingbarVoterRating.setRating(0);

            }
            else if(binding.radioSong2.isChecked())
            {
                votes2 = votes2 + 1;
                rating2 = rating2 + (int)binding.ratingbarVoterRating.getRating();
                binding.textviewNumVotes2.setText(String.valueOf(votes2));
                averageRating = rating2 / votes2;
                binding.progressbarAverageRating2.setProgress(averageRating);
                binding.ratingbarVoterRating.setRating(0);
            }
            else
            {
                votes3 = votes3 + 1;
                rating3 = rating3 + (int)binding.ratingbarVoterRating.getRating();
                binding.textviewNumVotes3.setText(String.valueOf(votes3));
                averageRating = rating3 / votes3;
                binding.progressbarAverageRating3.setProgress(averageRating);
                binding.ratingbarVoterRating.setRating(0);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setting the background color
        binding.seekbarRed.setOnSeekBarChangeListener(seekBar_red_changeListener);
        binding.seekbarGreen.setOnSeekBarChangeListener(seekBar_green_changeListener);
        binding.seekbarBlue.setOnSeekBarChangeListener(seekBar_blue_changeListener);

        //playing song
        binding.radiogroupSongs.setOnCheckedChangeListener(radiogroup_songs_CheckedChangeListener);

        if(binding.radioSong1.isChecked())
        {
            myPlayer = MediaPlayer.create(PlayerActivity.this, R.raw.track1);
            myPlayer.start();
            play(R.raw.track1,getResources().getDrawable(R.drawable.track1));
        }
        else if(binding.radioSong2.isChecked())
        {
            myPlayer = MediaPlayer.create(PlayerActivity.this, R.raw.track2);
            myPlayer.start();
            play(R.raw.track2,getResources().getDrawable(R.drawable.track2));
        }
        else
        {
            myPlayer = MediaPlayer.create(PlayerActivity.this, R.raw.track3);
            myPlayer.start();
            play(R.raw.track3,getResources().getDrawable(R.drawable.track3));
        }

        binding.seekbarSongPosition.setOnSeekBarChangeListener(seekbar_song_position_changeListener);
        binding.buttonCastVote.setOnClickListener(button_cast_vote_clickListener);

    }


}