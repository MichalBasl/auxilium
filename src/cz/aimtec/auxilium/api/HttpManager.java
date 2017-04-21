package cz.aimtec.auxilium.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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
import net.sourceforge.stripes.action.ActionBeanContext;

public class HttpManager {

	private Member member;
	private Properties appProperties;
	protected final Logger logger = LogManager.getLogger(getClass());

	public HttpManager(ActionBeanContext cntx) {
		this.member = (Member) cntx.getRequest().getAttribute("member");
		this.appProperties = (Properties) cntx.getRequest().getAttribute("appProperties");
		logger.debug("initialization");
	}

	public JSONArray getRequestList() {

		JSONArray json;
		logger.debug("GET /requestList");

		try {

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
			logger.debug("StatusCode " + httpResponseCode);

			if (httpResponseCode == 200) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String inputLine;
				StringBuffer result = new StringBuffer();

				while ((inputLine = rd.readLine()) != null) {
					result.append(inputLine);
				}
				rd.close();

				String httpResponseBody = result.toString();
				logger.debug("ResponseBody " + httpResponseBody);

				json = new JSONArray(httpResponseBody);

			} else {
				json = new JSONArray();
			}

		} catch (URISyntaxException e) {
			json = new JSONArray();
			logger.error(e.getClass().getName() + " " + e.getMessage());
		} catch (ClientProtocolException e) {
			json = new JSONArray();
			logger.error(e.getClass().getName() + " " + e.getMessage());
		} catch (IOException e) {
			json = new JSONArray();
			logger.error(e.getClass().getName() + " " + e.getMessage());
		}

		return json;

	}

	public Boolean postRequest(JSONObject json) {

		Boolean out = false;
		logger.debug("POST /request");

		try {

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
			logger.debug("StatusCode " + httpResponseCode);

			if (httpResponseCode == 200) {
				out = true;
			}

		} catch (URISyntaxException e) {
			logger.error(e.getClass().getName() + " " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getClass().getName() + " " + e.getMessage());
		} catch (ClientProtocolException e) {
			logger.error(e.getClass().getName() + " " + e.getMessage());
		} catch (IOException e) {
			logger.error(e.getClass().getName() + " " + e.getMessage());
		}

		return out;
	}

	public JSONObject getUser() {

		JSONObject json;
		logger.debug("GET /user");

		try {

			URIBuilder builder = new URIBuilder();
			builder.setScheme(appProperties.getProperty("api.protocol"));
			builder.setHost(appProperties.getProperty("api.host"));
			builder.setPort(Integer.parseInt(appProperties.getProperty("api.port")));
			builder.setPath(appProperties.getProperty("api.base") + "/user");
			builder.addParameter("domain", member.getDomain());
			builder.addParameter("usrname", member.getUsername());
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
			logger.debug("StatusCode " + httpResponseCode);

			if (httpResponseCode == 200) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String inputLine;
				StringBuffer result = new StringBuffer();

				while ((inputLine = rd.readLine()) != null) {
					result.append(inputLine);
				}
				rd.close();

				String httpResponseBody = result.toString();
				logger.debug("ResponseBody " + httpResponseBody);

				json = new JSONObject(httpResponseBody);

			} else {
				json = new JSONObject();
			}

		} catch (URISyntaxException e) {
			json = new JSONObject();
			logger.error(e.getClass().getName() + " " + e.getMessage());
		} catch (ClientProtocolException e) {
			json = new JSONObject();
			logger.error(e.getClass().getName() + " " + e.getMessage());
		} catch (IOException e) {
			json = new JSONObject();
			logger.error(e.getClass().getName() + " " + e.getMessage());
		}

		return json;
	}

}
