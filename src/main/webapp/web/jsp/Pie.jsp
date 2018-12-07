<%@ page import="com.alcuras.web.negocio.utils.Utils" %>
<div id="footer">
    	<img src="/web/img/logoMini.png" alt="Alcuras logo" />
        Alcuras &reg; <%=Utils.getCurrentDate("yyyy")%> <a href="pie-privad"><spring:message code="pie.privad"/>.</a> <a href="pie-aviso"><spring:message code="pie.legal"/>.</a>
    </div>
</div>
</body>
</html>
