package puzgame;


import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

import javax.swing.*;
import util.move;




@SuppressWarnings("serial")
public  class Aigame extends JFrame{


	private JButton btn[] ;
	private JButton btn2[];
	private JPanel spanel = new JPanel();
	private JPanel spanel2 = new JPanel();
	private int cnt;
	private Stack<Integer> st = new Stack<Integer>(); // pop을 하기위해 스택이 필요하다.
	private Integer save; // 반대방향으로 넣을 Integer타입이다.
	private JPanel npanel = new JPanel();
	private move mv = new move();
	
	
	public Aigame(int cnt)
	{ 	
		this.cnt = cnt;	
	} //cnt를 생성자로
	
	public void start()
	{
		setTitle("Puzzle Game");
		btn=new JButton[cnt*cnt];
		btn2= new JButton[cnt*cnt];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
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
					int[] way = new int [4]; // 4방향에 대한 배열이다.
					int cpy = mv.inin(b,btn,way,cnt);	
					int count1 = 0;



					for(int i = 0; i<way.length; i++)
						if(way[i]!=-1 && !btn[way[i]].isEnabled()) // 옮길 수 있는 타일 중에 활성화 되어있는 타일인 경우이다.
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
					if(count1 == cnt*cnt-1) // 전체다 맞게되면 전체 타일을 비활성화 한다.
						for(int k = 0; k<btn.length; k++)
						{
							btn[k].setEnabled(false);
							
						}


				}
			});	

		}
	}

	public void shffle()
	{

		for(int i = 0; i<btn.length; i++) // 1과 2의 타일을 똑같이 배치된 버튼들을 섞는 과정이다.
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

			int i = (int)(Math.random()*4); //상하좌우 랜덤



			if(way[i]!=-1)
			{
				if(i == 0) { save = i+1;}

				else if(i == 1) { save = i-1;}

				else if(i == 2){save = i+1;	}

				else if(i == 3){save = i-1;}
				// 위면 아래, 아래면 위, 좌면 우, 우면 좌를 저장한다.
				st.add(save);

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





	public void setup() // 버튼 세팅
	{

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

				JPanel sumpanel = new JPanel();



				spanel.setLayout(new GridLayout(cnt,cnt));

				spanel2.setLayout(new GridLayout(cnt,cnt));
				sumpanel.setLayout(new GridLayout(1,2,20,20));


				sumpanel.add(spanel);
				sumpanel.add(spanel2);
				add(sumpanel, BorderLayout.CENTER);

				shffle();

				change();
				
				AImode t1 = new AImode(btn2, cnt,st); // start버튼을 누르면 스레드가 시작된다.
				t1.start();

	
				b.setEnabled(false);
			}
			else if((b.getText()).equals("Close"))
			{
				dispose();

			}


		}
	}




}

