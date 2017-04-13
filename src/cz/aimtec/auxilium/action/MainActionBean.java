package cz.aimtec.auxilium.action;

import cz.aimtec.auxilium.object.Member;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class MainActionBean implements ActionBean {

	private Member member;
	private ActionBeanContext context;
	protected final Logger logger = LogManager.getLogger(getClass());
	private static final String VIEW = "/WEB-INF/jsp/homepage.jsp";

	@DefaultHandler
	public Resolution indexMain() {
		return new ForwardResolution(VIEW);
	}

	@Override
	public ActionBeanContext getContext() {
		return context;
	}

	@Override
	public void setContext(ActionBeanContext cntxt) {
		member = (Member) cntxt.getRequest().getAttribute("member");
		logger.debug(member.getDetails());
		context = cntxt;
	}

	public Member getMember() {
		return member;
	}

}
