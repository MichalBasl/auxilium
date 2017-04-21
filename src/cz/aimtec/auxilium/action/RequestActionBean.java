package cz.aimtec.auxilium.action;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cz.aimtec.auxilium.api.HttpManager;
import cz.aimtec.auxilium.object.Member;
import cz.aimtec.auxilium.object.Request;
import cz.aimtec.auxilium.object.RequestList;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

public class RequestActionBean implements ActionBean {

	private int id;
	@Validate(required = true, maxlength = 255)
	private String title;
	@Validate(required = true)
	private String description;
	private String note;
	private Date date_start;
	@Validate(required = true)
	private Date date_up;
	private Date date_end;
	@Validate(required = true, maxlength = 255)
	private String applicant;
	@Validate(required = true, maxlength = 255)
	private String submitter;
	@Validate(required = true, maxlength = 255)
	private String worker;

	private HttpManager api;
	private Member member;
	private Request requestForm;
	private List<Request> requestList;
	private ActionBeanContext context;

	protected final Logger logger = LogManager.getLogger(getClass());
	private final String REQ_LIST = "/Request.action?requestList";
	private final String LIST = "/WEB-INF/jsp/requestList.jsp";
	private final String CREATE = "/WEB-INF/jsp/requestNew.jsp";
	private final String EDIT = "/WEB-INF/jsp/requestEdit.jsp";

	@Override
	public ActionBeanContext getContext() {
		return context;
	}

	@Override
	public void setContext(ActionBeanContext cntx) {
		member = (Member) cntx.getRequest().getAttribute("member");
		api = new HttpManager(cntx);
		context = cntx;
	}

	@DontValidate
	public Resolution requestList() {

		JSONArray json = api.getRequestList();

		RequestList rqLst = new RequestList();
		rqLst.setRequestListFromJSON(json);
		requestList = rqLst.getRequestList();

		return new ForwardResolution(LIST);
	}

	@DontValidate
	public Resolution requestNew() {
		return new ForwardResolution(CREATE);
	}

	@DontValidate
	public Resolution requestEdit() {
		return new ForwardResolution(EDIT);
	}

	public Resolution requestCreate() {
		return null;
	}

	public Resolution setRequestForm() throws Exception {

		requestForm = new Request();
		requestForm.setId(id);
		requestForm.setTitle(title);
		requestForm.setDescription(description);
		requestForm.setNote(note);
		requestForm.setApplicant(applicant);
		requestForm.setSubmitter(submitter);
		requestForm.setWorker(worker);
		requestForm.setDate_start(date_start);
		requestForm.setDate_up(date_up);
		requestForm.setDate_end(date_end);

		JSONObject json = requestForm.getRequestJSON();
		if (!api.postRequest(json)) {
			logger.error("request is not processed");
		}

		return new RedirectResolution(REQ_LIST);
	}

	// Getters

	public Member getMember() {
		return member;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Date getDate_end() {
		return date_end;
	}

	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

}
