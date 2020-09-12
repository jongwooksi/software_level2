package menu;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import puzgame.Aigame;
import puzgame.astargame;
import puzgame.blindgame;
import puzgame.multigame;
import puzgame.rungame;

@SuppressWarnings("serial")
public class menu extends JFrame implements ActionListener{
	
	
	
	
		menu()
		{
			super("Puzzle Game");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Container ContentPane = getContentPane();
			
			ContentPane.setLayout(null);
			setResizable(false);
			startgame();
			setBounds(400, 200,0, 0);
			setSize(400,450);
			setVisible(true);
			
			// �޴����� �⺻Ʋ�� ����Ѵ�.
		}
	
		
		public void startgame()
		{
			JLabel la = new JLabel("PUZZLE GAME"); 
			JLabel name = new JLabel("programed by ������");
			la.setLocation(50,10);
			la.setSize(400, 50);
			la.setFont(new Font("����", Font.BOLD, 40)); 
			name.setLocation(100,40);
			name.setSize(200, 50);
			name.setFont(new Font("����", Font.ITALIC, 20)); // ��Ʈ ����
			add(la);
			add(name);
			for(int i = 3; i<12; i++) // �޴��� ��ư�� ��ġ�ϴ� �����̴�. 
			{
				if(i<7)
				{
					JButton select = new JButton("1�ο� ���� "+Integer.toString(i*i-1)+"����");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 7)
				{
					JButton select = new JButton("2�ο� ����");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 8)
				{
					JButton select = new JButton("��ǻ�� ����");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 9)
				{
					JButton select = new JButton("��ǻ�� ���� A*");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 10)
				{
					JButton select = new JButton("����ε� ����");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 11)
				{
					JButton select = new JButton("�����ϱ�");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
			}
		}
		
		public void actionPerformed(ActionEvent e)
		{
			JButton selected = (JButton)e.getSource();
			
			if((selected.getText()).equals("1�ο� ���� 8����"))
				start(3);
			else if((selected.getText()).equals("1�ο� ���� 15����"))
				start(4);	
			else if((selected.getText()).equals("1�ο� ���� 24����"))
				start(5);
			else if((selected.getText()).equals("1�ο� ���� 35����"))
				start(6);
			else if((selected.getText()).equals("2�ο� ����"))
				multistart(3);
			else if((selected.getText()).equals("��ǻ�� ����"))	
				Aigamestart(3);
			else if((selected.getText()).equals("��ǻ�� ���� A*"))
				astarstart(3);		
			else if((selected.getText()).equals("����ε� ����"))
				Blindstart(3);			
			else if((selected.getText()).equals("�����ϱ�"))
				System.exit(0);
			// ������ �ٸ� Ŭ������ �����Ű�����Ѵ�. ��ư�� �ؽ�Ʈ�� ��ġ�ϸ� ����ȴ�.
		}
	
	
	

		public void start(int cnt)
		{
			rungame run = new rungame(cnt);
			run.start();
		}
	
		public void multistart(int cnt)
		{
			multigame multi = new multigame(cnt);
			multi.start();
		}
	
		public void Aigamestart(int cnt)
		{
			Aigame ai = new Aigame(cnt);
			ai.start();
		}
	
		public void Blindstart(int cnt)
		{
			blindgame bg = new blindgame(cnt);
			bg.start();
		}
		
		public void astarstart(int cnt)
		{
			astargame as = new astargame(cnt);
			as.start();
		}
		public static void main(String[] args) {
			new menu();
			
			
	}
		


	
	

	

}
