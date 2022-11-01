package ru.job4j.ood.isp.menu;

public class TestAction implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("TestAction run!");
    }
}
