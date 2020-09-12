package puzgame;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import util.move;
import util.winlose;


@SuppressWarnings("serial")
public  class multigame extends JFrame {

	private JPanel spanel = new JPanel();
	private JPanel spanel2 = new JPanel();
	private JButton btn[];
	private JButton btn2[];
	private int cnt;
	private JPanel sumpanel = new JPanel();
	move mv = new move();
	winlose wl = new winlose(0);
	
	public multigame(int cnt)
	{ 
 
		this.cnt = cnt;
	
	}

	public void start()
	{
		setTitle("Puzzle Game");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btn= new JButton[cnt*cnt];
		btn2 = new JButton[cnt*cnt];
		setResizable(false);
		setup();

		setSize(1000,500);
		setVisible(true);
	}

	public void player2change()
	{
		for(int i = 0 ; i<btn2.length; i++)
		{


			btn[i].addKeyListener(new KeyAdapter() { // 키보드 입력이다. 플레이어 1이 시작해야 할 수 있도록 제한을 두었다.
				public void keyPressed(KeyEvent e)
				{
					int count2 = 0;
					int[] way2 = new int [4];
					int cpy = mv.inin2(btn2,way2,cnt);

					switch(e.getKeyCode()) 
					{
					case KeyEvent.VK_UP: 
					{
						change(way2,cpy,btn2,0);
						break;  
					}
					case KeyEvent.VK_DOWN:
					{
						change(way2,cpy,btn2,1);
						break;  
					}
					case KeyEvent.VK_LEFT:
					{
						change(way2,cpy,btn2,2);
						break;  
					}
					case KeyEvent.VK_RIGHT:
					{
						change(way2,cpy,btn2,3);
						break;  
					}

					}

					for(int j = 0; j<btn2.length; j++)
					{
						if(btn2[j].getText().equals(String.valueOf(j+1)) )
							count2++;
						else
							break;
					}
					if(count2 == cnt*cnt-1)
						for(int k = 0; k<btn.length; k++)
						{
							btn[k].setEnabled(false);
							btn2[k].setEnabled(false);
							wl.player2_win();
						}


				}



			});	



		}
	}
	public void player1change()
	{

		for(int i = 0 ; i<btn.length; i++)
		{


			btn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					JButton b = (JButton)e.getSource();				
					int[] way = new int [4];
					int cpy = mv.inin(b,btn,way,cnt);	
					int count1 = 0;


					for(int i = 0; i<way.length; i++)
						if(way[i]!=-1 && !btn[way[i]].isEnabled())
						{
							JButton temp_act_button, temp_inact_button;

							temp_act_button = btn[cpy];
							temp_inact_button = btn[way[i]];

							temp_inact_button.setText(temp_act_button.getText());
							temp_act_button.setText("");

							temp_inact_button.setEnabled(true);
							temp_act_button.setEnabled(false);
							break;


						}

					for(int j = 0; j<btn.length; j++)
					{
						if(btn[j].getText().equals(String.valueOf(j+1)) )
							count1++;
						else
							break;
					}
					if(count1 == cnt*cnt-1)
						for(int k = 0; k<btn.length; k++)
						{
							btn[k].setEnabled(false);
							btn2[k].setEnabled(false);
							wl.player1_win();
						}


				}
			});	

		}
	}
	public void shffle()
	{
		for(int i = 0; i<btn.length; i++)
		{
			btn[i] = new JButton(String.valueOf(i+1));
			spanel.add(btn[i]);
			btn[i].setEnabled(true);
			btn2[i] = new JButton(String.valueOf(i+1));
			spanel2.add(btn2[i]);
			btn2[i].setEnabled(true);
		}
		btn[cnt*cnt-1].setText("");
		btn[cnt*cnt-1].setEnabled(false);
		btn2[cnt*cnt-1].setText("");
		btn2[cnt*cnt-1].setEnabled(false);


		for(int j = 0; j<100; j++)
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
				JButton temp_act_button, temp_inact_button,temp_act_button2, temp_inact_button2;

				temp_inact_button = btn[cpy];
				temp_act_button = btn[way[i]];

				temp_inact_button.setText(temp_act_button.getText());
				temp_act_button.setText("");

				temp_inact_button.setEnabled(true);
				temp_act_button.setEnabled(false);	

				temp_inact_button2 = btn2[cpy];
				temp_act_button2 = btn2[way[i]];

				temp_inact_button2.setText(temp_act_button2.getText());
				temp_act_button2.setText("");

				temp_inact_button2.setEnabled(true);
				temp_act_button2.setEnabled(false);	


			}



		}

	}



	public void change(int way2[], int cpy, JButton btn2[],int where)
	{
		JButton temp_act_button, temp_inact_button;
		if(way2[where]!=-1)
		{
			temp_inact_button = btn2[cpy];
			temp_act_button = btn2[way2[where]];

			temp_inact_button.setText(temp_act_button.getText());
			temp_act_button.setText("");

			temp_inact_button.setEnabled(true);
			temp_act_button.setEnabled(false);
		}




	}

	public void setup()
	{
		JPanel npanel = new JPanel();
		npanel.setLayout(new FlowLayout());
		npanel.setBackground(Color.LIGHT_GRAY);
		JButton startbtn = new JButton("Start");
		JButton closebtn = new JButton("Close");

		npanel.add(startbtn);
		npanel.add(closebtn);

		startbtn.addActionListener(new myActionListener());
		closebtn.addActionListener(new myActionListener());

		add(npanel, BorderLayout.NORTH); 
	}







	private class myActionListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{
			JButton b =(JButton)e.getSource();

			if((b.getText()).equals("Start"))
			{


				spanel.setLayout(new GridLayout(cnt,cnt));

				spanel2.setLayout(new GridLayout(cnt,cnt));
				sumpanel.setLayout(new GridLayout(1,2,20,20));


				sumpanel.add(spanel);
				sumpanel.add(spanel2);
				add(sumpanel, BorderLayout.CENTER);


				shffle();

				player1change();
				player2change();

				b.setEnabled(false);
			}
			else if((b.getText()).equals("Close"))
			{
				dispose();

			}




		}
	}


}
