package servlet;

import java.io.IOException;
import java.time.LocalDate;
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("HomeServlet実行");

		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		DailyDAO dao = new DailyDAO();

		// ミッション取得
		String[] missions = dao.getTodayMissions(userId);

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

		// キャラ取得
		UserDAO userDao = new UserDAO();
		int charaId = userDao.getCharaId(userId);
		int typeId = userDao.getTypeId(charaId);

		System.out.println("charaId=" + charaId);
		System.out.println("typeId=" + typeId);

		request.setAttribute("charaId", charaId);
		request.setAttribute("typeId", typeId);

		String charaImage = "default.png";
		String charaMessage = "今日も頑張ろう！";

		Random random = new Random();

		switch (charaId) {

		case 1:
		case 2:
		case 3:
		case 4:

			charaImage = "dog.png";

			String[] dogMessages = { "ミッション達成を目指そうワン！", "今日も元気にいくワン！", "少しずつでも前進だワン！", "頑張った分だけ成長するワン！",
					"一緒にゴールを目指そうワン！", "無理せずコツコツだワン！", "昨日の自分を超えよう！", "その調子だワン！", "今日も応援してるワン！", "まずは一つ達成してみようワン！" };

			charaMessage = dogMessages[random.nextInt(dogMessages.length)];
			break;

		case 5:
		case 6:
		case 7:
		case 8:

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

		// 達成数取得
		int completeCount = dao.getTodayCompleteCount(userId);

		System.out.println("達成数=" + completeCount);

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
		// 現在日時
		LocalTime now = LocalTime.now();
		int hour = now.getHour();

		// 現在月
		int month = LocalDate.now().getMonthValue();

		// 季節判定
		String season;

		if (month >= 3 && month <= 5) {
			season = "spring";
		} else if (month >= 6 && month <= 8) {
			season = "summer";
		} else if (month >= 9 && month <= 11) {
			season = "autumn";
		} else {
			season = "winter";
		}

		// 背景画像
		String backgroundImage;

		System.out.println("charaId=" + charaId);
		System.out.println("typeId=" + typeId);

		boolean outsideType = (typeId == 1 || typeId == 3);
		boolean daytime = (hour >= 6 && hour < 18);

		if (outsideType) {

			// 外背景
			backgroundImage = "outside1_" + season + (daytime ? "_noon.png" : "_night.png");

		} else {

			// 部屋背景
			backgroundImage = "home1_" + season + (daytime ? "_noon.png" : "_night.png");
		}

		request.setAttribute("backgroundImage", backgroundImage);

		System.out.println("backgroundImage=" + backgroundImage);
		boolean isDog = (charaId == 1 || charaId == 3);
		boolean isCat = (charaId == 2 || charaId == 4);

		System.out.println("typeId=" + typeId);
		System.out.println("backgroundImage=" + backgroundImage);

		boolean morningType = (typeId == 1 || typeId == 2);
		boolean nightType = (typeId == 3 || typeId == 4);

		// 朝型（type 1,2）
		if (morningType) {

			// 朝ご飯（8時）
			if (hour == 8) {

				if (isDog) {
					charaImage = "dog_morning.png";
					charaMessage = "朝ご飯を食べるワン！";
				} else if (isCat) {
					charaImage = "cat_morning.png";
					charaMessage = "朝ご飯ニャー！";
				}

			}
			// 昼ご飯（12時）
			else if (hour == 12) {

				if (isDog) {
					charaImage = "dog_lunch.png";
					charaMessage = "お昼ご飯だワン！";
				} else if (isCat) {
					charaImage = "cat_lunch.png";
					charaMessage = "魚ランチだニャー！";
				}

			}
			// 晩ご飯（19時）
			else if (hour == 19) {

				if (isDog) {
					charaImage = "dog_dinner.png";
					charaMessage = "晩ご飯の時間だワン！";
				} else if (isCat) {
					charaImage = "cat_dinner.png";
					charaMessage = "魚がおいしいニャー！";
				}

			}
			// お風呂（20時）
			else if (hour == 20) {

				if (isDog) {
					charaImage = "dog_bath.png";
					charaMessage = "お風呂でさっぱりワン！";
				} else if (isCat) {
					charaImage = "cat_bath.png";
					charaMessage = "ぽかぽかニャー！";
				}

			}

		}
		// 夜型（type 3,4）
		else if (nightType) {

			// 晩ご飯（0時）
			if (hour == 0) {

				if (isDog) {
					charaImage = "dog_dinner.png";
					charaMessage = "晩ご飯の時間だワン！";
				} else if (isCat) {
					charaImage = "cat_dinner.png";
					charaMessage = "魚がおいしいニャー！";
				}

			}
			// 朝ご飯（5時）
			else if (hour == 5) {

				if (isDog) {
					charaImage = "dog_morning.png";
					charaMessage = "朝ご飯を食べるワン！";
				} else if (isCat) {
					charaImage = "cat_morning.png";
					charaMessage = "朝ご飯ニャー！";
				}

			}
			// 昼ご飯（12時）
			else if (hour == 12) {

				if (isDog) {
					charaImage = "dog_lunch.png";
					charaMessage = "お昼ご飯だワン！";
				} else if (isCat) {
					charaImage = "cat_lunch.png";
					charaMessage = "魚ランチだニャー！";
				}

			}
			// お風呂（13時）
			else if (hour == 13) {

				if (isDog) {
					charaImage = "dog_bath.png";
					charaMessage = "お風呂でさっぱりワン！";
				} else if (isCat) {
					charaImage = "cat_bath.png";
					charaMessage = "ぽかぽかニャー！";
				}

			}

		}

		request.setAttribute("charaImage", charaImage);
		request.setAttribute("charaMessage", charaMessage);

		HomeDAO homeDao = new HomeDAO();

		int[] radarData = homeDao.getRadarData(userId);

		request.setAttribute("radarData", radarData);

		request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}
}