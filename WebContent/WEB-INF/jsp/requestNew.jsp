<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>

<s:layout-render name="/WEB-INF/jsp/homepage.jsp">

  <s:layout-component name="column">
    <h1><f:message key="information" /></h1>
    <div id="helpFormItem"></div>
  </s:layout-component>

  <s:layout-component name="content">
    <h1><f:message key="create.request" /></h1>
    <s:form action="/Request.action" class="form">
      <div>
        <fieldset>
          <p>
            <s:label for="title" class="required"></s:label>
            <br>
            <s:text name="title" id="title" tabindex="1" onfocus="helpFormItem(this);" onblur="$('#helpFormItem').html(null);"></s:text>
          </p>

          <p>
            <s:label for="applicant" class="required"></s:label>
            <br>
            <s:text name="applicant" id="applicant" tabindex="2" value="${actionBean.member.domain}/${actionBean.member.username}" onfocus="helpFormItem(this);"
              onblur="$('#helpFormItem').html(null);"></s:text>
          </p>

          <p>
            <s:label for="submitter"></s:label>
            <br>
            <s:text name="submitter" id="submitter" tabindex="3" readonly="readonly" value="${actionBean.member.domain}/${actionBean.member.username}" onfocus="helpFormItem(this);"
              onblur="$('#helpFormItem').html(null);"></s:text>
          </p>

          <p>
            <s:label for="date_up" class="required"></s:label>
            <br>
            <s:text name="date_up" id="date_up" class="date-pick" tabindex="3" onfocus="helpFormItem(this);" onblur="$('#helpFormItem').html(null);"></s:text>
          </p>
        </fieldset>

        <fieldset>
          <p>
            <s:label for="description" class="required"></s:label>
            <br>
            <s:textarea name="description" id="description" tabindex="4" onfocus="helpFormItem(this);" onblur="$('#helpFormItem').html(null);"></s:textarea>
          </p>
          <s:hidden name="worker" value="noname"></s:hidden>
        </fieldset>

        <p class="buttons">
          <s:submit name="requestCreate" id="submit" class="submit" tabindex="5" onfocus="helpFormItem(this);" onblur="$('#helpFormItem').html(null);" />
          <s:reset name="reset" id="reset" class="reset" tabindex="6" onfocus="helpFormItem(this);" onblur="$('#helpFormItem').html(null);" />
        </p>
      </div>
    </s:form>
    <p class="required"><f:message key="required.items" /></p>
    <script type="text/javascript">
					document.getElementById("title").focus();
				</script>
  </s:layout-component>

</s:layout-render>