package util;


public class timer extends Thread{

	private int t_cnt;
	private boolean end;
	


	public timer(int t_cnt, boolean end)
	{
		this.t_cnt = t_cnt;
		this.end = end;
	}
	
	public void setend(boolean end)
	{
		this.end = end;
	}

	public void settcnt(int t_cnt)
	{
		this.t_cnt = t_cnt;
	}
	
	public void run() // 시간을 알게 해준다.
	
	{
		while(end == false)
		{
			t_cnt++;
		
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			
		
	}
	
	public int settime()
	{
		return t_cnt;
	}
}
