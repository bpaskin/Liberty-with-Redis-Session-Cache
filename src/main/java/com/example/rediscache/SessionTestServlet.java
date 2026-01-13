package com.example.rediscache;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

/**
 * Servlet to demonstrate Redis-backed session persistence
 */
@WebServlet(name = "SessionTestServlet", urlPatterns = {"/session", "/session/*"})
public class SessionTestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Redis Session Persistence Test</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
            out.println("h1 { color: #333; }");
            out.println("table { border-collapse: collapse; width: 100%; margin: 20px 0; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("button { margin: 5px; padding: 10px 15px; }");
            out.println(".info { background-color: #e7f3ff; padding: 15px; border-radius: 5px; margin: 10px 0; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Redis Session Persistence Test</h1>");
            
            // Handle actions
            if ("set".equals(action)) {
                String key = request.getParameter("key");
                String value = request.getParameter("value");
                if (key != null && value != null && !key.trim().isEmpty()) {
                    session.setAttribute(key, value);
                    out.println("<div class='info'>Set session attribute: " + key + " = " + value + "</div>");
                }
            } else if ("remove".equals(action)) {
                String key = request.getParameter("key");
                if (key != null && !key.trim().isEmpty()) {
                    session.removeAttribute(key);
                    out.println("<div class='info'>Removed session attribute: " + key + "</div>");
                }
            } else if ("invalidate".equals(action)) {
                session.invalidate();
                session = request.getSession(true);
                out.println("<div class='info'>Session invalidated and new session created</div>");
            }
            
            // Display session information
            out.println("<h2>Session Information</h2>");
            out.println("<table>");
            out.println("<tr><th>Property</th><th>Value</th></tr>");
            out.println("<tr><td>Session ID</td><td>" + session.getId() + "</td></tr>");
            out.println("<tr><td>Creation Time</td><td>" + 
                       LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(session.getCreationTime()), 
                                             java.time.ZoneId.systemDefault()).format(FORMATTER) + "</td></tr>");
            out.println("<tr><td>Last Accessed</td><td>" + 
                       LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(session.getLastAccessedTime()), 
                                             java.time.ZoneId.systemDefault()).format(FORMATTER) + "</td></tr>");
            out.println("<tr><td>Max Inactive Interval</td><td>" + session.getMaxInactiveInterval() + " seconds</td></tr>");
            out.println("<tr><td>Is New</td><td>" + session.isNew() + "</td></tr>");
            out.println("</table>");
            
            // Display session attributes
            out.println("<h2>Session Attributes</h2>");
            out.println("<table>");
            out.println("<tr><th>Name</th><th>Value</th><th>Action</th></tr>");
            
            Enumeration<String> attributeNames = session.getAttributeNames();
            boolean hasAttributes = false;
            while (attributeNames.hasMoreElements()) {
                hasAttributes = true;
                String name = attributeNames.nextElement();
                Object value = session.getAttribute(name);
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + (value != null ? value.toString() : "null") + "</td>");
                out.println("<td><a href='?action=remove&key=" + name + "'>Remove</a></td>");
                out.println("</tr>");
            }
            
            if (!hasAttributes) {
                out.println("<tr><td colspan='3'>No session attributes found</td></tr>");
            }
            out.println("</table>");
            
            // Add new attribute form
            out.println("<h2>Add Session Attribute</h2>");
            out.println("<form method='get'>");
            out.println("<input type='hidden' name='action' value='set'>");
            out.println("Key: <input type='text' name='key' placeholder='attribute name'> ");
            out.println("Value: <input type='text' name='value' placeholder='attribute value'> ");
            out.println("<button type='submit'>Add Attribute</button>");
            out.println("</form>");
            
            // Action buttons
            out.println("<h2>Actions</h2>");
            out.println("<button onclick=\"location.href='?'\">Refresh</button>");
            out.println("<button onclick=\"location.href='?action=invalidate'\">Invalidate Session</button>");
            out.println("<button onclick=\"location.href='?action=set&key=timestamp&value=" + 
                       System.currentTimeMillis() + "'\">Add Timestamp</button>");
            out.println("<button onclick=\"location.href='?action=set&key=counter&value=' + " +
                       "((parseInt('" + session.getAttribute("counter") + "') || 0) + 1)\">Increment Counter</button>");
            
            // Instructions
            out.println("<h2>Testing Instructions</h2>");
            out.println("<div class='info'>");
            out.println("<p><strong>To test Redis session persistence:</strong></p>");
            out.println("<ol>");
            out.println("<li>Add some session attributes using the form above</li>");
            out.println("<li>Restart the Liberty server</li>");
            out.println("<li>Return to this page with the same session ID</li>");
            out.println("<li>Verify that your session attributes are still present</li>");
            out.println("</ol>");
            out.println("<p><strong>Note:</strong> Make sure Redis is running on localhost:6379 (or configure the connection in server.xml)</p>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Redis Session Persistence Test Servlet";
    }
}