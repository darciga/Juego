package Juego;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class Dibujo 
{
	int manzana[]={0x0030,0x0060,0x0040,0x0738,0x0FFC,0x3FFC,0x3FFF,0x7FFE,
			0x7FFC,0x7FFC,0x7FFC,0x7FFC,0x7FFE,0x3FFF,0x1FFE,0x0FFC,0x0738};
	
	int x,y,velocidad,maxx,maxy;
	Random obr=new Random();
	
	
	public Dibujo(int maxx,int maxy,int v)
	{
		x=obr.nextInt(760);
		y=0;
		velocidad=v;
		this.maxx=maxx;
		this.maxy=maxy;
	}
	
	public void dibujarM(Color color, Graphics g)
	{
		for (int i = 0; i < manzana.length; i++)
		{
            for (int j = 0; j < 16; j++)
            {
                int x1 = manzana[i];
                int operacion = x1 & (0x8000 >> j);
                if (operacion != 0)
                {
                	g.setColor(color);
                    g.drawLine(x + j, y + i, x + j, y + i);
                }
            }
        }

	}
	
	public void Dibujar(Graphics g)
	{
		dibujarM(Color.white, g);
		
	}

}
