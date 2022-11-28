import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SlidingPuzzle {

	JFrame jframe = new JFrame("SlidingPuzzle");
	ArrayList<Cell> puzzle = new ArrayList<>();
	ArrayList<Cell> endcheck = new ArrayList<>();
	int WIDTH = 640;
	int HEIGHT = 480;
	int SCALE = 100;
	int time = 0;
	int count = 0;

	public SlidingPuzzle() {
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setLayout(null);
		for (int i = 0; i < 9; i++) {
			if (i < 3) {
				puzzle.add(new Cell(((i % 3) * 100) + 100, 100));
				endcheck.add(new Cell(((i % 3) * 100) + 100, 100));
			} else if (i < 6) {
				puzzle.add(new Cell(((i % 3) * 100) + 100, 200));
				endcheck.add(new Cell(((i % 3) * 100) + 100, 200));
			} else {
				puzzle.add(new Cell(((i % 3) * 100) + 100, 300));
				endcheck.add(new Cell(((i % 3) * 100) + 100, 300));
			}
			puzzle.get(i).text = (i + 1) + "";
			puzzle.get(i).button.setText(puzzle.get(i).text);
			puzzle.get(i).button.setBounds(puzzle.get(i).getX(), puzzle.get(i).getY(), SCALE, SCALE);
			jframe.add(puzzle.get(i).button);
		}
		
		
		puzzle.get(8).button.setText(" ");
		puzzle.get(0).button.addActionListener(new ClickEvent(0));
		puzzle.get(1).button.addActionListener(new ClickEvent(1));
		puzzle.get(2).button.addActionListener(new ClickEvent(2));
		puzzle.get(3).button.addActionListener(new ClickEvent(3));
		puzzle.get(4).button.addActionListener(new ClickEvent(4));
		puzzle.get(5).button.addActionListener(new ClickEvent(5));
		puzzle.get(6).button.addActionListener(new ClickEvent(6));
		puzzle.get(7).button.addActionListener(new ClickEvent(7));
	}

	public void go() {
		while(true) {
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time++;
			
			jframe.repaint();
		}
	}

	class Cell {
		JButton button = new JButton();
		int x = 0;
		int y = 0;
		String text = "";

		public Cell() {
		}

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}

	class ClickEvent implements ActionListener {
		int index;
		int lastX = 0;;
		int lastY = 0;;
		int nowX = 0;
		int nowY = 0;

		public ClickEvent(int index) {
			this.index = index;
		}

		public ClickEvent() {
		}

		public void actionPerformed(ActionEvent e) {
			this.nowX = puzzle.get(index).getX();
			this.nowY = puzzle.get(index).getY();
			this.lastX = puzzle.get(8).getX();
			this.lastY = puzzle.get(8).getY();
			int check = Math.abs(nowX-lastX)+Math.abs(nowY-lastY);
			if(check>=100 && check < 200) {
				
			
			puzzle.get(8).setX(nowX);
			puzzle.get(8).setY(nowY);

			puzzle.get(index).setX(lastX);
			puzzle.get(index).setY(lastY);

			puzzle.get(index).button.setBounds(lastX, lastY, SCALE, SCALE);
			puzzle.get(8).button.setBounds(nowX, nowY, SCALE, SCALE);
			count++;
			if(Terminationcondition()) {
				System.out.println(count);
			}
			
			}
		}

	}
	public boolean Terminationcondition() {
		for(int i  = 8; i >= 0; i--) {
			if(puzzle.get(i).getX() != endcheck.get(i).getX()) {
				return false;
			}
			if(puzzle.get(i).getY() != endcheck.get(i).getY()) {
				return false;
			}
		}
		return true;
		
	}

}
//getX �ϰ� ���ؼ� ����? ���ϴ°� i �ؼ� 100 �׷��� �ָ� �ɵ�? �׷� ���� �ϳ��� �� ���ϵ�?
// https://www.freeimages.com/kr/photo/cat-s-face-1553769

//�׸� �ֱ�
//ī��Ʈ,�ð� �гε�
//����ȭ�� �г�
