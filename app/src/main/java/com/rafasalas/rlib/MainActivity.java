package com.rafasalas.rlib;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class MainActivity extends Activity {
    private Visualizer mVisualizer;
    pruebaView prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Context ctx = getApplicationContext();
        prueba=new pruebaView(ctx);
        init();
        setContentView(prueba);
    }
    private void init(){
      /*  mPlayer = MediaPlayer.create(this, R.raw.dummy_01);
        mPlayer.setLooping(true);
        mPlayer.start();
        link(mPlayer);

    }
    public void link(MediaPlayer player)
    {
        if(player == null)
        {
            throw new NullPointerException("Cannot link to null MediaPlayer");
        }

*/
        mVisualizer = new Visualizer(0);
        mVisualizer.setEnabled(false);
        // mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        mVisualizer.setCaptureSize(128);

        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener()
        {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                              int samplingRate)
            { float sum=0;
                //Log.v("wave_form", " " +bytes.length+" "+bytes[0]+" "+bytes[60]+" "+bytes[125]);
                for (int i = 0; i < bytes.length; i++) {
                    sum=sum+(float)bytes[i];
                }
                //if (sum<-16383){sum=1;}else{sum=sum/1000;}
                if (sum<-12000 || sum>12000){sum=1;}else{sum=sum/1000;}
                prueba.hole.sentido=-1-sum;
                prueba.lateral1.sentido=-0.5f*sum;
                prueba.lateral2.sentido=-0.5f*sum;
                prueba.lateral3.sentido=-0.5f*sum;
                prueba.lateral4.sentido=-0.5f*sum;


                Log.v("sumatorio"," "+sum);
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes,
                                         int samplingRate)
            {
                Log.v("fft", " " + bytes.length+bytes[0] + " "+bytes[60] + " " + bytes[125]);
            }
        };

        mVisualizer.setDataCaptureListener(captureListener,
                Visualizer.getMaxCaptureRate() / 2, true, true);

        // Enabled Visualizer and disable when we're done with the stream

        mVisualizer.setEnabled(true);
      /*  player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mVisualizer.setEnabled(false);
                mVisualizer.release();
            }
        });*/
    }


    public void release()
    {
        mVisualizer.release();
    }

}
