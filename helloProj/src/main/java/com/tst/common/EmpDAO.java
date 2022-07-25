package com.tst.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO extends DAO {
	
	public int updateMember(String name, String pass, String role) {
		int r=0;
		try {
			connect();
			String sql = "update members set member_password = ?, member_role= ? where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, role);
			pstmt.setString(3, name);
			
			r = pstmt.executeUpdate();
			System.out.println(r+"건 수정됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return r;
	}

	//USER_NAME, USER_PASS, ROLE => 입력
	public boolean insertMember(String name, String pass, String role) {
		String sql = "insert into members values(?,?,?)";
		connect();
		int r=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pass);
			pstmt.setString(3,role);
			
			r = pstmt.executeUpdate();
			
			System.out.println(r+"건 입력됨.");
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return false;
		
	}
	
	
	public List<Employee> getEmpInfo(String name) {
		String sql = "select * from employees where first_name =?";
		connect(); // conn 객체를 만들어줌
		List<Employee> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			// rs.next()->한건을 가지고 오겠습니다.
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getNString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setJobId(rs.getString("job_id"));

				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	/*
	 * 서블릿 : 사원정보 리스트 출력(EmpListServlet)
	 * 			사원번호, 이름, 이메일, 입사일자, 급여 , 직무(job_id)
	 * EMPDAO : empList();
	 */
	
	public List<Employee> getEmpList(){
		List<Employee> list = new ArrayList<>();
		try {
			connect();
			String sql = "select employee_id, first_name,email,hire_date,salary,job_id from employees";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery(sql);
			
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				emp.setJobId(rs.getString("job_id"));

				list.add(emp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		} return list;
	} 
	
	

	public List<Employee> selectEmail(String email) {
		List<Employee> list = new ArrayList<>();
		try {
			connect(); // conn 객체를 만들어줌
			String sql = "select * from employees where email like '%"+email+"%'";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);

			// rs.next()->한건을 가지고 오겠습니다.
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setJobId(rs.getString("job_id"));

				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	

	
	
	
}
