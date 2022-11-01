package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Scanner;

public class TODOApp {
    public static final String MENU =
            """
                    Введите 1 для добавления новой задачи;
                    Введите 2 для добавления подзадачи;
                    Введите 3 для просмотра списка задач;
                    Введите 0 для выхода.""";
    public static final int ADD_TASK = 1;
    public static final int ADD_SUBTASK = 2;
    public static final int VIEW_TASKS = 3;
    public static final int EXIT = 0;
    public static final String TASK_NAME = "Введите название задачи";
    public static final String SUBTASK_NAME = "Введите название подзадачи";
    public static final String TASK_ADDED = "Задача добавлена!";
    public static final String TASK_NOT_ADDED = "Задача не была добавлена";
    public static final String SUBTASK_ADDED = "Подзадача добавлена!";
    public static final String SUBTASK_NOT_ADDED = "Подзадача не была добавлена";
    private final Menu menu;
    private final MenuPrinter printer;
    private final List<ActionDelegate> actions;


    public TODOApp(Menu menu, MenuPrinter printer, List<ActionDelegate> actions) {
        this.menu = menu;
        this.printer = printer;
        if (actions.isEmpty()) {
            throw new IllegalArgumentException("Добавьте в список действий хотя бы одно действие!");
        }
        this.actions = actions;
    }

    public void init() {
        boolean run = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (run) {
                System.out.println(MENU);
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case ADD_TASK:
                        System.out.println(TASK_NAME);
                        String subtask = scanner.nextLine();
                        if (menu.add(null, subtask, getAction())) {
                            System.out.println(TASK_ADDED);
                        } else {
                            System.out.println(TASK_NOT_ADDED);
                        }
                        break;
                    case ADD_SUBTASK:
                        System.out.println(TASK_NAME);
                        String task = scanner.nextLine();
                        System.out.println(SUBTASK_NAME);
                        String subtask1 = scanner.nextLine();
                        if (menu.add(task, subtask1, getAction())) {
                            System.out.println(SUBTASK_ADDED);
                        } else {
                            System.out.println(SUBTASK_NOT_ADDED);
                        }
                        break;
                    case VIEW_TASKS:
                        printer.print(menu);
                        break;
                    case EXIT:
                        run = false;
                        break;
                    default:
                        System.out.println("Введите корректный номер пункта!");
                }
            }
        }
    }

    private ActionDelegate getAction() {
        return actions.get((int) (Math.random() * actions.size()));
    }

    public static void main(String[] args) {
        TODOApp app = new TODOApp(new SimpleMenu(), new Printer(), List.of(new TestAction(), new AppAction()));
        app.init();
    }
}
