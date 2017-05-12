package com.rafasalas.rlib;

/**
 * Created by salas on 29/09/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import com.rafasalas.*;
import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.composites.Mandala;
import com.rafasalas.rafalib.composites.cloud;
import com.rafasalas.rafalib.composites.red;
import com.rafasalas.rafalib.composites.sistema;
import com.rafasalas.rafalib.particles.particulasimple;

import java.util.Random;

import processing.core.PVector;

import static android.content.ContentValues.TAG;

public class pruebaView extends View{
    //

    Atractor hole, lateral1, lateral2, lateral3,lateral4;
    //Mandala mandy;
    PVector centro, part;
    sistema sistem, sistem2;
    // red redecilla;
    //particulasimple particle;

    public float intensity;


    public pruebaView (Context context){
        super(context);
        int width=context.getResources().getDisplayMetrics().widthPixels;
        int height=context.getResources().getDisplayMetrics().heightPixels;
        part=new PVector(10,-5);
        centro=new PVector(500, 500);
        //redecilla=new red(-500,-100,10,10,180,150,true, 100,context);
        //mandy=new Mandala(centro, 25,10,10,10,15,10, true, (float).009, true,(float).001, width, height,context);
        //particle=new particulasimple(part, 10, true, 0 , 40, 18, context);
        // particle.carga_dibujo ("tree2","drawable", "com.rafasalas.rlib");
        //redecilla.carga_dibujo ("geo","drawable", "com.rafasalas.rlib");
        //redecilla.rozamiento((float)0.0015);
        //redecilla.muelle((float)0.0075);
        //redecilla.invertir_masa();
        // redecilla. tresbolillo=true;
        //redecilla.masa_fija(10);
        intensity=-1;
        sistem=new cloud(context, 110);
        sistem.isboxed(width, height);
        //sistem2=new cloud(context, 80);
        //sistem2.isboxed(width, height);
        hole=new Atractor(1);
        lateral1=new Atractor(1);
        lateral2=new Atractor(1);
        lateral3=new Atractor(1);
        lateral4=new Atractor(1);
        hole.sentido=-(float)1;
        //hole.rotador("levo",10, -100 );
        hole.posicion.x=width/2;
        hole.posicion.y=height/2;
        lateral1.posicion=new PVector(width/2, height/8);
        lateral2.posicion=new PVector(width/8, height/2);
        lateral3.posicion=new PVector(7*(width/8), height/2);
        lateral4.posicion=new PVector((width/2), 7*(height/8));
        //redecilla.colorize_dibujo(200, 255,0,255);

    }
    @Override

    protected void onDraw(Canvas canvas){

        canvas.drawColor(0xFFFFFFFF);
        //Random rnd=new Random();
        //hole.sentido=-rnd.nextFloat()-1;

        //redecilla.mostrar_dibujo(canvas, 150);
        // particle.mostrar_dibujo(canvas,1);
        //hole.sentido=intensity;
        sistem.acelera_particulas(hole);
        //sistem2.acelera_particulas(hole);
        sistem.acelera_particulas(lateral1);
        sistem.acelera_particulas(lateral2);
        sistem.acelera_particulas(lateral3);
        sistem.acelera_particulas(lateral4);
        sistem.actualiza_particula();
        sistem.dibujaparticulas(canvas);
        //sistem2.actualiza_particula();
        //sistem2.dibujaparticulas(canvas);
        update();
        invalidate();


    }

    protected void update(){
        //redecilla.acelerar(centro);
        // particle.actualizar();


    }

}
