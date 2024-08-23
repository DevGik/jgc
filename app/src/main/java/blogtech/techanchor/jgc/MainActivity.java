package blogtech.techanchor.jgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public MediaPlayer backgroundmscc;

    Animation scaleUp, scaleDown;

    ImageView ivfacebook, ivwhatsapp, imageViewShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick);

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        ivfacebook = findViewById(R.id.iv_facebook);
        ivwhatsapp = findViewById(R.id.iv_whatsapp);
        imageViewShare = findViewById(R.id.shareImageView);



        ivfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profileUrl = "https://www.facebook.com/joshua.aoc.3?mibextid=ZbWKwL";
                openLink(profileUrl);
                mediaPlayer.start();
            }
        });

        ivwhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupInviteLink = "https://chat.whatsapp.com/FT47FmJYNEUEx4qb2NbWGu";
                openWhatsAppGroup(groupInviteLink);
                mediaPlayer.start();
            }
        });





        imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewShare(MainActivity.this);
                mediaPlayer.start();

            }
        });


        backgroundmscc = MediaPlayer.create(MainActivity.this, R.raw.rccg_anthem);
        backgroundmscc.setLooping(true);
        backgroundmscc.start();

        imageViewShare.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                    imageViewShare.startAnimation(scaleUp);

                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    imageViewShare.startAnimation(scaleDown);
                }

                return false;
            }
        });

        ivfacebook.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                    ivfacebook.startAnimation(scaleUp);

                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    ivfacebook.startAnimation(scaleDown);
                }

                return false;
            }
        });

        ivwhatsapp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                    ivwhatsapp.startAnimation(scaleUp);

                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    ivwhatsapp.startAnimation(scaleDown);
                }

                return false;
            }
        });


    }

    private void openWhatsAppGroup(String groupInviteLink) {
        try {
            // Create the Intent with the WhatsApp group invite link
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(groupInviteLink));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // If WhatsApp is not installed, display an error message
            Toast.makeText(this, "WhatsApp is not installed", Toast.LENGTH_SHORT).show();
        }
    }


    private void openLink(String profileUrl) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + profileUrl));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // If the Facebook app is not installed, open the link in a web browser
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + profileUrl));
            startActivity(webIntent);
        }
    }






    private void imageViewShare (Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Download the JGC app to stay connected with our church activities: " + "https://play.google.com/store/apps/details?id=blogtech.techanchor.jgc");
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundmscc.release();
    }
}