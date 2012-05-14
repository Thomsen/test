<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>

  <title>Hello Jsp!</title>

  <meta name="Generator" content="Vim 7.2 (Vi IMproved editor; http://www.vim.org/)">
  <meta name="Author" content="">
  <meta name="Copyright" content="Copyright (C) 五月 11, 2012 ">
  <link rev="made" href="mailto:">

  <style type="text/css">
   <!--
   body {background: #FFFFFF; color: #000000;}
   a:link {color: #0000EE;}
   a:visited {color: #990066;}
   a:hover, a:active, a:focus {color: #FF0000;}
   -->
  </style>
  
  <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>

 </head>
 <body bgcolor="#FFFFFF" text="#000000" link="#0000EE" alink="#FF0000" vlink="#990066">

     <h1 align="center">
        Hello Jsp!
     </h1>

  <p>
  </p>

  <hr width="75%">

  <p>
  Last Modified: <i>五月 11, 2012</i>
  </p>

  <address>
   <a href="mailto:"> &lt;hello reloadalbe &gt;</a>
  </address>

  <p>
    <%@ page import = "java.util.*" %>
     Current data: <%= new Date() %>
  </p>

 </body>
</html>
