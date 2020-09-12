package puzgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import util.move;
import util.timer;
import util.winlose;


@SuppressWarnings("serial")
public class rungame extends JFrame{

	private int cnt;
	private int t_cnt;

	private JPanel npanel = new JPanel();
	private JPanel spanel = new JPanel();
	private JButton btn[];
	private JButton newbtn = new JButton("New");
	private JButton openbtn = new JButton("Open");
	private JButton savebtn = new JButton("Save");
	private boolean end = false; // true가 되면 스레드를 종료한다.
	

	move mv = new move();
	timer tm = new timer(t_cnt,end);
	
	public rungame(int cnt)
	{ 
 
		this.cnt = cnt;
	
	}

	public void start()
	{
		setTitle("Puzzle Game") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200+100*cnt,200+100*cnt); // cnt값에 따라 화면의 크기가 달라진다.
		setVisible(true);
		
		setResizable(false); // 사이즈 조절을 못하게 한다.
	
		btn = new JButton[cnt*cnt];
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


					for(int i = 0; i<way.length; i++)
					{


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


					}


					for(int j = 0; j<btn.length; j++)
					{
						if(btn[j].getText().equals(String.valueOf(j+1)) )
							count++;
						else
							break;
					}
					if(count == cnt*cnt-1)
					{
						end= true;
						for(int k = 0; k<btn.length; k++)
						{
							btn[k].setEnabled(false);
							savebtn.setEnabled(false);
						}
					}
					if(end == true)
					{
						
							t_cnt = tm.settime();
							tm.setend(true); // 스레드에게 종료하라고 한다.
							winlose wl = new winlose(t_cnt);					
							wl.play_end();
						
					
						
						
					}
					
					
					
				}
			});	
		}

	}

	public void suffle()
	{

		for(int j = 0; j<100*cnt; j++)
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



		}
	}

	public void opensuffle()
	{
		int count = 0;
		int[] way = new int [4];
		int cpy = 0;
		for(cpy = 0; cpy < btn.length; cpy++)
			if(!btn[cpy].isEnabled())
				break;


		mv.movepuz(cpy,way,cnt);

		while(count <1)
		{
			int i = (int)(Math.random()*4);

			if(way[i]!=-1)
			{
				JButton temp_act_button, temp_inact_button;

				temp_inact_button = btn[cpy];
				temp_act_button = btn[way[i]];

				temp_inact_button.setText(temp_act_button.getText());
				temp_act_button.setText("");
				temp_act_button.setText(temp_inact_button.getText());
				temp_inact_button.setText("");	

				count++;
			}
		}













	}


	public void setup()
	{
		npanel.setLayout(new FlowLayout());
		npanel.setBackground(Color.LIGHT_GRAY);
	
		
		JButton closebtn = new JButton("Close");

		npanel.add(newbtn);
		npanel.add(openbtn);
		npanel.add(savebtn);
		npanel.add(closebtn);
		
		
		
		
		newbtn.addActionListener(new myActionListener());
		openbtn.addActionListener(new myActionListener());
		savebtn.addActionListener(new myActionListener());
		closebtn.addActionListener(new myActionListener());



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






	public void Savedata()
	{
		int[] sb = new int[cnt*cnt+1]; // 배열 전체 세팅과 현재 소요된 시간을 함께 배열로 저장한다.

		for(int i = 0; i<btn.length; i++)
		{
			if(btn[i].getText().equals(""))
				sb[i] = 0;
			else
				sb[i] = Integer.parseInt(btn[i].getText());
		}
		tm.setend(true);
		
		sb[cnt*cnt] = tm.settime(); // 마지막 값은 시간
		
		try
		{

			FileOutputStream fos = new FileOutputStream("save"+cnt+".txt");	//저장하는 방식
			for(int i = 0 ; i<btn.length; i++)
			{
				fos.write(sb[i]);
				
			}

			fos.close();

		} catch (IOException e) 
		{

			e.printStackTrace();
		}



	}

	public void Opendata()
	{
		int [] sb = new int[cnt*cnt+1];

		try
		{
			FileInputStream fis = new FileInputStream("save"+cnt+".txt");


			int n =0;
			int c;

			while((c=fis.read())!= -1) // 끝까지 불러온다.
			{
				sb[n] = c;
				
				n++;
			}
			
			t_cnt = sb[n-1];
			tm.settcnt(t_cnt);
		
			fis.close();

			
		
		} 
		catch (IOException e)
		{

			e.printStackTrace();
		}


		for(int i = 0; i<btn.length; i++)
		{
			if(sb[i]==0)
			{
				btn[i] = new JButton("");
				spanel.add(btn[i]);
				btn[i].setEnabled(false);
			}
			else
			{
				btn[i] = new JButton(String.valueOf(sb[i]));
				spanel.add(btn[i]);
				btn[i].setEnabled(true);
			}		

		}

	}


	private class myActionListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e)
		{
			JButton b =(JButton)e.getSource();


			if((b.getText()).equals("New"))
			{
				spanel.setLayout(new GridLayout(cnt,cnt));
				add(spanel, BorderLayout.CENTER);

				batch();
				suffle();
				change();
				
				t_cnt = 0;
				
				tm.start();
				
			
				b.setEnabled(false);
				openbtn.setEnabled(false);
			
			}
			else if((b.getText()).equals("Open"))
			{

				spanel.setLayout(new GridLayout(cnt,cnt));
				add(spanel, BorderLayout.CENTER);

				Opendata();
				opensuffle();

				change();
				
		
				tm.start();
				b.setEnabled(false);
				newbtn.setEnabled(false);
			
			}

			else if((b.getText()).equals("Save"))
			{
				
				Savedata();
				dispose();
			}


			else if((b.getText()).equals("Close"))
			{
				tm.setend(true);
				dispose();

			}




		}
	}
}
