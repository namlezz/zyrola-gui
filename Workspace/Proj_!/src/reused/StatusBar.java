package reused;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public
class StatusBar extends JPanel {

	private JLabel status;

	public StatusBar() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(10, 23));

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setOpaque(false);

		status = new JLabel();
		rightPanel.add(status, BorderLayout.LINE_START);

		add(rightPanel);
		setBackground(SystemColor.control);
	}

	public void message(String msg) {
		status.setText(msg);
		Timer t = new Timer(5000, new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	status.setText(null);
            }
        });
        t.setRepeats(false);
        t.start();
	}
	

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int y = 0;
		g.setColor(new Color(156, 154, 140));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(196, 194, 183));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(218, 215, 201));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(233, 231, 217));
		g.drawLine(0, y, getWidth(), y);

		y = getHeight() - 3;
		g.setColor(new Color(233, 232, 218));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(233, 231, 216));
		g.drawLine(0, y, getWidth(), y);
		y = getHeight() - 1;
		g.setColor(new Color(221, 221, 220));
		g.drawLine(0, y, getWidth(), y);

	}

}
