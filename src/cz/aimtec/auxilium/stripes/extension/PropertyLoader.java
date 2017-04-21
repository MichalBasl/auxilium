package cz.aimtec.auxilium.stripes.extension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cz.aimtec.auxilium.api.HttpManager;
import cz.aimtec.auxilium.object.Member;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

@Intercepts(LifecycleStage.RequestInit)
public class PropertyLoader implements Interceptor {

	protected final Logger logger = LogManager.getLogger(getClass());

	@Override
	public Resolution intercept(ExecutionContext context) {

		// Original resolution
		Resolution resolution = context.getResolution();

		// Locale
		String lc = context.getActionBeanContext().getRequest().getLocale().toString();
		logger.debug("locale " + lc.toString());

		// Establish root path to properties
		String path = this.getClass().getClassLoader().getResource("").getPath();
		logger.debug("path " + path);

		// Application properties
		try {
			// Read properties
			String appProp = "Application.properties";
			logger.debug("read properties file " + appProp);
			Properties appProperties = new Properties();
			FileInputStream appInput = new FileInputStream(new File(path + appProp));
			appProperties.load(appInput);
			// Add properties to context
			context.getActionBeanContext().getRequest().setAttribute("appProperties", appProperties);
		} catch (IOException eApp) {
			logger.error("appProperties error - " + eApp.getMessage());
		}

		// HelpForm properties
		try {
			// Read properties
			String hlpfProp = "HelpFormItems_" + lc + ".properties";
			logger.debug("read properties file " + hlpfProp);
			Properties hlpfProperties = new Properties();
			FileInputStream hlpfInput = new FileInputStream(new File(path + hlpfProp));
			hlpfProperties.load(hlpfInput);
			// Add properties to context
			context.getActionBeanContext().getRequest().setAttribute("hlpfProperties", hlpfProperties);
		} catch (IOException eHlpf) {
			logger.error("hlpfProperties error - " + eHlpf.getMessage());
		}

		// Add role to member
		Member member = (Member) context.getActionBeanContext().getRequest().getAttribute("member");
		HttpManager api = new HttpManager(context.getActionBeanContext());
		JSONObject user = api.getUser();

		String role = "user";
		JSONArray rls = user.getJSONArray("role");
		for (int i = 0; i < rls.length(); i++) {
			String r = rls.getString(i);
			if (r.equals("admin")) {
				role = r;
			}
		}
		member.setRole(role);

		logger.debug("set role of " + member.getDomainUsername() + " to " + member.getRole());

		// Add member to context
		context.getActionBeanContext().getRequest().setAttribute("member", member);

		// Proceed resolution
		try {
			resolution = context.proceed();
		} catch (Exception eProceed) {
			logger.error(eProceed.getMessage());
		}
		return resolution;
	}

}
