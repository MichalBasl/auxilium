package cz.aimtec.auxilium.object;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;

public class RequestList {

	private List<Request> requestList;
	protected final Logger logger = LogManager.getLogger(getClass());

	public RequestList() {
		this.requestList = new ArrayList<Request>();
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	public void setRequestListFromJSON(JSONArray json) {
		for (int i = 0; i < json.length(); i++) {
			JSONObject o = json.getJSONObject(i);
			Request rq = new Request();
			rq.setRequestFromJSON(o);
			this.requestList.add(rq);
		}
	}

}
