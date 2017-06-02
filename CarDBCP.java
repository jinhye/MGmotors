package mgCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 * Car table java beans program through DBCP
 */
public class CarDBCP {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private DataSource ds = null;
	
	public CarDBCP(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Database connection
	public void connect(){
		try{
			con = ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Database disconnection
	public void disConnect(){
		if(pstmt != null){
			try{
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(con != null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	//All records return
	public ArrayList<CarEntity> getCarList(){
		connect();
		ArrayList<CarEntity> list = new ArrayList<>();
		String sql = "select * from Car";
		
		try{
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				CarEntity car = new CarEntity();
				
				car.setVin(rs.getString("vin"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setMileage(rs.getInt("mileage"));
				
				list.add(car);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		
		return list;
	}
}
