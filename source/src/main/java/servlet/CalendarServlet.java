@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Calendar cal = Calendar.getInstance();

        int year;
        int month;

        try {
            year = Integer.parseInt(request.getParameter("year"));
            month = Integer.parseInt(request.getParameter("month")) - 1;
        } catch (Exception e) {
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
        }

        cal.set(year, month, 1);

        int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar prev = (Calendar) cal.clone();
        prev.add(Calendar.MONTH, -1);

        Calendar next = (Calendar) cal.clone();
        next.add(Calendar.MONTH, 1);

        // DAO呼び出し（ここ重要）
        EventDAO dao = new EventDAO();

        // 例：その月の予定取得
        List<Event> events = dao.findByMonth(year, month + 1);

        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("startDayOfWeek", startDayOfWeek);
        request.setAttribute("lastDay", lastDay);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);
        request.setAttribute("events", events);

        RequestDispatcher rd = request.getRequestDispatcher("/calendar.jsp");
        rd.forward(request, response);
    }
}