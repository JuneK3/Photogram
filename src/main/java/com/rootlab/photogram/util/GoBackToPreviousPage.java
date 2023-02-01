package com.rootlab.photogram.util;

public class GoBackToPreviousPage {
    public static String alert(String message) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+message+"');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }
}

