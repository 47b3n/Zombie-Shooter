public class FPS {
	
	long now;
	long timer;
	int fps;
	int cfps;
	
	public void update()
	{
		now = System.currentTimeMillis();
		cfps++;
		if (now-timer>=1000) 
		{
			fps = cfps;
			cfps = 0;
			timer = now;
		}
	}
	
	public int getFPS()
	{
		return fps;
	}

}
