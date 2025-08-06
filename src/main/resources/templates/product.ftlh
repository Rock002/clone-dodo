${food.name}<br>
${food.cost}<br>
<form action="/addToList/${food.id}" method="post">
    <input type="submit" value="добавить"/>
<form/>

<form action="/backToMain" method="post">
    <input type="submit" value="На главную">
</form>