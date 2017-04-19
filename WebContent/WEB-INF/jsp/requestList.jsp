<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>

<s:layout-render name="/WEB-INF/jsp/homepage.jsp">

  <s:layout-component name="content">
    <h1>Seznam požadavků</h1>
    <table width="100%">
      <tbody>
        <tr>
          <th>id</th>
          <th>datum založení</th>
          <th>titulek požadavku</th>
          <th>požadovaný termín</th>
          <th>žadatel</th>
          <th>zadavatel</th>
          <th>řešitel</th>
          <th>datum uzavření</th>
        </tr>
        <c:forEach items="${actionBean.requestList}" var="request" varStatus="status">
          <tr class="highlighted">
            <td>${request.id}</td>
            <td><s:format value="${request.date_start}" formatPattern="dd.MM.yyyy HH:mm" /></td>
            <td><s:link href="/Request.action" event="editRequest">
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