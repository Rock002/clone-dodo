<!DOCTYPE html>
<html>
<head>
    <title>Orders Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.0/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <style>
        #orders-container {
            margin-top: 20px;
        }
        .order {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h1>Real-time Orders</h1>
    <div id="orders-container">
        <!-- Сообщения будут добавляться сюда через JavaScript -->
        <p id="no-orders-message">Нет данных о заказах</p>
    </div>
    <script>
        const socket = new SockJS('/ws');
        const stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            // Подписываемся на топик
            stompClient.subscribe('/topic/messages', function(message) {
                // При получении сообщения удаляем сообщение "Нет данных", если оно есть
                const noOrdersMessage = document.getElementById('no-orders-message');
                if (noOrdersMessage) {
                    noOrdersMessage.remove();
                }

                // Создаем элемент для нового заказа
                const orderElement = document.createElement('div');
                orderElement.className = 'order';
                orderElement.textContent = message.body;

                // Добавляем в контейнер
                document.getElementById('orders-container').appendChild(orderElement);
            });
        }, function(error) {
            console.error('Connection error: ', error);
        });
    </script>
</body>
</html>