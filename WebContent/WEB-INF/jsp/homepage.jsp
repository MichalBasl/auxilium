<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>

<s:layout-definition>
  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  <html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <title><f:message key="web.title" /></title>
  <link href="images/style.css" rel="stylesheet" type="text/css" />
  <link href="images/datepicker.css" rel="stylesheet" type="text/css" />
  <link rel="shortcut icon" href="images/favicon.ico" />
  <script src="javascripts/jquery.js"></script>
  <script src="javascripts/datePicker.js"></script>
  <script src="javascripts/date_${pageContext.request.locale}.js"></script>
  <script src="javascripts/script.js"></script>
  </head>
  <body>
    <div id="headerPan">
      <s:link href="/Main.action" class="logo">
        <span>&nbsp;</span>
      </s:link>
      <p class="headerlink">
        Copyright &copy; 2017, Aimtec a.s.<br /> <span class="login">${actionBean.member.domain}/${actionBean.member.username} (${actionBean.member.role})</span>
      </p>
      <h1><f:message key="app.title" /></h1>
      <ul>
        <li class="first"><s:link href="/Request.action" event="requestNew">
            <img name="<f:message key="new.request" />" src="images/application_add.png" border="0" />
            <span>&nbsp;<f:message key="new.request" /></span>
          </s:link></li>
        <li><s:link href="/Request.action" event="requestList">
            <img name="<f:message key="list.requests" />" src="images/application_view_list.png" border="0" />
            <span>&nbsp;<f:message key="list.requests" /></span>
          </s:link></li>
      </ul>
    </div>
  
    <div id="bodyPan">
      <div id="leftPan"><s:layout-component name="column" /></div>
      <s:layout-component name="content" />
      <s:errors />
      <s:messages />
    </div>
  
  </body>
  </html>
</s:layout-definition>