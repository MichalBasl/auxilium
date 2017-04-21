package cz.aimtec.auxilium.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

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

	private Member member;
	private Request requestForm;
	private List<Request> requestList;
	private Properties appProperties;
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
		appProperties = (Properties) cntx.getRequest().getAttribute("appProperties");
		context = cntx;
	}

	@DontValidate
	public Resolution requestList() throws Exception {

		URIBuilder builder = new URIBuilder();
		builder.setScheme(appProperties.getProperty("api.protocol"));
		builder.setHost(appProperties.getProperty("api.host"));
		builder.setPort(Integer.parseInt(appProperties.getProperty("api.port")));
		builder.setPath(appProperties.getProperty("api.base") + "/requestList");
		builder.addParameter("client_id", appProperties.getProperty("client.id"));
		builder.addParameter("client_secret", appProperties.getProperty("client.secret"));
		URI uri = builder.build();
		logger.debug(uri.toString());

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(uri);
		request.addHeader("Accept", "application/json");
		request.addHeader("X-UserName", member.getDomainUsername());
		request.addHeader("X-UserRole", member.getRole());

		HttpResponse response = client.execute(request);
		int httpResponseCode = response.getStatusLine().getStatusCode();
		logger.debug("httpResponseCode=" + httpResponseCode);

		if (httpResponseCode == 200) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String inputLine;
			StringBuffer result = new StringBuffer();

			while ((inputLine = rd.readLine()) != null) {
				result.append(inputLine);
			}
			rd.close();

			String httpResponseBody = result.toString();
			logger.debug("httpResponseBody=" + httpResponseBody);

			JSONArray json = new JSONArray(httpResponseBody);

			RequestList rqLst = new RequestList();
			rqLst.setRequestListFromJSON(json);
			requestList = rqLst.getRequestList();
		}

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

		URIBuilder builder = new URIBuilder();
		builder.setScheme(appProperties.getProperty("api.protocol"));
		builder.setHost(appProperties.getProperty("api.host"));
		builder.setPort(Integer.parseInt(appProperties.getProperty("api.port")));
		builder.setPath(appProperties.getProperty("api.base") + "/request");
		builder.addParameter("client_id", appProperties.getProperty("client.id"));
		builder.addParameter("client_secret", appProperties.getProperty("client.secret"));
		URI uri = builder.build();
		logger.debug(uri.toString());

		StringEntity body = new StringEntity(json.toString());

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(uri);
		request.addHeader("Content-Type", "application/json");
		request.addHeader("X-UserName", member.getDomainUsername());
		request.addHeader("X-UserRole", member.getRole());
		request.setEntity(body);

		HttpResponse response = client.execute(request);
		int httpResponseCode = response.getStatusLine().getStatusCode();
		logger.debug("httpResponseCode=" + httpResponseCode);

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
