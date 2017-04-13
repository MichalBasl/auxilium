<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>HelpDesk</title>
<link href="images/style.css" rel="stylesheet" type="text/css" />
<link href="images/datepicker.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/favicon.ico" />
<script src="javascripts/jquery.js"></script>
<script src="javascripts/datePicker.js"></script>
<script src="javascripts/date.js"></script>
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
    <h1>Interní IT HelpDesk aplikace</h1>
    <ul>
      <li class="first"><s:link href="/Request.action" event="newRequest">
          <img name="Nový požadavek" src="images/application_add.png" border="0" />
          <span>&nbsp;nový požadavek</span>
        </s:link></li>
      <li><s:link href="/Request.action" event="listRequest">
          <img name="Nový požadavek" src="images/application_view_list.png" border="0" />
          <span>&nbsp;seznam požadavků</span>
        </s:link></li>
    </ul>
  </div>

  <div id="bodyPan">
    <div id="leftPan"></div>
    <s:errors />
    <s:messages />
  </div>

</body>
</html>

