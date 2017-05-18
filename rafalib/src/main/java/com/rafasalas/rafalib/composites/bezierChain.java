package com.rafasalas.rafalib.composites;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

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
    public bezierChain(int width, int height){
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
            links.get(i).boxed(true, width, height);
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
    public void actualiza_particula() {
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
        public void mostrar(Canvas canvas){
        Paint paint;
        paint = new Paint();
        paint.setARGB(255,0,0,0);
        paint.setStyle(Paint.Style.STROKE);
           paint.setStrokeWidth(1.0f);
            paint.setAntiAlias(true);
            Path p = new Path();
            Mat_point l=links.get(0);
            p.reset();
            p.moveTo(l.posicion.x,l.posicion.y);
            for (int i = 1; i < links.size()-1; i=i+2) {
                Mat_point l1 = links.get(i);
                Mat_point l2 = links.get(i+1);
                //Mat_point l3 =  links.get(i+2);
                p.quadTo(l1.posicion.x, l1.posicion.y, l2.posicion.x,l2.posicion.y);
                //p.moveTo(l3.posicion.x,l3.posicion.y);
                    }
            canvas.drawPath(p,paint);
    }




}