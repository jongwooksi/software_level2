package puzgame;

// asdasd
import java.util.Stack;

import javax.swing.JButton;
import util.move;


class AImode extends Thread {

	private int cnt;
	private JButton[] btn;
	int count = 0;
	private Stack<Integer> st = new Stack<Integer>();
	move mv = new move();

	AImode(JButton[]  btn, int cnt,  Stack<Integer> st) {
		this.cnt = cnt;  
		this.btn = btn;
		this.st= st;
	}

	

	public void run()
	{		
		while(count != cnt*cnt-1 )

		{

			try
			{		

				int[] way = new int [4];				
				int cpy = 0;
				count = 0;

				for(cpy = 0; cpy < btn.length; cpy++)
					if(!btn[cpy].isEnabled())
						break;

				mv.movepuz(cpy,way,cnt);

				int pop = st.pop(); // ���ؿ��� �� �ؼ� ���� ������ �����̴�.


				JButton temp_act_button, temp_inact_button;

				temp_inact_button = btn[cpy];
				temp_act_button = btn[way[pop]];


				temp_inact_button.setText(temp_act_button.getText());
				temp_act_button.setText("");

				temp_inact_button.setEnabled(true);
				temp_act_button.setEnabled(false);	




				
				Thread.sleep(200);	
				
				for(int j = 0; j<btn.length; j++)
				{
					if(btn[j].getText().equals(String.valueOf(j+1)) )
						count++;
					else
						break;
				}
				
				if(count == cnt*cnt-1) // �� ������ �����Ѵ�.
					for(int k = 0; k<btn.length; k++)
						btn[k].setEnabled(false);
				
				

			}

			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
	}	

}

