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
	private int[][] start;  // �ʱ� ���� �������ش�.
	private int[][] end; //��ǥ�� �ϴ� �迭�̴�.
	private int x = 0, y = 0; // ��ĭ�� ����ִ��� �˾ƾ� �� �� ����.
	private ArrayList<int[][]> listpuz = new ArrayList<int[][]>(); // ��ǥ�� ���� ����� ã���� ��Ʈ�� ���� ���ʴ�� �����Ѵ�. 
	private int[] row = { 1, 0, -1, 0 }; // �����¿���ÿ� ���δ�.
	private int[] col = { 0, -1, 0, 1 }; // �����¿���ÿ� ���δ�.
	private PriorityQueue<astarnode> que; // �켱���� ť�ν�, �������� ���� ���Ͽ� ������ �����ϰ� �ִ� ���¸� �����Ѵ�.
	
	public astargame(int cnt)
	{ 	
		this.cnt = cnt;	
	}
	
	public void start()
	{
		setTitle("Puzzle Game");
		btn=new JButton[cnt*cnt];
		btn2= new JButton[cnt*cnt];
		start = new int[cnt][cnt]; // �ʻ���� ���� �ʱ� ���� ����
		end = new int[cnt][cnt]; // �ʻ���� ���� ��ǥ ���� ����
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
		for(int i = 0 ; i < cnt ; i++) // �ʻ���� �´� ��ǥ���� ����
		{
			for(int j = 0 ; j < cnt ; j++)
			{
				end[i][j] = endtemp++; // 1���� ������ ��ġ
			}
		}
		
		end[cnt-1][cnt-1] = 0; // ��ǥ������ ������ĭ�� 0(��ĭ)
		
		for(int i = 0 ; i < cnt ; i++)  //���� ������ NumBtn�� ������ �ʱ���·� �ش�
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
					y = cpy%cnt; // ��ĭ�� ��� �ִ��� �� �� �ִ� ����̴�.
				}
				catch(OutOfMemoryError e) {	}	
				catch(NumberFormatException e) {}
				}
		    }
		
		try{ 
			astarstart(start, end, x, y, btn2); // ���� ���¿�, ��ǥ, ��ĭ�� ��ġ, ��ư�迭�� ���ڷ� �ѱ��.
		}
		catch(OutOfMemoryError e) {	}	
		
	
	}
	
	public boolean check(int x, int y, int start[][]) {  
		
		return (x >= 0 && x < start.length && y >= 0 && y < start.length); // ��ĭ�� �ε����� ������ ������� �˻��Ѵ�.
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



			if(way[i]!=-1) // -1�� �ƴѰ��� ������
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
		
		Comparator<astarnode> comparator = (a, b) -> (a.h + a.g) - (b.h + b.g); // ���ٽ����� ����Ͽ���. 
		
		que = new PriorityQueue<astarnode>(1000, comparator); // �켱���� ť�� ���� ���ٽ��� �ִ´�.
		
		astarnode rootpuz = new astarnode(start, x, y, x, y, 0, null); // �ʱⰪ�� rootpuz�� �Ѵ�.
		rootpuz.h = calh(start, end); 
		que.add(rootpuz);
		
		while (!que.isEmpty()) {
			astarnode min = que.poll();  
			
			if (min.h == 0) // �ٸ��� �ϳ��� ���� ��
			{ 
				
				pathoutput(min,btn2); 
				staroutput st = new staroutput(btn2,listpuz); 
				st.start(); 
				return;
			}
			
	
			for (int i = 0; i < 4; i++) // �����¿� ��ü �� 
			{
	            if (check(min.x + row[i], min.y + col[i],start)) { // ���� �� ���� �Ƶ��� ����� �����̴�.
	            	astarnode childpuz = new astarnode(min.mat, min.x, min.y, min.x + row[i], min.y + col[i], min.g + 1, min);
	            	childpuz.h = calh(childpuz.mat, end); 
	            	que.add(childpuz); 
	            }
	        }
		}
	}
	
	
	
	
	public void matoutput(int[][] mat,JButton btn2[]  ) {  // ��̸���Ʈ�� ���� �ִ´�.
		listpuz.add(mat); 
	}
	
	public void pathoutput(astarnode rootpuz, JButton btn2[]) { // ����Լ��ν� ��Ʈ������ �� �� matoutput�� ���δ� �Ѵ�.
		
		if (rootpuz != null)
		{
			pathoutput(rootpuz.parentpuz,btn2); 
			matoutput(rootpuz.mat,btn2);  
		}
		else
			return;
		
	}
	
	public int calh(int[][] start, int[][] end) { // h�� ���� ����Ѵ�.
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
		
	
	public astarnode(int[][] mat, int x, int y, int xnew, int ynew, int g, astarnode parentpuz) // ��ư��ġ���¿� ���� ������
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
				for(int i=0;i<listpuz.size();i++) // ����� listpuz���� ���� ���� ȭ������ ���δ� ����ϸ鼭 ������ �����ش�.
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
					if( i == listpuz.size()-1) // ��ü �� �Ǹ� ��Ȱ��ȭ
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