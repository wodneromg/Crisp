
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*我们继承WindowAdapter抽象类并实现MouseListener interface,
 *因此我们必须把MouseListener中的5个方法都实现，如果不想实现
 *可用匿名内部类的方法编写处理程序。
 */
public class MouseDemo1 extends WindowAdapter implements MouseListener {
	JFrame f = null;
	JButton b1 = null;
	JLabel label = null;

	public MouseDemo1() {
		f = new JFrame("MouseDemo1");
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new GridLayout(2, 1));
		b1 = new JButton("按钮");
		label = new JLabel("起始状态，还没有鼠标事件", JLabel.CENTER);
		b1.addMouseListener(this);
		contentPane.add(label);
		contentPane.add(b1);
		f.pack();
		f.show();
		f.addWindowListener(this);
	}

	public void mousePressed(MouseEvent e) {
		label.setText("你已经压下鼠标按钮");
	}

	public void mouseReleased(MouseEvent e) {
		label.setText("你已经放开鼠标按钮");
	}

	public void mouseEntered(MouseEvent e) {
		label.setText("鼠标光标进入按钮");
	}

	public void mouseExited(MouseEvent e) {
		label.setText("鼠标光标离开按钮");
	}

	public void mouseClicked(MouseEvent e) {
		label.setText("你已经按下按钮");
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public static void main(String[] args) {
		new MouseDemo1();
	}
}
