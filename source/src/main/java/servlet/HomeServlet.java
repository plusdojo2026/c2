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
import model.User;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			}
		} else {
		}

		request.setAttribute("missions", missions);

		int[] completes = dao.getTodayCompletes(userId);
		request.setAttribute("completes", completes);

		// キャラ取得
		UserDAO userDao = new UserDAO();
		int charaId = userDao.getCharaId(userId);
		int typeId = userDao.getTypeId(userId);

		User user = userDao.findByUserId(userId);

		String userNickname = user.getUserNickname();

		String charaNickname = user.getCharaNickname();

		request.setAttribute("charaId", charaId);
		request.setAttribute("typeId", typeId);
		request.setAttribute("charaNickname", charaNickname);

		String charaImage = "default.png";
		String charaMessage = "今日も頑張ろう！";

		Random random = new Random();

		switch (charaId) {

		case 1:
		case 2:
		case 3:
		case 4:

			String[] dogImages = { "dog.png", "dog2.png", "dog3.png", "dog4.png" };

			charaImage = dogImages[random.nextInt(dogImages.length)];

			String[] dogMessages = {

					// ニックネームあり
					userNickname + "、今日も一緒に頑張るワン！", userNickname + "ならきっとできるワン！", userNickname + "、無理はしすぎないワン！",
					userNickname + "、応援してるワン！", userNickname + "、まずは一歩だワン！", userNickname + "、今日も元気にいくワン！",
					userNickname + "、その調子だワン！",

					// ニックネームなし
					"ミッション達成を目指そうワン！", "少しずつでも前進だワン！", "頑張った分だけ成長するワン！", "一緒にゴールを目指そうワン！", "コツコツ続けるのが大事だワン！",
					"焦らなくても大丈夫だワン！", "今日は何から始めるワン？", "応援してるワン！" };

			charaMessage = dogMessages[random.nextInt(dogMessages.length)];
			break;

		case 5:
		case 6:
		case 7:
		case 8:

			String[] catImages = { "cat.png", "cat2.png", "cat3.png", "cat4.png", "cat5.png" };

			charaImage = catImages[random.nextInt(catImages.length)];

			String[] catMessages = {

					// ニックネームあり
					userNickname + "、今日も一緒に頑張るニャー！", userNickname + "なら大丈夫ニャー！", userNickname + "、焦らなくていいニャー！",
					userNickname + "、ちゃんと見てるニャー！", userNickname + "、その調子ニャー！", userNickname + "、今日も応援してるニャー！",
					userNickname + "、無理しすぎはダメニャー！",

					// ニックネームなし
					"コツコツ続けるのが大事ニャー！", "まずは一つ終わらせるニャー！", "少しずつ成長するニャー！", "休憩もしながら頑張るニャー！", "今日もいい日になりそうニャー！",
					"できたらたくさん褒めるニャー！", "ゆっくりでも前に進んでるニャー！", "まずは気楽にやるニャー！" };

			charaMessage = catMessages[random.nextInt(catMessages.length)];
			break;

		default:
			charaImage = "default.png";
			charaMessage = "今日も頑張ろう！";
			break;
		}

		// 達成数取得
		int completeCount = dao.getTodayCompleteCount(userId);

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

		// ミッション総達成数取得
		HomeDAO homeDAO = new HomeDAO();

		int totalCompleteCount = homeDAO.getTotalClearCount(userId);

		// 背景レベル決定
		int backgroundLevel;

		if (totalCompleteCount >= 300) {
			backgroundLevel = 5;
		} else if (totalCompleteCount >= 150) {
			backgroundLevel = 4;
		} else if (totalCompleteCount >= 60) {
			backgroundLevel = 3;
		} else if (totalCompleteCount >= 20) {
			backgroundLevel = 2;
		} else {
			backgroundLevel = 1;
		}

		request.setAttribute("backgroundLevel", backgroundLevel);

		// 時間帯
		LocalTime now = LocalTime.now();
		int hour = now.getHour();

		// 月
		int month = LocalDate.now().getMonthValue();

		// 季節
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

		boolean outsideType = (typeId == 1 || typeId == 3);
		boolean daytime = (hour >= 6 && hour < 18);

		// 背景画像
		String backgroundImage;

		if (outsideType) {
			backgroundImage = "outside" + backgroundLevel + "_" + season + (daytime ? "_noon.png" : "_night.png");
		} else {
			backgroundImage = "home" + backgroundLevel + "_" + season + (daytime ? "_noon.png" : "_night.png");
		}

		request.setAttribute("season", season);
		request.setAttribute("backgroundImage", backgroundImage);

		boolean isDog = (charaId >= 1 && charaId <= 4);
		boolean isCat = (charaId >= 5 && charaId <= 8);

		boolean morningType = (typeId == 1 || typeId == 2);
		boolean nightType = (typeId == 3 || typeId == 4);

		// 朝型（type 1,2）
		if (morningType) {

			if (hour == 7) {

				String[] messages = { "おはよう！今日も頑張るワン！", "朝だワン！元気いっぱい！", "ぐっすり眠れたワン！", "新しい一日の始まりだワン！",
						"おはよう！一緒に頑張ろうワン！" };

				if (isDog) {
					charaImage = "dog.png";
					charaMessage = messages[random.nextInt(messages.length)];
				}

				String[] catMessages = { "おはようニャー！", "朝の日差しが気持ちいいニャー", "今日も一日よろしくニャー", "よく眠れたニャー", "朝ご飯が楽しみニャー" };

				if (isCat) {
					charaImage = "cat.png";
					charaMessage = catMessages[random.nextInt(catMessages.length)];
				}
			}
			// 朝ご飯（8時）
			else if (hour == 8) {

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

			} else if (hour == 22) {

				String[] messages = { "もう少しで寝る時間だワン", "歯磨きをしてくるワン！", "そろそろお布団に入るワン", "今日も一日お疲れさまだワン", "寝る準備をしているワン！" };

				if (isDog) {
					charaImage = "dog.png";
					charaMessage = messages[random.nextInt(messages.length)];
				}

				String[] catMessages = { "そろそろ眠くなってきたニャー", "お布団が呼んでるニャー", "寝る前にのんびりするニャー", "今日もお疲れさまニャー",
						"もう少しでおやすみニャー" };

				if (isCat) {
					charaImage = "cat.png";
					charaMessage = catMessages[random.nextInt(catMessages.length)];
				}
			} else if (hour >= 23 || hour < 7) {

				if (isDog) {
					charaImage = "dog_sleep.png";
					charaMessage = "すやすや眠ってるワン...";
				} else if (isCat) {
					charaImage = "cat_sleep.png";
					charaMessage = "ぐっすりおやすみニャー...";
				}
			}

		}
		// 夜型（type 3,4）
		else if (nightType) {

			if (hour == 23) {

				String[] messages = { "おはようだワン！", "これから活動開始だワン！", "よく眠れたワン！", "夜の時間が始まるワン！", "今日も頑張るワン！" };

				if (isDog) {
					charaImage = "dog.png";
					charaMessage = messages[random.nextInt(messages.length)];
				}

				String[] catMessages = { "おはようニャー！", "夜の始まりニャー！", "元気いっぱいニャー！", "よく眠れたニャー", "これから活動するニャー" };

				if (isCat) {
					charaImage = "cat.png";
					charaMessage = catMessages[random.nextInt(catMessages.length)];
				}
			}
			// 晩ご飯（0時）
			else if (hour == 0) {

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

			} else if (hour == 14) {

				String[] messages = { "そろそろ寝る時間だワン", "お昼だけどおやすみだワン", "寝る準備をしているワン", "今日も頑張ったワン！", "お布団に向かうワン" };

				if (isDog) {
					charaImage = "dog.png";
					charaMessage = messages[random.nextInt(messages.length)];
				}

				String[] catMessages = { "もう少しで寝るニャー", "お昼寝じゃなく本気寝ニャー", "眠くなってきたニャー", "お布団に行くニャー", "おやすみニャー" };

				if (isCat) {
					charaImage = "cat.png";
					charaMessage = catMessages[random.nextInt(catMessages.length)];
				}
			} else if (hour >= 15 && hour < 23) {

				if (isDog) {
					charaImage = "dog_sleep.png";
					charaMessage = "むにゃむにゃ...眠いワン...";
				} else if (isCat) {
					charaImage = "cat_sleep.png";
					charaMessage = "ニャー...zzz";
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