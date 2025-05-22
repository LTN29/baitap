package client.models.reps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.models.entities.Category;
import client.models.utils.DBUtil;

//xử lý database (DAO = Data Access Object)
public class CategoryDAO {
	
	public static List<Category> getAll() throws SQLException {
	    List<Category> list = new ArrayList<>();
	    String sql = "SELECT * FROM Category";
	    try (
	        Connection conn = DBUtil.getInstance().getConnect();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	    ) {
	        while (rs.next()) {
	            Category c = new Category(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getBoolean("active")
	            );
	            list.add(c);
	        }
	    }
	    return list;
	}
	
	public static int insert(Category c) throws SQLException{
		String sql="INSERT INTO Category(Name,active) VALUES (?,?)";
		try(
				Connection conn=DBUtil.getInstance().getConnect();
				PreparedStatement stmt=conn.prepareStatement(sql);
				){
			stmt.setString(1, c.get_name());
			stmt.setBoolean(2,c.is_active());
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows == 0) {
				throw new SQLException("Insert Category failed, no rows affected");
			}
			try (ResultSet generatedKeys=stmt.getGeneratedKeys()){
				if(generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}else {
					throw new SQLException("Insert category failed, no ID obtained.");
				}
			}
			
			
		}
	}
	
	public static int update(Category c) throws SQLException {
	    String sql = "UPDATE Category SET name = ?, active = ? WHERE id = ?";
	    try (
	        Connection conn = DBUtil.getInstance().getConnect();
	        PreparedStatement stmt = conn.prepareStatement(sql)
	    ) {
	        stmt.setString(1, c.get_name());
	        stmt.setBoolean(2, c.is_active());
	        stmt.setInt(3, c.get_id());
	        return stmt.executeUpdate() ; 
	    }
	}
	

	public static int deleteById(int id) throws SQLException {
		String sql= "DELETE From Category WHERE id = ?";
		try(
				Connection conn = DBUtil.getInstance().getConnect();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setInt(id, 1);
			return stmt.executeUpdate();
		}
		
	}

}
