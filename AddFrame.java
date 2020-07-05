import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddFrame extends JFrame
{
	Container c;
	JLabel lblId, lblName;
	JTextField txtId, txtName;
	JButton btnSave, btnBack;
	JPanel p1, p2;
	
	AddFrame(boolean isUpdate)
	{
		c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		p1 = new JPanel();
		lblId = new JLabel("ID");
		txtId = new JTextField(3);
		lblName = new JLabel("NAME");
		txtName = new JTextField(10);
		
		p1.add(lblId);
		p1.add(txtId);
		p1.add(lblName);
		p1.add(txtName);
		c.add(p1);
		
		p2 = new JPanel();
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");
		p2.add(btnSave);
		p2.add(btnBack);
		c.add(p2);
		
		ActionListener a1 = (ae) -> { MainFrame a = new MainFrame(); dispose(); };
		btnBack.addActionListener(a1);
		
		ActionListener a2 = (ae) -> {
			String id = txtId.getText();
			String name = txtName.getText();
			new DbHandler().addEmployee(Integer.parseInt(id), name);
		};
		
		ActionListener a3 = (ae) -> {
			String id = txtId.getText();
			String name = txtName.getText();
			new DbHandler().updateEmployee(Integer.parseInt(id), name);
		};
		
		btnSave.addActionListener((isUpdate) ? a3 : a2);
		
		if(isUpdate)	setTitle("Update Employee");
		else			setTitle("Add Employee");
		setSize(350, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
}