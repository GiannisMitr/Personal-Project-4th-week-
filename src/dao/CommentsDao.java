package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Comment;

public class CommentsDao {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void post(Comment comment) throws Exception {

		try {
			String sql;
			sql = "INSERT INTO comments (name,email,comment,datetime) " + "VALUES (?,?,?,?);";

			PreparedStatement stmt = Database.open().prepareStatement(sql);
			stmt.setString(1, EscapeHtml.escape(comment.getName()));		
			stmt.setString(2, EscapeHtml.escape(comment.getEmail()));
			stmt.setString(3, EscapeHtml.escape(comment.getComment()));
			stmt.setString(4, comment.getDatetime());

			stmt.executeUpdate();
			stmt.close();

		}

		catch (Exception e) {
			throw new Exception("An error occured while posting comment to database: " + e.getMessage());
		}

	}

	public List<Comment> bring(String dateFrom, String dateTo, String order) throws Exception {

		try {

			String sqlquery = "SELECT * FROM giannism.comments" + " where date(datetime) <=? and date(datetime) >=?"
					+ " Order by datetime ";

			if (order.equals("Old First")) {
				sqlquery += "asc;";
			} else {
				sqlquery += "desc;";
			}

			PreparedStatement stmt = Database.open().prepareStatement(sqlquery);
			if (dateTo == null || dateTo.length() == 0) {
				stmt.setString(1, "CURDATE()");
			} else {
				stmt.setString(1, dateTo);
			}

			if (dateFrom == null || dateFrom.length() == 0) {
				stmt.setString(2, "");
			} else {
				stmt.setString(2, dateFrom);
			}

			ResultSet rs = stmt.executeQuery();

			List<Comment> commentList = new ArrayList<Comment>();

			while (rs.next()) {

				commentList.add(new Comment(rs.getString("name"), rs.getString("email"), rs.getString("comment"),
						rs.getTimestamp("datetime"), rs.getInt("idcomments")));

			}

			rs.close();
			stmt.close();

			return commentList;

		} catch (Exception e) {
			throw new Exception("An error occured while getting comments from database: " + e.getMessage());
		}

	}

	public void delete(int id) throws Exception {

		try {
			String sql;
			sql = "DELETE FROM comments WHERE idcomments=?;";

			PreparedStatement stmt = Database.open().prepareStatement(sql);
			stmt.setInt(1, id);

			stmt.executeUpdate();
			stmt.close();

		}

		catch (Exception e) {
			throw new Exception("An error occured while deleting comment from database: " + e.getMessage());
		}

	}

}
