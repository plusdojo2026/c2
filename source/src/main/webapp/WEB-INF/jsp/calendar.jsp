<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.time.YearMonth" %>
<%@ page import="java.util.Map" %>

<%
YearMonth ym =
    (YearMonth) request.getAttribute("yearMonth");

Map<Integer,Integer> monthlyResult =
    (Map<Integer,Integer>) request.getAttribute("monthlyResult");

int year = (Integer) request.getAttribute("year");
int month = (Integer) request.getAttribute("month");

int firstDay = ym.atDay(1).getDayOfWeek().getValue();
int lastDay = ym.lengthOfMonth();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ミッション達成カレンダー</title>

<link rel="stylesheet" href="css/calendar.css">
</head>

<body>

<h2><%= year %>年 <%= month %>月 ミッション達成状況</h2>

<table>

<tr>
<th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th><th>日</th>
</tr>

<tr>

<%
for(int i = 1; i < firstDay; i++){
%>
<td></td>
<%
}

for(int day = 1; day <= lastDay; day++){

    Integer count = monthlyResult.get(day);

    String mark = "";
    String markClass = "";

    if(count != null){
        switch(count){
        case 0: mark="×"; markClass="bad"; break;
        case 1: mark="△"; markClass="middle"; break;
        case 2: mark="〇"; markClass="good"; break;
        case 3: mark="◎"; markClass="good"; break;
        }
    }

    int position = firstDay + day - 1;
%>

<td>
<div class="day"><%= day %></div>
<div class="mark <%= markClass %>"><%= mark %></div>
</td>

<%
    if(position % 7 == 0){
%>
</tr><tr>
<%
    }
}
%>

</tr>
</table>

<br>

<form action="HomeServlet" method="get">
<input type="submit" value="ホームへ戻る">
</form>

</body>
</html>