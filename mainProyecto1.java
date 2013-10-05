package Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class mainProyecto1 extends JPanel implements KeyListener 
{
	JFrame Vent;
	Container cont;
	int robot[] = { 0x01000080, 0x01800180, 0x00C00300, 0x00C00300, 0x007FFE00,
			0x00FFFF00, 0x01FFFF80, 0x03FFFFC0, 0x039FF9C0, 0x079FF9E0,
			0x07FFFFE0, 0x0FFFFFF0, 0x0FFFFFF0, 0x00000000, 0x4FFFFFF2,
			0xEFFFFFF7, 0xEFFFFFF7, 0xEFFFFFF7, 0xEFFFFFF7, 0xEFFFFFF7,
			0xEFFFFFF7, 0xEFFFFFF7, 0xEFFFFFF7, 0xEFFFFFF7, 0xEFFFFFF7,
			0x4FFFFFF2, 0x0FFFFFF0, 0x0FFFFFF0, 0x0FFFFFF0, 0x0FFFFFF0,
			0x07FFFFC0, 0x001C3800, 0x001C3800, 0x001C3800, 0x001C3800,
			0x001C3800, 0x001C3800, 0x00081000 };
	int posx;
	int posy;
	Random obr = new Random();
	Dibujo m;
	Color color;
	LinkedList<Dibujo> Manzana;
	boolean ban = true;
	hiloDib hm;
	boolean fin=true;
	int score=0;

	public mainProyecto1(String titulo) 
	{
		Vent = new JFrame(titulo);
		Image icon = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("images/icon.png"));
		Vent.setIconImage(icon);
		cont = Vent.getContentPane();
		cont.setLayout(new BorderLayout());
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		Vent.setSize(800, 600);
		Vent.setLocationRelativeTo(null);
		Vent.setFocusable(true);
		Vent.setVisible(true);
		Vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Vent.setResizable(false);
		cont.add(this, BorderLayout.CENTER);
		posx = randx();
		posy = 440;
		Vent.addKeyListener(this);
		color = new Color(164, 198, 57);
		Manzana = new LinkedList<Dibujo>();
		agregarM();
		
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if(!fin())
		{
			g.setColor(Color.white);
			hm.sacar();
			JOptionPane.showMessageDialog(null, "Juego finalizado. \n Tu puntuacion es: "+score+"00 puntos.","Game Over!!!", c);
			System.exit(0);
		}
		else
		{
			//g.setColor(Color.DARK_GRAY);
			
			
			/*
			g.drawString("Android x " + posx, 5, 20);
			g.drawString("Android y " + posy, 5, 30);
			g.drawString("Manzana x " + m.x, 5, 40);
			g.drawString("Manzana y " + m.y, 5, 50);
			*/
			g.setColor(Color.DARK_GRAY);
			g.drawString(sefue()+" ", 5, 60);
			g.setColor(Color.white);
			g.drawString("Puntuacion: " +score+"00" , 5, 20);
			g.drawLine(0, 370, 800, 370);
			dibujar(color, posx, posy, g);
			for (int i = 0; i < Manzana.size(); i++) 
			{
				
				if (ban) {
					m = Manzana.get(i);
					m.dibujarM(Color.white, g);					

				} else 
				{
					try 
					{
						Thread.sleep(100);
					} 
					catch (InterruptedException e) 
					{
						
					}
					hm.sacar();
					Manzana.removeFirst();
					ban = true;
					agregarM();
					score++;
					
					}			
			}
	
		}	

	}

	public int randx() 
	{
		int x = obr.nextInt(750);
		return x;
	}

	public void dibujar(Color color, int x, int y, Graphics g) 
	{
		
		for (int i = 0; i < robot.length; i++) 
		{
			
			for (int j = 0; j < 32; j++) 
			{
				int x1 = robot[i];
				int operacion = x1 & (0x80000000 >>> j);
				if (operacion != 0) {
					
					g.setColor(color);
					g.drawLine(x + j, y + i, x + j, y + i);

				}
			}
		}

	}
	
	
	int c=0;
	public int sefue()
	{
		if(m.y>579)
			c++;
		return c;
	}
	public boolean fin()
	{
		boolean fin=true;
		if(c>=3)
			fin=false;
		return fin;
	}
	public void agregarM()
	{
		m = new Dibujo(800, 600, 10);
		hm = new hiloDib(mainProyecto1.this);
		Manzana.add(m);
		hm.start();
	}
	
	public boolean colision()
	{
		if ((m.y >= posy-20 && m.y <= posy+20) && (m.x >= posx-20 && m.x <= posx+20))
			ban = false;
		return ban;
	}
	
	public void Posicion()
	{
		
		m.y+=m.velocidad;
		ban=colision();
		if(m.y>580)
		{
			hm.sacar();
			Manzana.removeFirst();
			agregarM();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent ke) 
	{
		// TODO Auto-generated method stub
		int code = ke.getKeyCode();
		switch (code) {
		case KeyEvent.VK_RIGHT:
			if (posx < 760)
				posx += 20;
			else
				posx = 10;
			ban=colision();
			break;
		case KeyEvent.VK_LEFT:
			if (posx > 10)
				posx -= 20;
			else
				posx = 760;
			ban=colision();
			break;
		case KeyEvent.VK_UP:
			if (posy > 370)
				posy -= 20;
			else
				posy = 370;
			ban=colision();
			break;
		case KeyEvent.VK_DOWN:
			if (posy < 533)
				posy += 20;
			else
				posy = 533;
			ban=colision();
			break;
		}
		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) 
	{
		new mainProyecto1("Android");
	}
        
}
