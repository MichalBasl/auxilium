package cz.aimtec.auxilium.stripes.extension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

		// Establish root path to properties
		String path = this.getClass().getClassLoader().getResource("").getPath();
		logger.debug("path " + path);

		// Application properties
		try {
			// Read properties
			String appPropPath = path + "app.properties";
			logger.debug("read properties file " + appPropPath);
			Properties appProperties = new Properties();
			FileInputStream appInput = new FileInputStream(new File(appPropPath));
			appProperties.load(appInput);
			// Add properties to context
			context.getActionBeanContext().getRequest().setAttribute("appProperties", appProperties);
		} catch (IOException eApp) {
			logger.error("appProperties error - " + eApp.getMessage());
		}

		// User properties and member
		try {
			// Read properties
			String userPropPath = path + "user.properties";
			logger.debug("read properties file " + userPropPath);
			Properties userProperties = new Properties();
			FileInputStream userInput = new FileInputStream(new File(userPropPath));
			userProperties.load(userInput);
			// Add role to member
			Member member = (Member) context.getActionBeanContext().getRequest().getAttribute("member");
			String role = userProperties.getProperty(member.getDomainUsername(), "user");
			member.setRole(role);
			logger.debug("set role of " + member.getDomainUsername() + " to " + role);
			// Add member to context
			context.getActionBeanContext().getRequest().setAttribute("member", member);
		} catch (IOException eUser) {
			logger.error("userProperties error - " + eUser.getMessage());
		}

		// Proceed resolution
		try {
			resolution = context.proceed();
		} catch (Exception eProceed) {
			logger.error(eProceed.getMessage());
		}
		return resolution;
	}

}
