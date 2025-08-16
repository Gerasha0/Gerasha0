# 🧪 КОНСОЛЬНІ ТЕСТИ API - Система доставки їжі

## 📋 Опис тестування

Цей документ містить команди для тестування API через консоль PowerShell та їх відповідність до функцій веб-інтерфейсу.

---

## 🚀 Запуск сервера

```powershell
# Перейти в директорію проекту
cd "d:\Study2\3 kurs\АППЗ\lab3\lab3"

# Запустити сервер
mvn spring-boot:run
```

---

## 🍽️ ТЕСТИ ДЛЯ СТРАВ (DISHES API)

### 1. Отримати всі страви
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/dishes" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🍽️ GET /api/dishes - Тестувати` в веб-інтерфейсі

---

### 2. Отримати страву за ID
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/dishes/1" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🔍 GET /api/dishes/{id} - Тестувати` в веб-інтерфейсі

---

### 3. Фільтрація страв за типом
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/dishes/by-type/FIRST_COURSE" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🔎 GET /api/dishes/by-type/{type} - Тестувати` в веб-інтерфейсі

---

### 4. Створити нову страву
**Консольна команда:**
```powershell
$dishData = @{
    name = "Тестова страва"
    description = "Опис тестової страви"
    price = 75.50
    dishType = "APPETIZER"
    available = $true
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/dishes" -Method POST -ContentType "application/json" -Body $dishData | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `➕ POST /api/dishes - Створити страву` в веб-інтерфейсі

---

### 5. Оновити страву
**Консольна команда:**
```powershell
$updatedDish = @{
    name = "Оновлена страва"
    description = "Оновлений опис"
    price = 85.00
    dishType = "SECOND_COURSE"
    available = $true
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/dishes/1" -Method PUT -ContentType "application/json" -Body $updatedDish | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `✏️ PUT /api/dishes/{id} - Тестувати` в веб-інтерфейсі

---

### 6. Видалити страву
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/dishes/13" -Method DELETE
Write-Host "Страву видалено"
```
**Відповідає кнопці:** `🗑️ DELETE /api/dishes/{id} - Тестувати` в веб-інтерфейсі

---

### 7. Пошук страв за назвою
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/dishes/search?name=борщ" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🔍 GET /api/dishes/search?name={name} - Тестувати` в веб-інтерфейсі

---

### 8. Отримати доступні страви
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/dishes/available" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `✅ GET /api/dishes/available - Тестувати` в веб-інтерфейсі

---

## 🛒 ТЕСТИ ДЛЯ ЗАМОВЛЕНЬ (ORDERS API)

### 1. Отримати всі замовлення
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/orders" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🛒 GET /api/orders - Тестувати` в веб-інтерфейсі

---

### 2. Отримати замовлення за ID
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/orders/1" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🔍 GET /api/orders/{id} - Тестувати` в веб-інтерфейсі

---

### 3. Створити нове замовлення
**Консольна команда:**
```powershell
$orderData = @{
    customerName = "Тест Користувач"
    customerPhone = "+380501234567"
    deliveryAddress = "вул. Тестова 1"
    orderItems = @(
        @{ dishId = 1; quantity = 2 },
        @{ dishId = 2; quantity = 1 }
    )
    orderType = "CUSTOM"
} | ConvertTo-Json -Depth 3

Invoke-RestMethod -Uri "http://localhost:8080/api/orders" -Method POST -ContentType "application/json" -Body $orderData | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `➕ POST /api/orders - Створити замовлення` в веб-інтерфейсі

---

### 4. Створити комплексний обід
**Консольна команда:**
```powershell
$complexLunchData = @{
    customerName = "Клієнт комплексного обіду"
    customerPhone = "+380509876543"
    deliveryAddress = "вул. Обідня 10"
    firstCourseId = 1
    secondCourseId = 4
    saladId = 7
    drinkId = 11
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/orders/complex-lunch" -Method POST -ContentType "application/json" -Body $complexLunchData | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🍽️ POST /api/orders/complex-lunch - Створити комплексний обід` в веб-інтерфейсі

---

### 5. Оновити статус замовлення
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/orders/1/status?status=CONFIRMED" -Method PUT | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🔄 PUT /api/orders/{id}/status - Тестувати` в веб-інтерфейсі

---

### 6. Додати страву до замовлення
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/orders/1/dishes/3?quantity=2" -Method POST | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `➕ POST /api/orders/{orderId}/dishes/{dishId} - Тестувати` в веб-інтерфейсі

---

### 7. Скасувати замовлення
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/orders/1/cancel" -Method PUT
Write-Host "Замовлення скасовано"
```
**Відповідає кнопці:** `❌ PUT /api/orders/{id}/cancel - Тестувати` в веб-інтерфейсі

---

### 8. Фільтрація замовлень за статусом
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/orders/by-status/PENDING" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🔎 GET /api/orders/by-status/{status} - Тестувати` в веб-інтерфейсі

---

### 9. Фільтрація замовлень за типом
**Консольна команда:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/orders/by-type/COMPLEX_LUNCH" -Method GET | ConvertTo-Json -Depth 3
```
**Відповідає кнопці:** `🔎 GET /api/orders/by-type/{type} - Тестувати` в веб-інтерфейсі

---

## 🌐 Веб-інтерфейси

### 1. Тестовий API інтерфейс
```
http://localhost:8080/
```
- Містить кнопки для тестування всіх API endpoints
- Показує JSON відповіді в реальному часі
- Моніторинг статусу сервера

### 2. Реальний сайт ресторану
```
http://localhost:8080/restaurant.html
```
- Повноцінний сайт доставки їжі
- Динамічне завантаження меню з API
- Функціональні кнопки замовлення

---

## 📊 Статистика тестування

- **Загальна кількість endpoints**: 12
- **Тестів для страв**: 8
- **Тестів для замовлень**: 9
- **Веб-інтерфейсів**: 2

---

## 💡 Поради для тестування

1. **Запустіть сервер** перед виконанням будь-яких тестів
2. **Використовуйте валідні ID** - перевірте які ID існують через GET запити
3. **Дотримуйтесь правильного формату JSON** для POST/PUT запитів
4. **Перевірте статуси відповідей** - 200/201 для успіху, 400/404/500 для помилок
5. **Тестуйте послідовно** - спочатку створіть дані, потім їх модифікуйте

---

*Створено для лабораторної роботи з багаторівневої архітектури*
*Дата: 5 серпня 2025 року*
