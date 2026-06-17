package servlet;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DailyDAO;
import dao.HomeDAO;
import dao.UserDAO;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("HomeServlet実行");

		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");

<<<<<<< Updated upstream
        if (userId == null) {
=======
		if (userId == null) {
>>>>>>> Stashed changes

			response.sendRedirect(request.getContextPath() + "/LoginServlet");

			return;
		}

		DailyDAO dao = new DailyDAO();

		String[] missions = dao.getTodayMissions(userId);

		System.out.println("===== missions =====");

		if (missions != null) {
			for (String m : missions) {
				System.out.println(m);
			}
		} else {
			System.out.println("missions = null");
		}

		request.setAttribute("missions", missions);

		int[] completes = dao.getTodayCompletes(userId);

		request.setAttribute("completes", completes);

		UserDAO userDao = new UserDAO();

		int charaId = userDao.getCharaId(userId);

		request.setAttribute("charaId", charaId);

		String charaImage = "default.png";
		String charaMessage = "今日も頑張ろう！";

		Random random = new Random();

		switch (charaId) {

		case 1:
			charaImage = "dog.png";

			String[] dogMessages = { "ミッション達成を目指そうワン！", "今日も元気にいくワン！", "少しずつでも前進だワン！", "頑張った分だけ成長するワン！",
					"一緒にゴールを目指そうワン！", "無理せずコツコツだワン！", "昨日の自分を超えよう！", "その調子だワン！", "今日も応援してるワン！", "まずは一つ達成してみようワン！" };

			charaMessage = dogMessages[random.nextInt(dogMessages.length)];
			break;

		case 2:
			charaImage = "cat.png";

			String[] catMessages = { "ニャー！今日も一緒に頑張るニャー！", "焦らなくて大丈夫ニャー！", "コツコツ続けるのが大事ニャー！", "まずは一つ終わらせるニャー！",
					"きっとできるニャー！", "今日も応援してるニャー！", "少しずつ成長するニャー！", "休憩もしながら頑張るニャー！", "その調子ニャー！", "達成したら褒めてあげるニャー！" };

			charaMessage = catMessages[random.nextInt(catMessages.length)];
			break;

		default:
			charaImage = "default.png";
			charaMessage = "今日も頑張ろう！";
			break;
		}

		// ミッション達成数取得
		int completeCount = dao.getTodayCompleteCount(userId);

		System.out.println("達成数=" + completeCount);

		// 達成状況によるコメント
		if (completeCount >= 3) {

			if (charaId == 1) {

				String[] dogComplete = { "全部達成すごいワン！", "今日は100点だワン！", "頑張った成果だワン！", "たくさん褒めたいワン！", "最高の一日だワン！" };

				charaMessage = dogComplete[random.nextInt(dogComplete.length)];

			} else if (charaId == 2) {

				String[] catComplete = { "全部達成おめでとうニャー！", "すごいニャー！", "今日は満点ニャー！", "いっぱい褒めるニャー！", "最高だったニャー！" };

				charaMessage = catComplete[random.nextInt(catComplete.length)];
			}

		} else if (completeCount == 2) {

			if (charaId == 1) {

				String[] dog2 = { "あと1つだワン！", "もう少しでコンプリートだワン！", "頑張ってるワン！" };

				charaMessage = dog2[random.nextInt(dog2.length)];

			} else if (charaId == 2) {

				String[] cat2 = { "あと少しニャー！", "もう一息ニャー！", "順調ニャー！" };

				charaMessage = cat2[random.nextInt(cat2.length)];
			}

		} else if (completeCount == 1) {

			if (charaId == 1) {

				String[] dog1 = { "1つ達成したワン！", "いいスタートだワン！", "その調子だワン！" };

				charaMessage = dog1[random.nextInt(dog1.length)];

			} else if (charaId == 2) {

				String[] cat1 = { "1つ達成したニャー！", "いい感じニャー！", "その調子ニャー！" };

				charaMessage = cat1[random.nextInt(cat1.length)];
			}
		}

		// 時間帯による画像変更
		LocalTime now = LocalTime.now();

		int hour = now.getHour();

		if (hour >= 23 || hour < 7) {

			if (charaId == 1) {
				charaImage = "dog_sleep.png";
				charaMessage = "おやすみワン...";
			} else if (charaId == 2) {
				charaImage = "cat_sleep.png";
				charaMessage = "すやすやニャー...";
			}

		} else if (hour == 20) {

			if (charaId == 1) {
				charaImage = "dog_.png";
				charaMessage = "お風呂でさっぱりワン！";
			} else if (charaId == 2) {
				charaImage = "cat_bath.png";
				charaMessage = "ぽかぽかで気持ちいいニャー！";
			}
		} else if (hour == 8) {

			if (charaId == 1) {
				charaImage = "dog_morning.png";
				charaMessage = "朝ごはんを食べるワン！";
			} else if (charaId == 2) {
				charaImage = "cat_morning.png";
				charaMessage = "エネルギーを付けるニャー！";
			}
		}

		request.setAttribute("charaImage", charaImage);
		request.setAttribute("charaMessage", charaMessage);

		HomeDAO homeDao = new HomeDAO();

		int[] radarData = homeDao.getRadarData(userId);

		request.setAttribute("radarData", radarData);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}