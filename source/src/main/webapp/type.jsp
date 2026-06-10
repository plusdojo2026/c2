<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タイプ選択</title>

<style>
body {
    text-align: center;
    margin-top: 100px;
    font-family: sans-serif;
}

.type-button {
    width: 200px;
    height: 60px;
    margin: 10px;
    font-size: 18px;
    cursor: pointer;
}
</style>

</head>
<body>

<h1>タイプを選択してください</h1>

<form action="TypeSelectServlet" method="post">

    <button type="submit" name="type" value="morning_sunny" class="type-button">
        朝方晴れ
    </button>

    <button type="submit" name="type" value="morning_cloudy" class="type-button">
        朝方曇り
    </button>

    <button type="submit" name="type" value="night_sunny" class="type-button">
        夜型晴れ
    </button>

    <button type="submit" name="type" value="night_cloudy" class="type-button">
        夜型曇り
    </button>

</form>

</body>
</html>