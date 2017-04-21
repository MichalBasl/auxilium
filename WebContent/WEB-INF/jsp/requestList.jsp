<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>

<s:layout-render name="/WEB-INF/jsp/homepage.jsp">

  <s:layout-component name="column">
    <c:choose>
      <c:when test="${member.role == 'admin'}">
        <h1><f:message key="display" /></h1>
        <ul>
          <li><s:link href="/Request.action" event="listRequestAll"><f:message key="list.all.requests" /></s:link></li>
        </ul>
      </c:when>
    </c:choose>
  </s:layout-component>
  
  <s:layout-component name="content">
    <h1><f:message key="list.requests" /></h1>
    <table>
      <tbody>
        <tr>
          <th>#ID</th>
          <th><f:message key="cz.aimtec.auxilium.action.RequestActionBean.date_start" /></th>
          <th><f:message key="cz.aimtec.auxilium.action.RequestActionBean.title" /></th>
          <th><f:message key="cz.aimtec.auxilium.action.RequestActionBean.date_up" /></th>
          <th><f:message key="cz.aimtec.auxilium.action.RequestActionBean.applicant" /></th>
          <th><f:message key="cz.aimtec.auxilium.action.RequestActionBean.submitter" /></th>
          <th><f:message key="cz.aimtec.auxilium.action.RequestActionBean.worker" /></th>
          <th><f:message key="cz.aimtec.auxilium.action.RequestActionBean.date_end" /></th>
        </tr>
        <c:forEach items="${actionBean.requestList}" var="request" varStatus="status">
          <tr class="highlighted">
            <td>${request.id}</td>
            <td><s:format value="${request.date_start}" formatPattern="dd.MM.yyyy HH:mm" /></td>
            <td><s:link href="/Request.action" event="requestEdit">
                <s:param name="id">${request.id}</s:param>${request.title}</s:link></td>
            <td><s:format value="${request.date_up}" formatPattern="dd.MM.yyyy" /></td>
            <td>${request.applicant}</td>
            <td>${request.submitter}</td>
            <td>${request.worker}</td>
            <td><s:format value="${request.date_end}" formatPattern="dd.MM.yyyy HH:mm" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </s:layout-component>

</s:layout-render>