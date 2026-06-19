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

int year =
    (Integer) request.getAttribute("year");

int month =
    (Integer) request.getAttribute("month");

int firstDay =
    ym.atDay(1)
      .getDayOfWeek()
      .getValue(); // 月=1 ～ 日=7

int lastDay =
    ym.lengthOfMonth();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ミッション達成カレンダー</title>

<style>

body{
    font-family:sans-serif;
    text-align:center;
}

table{
    border-collapse:collapse;
    margin:auto;
}

th,td{
    border:1px solid black;
    width:80px;
    height:80px;
    vertical-align:top;
}

th{
    background:#f0f0f0;
}

.day{
    font-weight:bold;
}

.mark{
    font-size:20px;
    margin-top:10px;
}

</style>

</head>
<body>

<h2>
<%= year %>年
<%= month %>月
ミッション達成状況
</h2>

<table>

<tr>
    <th>月</th>
    <th>火</th>
    <th>水</th>
    <th>木</th>
    <th>金</th>
    <th>土</th>
    <th>日</th>
</tr>

<tr>

<%
for(int i = 1; i < firstDay; i++){
%>
    <td></td>
<%
}

for(int day = 1; day <= lastDay; day++){

    Integer count =
        monthlyResult.get(day);

    String mark = "";

    if(count != null){

        switch(count){

        case 0:
            mark = "×";
            break;

        case 1:
            mark = "△";
            break;

        case 2:
            mark = "〇";
            break;

        case 3:
            mark = "◎";
            break;
        }
    }

    int position =
        firstDay + day - 1;
%>

<td>

<div class="day">
    <%= day %>
</div>

<div class="mark">
    <%= mark %>
</div>

</td>

<%
    if(position % 7 == 0){
%>

</tr>
<tr>

<%
    }
}
%>

</tr>

</table>

<br>

<form action="HomeServlet" method="get">

    <input
        type="submit"
        value="ホームへ戻る">

</form>

</body>
</html>