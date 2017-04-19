package cz.aimtec.auxilium.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import cz.aimtec.auxilium.object.Member;
import cz.aimtec.auxilium.object.Request;
import cz.aimtec.auxilium.object.RequestList;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class RequestActionBean implements ActionBean {

	private Member member;
	private List<Request> requestList;
	private Properties appProperties;
	private String apiUrl;
	private ActionBeanContext context;

	protected final Logger logger = LogManager.getLogger(getClass());
	private final String LIST = "/WEB-INF/jsp/requestList.jsp";

	@Override
	public ActionBeanContext getContext() {
		return context;
	}

	@Override
	public void setContext(ActionBeanContext cntx) {
		member = (Member) cntx.getRequest().getAttribute("member");
		appProperties = (Properties) cntx.getRequest().getAttribute("appProperties");
		apiUrl = appProperties.getProperty("api.protocol") + "://" + appProperties.getProperty("api.host") + ":"
				+ appProperties.getProperty("api.port") + appProperties.getProperty("api.base");
		context = cntx;
	}

	@DontValidate
	public Resolution requestList() throws Exception {

		URL api = new URL(apiUrl + "/requestList");
		HttpURLConnection con = (HttpURLConnection) api.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("X-UserName", member.getDomainUsername());
		con.setRequestProperty("X-UserRole", member.getRole());

		int httpResponseCode = con.getResponseCode();
		logger.debug("httpResponseCode=" + httpResponseCode);

		if (httpResponseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String httpResponseBody = response.toString();
			logger.debug("httpResponseBody=" + httpResponseBody);

			JSONArray json = new JSONArray(httpResponseBody);
			
			RequestList rqLst = new RequestList();
			rqLst.setRequestListFromJSON(json);
			requestList = rqLst.getRequestList();
		}

		return new ForwardResolution(LIST);
	}

	// Getters

	public Member getMember() {
		return member;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

}
