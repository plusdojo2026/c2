<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/type.css">
<title>がんばろうあいぼう | タイプ選択</title>



</head>
<body>

	<div class=type-info>
    	<br><br>
    	<h1>タイプの選択</h1>
    	<p>4つの生活タイプの中からあなたに合うものを選んでね</p>


	<form action="TypeSelectServlet" method="post">

    	<button type="submit" name="type" value="morning_sunny" class="type-button1">
        朝型晴れ
    	</button>

    	<button type="submit" name="type" value="morning_cloudy" class="type-button1">
        朝型曇り
    	</button>

    	<button type="submit" name="type" value="night_sunny" class="type-button2">
        夜型晴れ
    	</button>

    	<button type="submit" name="type" value="night_cloudy" class="type-button2">
        夜型曇り
    	</button>

	</form>
	</div>
	
	<div class="type-info2">
                <h3>[それぞれのタイプ説明]</h3>
                <p>朝型晴れ：7時起床、23時就寝に近い生活の方が外出する日</p>
	            <p>朝型曇り：7時起床、23時就寝に近い生活の方が家にいる日</p>
                <p>夜型晴れ：23時起床、15時就寝に近い生活の方が外出する日</p>
                <p>夜型曇り：23時起床、15時就寝に近い生活の方が家にいる日</p>
        </div>
	

</body>
</html>