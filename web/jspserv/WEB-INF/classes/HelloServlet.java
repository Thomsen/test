import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head><title>Hello Servlet</title></head>");
        pw.println("<body>");
        pw.println("<p>Hello Servlet</p>");
        pw.println("<ul><li>param1: " + 
                request.getParameter("param1") + "</li><li>param2: " +
                request.getParameter("param2") + "</li><li>param3: " +
                request.getParameter("param3") + "</li></ul>" );

        pw.println("<p>test 4 response status code</p>");

        if (request.getParameter("searchString") != null) {
            String searchString = URLEncoder.encode(request.getParameter("searchString"));
            String searchEngine = request.getParameter("searchEngine");
            String numResults = request.getParameter("numResults");

            if ("google".equals(searchEngine)) {
                String url = response.encodeURL("http://www.google.com.hk/search?q=" + searchString + "&num=" + numResults);
                response.sendRedirect(url);
                return ;
            }
            response.sendError(response.SC_NOT_FOUND, "no search engine");
        }

        // response header
        response.setHeader("Refresh", "5");

        // Cookie
        Cookie[] cookies = request.getCookies();

        // Session
        HttpSession session = request.getSession(true);

        pw.println("<p>test 2 request headers</p>");

        Enumeration headerName = request.getHeaderNames();
        while(headerName.hasMoreElements()) {
            String header = (String)headerName.nextElement();
            pw.println("<table border=1 align=center><tr><td>" + header + "</td>");
            pw.println("<td>" + request.getHeader(header) + "</td></tr></table>");
        }

        pw.println("<p> test 3 cgi variables</p>");

        String[][] vars = {
            { "Auth Type", request.getAuthType() },
            { "Content Length", String.valueOf(request.getContentLength()) },
            { "Content Type", request.getContentType() },
            { "Document Root", getServletContext().getRealPath("/") },
            { "Path Info", request.getPathInfo() },
            { "Path Translated", request.getPathTranslated() },
            { "Query String", request.getQueryString() },
            { "Remote Addr", request.getRemoteAddr() },
            { "Remote User", request.getRemoteUser() },
            { "Remote Host", request.getRemoteHost() },
            { "Request Method", request.getMethod() },
            { "Script Name", request.getServletPath() },
            { "Server name", request.getServerName() },
            { "Server Port", String.valueOf(request.getServerPort()) },
            { "Server Protocol", request.getProtocol() },
            { "Server Software", getServletContext().getServerInfo() }};
        for (int i=0; i<vars.length; i++) {
            String varName = vars[i][0];
            String varValue = vars[i][1];
            if (varValue == null) {
                varValue = "<i>Not specified</i>";
            }
            pw.println("<table border=1 align=center><tr><td>" + varName + "</td><td>" + varValue + "</td></tr></table>");
        }

        pw.println("</body></html>");
    }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
   }

}
