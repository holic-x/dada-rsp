package com.dada.report.filter;

import javax.servlet.http.HttpServletRequest;

import com.dada.pojo.UserInfo;

public class CustomHolder {

    private static final ThreadLocal<UserInfo> userHolder = new ThreadLocal<UserInfo>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(UserInfo userInfo) {
        userHolder.set(userInfo);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static UserInfo getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}