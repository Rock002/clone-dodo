<#list foodList as food>
    ${food.name}<br>
    ${food.cost}<br>
</#list>

<form action="/backToMain" method="post">
    <input type="submit" value="На главную">
</form>