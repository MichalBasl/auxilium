package cz.aimtec.auxilium.stripes.extension;

import java.io.File;
import java.io.FileInputStream;
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
public class ConfigurationLoader implements Interceptor {

	protected final Logger logger = LogManager.getLogger(getClass());

	@Override
	public Resolution intercept(ExecutionContext context) throws Exception {

		String separator = File.separator;
		String path = context.getActionBeanContext().getServletContext()
				.getRealPath(separator + "WEB-INF" + separator + "classes");
		logger.debug("path " + path);

		FileInputStream userInput = new FileInputStream(new File(path + separator + "user.properties"));
		logger.debug("read " + path + separator + "user.properties");
		
		Properties userProperties = new Properties();

		userProperties.load(userInput);

		Member member = (Member) context.getActionBeanContext().getRequest().getAttribute("member");

		String role = userProperties.getProperty(member.getDomainUsername(), "unknown");
		member.setRole(role);

		context.getActionBeanContext().getRequest().setAttribute("member", member);
		logger.debug("set attribute <member> " + member.getDetails());

		Resolution resolution = context.proceed();
		return resolution;

	}

}
