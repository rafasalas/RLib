package com.rafasalas.rlib;

/**
 * Created by salas on 29/09/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.rafasalas.*;
import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.composites.Mandala;
import com.rafasalas.rafalib.composites.red;
import com.rafasalas.rafalib.composites.sistema;
import com.rafasalas.rafalib.particles.particulasimple;
import processing.core.PVector;

public class test extends View{
    //

                Atractor hole;
                //Mandala mandy;
                PVector centro, part;
    sistema sistem;
               // red redecilla;
    //particulasimple particle;




    public test (Context context){
        super(context);
        int width=getWidth();
        int height=getHeight();
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
        sistem=new sistema(context);
       hole=new Atractor(1);
        hole.sentido=-(float).05;
        hole.rotador("levo",150, 20 );
        hole.posicion.x=500;
       hole.posicion.y=500;
        //redecilla.colorize_dibujo(200, 255,0,255);

    }
    @Override

    protected void onDraw(Canvas canvas){

        canvas.drawColor(0xFF000000);
        sistem.otraparticula();
        //redecilla.mostrar_dibujo(canvas, 150);
       // particle.mostrar_dibujo(canvas,1);
        sistem.acelera_particulas(hole);
        sistem.actualiza_particula();
        sistem.dibujaparticulas(canvas);

        update();
        invalidate();


    }

    protected void update(){
       // redecilla.acelerar(centro);
       // particle.actualizar();


    }

}
