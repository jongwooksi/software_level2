package puzgame;

import java.awt.*;
import java.awt.event.*;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;
import util.move;



@SuppressWarnings("serial")
public  class astargame extends JFrame{


	private JButton btn[] ;
	private JButton btn2[];
	private JPanel spanel = new JPanel();
	private JPanel spanel2 = new JPanel();
	private int cnt;
	private JPanel npanel = new JPanel();
	private move mv = new move();
	private int[][] start;  // 초기 값을 저장해준다.
	private int[][] end; //목표를 하는 배열이다.
	private int x = 0, y = 0; // 빈칸이 어디에있는지 알아야 할 때 쓴다.
	private ArrayList<int[][]> listpuz = new ArrayList<int[][]>(); // 목표로 가는 방법을 찾으면 루트로 가서 차례대로 저장한다. 
	private int[] row = { 1, 0, -1, 0 }; // 상하좌우관련에 쓰인다.
	private int[] col = { 0, -1, 0, 1 }; // 상하좌우관련에 쓰인다.
	private PriorityQueue<astarnode> que; // 우선순위 큐로써, 움직여진 값에 대하여 정보를 내포하고 있는 상태를 저장한다.
	
	public astargame(int cnt)
	{ 	
		this.cnt = cnt;	
	}
	
	public void start()
	{
		setTitle("Puzzle Game");
		btn=new JButton[cnt*cnt];
		btn2= new JButton[cnt*cnt];
		start = new int[cnt][cnt]; // 맵사이즈에 따른 초기 상태 설정
		end = new int[cnt][cnt]; // 맵사이즈에 따른 목표 상태 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		setResizable(false);
		setup();
	}

	public void astarset()
	{
	
		int cnttemp = 0;
		int endtemp = 1;
		for(int i = 0 ; i < cnt ; i++) // 맵사이즈에 맞는 목표상태 설정
		{
			for(int j = 0 ; j < cnt ; j++)
			{
				end[i][j] = endtemp++; // 1부터 끝까지 배치
			}
		}
		
		end[cnt-1][cnt-1] = 0; // 목표상태의 마지막칸은 0(빈칸)
		
		for(int i = 0 ; i < cnt ; i++)  //섞인 상태의 NumBtn의 값들을 초기상태로 준다
		{
			for(int j = 0 ; j < cnt ; j++)
			{
				try{ 

					start[i][j] = Integer.parseInt(btn2[cnttemp++].getText());
					
					int cpy = 0;
					for(cpy = 0; cpy < btn2.length; cpy++)
						if(!btn2[cpy].isEnabled())
							break;
					
					x = cpy/cnt;
					y = cpy%cnt; // 빈칸이 어디에 있는지 알 수 있는 방법이다.
				}
				catch(OutOfMemoryError e) {	}	
				catch(NumberFormatException e) {}
				}
		    }
		
		try{ 
			astarstart(start, end, x, y, btn2); // 현재 상태와, 목표, 빈칸의 위치, 버튼배열을 인자로 넘긴다.
		}
		catch(OutOfMemoryError e) {	}	
		
	
	}
	
