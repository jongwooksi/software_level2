package util;

import javax.swing.JButton;

public class move {

	public void movepuz(int data, int[]moving, int cnt) // �������� ���ϴ� ���� -1�� �����Ѵ�. 0 1 2 3�� ���ʴ�� �� �� �� �츦 ����Ѵ�.
	{
		moving[0] = data-cnt;		
		moving[1] = data+cnt;
		moving[2] = data-1;
		moving[3] = data+1;
		
		if(moving[0]<0)
			moving[0] = -1;
		
		if(moving[1]>=(cnt*cnt))
			moving[1] = -1;
		
		if(moving[2]<0 || moving[2]%cnt == cnt-1)
			moving[2] = -1;
		
		if(moving[3] % cnt == 0) 
			moving[3] = -1;

	}

	
	public int inin(JButton b, JButton btn[], int[]moving, int cnt) // ��ĭ�� ��ġ�� ã�� movepuz���� �����ϰ� �Ѵ�.
	{
		int cpy = 0;
		for(cpy = 0; cpy < btn.length; cpy++)
			if(b == btn[cpy])
				break;


		movepuz(cpy,moving,cnt);
		return cpy;
	}

	public int inin2(JButton btn2[], int[]moving, int cnt)
	{
		int cpy = 0;
		for(cpy = 0; cpy < btn2.length; cpy++)
			if(!btn2[cpy].isEnabled())
				break;


		movepuz(cpy,moving,cnt);
		return cpy;
	}

	


}
