import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame
{
	Container c;
	JLabel lblId;
	JTextField txtId;
	JButton btnDelete, btnBack;
	JPanel p1, p2;
	
	DeleteFrame()
	{
		c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		p1 = new JPanel();
		lblId = new JLabel("ID");
		txtId = new JTextField(3);
		p1.add(lblId);
		p1.add(txtId);
		c.add(p1);
		
		p2 = new JPanel();
		btnDelete = new JButton("Delete");
		btnBack = new JButton("Back");
		p2.add(btnDelete);
		p2.add(btnBack);
		c.add(p2);
		
		ActionListener a1 = (ae) -> { MainFrame a = new MainFrame(); dispose(); };
		btnBack.addActionListener(a1);
		
		ActionListener a2 = (ae) -> {
			String id = txtId.getText();
			new DbHandler().deleteEmployee(Integer.parseInt(id));
		};
		btnDelete.addActionListener(a2);
		
		setTitle("Delete Employee");
		setSize(350, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
}