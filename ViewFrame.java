import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewFrame extends JFrame
{
	Container c;
	TextArea ta;
	JButton btnBack;
	
	ViewFrame()
	{
		c = getContentPane();
		ta = new TextArea(4, 40);
		btnBack = new JButton("Back");
		
		c.add(ta);
		c.add("South", btnBack);
		
		ta.setText(new DbHandler().viewEmployee());
		
		ActionListener a1 = (ae) -> { MainFrame a = new MainFrame(); dispose(); };
		btnBack.addActionListener(a1);
		
		setTitle("View Employee");
		setSize(350, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}