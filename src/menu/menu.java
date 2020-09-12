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
			
			// 메뉴에서 기본틀을 담당한다.
		}
	
		
		public void startgame()
		{
			JLabel la = new JLabel("PUZZLE GAME"); 
			JLabel name = new JLabel("programed by 시종욱");
			la.setLocation(50,10);
			la.setSize(400, 50);
			la.setFont(new Font("돋움", Font.BOLD, 40)); 
			name.setLocation(100,40);
			name.setSize(200, 50);
			name.setFont(new Font("돋움", Font.ITALIC, 20)); // 폰트 설정
			add(la);
			add(name);
			for(int i = 3; i<12; i++) // 메뉴에 버튼을 배치하는 과정이다. 
			{
				if(i<7)
				{
					JButton select = new JButton("1인용 게임 "+Integer.toString(i*i-1)+"퍼즐");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 7)
				{
					JButton select = new JButton("2인용 게임");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 8)
				{
					JButton select = new JButton("컴퓨터 대전");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 9)
				{
					JButton select = new JButton("컴퓨터 대전 A*");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 10)
				{
					JButton select = new JButton("블라인드 게임");	
					select.addActionListener(this);
					select.setLocation(120,25+30*i);
					select.setSize(150,20);
					add(select);
				}
				
				else if(i == 11)
				{
					JButton select = new JButton("종료하기");	
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
			
			if((selected.getText()).equals("1인용 게임 8퍼즐"))
				start(3);
			else if((selected.getText()).equals("1인용 게임 15퍼즐"))
				start(4);	
			else if((selected.getText()).equals("1인용 게임 24퍼즐"))
				start(5);
			else if((selected.getText()).equals("1인용 게임 35퍼즐"))
				start(6);
			else if((selected.getText()).equals("2인용 게임"))
				multistart(3);
			else if((selected.getText()).equals("컴퓨터 대전"))	
				Aigamestart(3);
			else if((selected.getText()).equals("컴퓨터 대전 A*"))
				astarstart(3);		
			else if((selected.getText()).equals("블라인드 게임"))
				Blindstart(3);			
			else if((selected.getText()).equals("종료하기"))
				System.exit(0);
			// 각자의 다른 클래스로 실행시키려고한다. 퍼튼의 텍스트와 일치하면 실행된다.
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
