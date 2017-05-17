package com.rafasalas.rafalib.composites;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.rafasalas.rafalib.atractors.Atractor;
import com.rafasalas.rafalib.particles.Mat_point;
import com.rafasalas.rafalib.particles.particulasimple;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;

/**
 * Created by salas on 17/05/2017.
 */

public class bezierChain {
    int nodes, radius_link;
    float masainicial;
    ArrayList<Mat_point> links;
    float[] masses;
    PVector velocidadinicial;
    PVector origen;
    int limX, limY;
    PVector browniano;
    boolean esbrowniano;
    float magbrowniano;
    bezierChain(int width, int height){
        Random rnd=new Random();
        nodes=7;
        radius_link=200;
        masainicial=(rnd.nextFloat()*40)+10;
        origen=new PVector(rnd.nextInt (width),rnd.nextInt (height));
        links=new ArrayList<Mat_point>();
        esbrowniano=true;
        magbrowniano=.8f;
        float [] masses=new float [nodes];
        for(int i=0; i<nodes; i++){

            if (i==0){velocidadinicial=new PVector (rnd.nextFloat ()*5,rnd.nextFloat ()*5);} else {velocidadinicial=new PVector (0,0);}
            //masses[i]=masaparticula*((random(10,100))*0.01);
            masses[i]=masainicial/((i+1));
            links.add(new Mat_point(origen,  masses[i]));


            links.get(i).eterna=true;
            links.get(i).limitx=width;
            links.get(i).limity=height;
            links.get(i).velocidad=velocidadinicial;

        }
    }
    public void acelera_particulas(Atractor atractor){

        for (int i = 0; i < links.size(); i++) {
           Mat_point p = links.get(i);
            p.acelerar(atractor.fuerza(p.posicion));
            if (esbrowniano == true) {
                browniano = new PVector(0, magbrowniano);
                browniano.rotate(p.velocidad.heading());
                p.acelerar(browniano);
            }

        }
    }
    void actualiza_particula() {
        for (int i = 0; i < links.size(); i++) {
            Mat_point l = links.get(i);

            l.actualizar();
            if (i != 0) {
                Mat_point l_ant = links.get(i - 1);
                PVector radius = PVector.sub(l.posicion, l_ant.posicion);
                radius.limit(radius_link);
                l.posicion = PVector.add(l_ant.posicion, radius);
            }
        }
    }
        void mostrar(Canvas canvas){
        Paint paint;
        paint = new Paint();
        paint.setARGB(255,0,0,0);
        paint.setStyle(Paint.Style.STROKE);

            for (int i = 0; i < links.size(); i++) {
                Mat_point l = links.get(i);

                //l.mostrar();
            /////////////////////////////////////////////////



        }
    }




}
