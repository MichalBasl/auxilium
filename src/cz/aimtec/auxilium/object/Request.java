package cz.aimtec.auxilium.object;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class Request {

	private int id;
	private String applicant;
	private Date date_end;
	private Date date_start;
	private Date date_up;
	private String description;
	private String note;
	private String submitter;
	private String title;
	private String worker;
	protected final Logger logger = LogManager.getLogger(getClass());

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getDate_end() {
		return date_end;
	}

	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}

	public Date getDate_start() {
		return date_start;
	}

	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}

	public Date getDate_up() {
		return date_up;
	}

	public void setDate_up(Date date_up) {
		this.date_up = date_up;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public void setRequestFromJSON(JSONObject json) {

		id = json.getInt("id");

		applicant = json.getString("applicant");
		description = json.getString("description");
		note = json.getString("note");
		submitter = json.getString("submitter");
		title = json.getString("title");
		worker = json.getString("worker");

		try {
			date_start = new Timestamp(Long.parseLong(json.getString("date_start")));
		} catch (NumberFormatException e) {
			logger.error("cannot cast date_start " + e.getMessage());
		}

		try {
			date_up = new Timestamp(Long.parseLong(json.getString("date_up")));
		} catch (NumberFormatException e) {
			logger.error("cannot cast date_up " + e.getMessage());
		}

		try {
			date_end = new Timestamp(Long.parseLong(json.getString("date_end")));
		} catch (NumberFormatException e) {
			logger.error("cannot cast date_end " + e.getMessage());
		}

	}
}