	public boolean check(int x, int y, int start[][]) {  
		
		return (x >= 0 && x < start.length && y >= 0 && y < start.length); // 빈칸의 인덱스가 범위를 벗어나는지 검사한다.
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




		for(int j = 0; j<20*cnt; j++)
		{
			int[] way = new int [4];
			int cpy = 0;
			for(cpy = 0; cpy < btn.length; cpy++)
				if(!btn[cpy].isEnabled())
					break;

			mv.movepuz(cpy,way,cnt);

			int i = (int)(Math.random()*4);



			if(way[i]!=-1) // -1이 아닌경우로 움직임
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




	public void setup()
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
				astarset();
	
				b.setEnabled(false);
			}
			else if((b.getText()).equals("Close"))
			{
				dispose();

			}


		}
	}


	
	
	
	public void astarstart(int[][] start, int[][] end, int x, int y,JButton btn2[] ) { 
		
		Comparator<astarnode> comparator = (a, b) -> (a.h + a.g) - (b.h + b.g); // 람다식으로 사용하였다. 
		
		que = new PriorityQueue<astarnode>(1000, comparator); // 우선순위 큐에 위의 람다식을 넣는다.
		
		astarnode rootpuz = new astarnode(start, x, y, x, y, 0, null); // 초기값을 rootpuz로 한다.
		rootpuz.h = calh(start, end); 
		que.add(rootpuz);
		
		while (!que.isEmpty()) {
			astarnode min = que.poll();  
			
			if (min.h == 0) // 다른게 하나도 없을 때
			{ 
				
				pathoutput(min,btn2); 
				staroutput st = new staroutput(btn2,listpuz); 
				st.start(); 
				return;
			}
			
	
			for (int i = 0; i < 4; i++) // 상하좌우 전체 다 
			{
	            if (check(min.x + row[i], min.y + col[i],start)) { // 뽑힌 그 값의 아들을 만드는 과정이다.
	            	astarnode childpuz = new astarnode(min.mat, min.x, min.y, min.x + row[i], min.y + col[i], min.g + 1, min);
	            	childpuz.h = calh(childpuz.mat, end); 
	            	que.add(childpuz); 
	            }
	        }
		}
	}
	
	
	
	
	public void matoutput(int[][] mat,JButton btn2[]  ) {  // 어레이리스트에 값을 넣는다.
		listpuz.add(mat); 
	}
	
	public void pathoutput(astarnode rootpuz, JButton btn2[]) { // 재귀함수로써 루트끝까지 간 후 matoutput을 점부다 한다.
		
		if (rootpuz != null)
		{
			pathoutput(rootpuz.parentpuz,btn2); 
			matoutput(rootpuz.mat,btn2);  
		}
		else
			return;
		
	}
	
	public int calh(int[][] start, int[][] end) { // h의 값을 계산한다.
		int calcnt = 0;
		int rownum = start.length; 
		for (int i = 0; i < rownum; i++) 
		{
			for (int j = 0; j < rownum; j++)
			{
				if (start[i][j] != 0 && start[i][j] != end[i][j])
				{  
					calcnt++; 
				}
			}
		}
		return calcnt;
	}
	

	
}





class astarnode {

	public int x, y, g, h;
	public astarnode parentpuz;
	public int[][] mat; 
		
	
	public astarnode(int[][] mat, int x, int y, int xnew, int ynew, int g, astarnode parentpuz) // 버튼배치상태에 대한 상세정보
	{
		
		this.parentpuz = parentpuz;  
		this.mat = new int[mat.length][];  
		this.x = xnew;
		this.y = ynew;
		this.h = 1000;
		this.g = g;
				
		for (int i = 0; i < mat.length; i++) {
			this.mat[i] = mat[i].clone(); 
		}
		
		this.mat[x][y]   =  this.mat[xnew][ynew];
		this.mat[xnew][ynew] = 0;
		
	}
	
}


class staroutput extends Thread
{
	private ArrayList<int[][]> listpuz = new ArrayList<int[][]>(); 
	private JButton btn2[];
	

	staroutput(JButton btn2[],ArrayList<int[][]> listpuz)
	{
		this.btn2 = btn2;
		this.listpuz = listpuz;
	}
	
	public void run()
	{
		while(true)
		{
			try {
				for(int i=0;i<listpuz.size();i++) // 저장된 listpuz에서 나온 값을 화면으로 점부다 출력하면서 과정을 보여준다.
				{
					Thread.sleep(600);
					for(int j=0;j<listpuz.get(i).length;j++)
					{
						for(int k=0;k<listpuz.get(i).length;k++)
						{
							btn2[listpuz.get(i).length*j+k].setText(Integer.toString(listpuz.get(i)[j][k]));
							
							if(btn2[listpuz.get(i).length*j+k].getText().equals("0"))
							{
								btn2[listpuz.get(i).length*j+k].setEnabled(false);
								btn2[listpuz.get(i).length*j+k].setText("");
							}
							else
							{
								btn2[listpuz.get(i).length*j+k].setEnabled(true);
							}
						
							
						}						
					}
					if( i == listpuz.size()-1) // 전체 다 되면 비활성화
						for(int m =0; m < btn2.length ; m++)
						{
							btn2[m].setEnabled(false);
						}
					
					
				}
				listpuz.clear();
				break;
			}
			catch(InterruptedException e) { }
		}
	}
}