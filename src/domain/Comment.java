package domain;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Comment {

	private String name;
	private String email;
	private String comment;
	private int id;
	private Calendar datetime;
	private DateFormat formatHtml = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm");
	private DateFormat formatSql = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public Comment(String name, String email, String comment) {
		setName(name);
		setEmail(email);
		setComment(comment);

		datetime = Calendar.getInstance();
	}

	// overloaded for date from the database
	public Comment(String name, String email, String comment, Timestamp datetime, int id) {
		this(name, email, comment);
		setId(id);

		try {
			this.datetime.setTimeInMillis(datetime.getTime());
		} catch (Exception e) {

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDatetime() {
		return formatSql.format(datetime.getTime());

	}

	public String getDatetimeHtml() {
		return formatHtml.format(datetime.getTime());

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
