package util;

import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class winlose extends JFrame { // 1인용과 2인용게임에서 상황종료할 때 띄우는 새로운 창의 내용이다.
	
	private int t_cnt;
	
	public winlose(int t_cnt)
	{
		this.t_cnt = t_cnt;
	}
	public void player1_win()
	{
		setTitle("Puzzle Game");
		setSize(300,100);
		setVisible(true);
		JLabel la = new JLabel("        				     play1의 승리!");
		add(la);
	}
	
	public void player2_win()
	{
		setTitle("Puzzle Game");
		setSize(300,100);
		setVisible(true);
		JLabel la = new JLabel("     				        play2의 승리!");
		add(la);
	}
	
	public void play_end()
	{
		setTitle("Puzzle Game");
		setSize(300,100);
		setVisible(true);
		JLabel la = new JLabel("     			  	      게임끝! " + "소요시간:  "+t_cnt+"초");
		add(la);
	}
	

}
