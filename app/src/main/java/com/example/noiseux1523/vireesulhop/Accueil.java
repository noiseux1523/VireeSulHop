package com.example.noiseux1523.vireesulhop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

public class Accueil extends Activity {

    private Thread mAccueilThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        final Accueil accueil = this;

        mAccueilThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(2000);
                    }
                }
                catch(InterruptedException ex){
                }
                finish();

                Intent intent = new Intent();
                intent.setClass(accueil, Menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };

        mAccueilThread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mAccueilThread){
                mAccueilThread.notifyAll();
            }
        }
        return true;
    }

}
