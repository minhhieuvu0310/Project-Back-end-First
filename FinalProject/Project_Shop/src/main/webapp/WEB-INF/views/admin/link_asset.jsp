<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Dashboard - Ace Admin</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value="resources/admin/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="resources/admin/css/font-awesome.min.css" />" />
    <link href="<c:url value="resources/admin/css/style.css" />" rel="stylesheet" type="text/css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <%-- <link rel="stylesheet" href="<c:url value="resources/admin/css/fonts.googleapis.com.css" />" /> --%>

    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value="resources/admin/css/ace.min.css" />" class="ace-main-stylesheet"
        id="main-ace-style" />


    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- ace settings handler -->
    <script src="<c:url value="resources/admin/js/ace-extra.min.js" />"></script>

    <script src="<c:url value="resources/admin/js/jquery-2.1.4.min.js" />"></script>

    <!--[if !IE]> -->
    <script type="text/javascript">
    	window.jQuery || document.write("<script src='resources/admin/js/jquery.min.js'>" + "<" + "/script>");
    </script>


    <script type="text/javascript">
    if ('ontouchstart' in document.documentElement)
        document.write("<script src='resources/admin/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
    </script>
    <script src="<c:url value="resources/admin/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="resources/admin/js/jquery-ui.custom.min.js" />"></script>
    <script src="<c:url value="resources/admin/js/jquery.ui.touch-punch.min.js" />"></script>
    <script src="<c:url value="resources/admin/js/jquery.easypiechart.min.js" />"></script>
    <script src="<c:url value="resources/admin/js/jquery.sparkline.index.min.js" />"></script>
    <script src="<c:url value="resources/admin/js/jquery.flot.min.js" />"></script>
    <script src="<c:url value="resources/admin/js/jquery.flot.pie.min.js" />"></script>
    <script src="<c:url value="resources/admin/js/jquery.flot.resize.min.js" />"></script>

    <!-- ace scripts -->
    <script src="<c:url value="resources/admin/js/ace-elements.min.js" />"></script>
    <script src="<c:url value="resources/admin/js/ace.min.js" />"></script>

    <script src="<c:url value="resources/admin/js/dataTables.colVis.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="resources/admin/js/jquery.dataTables.bootstrap.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="resources/admin/js/jquery.dataTables.min.js" />" type="text/javascript"></script>
</head>