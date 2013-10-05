package Juego;

public class hiloDib extends Thread {
	mainProyecto1 rec;
	
	boolean verdadero=true;
	
	public hiloDib(mainProyecto1 r)
	{
		rec=r;
	}
	
	public void run()
	{

		while(verdadero)
		{
			rec.Posicion();
			try{
				Thread.sleep(60);
				
			}catch(InterruptedException e)
			{
					
			}
			rec.repaint();
		}
		
	}
	public void sacar()
	   {
		   verdadero=false;
	   }

}
