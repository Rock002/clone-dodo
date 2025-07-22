<#list foodList as food>
    <form action="/product/${food.id}" method="post">
        ${food.name}<br>
        ${food.cost}<br>
        <input type="submit" value="открыть">
    </form>
</#list>

<form action="/toBasket" method="post">
    <input type="submit" value="Корзина">
</form>