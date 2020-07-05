import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

class MainFrame extends JFrame
{
	Container c;
	JButton btnAdd, btnView, btnUpdate, btnDelete;
	
	MainFrame()
	{
		c = getContentPane();
		c.setLayout(new FlowLayout());
		
		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);
		
		setTitle("EMP Management System");
		setSize(350, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		ActionListener a1 = (ae) -> { AddFrame a = new AddFrame(false); dispose(); };
		btnAdd.addActionListener(a1);
		
		ActionListener a2 = (ae) -> { ViewFrame a = new ViewFrame(); dispose(); };
		btnView.addActionListener(a2);
		
		ActionListener a3 = (ae) -> { AddFrame a = new AddFrame(true); dispose(); };
		btnUpdate.addActionListener(a3);
		
		ActionListener a4 = (ae) -> { DeleteFrame a = new DeleteFrame(); dispose(); };
		btnDelete.addActionListener(a4);
	}
	
	public static void main(String args[])
	{
		MainFrame m = new MainFrame();
	}
}

class DbHandler
{
	public static final String CURRENT_DB_TYPE = "MYSQL";
	
	private Connection getDbConnection(String database) throws SQLException
	{
		Connection con = null;
		if(database.equals("ORACLE"))
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1", "C##LPORTAL", "lportal");
		}
		else if(database.equals("MYSQL"))
		{
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost/ems?user=root&password=");
		}
		return con;
	}
	
	public void addEmployee(int id, String name)
	{
		try
		{
			Connection con = getDbConnection(CURRENT_DB_TYPE);
			String sql = "INSERT INTO employee VALUES (?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, name);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " records inserted");
			con.close();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(), "Issue: " + e);
		}
	}
	
	public String viewEmployee()
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			Connection con = getDbConnection(CURRENT_DB_TYPE);
			String sql = "SELECT * FROM employee";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String employeeInfo = String.format("Eid: %3d \t EName: %s", id, name);
				sb.append(employeeInfo + "\n");
				//sb.append("Eid: " + id + " EName: " + name + "\n");
			}
			rs.close();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(), "Issue: " + e);
		}
		return sb.toString();
	}
	
	public void updateEmployee(int id, String name)
	{
		try
		{
			if(checkEmployeeExists(id))
			{
				Connection con = getDbConnection(CURRENT_DB_TYPE);
				String sql = "UPDATE employee SET name = ? WHERE id = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, id);
				int r = pst.executeUpdate();
				JOptionPane.showMessageDialog(new JDialog(), r + " records updated");
				con.close();
			}
			else
			{
				JOptionPane.showMessageDialog(new JDialog(), "Issue: Employee with ID = " + id + " does not exist.");
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(), "Issue: " + e);
		}
	}
	
	public void deleteEmployee(int id)
	{
		try
		{
			if(checkEmployeeExists(id))
			{
				Connection con = getDbConnection(CURRENT_DB_TYPE);
				String sql = "DELETE FROM employee WHERE id = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				int r = pst.executeUpdate();
				JOptionPane.showMessageDialog(new JDialog(), r + " record deleted");
				con.close();
			}
			else
			{
				JOptionPane.showMessageDialog(new JDialog(), "Issue: Employee with ID = " + id + " does not exist.");
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(), "Issue: " + e);
		}
	}
	
	public boolean checkEmployeeExists(int id) throws SQLException
	{
		Connection con = getDbConnection(CURRENT_DB_TYPE);
		String sql = "SELECT * FROM employee WHERE id = '" + id + "'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) return true;
		return false;
	}
	
}