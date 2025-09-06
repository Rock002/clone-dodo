<#list foodList as food>
    <form action="/deleteFromBasket/${food.id}}" method="post">
        ${food.name}<br>
        ${food.cost}<br>
        <input type="submit" value="Удалить">
    </form>
</#list>

<form action="/postbook" method="post">
    <input type="submit" value="Заказать">
</form>

<form action="/backToMain" method="post">
    <input type="submit" value="На главную">
</form>