<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="com.alcuras.web.negocio.utils.Utils" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Expires", "0");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Robots" content="ALL">
<meta name="Author" content="Alcuras" lang="es_ES">
<meta name="Copyright" content="&copy; <%=Utils.getCurrentDate("yyyy")%>,Alcuras" lang="es_ES">
<meta name="Description" contents="Alcuras, salud, arte, trabajo en Alemania" lang="es_ES" />
<meta name="Title" contents="Alcuras" lang="es_ES" />
<meta name="Keywords" contents="salud Madrid arte piano opera bucear buceo viajes viajar" />

<title>Alcuras</title>
<link rel="stylesheet" type="text/css" media="screen" href="/web/css/main.css" />
<link rel="stylesheet" type="text/css" media="screen" href="/web/css/subMain.css" />