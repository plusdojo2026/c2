<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    // パラメータから年と月を取得（なければ現在日時）
    int year, month;
    Calendar cal = Calendar.getInstance();

    try {
        year = Integer.parseInt(request.getParameter("year"));
        month = Integer.parseInt(request.getParameter("month")) - 1; // Calendarは0始まり
    } catch (Exception e) {
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
    }

    // カレンダーを設定
    cal.set(year, month, 1);

    // 月初の曜日（0:日曜, 1:月曜, ...）
    int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

    // 月末日
    int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    // 前月・翌月リンク用
    Calendar prev = (Calendar) cal.clone();
    prev.add(Calendar.MONTH, -1);
    Calendar next = (Calendar) cal.clone();
    next.add(Calendar.MONTH, 1);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>カレンダー</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid #999; width: 40px; height: 40px; text-align: center; }
        th { background-color: #eee; }
        .sunday { color: red; }
        .saturday { color: blue; }
    </style>
</head>
<body>
    <h2>
        <a href="calendar.jsp?year=<%=prev.get(Calendar.YEAR)%>&month=<%=prev.get(Calendar.MONTH)+1%>">◀</a>
        <%= sdf.format(cal.getTime()) %>
        <a href="calendar.jsp?year=<%=next.get(Calendar.YEAR)%>&month=<%=next.get(Calendar.MONTH)+1%>">▶</a>
    </h2>

    <table>
        <tr>
            <th class="sunday">日</th>
            <th>月</th>
            <th>火</th>
            <th>水</th>
            <th>木</th>
            <th>金</th>
            <th class="saturday">土</th>
        </tr>
        <tr>
        <%
            // 空白セル（1日が始まる前まで）
            for (int i = 1; i < startDayOfWeek; i++) {
                out.print("<td></td>");
            }

            // 日付セル
            int dayOfWeekCounter = startDayOfWeek;
            for (int day = 1; day <= lastDay; day++) {
                String cssClass = "";
                if (dayOfWeekCounter == Calendar.SUNDAY) cssClass = "sunday";
                if (dayOfWeekCounter == Calendar.SATURDAY) cssClass = "saturday";

                out.print("<td class='" + cssClass + "'>" + day + "</td>");

                if (dayOfWeekCounter == Calendar.SATURDAY) {
                    out.print("</tr><tr>");
                    dayOfWeekCounter = Calendar.SUNDAY;
                } else {
                    dayOfWeekCounter++;
                }
            }

            // 最後の行の空白セル
            if (dayOfWeekCounter != Calendar.SUNDAY) {
                for (int i = dayOfWeekCounter; i <= Calendar.SATURDAY; i++) {
                    out.print("<td></td>");
                }
            }
        %>
        </tr>
    </table>
</body>
</html>
