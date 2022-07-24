package com.edu.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DAO {

	public List<Member> selectMember(String name) {
		List<Member> list = new ArrayList<>();
		try {
			connect();
			String sql = "select * from members where member_id like '%" + name + "%'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Member meb = new Member();
				meb.setMemberId(rs.getString("member_id"));
				meb.setMemberRole(rs.getInt("member_role"));
			

				list.add(meb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}
}
