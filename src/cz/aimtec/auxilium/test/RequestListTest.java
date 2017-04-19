package cz.aimtec.auxilium.test;

import java.util.List;

import org.json.JSONArray;

import cz.aimtec.auxilium.object.Request;
import cz.aimtec.auxilium.object.RequestList;

public class RequestListTest {

	public static void main(String[] args) {

		JSONArray json = new JSONArray(
				"[{\"id\":5,\"date_start\":\"1491975507881\",\"date_up\":\"1492034400000\",\"title\":\"Title\",\"description\":\"User description\",\"note\":\"\",\"applicant\":\"AIMTEC/basm\",\"submitter\":\"AIMTEC/basm\",\"worker\":\"AIMTEC/smbdy\"},{\"id\":7,\"date_start\":\"1491975507881\",\"date_end\":\"\",\"date_up\":\"1492034400000\",\"title\":\"Title\",\"description\":\"User description\",\"note\":\"\",\"applicant\":\"AIMTEC/basm\",\"submitter\":\"AIMTEC/basm\",\"worker\":\"AIMTEC/smbdy\"},{\"id\":9,\"date_start\":\"1491975507881\",\"date_end\":\"\",\"date_up\":\"1492034400000\",\"title\":\"Title\",\"description\":\"User description\",\"note\":\"\",\"applicant\":\"AIMTEC/basm\",\"submitter\":\"AIMTEC/basm\",\"worker\":\"AIMTEC/smbdy\"}]");

		RequestList rqLst = new RequestList();
		rqLst.setRequestListFromJSON(json);
		
		List<Request> rql = rqLst.getRequestList();

		System.out.println(rql.size());
		System.out.println(rqLst.toString());

	}

}
