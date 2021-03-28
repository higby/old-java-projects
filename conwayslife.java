import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class conwayslife implements MouseListener, ActionListener, Runnable {

 boolean[][] cells = new boolean[25][25];
 JFrame frame = new JFrame("YAH YEET BRANDEN HIGBY BABBBBEEEYYY!!!");
 LifePanel panel = new LifePanel(cells);
 Container south = new Container();
 JButton step = new JButton("Step");
 JButton start = new JButton("Start");
 JButton stop = new JButton("Stop");
 boolean running = false;

 public conwayslife() {
  frame.setSize(600, 600);
  frame.setLayout(new BorderLayout());
  panel.addMouseListener(this);
  frame.add(panel, BorderLayout.CENTER);
  south.setLayout(new GridLayout(1, 3));
  south.add(step);
  step.addActionListener(this);
  south.add(start);
  start.addActionListener(this);
  south.add(stop);
  stop.addActionListener(this);
  frame.add(south, BorderLayout.SOUTH);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);

 }
 public void step() {
  boolean[][] newCells = new boolean[cells.length][cells[0].length];
  for (int x = 0; x < cells.length; x++) {
   for (int y = 0; y < cells.length; y++) {
    int neighborCount = 0;
    if (x > 0 && y > 0 && cells[x - 1][y - 1] == true) {
     neighborCount++;
    }
    if (x > 0 && cells[x - 1][y] == true) {
     neighborCount++;
    }
    if (x > 0 && y < cells[0].length - 1 && cells[x - 1][y + 1] == true) {
     neighborCount++;
    }
    if (y > 0 && cells[x][y - 1] == true) {
     neighborCount++;
    }
    if (y < cells[0].length - 1 && cells[x][y + 1] == true) {
     neighborCount++;
    }
    if (x < cells.length - 1 && y > 0 && cells[x + 1][y - 1] == true) {
     neighborCount++;
    }
    if (x < cells.length - 1 && cells[x + 1][y] == true) {
     neighborCount++;
    }
    if (x < cells.length - 1 && y < cells[0].length - 1 && cells[x + 1][y + 1] == true) {
     neighborCount++;
    }
    if (cells[x][y] == true) {
     if (neighborCount == 2 || neighborCount == 3) {
      newCells[x][y] = true;
     } else {
      newCells[x][y] = false;

     }
    } else {
     if (neighborCount == 3) {
      newCells[x][y] = true;
     } else {
      newCells[x][y] = false;

     }
    }
   }
  }
  cells = newCells;
  panel.setGrid(newCells);
  frame.repaint();
 }

 @Override
 public void mouseClicked(MouseEvent e) {}

 @Override
 public void mousePressed(MouseEvent e) {}

 @Override
 public void mouseExited(MouseEvent e) {}

 @Override
 public void mouseEntered(MouseEvent e) {}

 @Override
 public void mouseReleased(MouseEvent e) {
  double width = (double) panel.getWidth() / cells[0].length;
  double height = (double) panel.getHeight() / cells.length;
  int y = Math.min(cells[0].length - 1, (int)(e.getX() / width));
  int x = Math.min(cells.length - 1, (int)(e.getY() / height));
  cells[x][y] = !cells[x][y];
  frame.repaint();
 }
 @Override
 public void actionPerformed(ActionEvent e) {
  if (e.getSource().equals(step)) {
   step();
   frame.repaint();
  }
  if (e.getSource().equals(start)) {
   if (running == false) {
    running = true;
    Thread t = new Thread(this);
    t.start();
   }
  }
  if (e.getSource().equals(stop)) {
   running = false;
  }

 }
 @Override
 public void run() {
  while (running == true) {
   step();
   try {
    Thread.sleep(250);
   } catch (Exception ex) {
    ex.printStackTrace();
   }
  }
 }

 public static void main(String[] args) {
  new conwayslife();
 }
}