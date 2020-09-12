package puzgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import util.move;



@SuppressWarnings("serial")
public class blindgame extends JFrame{

	private int cnt;
	private JPanel npanel = new JPanel();
	private JPanel spanel = new JPanel();
	private JButton btn[];
	private int blindbtn[];
	private move mv = new move();
	boolean first = true;
	public blindgame(int cnt)
	{ 
		this.cnt = cnt;

	}
	public void start()
	{
		setTitle("Puzzle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
		btn = new JButton[cnt*cnt];
		blindbtn = new int[cnt*cnt]; // 보이지않으며 내부적으로 동작한다.
		setResizable(false);
		setup();
	}

	public void change()
	{
		for(int i = 0 ; i<btn.length; i++)
		{
			btn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					JButton b = (JButton)e.getSource();
					int[] way = new int [4];
					int cpy = mv.inin(b,btn,way,cnt);	
					int count = 0;
					
					if(first = true) // 점부다 가려버린다.
					{
						for(int i = 0; i<btn.length; i++)
						{
							btn[i].setText("");
						}
						first = false; // 그리곤 false로 바꾼다.
						
					}

					for(int i = 0; i<way.length; i++)
					{


						if(way[i]!=-1 && !btn[way[i]].isEnabled())
						{
							JButton temp_act_button, temp_inact_button;
							int temp;
							
							
							temp_act_button = btn[cpy];
							temp_inact_button = btn[way[i]];

							
							
							temp_inact_button.setEnabled(true);
							temp_act_button.setEnabled(false);
							
							temp = blindbtn[cpy];
							blindbtn[cpy] = blindbtn[way[i]];
							blindbtn[way[i]] = temp;
							// 내부적으로 바꾸는 과정
							break;					

						}

					}


					for(int j = 0; j<blindbtn.length; j++)
					{
						if(blindbtn[j] == j+1) 
							count++;
						else
							break;
					}
					if(count == cnt*cnt-1)
						for(int k = 0; k<btn.length; k++)
							btn[k].setEnabled(false);


				}
			});	
		}

	}

	public void suffle()
	{

		for(int j = 0; j<500; j++)
		{
			int[] way = new int [4];
			int cpy = 0;
			for(cpy = 0; cpy < btn.length; cpy++)
				if(!btn[cpy].isEnabled())
					break;


			mv.movepuz(cpy,way,cnt);			

			int i = (int)(Math.random()*4);
			if(way[i]!=-1)
			{
				JButton temp_act_button, temp_inact_button;

				temp_inact_button = btn[cpy];
				temp_act_button = btn[way[i]];

				temp_inact_button.setText(temp_act_button.getText());
				temp_act_button.setText("");

				temp_inact_button.setEnabled(true);
				temp_act_button.setEnabled(false);					
			}

			if(j == 499)
			{
				for(int k =0; k<btn.length; k++)
				{
					if((btn[k].getText()).equals(""))
						blindbtn[k] = 0;
					else
						blindbtn[k] = Integer.parseInt(btn[k].getText());
				}
			}

		}
	}


	public void setup()
	{
		npanel.setLayout(new FlowLayout());
		npanel.setBackground(Color.LIGHT_GRAY);

		JButton startbtn = new JButton("Start");
		JButton hintbtn = new JButton("Hint");
		JButton closebtn = new JButton("Close");
		
		
		npanel.add(startbtn);
		npanel.add(closebtn);
		npanel.add(hintbtn);
		
		startbtn.addActionListener(new myActionListener());
		closebtn.addActionListener(new myActionListener());
		hintbtn.addActionListener(new myActionListener());


		add(npanel, BorderLayout.NORTH); 

	}

	public void batch()
	{

		for(int i = 0; i<btn.length; i++)
		{
			btn[i] = new JButton(String.valueOf(i+1));
			spanel.add(btn[i]);
			btn[i].setEnabled(true);
		}
		btn[cnt*cnt-1].setText("");
		btn[cnt*cnt-1].setEnabled(false);
	}




	private class myActionListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{
			JButton b =(JButton)e.getSource();


			if((b.getText()).equals("Start"))
			{
				spanel.setLayout(new GridLayout(cnt,cnt));
				add(spanel, BorderLayout.CENTER);

				batch();
				suffle();
				change();
				b.setEnabled(false);

			}
		

			else if((b.getText()).equals("Hint"))
			{
				for(int i = 0; i<btn.length; i++)
				{
					if(blindbtn[i] == 0)
						btn[i].setText("");
					else
						btn[i].setText(String.valueOf(blindbtn[i]));
				}
				// 힌트를 제공하는 과정이다. 
				first = true;
				b.setEnabled(false);
			}
			
			else if((b.getText()).equals("Close"))
			{
				dispose();

			}




		}
	}
}
