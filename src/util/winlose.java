package util;

import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class winlose extends JFrame { // 1�ο�� 2�ο���ӿ��� ��Ȳ������ �� ���� ���ο� â�� �����̴�.
	
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
		JLabel la = new JLabel("        				     play1�� �¸�!");
		add(la);
	}
	
	public void player2_win()
	{
		setTitle("Puzzle Game");
		setSize(300,100);
		setVisible(true);
		JLabel la = new JLabel("     				        play2�� �¸�!");
		add(la);
	}
	
	public void play_end()
	{
		setTitle("Puzzle Game");
		setSize(300,100);
		setVisible(true);
		JLabel la = new JLabel("     			  	      ���ӳ�! " + "�ҿ�ð�:  "+t_cnt+"��");
		add(la);
	}
	

}
