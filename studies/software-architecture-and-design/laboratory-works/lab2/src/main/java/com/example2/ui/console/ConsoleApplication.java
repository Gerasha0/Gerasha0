package com.example2.ui.console;

import com.example2.bll.dto.DishDto;
import com.example2.bll.dto.OrderDto;
import com.example2.bll.services.implementations.DishService;
import com.example2.bll.services.implementations.OrderService;
import com.example2.bll.services.interfaces.IDishService;
import com.example2.bll.services.interfaces.IOrderService;
import com.example2.dal.entities.DishType;
import com.example2.dal.entities.OrderStatus;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private final IDishService dishService;
    private final IOrderService orderService;
    private final Scanner scanner;
    
    public ConsoleApplication() {
        this.dishService = new DishService();
        this.orderService = new OrderService();
        this.scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        
        // Set console encoding to UTF-8 for proper Ukrainian text display
        try {
            System.setProperty("file.encoding", "UTF-8");
            System.setProperty("console.encoding", "UTF-8");
        } catch (Exception e) {
            // Ignore encoding setup errors
        }
    }
    
    public void run() {
        System.out.println("=== СИСТЕМА ДОСТАВКИ СТРАВ ===");
        
        // Initialize sample data only if database is empty
        initializeSampleDataIfNeeded();
        
        while (true) {
            showMainMenu();
            String input = getMenuInput("Ваш вибiр: ");
            
            if (input.equals("0")) {
                System.out.println("\nДо побачення!");
                return;
            }
            
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        showDishesMenu();
                        break;
                    case 2:
                        showOrdersMenu();
                        break;
                    case 3:
                        showSearchMenu();
                        break;
                    default:
                        System.out.println("Невiрний вибiр. Спробуйте ще раз.");
                        waitForEnter();
                }
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введiть число.");
                waitForEnter();
            }
        }
    }
    
    private void showMainMenu() {
        System.out.println("\n+============================================================+");
        System.out.println("|                        ГОЛОВНЕ МЕНЮ                       |");
        System.out.println("+============================================================+");
        System.out.println("| 1. [+] Управлiння стравами                                |");
        System.out.println("| 2. [*] Управлiння замовленнями                            |");
        System.out.println("| 3. [?] Пошук                                              |");
        System.out.println("| 0. [X] Вихiд                                              |");
        System.out.println("+============================================================+");
    }
    
    private void showDishesMenu() {
        while (true) {
            System.out.println("\n+============================================================+");
            System.out.println("|                        МЕНЮ СТРАВ                         |");
            System.out.println("+============================================================+");
            System.out.println("| 1. [?] Показати всi страви                                |");
            System.out.println("| 2. [T] Показати страви за типом                           |");
            System.out.println("| 3. [D] Показати страви за датою                           |");
            System.out.println("| 4. [*] Показати доступнi страви                           |");
            System.out.println("| 5. [+] Додати нову страву                                 |");
            System.out.println("| 0. [<] Повернутися до головного меню                      |");
            System.out.println("+============================================================+");
            
            String input = getMenuInput("Ваш вибiр: ");
            
            if (input.equals("0")) {
                return;
            }
            
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        showAllDishes();
                        break;
                    case 2:
                        showDishesByType();
                        break;
                    case 3:
                        showDishesByDate();
                        break;
                    case 4:
                        showAvailableDishes();
                        break;
                    case 5:
                        addNewDish();
                        break;
                    default:
                        System.out.println("Невiрний вибiр. Спробуйте ще раз.");
                        waitForEnter();
                }
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введiть число.");
                waitForEnter();
            }
        }
    }
    
    private void showOrdersMenu() {
        while (true) {
            System.out.println("\n==== МЕНЮ ЗАМОВЛЕНЬ ====");
            System.out.println("1. Створити замовлення");
            System.out.println("2. Показати всi замовлення");
            System.out.println("3. Показати замовлення за статусом");
            System.out.println("4. Додати страву до замовлення");
            System.out.println("5. Пiдтвердити замовлення");
            System.out.println("0. Повернутися до головного меню");
            
            int choice = getIntInput("Виберiть дiю: ");
            
            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    showAllOrders();
                    break;
                case 3:
                    showOrdersByStatus();
                    break;
                case 4:
                    addDishToOrder();
                    break;
                case 5:
                    confirmOrder();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Невiрний вибiр. Спробуйте ще раз.");
            }
        }
    }
    
    private void showSearchMenu() {
        while (true) {
            System.out.println("\n==== ПОШУК ====");
            System.out.println("1. Пошук страв за назвою");
            System.out.println("2. Пошук страв за цiновим дiапазоном");
            System.out.println("3. Пошук замовлень за клieнтом");
            System.out.println("0. Повернутися до головного меню");
            
            int choice = getIntInput("Виберiть дiю: ");
            
            switch (choice) {
                case 1:
                    searchDishesByName();
                    break;
                case 2:
                    searchDishesByPriceRange();
                    break;
                case 3:
                    searchOrdersByCustomer();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Невiрний вибiр. Спробуйте ще раз.");
            }
        }
    }
    
    private void showAllDishes() {
        System.out.println("\n=== ВСi СТРАВИ ===");
        List<DishDto> dishes = dishService.getAllDishes();
        
        if (dishes.isEmpty()) {
            System.out.println("Страви не знайдено.");
            return;
        }
        
        for (int i = 0; i < dishes.size(); i++) {
            DishDto dish = dishes.get(i);
            System.out.printf("%d. [ID:%d] %s%n", 
                i + 1, dish.getId(), dish.toString());
        }
    }
    
    private void showDishesByType() {
        System.out.println("\n=== ТИПИ СТРАВ ===");
        DishType[] types = DishType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s%n", i + 1, types[i].getDisplayName());
        }
        
        int choice = getIntInput("Виберiть тип страви: ");
        if (choice >= 1 && choice <= types.length) {
            DishType selectedType = types[choice - 1];
            List<DishDto> dishes = dishService.getDishesByType(selectedType);
            
            System.out.printf("\n=== СТРАВИ ТИПУ: %s ===%n", selectedType.getDisplayName());
            if (dishes.isEmpty()) {
                System.out.println("Страви цього типу не знайдено.");
            } else {
                dishes.forEach(dish -> System.out.printf("[ID:%d] %s%n", dish.getId(), dish.toString()));
            }
        }
    }
    
    private void showDishesByDate() {
        String dateStr = getStringInput("Введiть дату (YYYY-MM-DD): ");
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            List<DishDto> dishes = dishService.getDishesByDate(date);
            
            System.out.printf("\n=== СТРАВИ НА %s ===%n", dateStr);
            if (dishes.isEmpty()) {
                System.out.println("Страви на цю дату не знайдено.");
            } else {
                dishes.forEach(dish -> System.out.printf("[ID:%d] %s%n", dish.getId(), dish.toString()));
            }
        } catch (Exception e) {
            System.out.println("Невiрний формат дати. Використовуйте YYYY-MM-DD");
        }
    }
    
    private void showAvailableDishes() {
        System.out.println("\n=== ДОСТУПНi СТРАВИ ===");
        List<DishDto> dishes = dishService.getAvailableDishes();
        
        if (dishes.isEmpty()) {
            System.out.println("Доступнi страви не знайдено.");
        } else {
            dishes.forEach(dish -> System.out.printf("[ID:%d] %s%n", dish.getId(), dish.toString()));
        }
    }
    
    private void addNewDish() {
        if (!confirmAction("Додавання нової страви")) {
            return;
        }
        
        System.out.println("\n=== ДОДАТИ НОВУ СТРАВУ ===");
        
        String name = getStringInputWithBack("Назва страви");
        if (name == null) return;
        
        String description = getStringInputWithBack("Опис страви");
        if (description == null) return;
        
        System.out.print("Цiна (або 0 для повернення): ");
        String priceStr = scanner.nextLine().trim();
        if (priceStr.equals("0")) return;
        
        Double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                System.out.println("Цiна повинна бути бiльше 0.");
                waitForEnter();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Невiрний формат цiни.");
            waitForEnter();
            return;
        }
        
        System.out.println("\nВиберiть тип страви:");
        DishType[] types = DishType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s%n", i + 1, types[i].getDisplayName());
        }
        System.out.println("0. Повернутися назад");
        
        String typeInput = getMenuInput("Тип страви: ");
        if (typeInput.equals("0")) return;
        
        int typeChoice;
        try {
            typeChoice = Integer.parseInt(typeInput);
            if (typeChoice < 1 || typeChoice > types.length) {
                System.out.println("Невiрний тип страви.");
                waitForEnter();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Будь ласка, введiть число.");
            waitForEnter();
            return;
        }
        
        String dateStr = getStringInputWithBack("Дата доступностi (YYYY-MM-DD)");
        if (dateStr == null) return;
        
        LocalDate availableDate;
        try {
            availableDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            System.out.println("Невiрний формат дати. Використовуйте YYYY-MM-DD");
            waitForEnter();
            return;
        }
        
        DishDto dishDto = new DishDto(name, description, price, types[typeChoice - 1], availableDate);
        DishDto savedDish = dishService.createDish(dishDto);
        
        System.out.printf("Страву успiшно додано з ID: %d%n", savedDish.getId());
    }
    
    private void createOrder() {
        System.out.println("\n=== СТВОРИТИ ЗАМОВЛЕННЯ ===");
        
        String customerName = getStringInput("iм'я клieнта: ");
        String customerAddress = getStringInput("Адреса доставки: ");
        String customerPhone = getStringInput("Телефон: ");
        
        OrderDto order = orderService.createOrder(customerName, customerAddress, customerPhone);
        System.out.printf("Замовлення створено з ID: %d%n", order.getId());
    }
    
    private void showAllOrders() {
        System.out.println("\n=== ВСi ЗАМОВЛЕННЯ ===");
        List<OrderDto> orders = orderService.getAllOrders();
        
        if (orders.isEmpty()) {
            System.out.println("Замовлення не знайдено.");
        } else {
            orders.forEach(this::printOrderInfo);
        }
    }
    
    private void showOrdersByStatus() {
        System.out.println("\n=== СТАТУСИ ЗАМОВЛЕНЬ ===");
        OrderStatus[] statuses = OrderStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.printf("%d. %s%n", i + 1, statuses[i].getDisplayName());
        }
        
        int choice = getIntInput("Виберiть статус: ");
        if (choice >= 1 && choice <= statuses.length) {
            OrderStatus selectedStatus = statuses[choice - 1];
            List<OrderDto> orders = orderService.getOrdersByStatus(selectedStatus);
            
            System.out.printf("\n=== ЗАМОВЛЕННЯ Зi СТАТУСОМ: %s ===%n", selectedStatus.getDisplayName());
            if (orders.isEmpty()) {
                System.out.println("Замовлення з таким статусом не знайдено.");
            } else {
                orders.forEach(this::printOrderInfo);
            }
        }
    }
    
    private void addDishToOrder() {
        System.out.println("\n=== ДОДАТИ СТРАВУ ДО ЗАМОВЛЕННЯ ===");
        
        Long orderId = getLongInput("ID замовлення: ");
        
        showAvailableDishes();
        Long dishId = getLongInput("ID страви: ");
        Integer quantity = getIntInput("Кiлькiсть: ");
        
        try {
            OrderDto updatedOrder = orderService.addDishToOrder(orderId, dishId, quantity);
            System.out.println("Страву додано до замовлення:");
            printOrderInfo(updatedOrder);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    
    private void confirmOrder() {
        System.out.println("\n=== ПiДТВЕРДИТИ ЗАМОВЛЕННЯ ===");
        
        Long orderId = getLongInput("ID замовлення: ");
        
        try {
            OrderDto confirmedOrder = orderService.confirmOrder(orderId);
            System.out.println("Замовлення пiдтверджено:");
            printOrderInfo(confirmedOrder);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    
    private void searchDishesByName() {
        System.out.println("\nПОРАДА: Якщо українськi символи не працюють, спробуйте:");
        System.out.println("- Borscht (замiсть Борщ)");
        System.out.println("- Kotleta (замiсть Котлета)");
        System.out.println("- Caesar (замiсть Цезар)");
        System.out.println("- Tiramisu (замiсть Тiрамiсу)");
        System.out.println("- Compot (замiсть Компот)");
        
        String name = getStringInput("Введiть частину назви страви: ");
        List<DishDto> dishes = dishService.searchDishesByName(name);
        
        System.out.printf("\n=== РЕЗУЛЬТАТИ ПОШУКУ ДЛЯ: %s ===%n", name);
        if (dishes.isEmpty()) {
            System.out.println("Страви не знайдено.");
        } else {
            dishes.forEach(dish -> System.out.printf("[ID:%d] %s%n", dish.getId(), dish.toString()));
        }
    }
    
    private void searchDishesByPriceRange() {
        Double minPrice = getDoubleInput("Мiнiмальна цiна: ");
        Double maxPrice = getDoubleInput("Максимальна цiна: ");
        
        List<DishDto> dishes = dishService.getDishesByPriceRange(minPrice, maxPrice);
        
        System.out.printf("\n=== СТРАВИ В ДiАПАЗОНi %.2f - %.2f ГРН ===%n", minPrice, maxPrice);
        if (dishes.isEmpty()) {
            System.out.println("Страви в цьому цiновому дiапазонi не знайдено.");
        } else {
            dishes.forEach(dish -> System.out.printf("[ID:%d] %s%n", dish.getId(), dish.toString()));
        }
    }
    
    private void searchOrdersByCustomer() {
        String customerName = getStringInput("Введiть iм'я клieнта: ");
        List<OrderDto> orders = orderService.getOrdersByCustomer(customerName);
        
        System.out.printf("\n=== ЗАМОВЛЕННЯ КЛieНТА: %s ===%n", customerName);
        if (orders.isEmpty()) {
            System.out.println("Замовлення не знайдено.");
        } else {
            orders.forEach(this::printOrderInfo);
        }
    }
    
    private void printOrderInfo(OrderDto order) {
        System.out.printf("Замовлення #%d | Клieнт: %s | Статус: %s | Сума: %.2f грн%n",
            order.getId(), order.getCustomerName(), 
            order.getStatus().getDisplayName(), 
            order.getTotalPrice() != null ? order.getTotalPrice() : 0.0);
        
        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            System.out.println("  Страви:");
            order.getOrderItems().forEach(item -> 
                System.out.printf("    - %s%n", item.toString()));
        }
        System.out.println();
    }
    
    private void initializeSampleDataIfNeeded() {
        // Check if data already exists
        List<DishDto> existingDishes = dishService.getAllDishes();
        if (existingDishes == null || existingDishes.isEmpty()) {
            System.out.println("📦 Ініціалізація початкових даних...");
            initializeSampleData();
            System.out.println("✅ Початкові дані створено!");
        } else {
            System.out.println("📂 Завантажено існуючі дані (" + existingDishes.size() + " страв)");
        }
    }
    
    private void initializeSampleData() {
        // Додаeмо тестовi страви
        dishService.createDish(new DishDto("Борщ украiнськiй", "Традицiйний червоний борщ з м'ясом", 85.0, DishType.SOUP, LocalDate.now()));
        dishService.createDish(new DishDto("Котлета по-киiвськи", "Курячi котлети з маслом та зеленню", 120.0, DishType.MAIN_COURSE, LocalDate.now()));
        dishService.createDish(new DishDto("Салат Цезар", "Класичний салат з куркою та пармезаном", 95.0, DishType.SALAD, LocalDate.now()));
        dishService.createDish(new DishDto("Тiрамiсу", "iталiйський десерт з маскарпоне", 75.0, DishType.DESSERT, LocalDate.now()));
        dishService.createDish(new DishDto("Компот з сухофруктiв", "Домашнiй компот", 25.0, DishType.BEVERAGE, LocalDate.now()));
        
        // Добавляем дубликаты с английскими названиями для поиска
        dishService.createDish(new DishDto("Borscht", "Традицiйний червоний борщ з м'ясом", 85.0, DishType.SOUP, LocalDate.now()));
        dishService.createDish(new DishDto("Kotleta", "Курячi котлети з маслом та зеленню", 120.0, DishType.MAIN_COURSE, LocalDate.now()));
        dishService.createDish(new DishDto("Caesar Salad", "Класичний салат з куркою та пармезаном", 95.0, DishType.SALAD, LocalDate.now()));
        dishService.createDish(new DishDto("Tiramisu", "iталiйський десерт з маскарпоне", 75.0, DishType.DESSERT, LocalDate.now()));
        dishService.createDish(new DishDto("Compot", "Домашнiй компот", 25.0, DishType.BEVERAGE, LocalDate.now()));
    }
    
    private String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Поле не може бути порожнiм. Спробуйте ще раз.");
        }
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введiть цiле число.");
            }
        }
    }
    
    private long getLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введiть цiле число.");
            }
        }
    }
    
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введiть число.");
            }
        }
    }
    
    private String getMenuInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private void waitForEnter() {
        System.out.println("\nНатиснiть Enter для продовження...");
        scanner.nextLine();
    }
    
    private String getStringInputWithBack(String prompt) {
        while (true) {
            System.out.print(prompt + " (або 0 для повернення): ");
            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                return null; // Signal to go back
            }
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Поле не може бути порожнiм. Спробуйте ще раз.");
        }
    }
    
    private boolean confirmAction(String action) {
        while (true) {
            System.out.printf("\n--- %s ---\n", action);
            System.out.println("0. Повернутися до попереднього меню");
            System.out.println("1. Продовжити " + action.toLowerCase());
            String input = getMenuInput("Ваш вибiр: ");
            
            if (input.equals("0")) {
                return false;
            } else if (input.equals("1")) {
                return true;
            } else {
                System.out.println("Невiрний вибiр. Оберiть 0 або 1.");
                waitForEnter();
            }
        }
    }
}
