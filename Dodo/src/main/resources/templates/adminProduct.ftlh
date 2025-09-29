${food.name}<br>
${food.cost}<br>
<form action="/addToList/${food.id}" method="post">
    <input type="submit" value="добавить"/>
</form><br>

<form action="/postUpdate/${food.id}" method="post">
    <input type="text" name="name"><br>
    <input type="number" name="cost"><br>
    <input type="submit" value="обновить"/>
</form><br>

<form action="/remove/${food.id}" method="post">
    <input type="submit" value="удалить"/>
</form><br>

<form action="/backToMain" method="post">
    <input type="submit" value="На главную">
</form>