package cz.aimtec.auxilium.action;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cz.aimtec.auxilium.object.Member;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class MainActionBean implements ActionBean {

	private Member member;
	private Properties appProperties;
	private ActionBeanContext context;

	protected final Logger logger = LogManager.getLogger(getClass());
	private final String REQ_LIST = "/Request.action?requestList";

	@DefaultHandler
	public Resolution indexMain() {
		return new RedirectResolution(REQ_LIST);
	}

	@Override
	public ActionBeanContext getContext() {
		return context;
	}

	@Override
	public void setContext(ActionBeanContext cntx) {
		member = (Member) cntx.getRequest().getAttribute("member");
		appProperties = (Properties) cntx.getRequest().getAttribute("appProperties");
		logger.debug("member=" + member.getDetails());
		logger.debug("api=" + appProperties.getProperty("api.protocol") + "://" + appProperties.getProperty("api.host")
				+ ":" + appProperties.getProperty("api.port") + appProperties.getProperty("api.base"));
		context = cntx;
	}

	// Getters

	public Member getMember() {
		return member;
	}

}
