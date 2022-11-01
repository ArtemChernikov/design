package ru.job4j.ood.isp.menu;

public class AppAction implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("AppAction run!");
    }
}
