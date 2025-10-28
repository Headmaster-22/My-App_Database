package com.database_integration.App_Database.utility;

import jakarta.servlet.http.HttpServletRequest;
public class UrlUtil {
    public static String getApplicationUrl(HttpServletRequest request) {
        String appUrl = request.getRequestURL().toString();
        // String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return appUrl.replace(request.getServletPath(), " ");
    }
}
