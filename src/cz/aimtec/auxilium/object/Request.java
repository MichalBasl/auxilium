package cz.aimtec.auxilium.object;

import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}

	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}

	public void setDate_up(Date date_up) {
		this.date_up = date_up;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public int getId() {
		return id;
	}

	public String getApplicant() {
		return applicant;
	}

	public Date getDate_end() {
		return date_end;
	}

	public Date getDate_start() {
		return date_start;
	}

	public Date getDate_up() {
		return date_up;
	}

	public String getDescription() {
		return description;
	}

	public String getNote() {
		return note;
	}

	public String getSubmitter() {
		return submitter;
	}

	public String getTitle() {
		return title;
	}

	public String getWorker() {
		return worker;
	}

	public void setRequestFromJSON(JSONObject json) {

		this.setId(json.getInt("id"));

		try {
			this.setApplicant(json.getString("applicant"));
		} catch (JSONException e) {
			logger.error("cannot cast applicant " + e.getMessage());
		}

		try {
			this.setDescription(json.getString("description"));
		} catch (JSONException e) {
			logger.error("cannot cast description " + e.getMessage());
		}

		try {
			this.setSubmitter(json.getString("submitter"));
		} catch (JSONException e) {
			logger.error("cannot cast submitter " + e.getMessage());
		}

		try {
			this.setTitle(json.getString("title"));
		} catch (JSONException e) {
			logger.error("cannot cast title " + e.getMessage());
		}

		try {
			this.setNote(json.getString("note"));
		} catch (JSONException e) {
			logger.error("cannot cast note " + e.getMessage());
		}

		try {
			this.setWorker(json.getString("worker"));
		} catch (JSONException e) {
			logger.error("cannot cast worker " + e.getMessage());
		}

		try {
			this.setDate_start(new Timestamp(json.getLong("date_start")));
		} catch (JSONException e) {
			logger.error("cannot cast date_start " + e.getMessage());
		}

		try {
			this.setDate_up(new Timestamp(json.getLong("date_up")));
		} catch (JSONException e) {
			logger.error("cannot cast date_up " + e.getMessage());
		}

		try {
			this.setDate_end(new Timestamp(json.getLong("date_end")));
		} catch (JSONException e) {
			logger.error("cannot cast date_end " + e.getMessage());
		}

	}
	
	public JSONObject getRequestJSON() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("applicant", this.applicant);
		json.put("date_end", this.date_end);
		json.put("date_start", this.date_start);
		json.put("date_up", this.date_up);
		json.put("description", this.description);
		json.put("note", this.note);
		json.put("submitter", this.submitter);
		json.put("title", this.title);
		json.put("worker", this.worker);
		return json;
	}

}
