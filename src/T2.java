import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class T2 {
	public static void main(String[] args) {
		new Frame11();
	}
}

class Frame11 extends JFrame {

	private static final long serialVersionUID = 960670063256235391L;

	private final JLabel usernameLabel = new JLabel("this is frame11");
    private final JButton loginButton = new JButton("登陆");

    protected Frame11() {
    	setTitle("frame22");
    	setLayout(new FlowLayout());
    	add(usernameLabel);
        add(loginButton);
        
        //frame1按钮事件
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new Frame22();
					}
				});
				Frame11.this.dispose();
			}
		});
        
        setSize(200, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation( (screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
}

class Frame22 extends JFrame {
	
	private static final long serialVersionUID = 6055147068340138622L;
	
	private final JLabel q1 = new JLabel("this is frame22");
	
	protected Frame22() {
		setTitle("frame22");
		setLayout(new FlowLayout());
		add(q1);
		setSize(200, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation( (screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}
	
}
