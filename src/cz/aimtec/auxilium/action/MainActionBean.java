package cz.aimtec.auxilium.action;

import cz.aimtec.auxilium.object.Member;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class MainActionBean implements ActionBean {

	private Member member;
	private String formItem;
	private Properties appProperties;
	private Properties hlpfProperties;
	private ActionBeanContext context;

	protected final Logger logger = LogManager.getLogger(getClass());
	private final String REQ_LIST = "/Request.action?requestList";
	private final String HLP_FORM = "/WEB-INF/jsp/requestHelpFormItem.jsp";

	@Override
	public ActionBeanContext getContext() {
		return context;
	}

	@Override
	public void setContext(ActionBeanContext cntx) {
		member = (Member) cntx.getRequest().getAttribute("member");
		appProperties = (Properties) cntx.getRequest().getAttribute("appProperties");
		hlpfProperties = (Properties) cntx.getRequest().getAttribute("hlpfProperties");
		logger.debug("member=" + member.getDetails());
		logger.debug("api=" + appProperties.getProperty("api.protocol") + "://" + appProperties.getProperty("api.host")
				+ ":" + appProperties.getProperty("api.port") + appProperties.getProperty("api.base"));
		context = cntx;
	}

	@DefaultHandler
	public Resolution indexMain() {
		return new RedirectResolution(REQ_LIST);
	}

	public Resolution helpFormItem() {
		formItem = hlpfProperties.getProperty(formItem);
		return new ForwardResolution(HLP_FORM);
	}

	// Getters

	public Member getMember() {
		return member;
	}

	public String getFormItem() {
		return formItem;
	}

	public void setFormItem(String formItem) {
		this.formItem = formItem;
	}

}
