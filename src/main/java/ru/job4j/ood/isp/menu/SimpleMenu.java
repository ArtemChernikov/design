package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        if (Objects.equals(parentName, ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            rsl = true;
        } else {
            Optional<ItemInfo> optional = findItem(parentName);
            if (optional.isPresent()) {
                ItemInfo parent = optional.get();
                parent.menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> rsl = Optional.empty();
        Optional<ItemInfo> optional = findItem(itemName);
        if (optional.isPresent()) {
            ItemInfo itemInfo = optional.get();
            MenuItemInfo menuItemInfo = new MenuItemInfo(itemInfo.menuItem.getName(),
                    itemInfo.menuItem.getChildren().stream().map(MenuItem::getName).collect(Collectors.toList()),
                    itemInfo.menuItem.getActionDelegate(), itemInfo.number);
            menuItemInfo.getActionDelegate().delegate();
            rsl = Optional.of(menuItemInfo);
        }
        return rsl;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        List<MenuItemInfo> list = new ArrayList<>();
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo item = dfsIterator.next();
            list.add(new MenuItemInfo(item.menuItem, item.number));
        }
        return list.listIterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        Optional<ItemInfo> rsl = Optional.empty();
        while (iterator.hasNext()) {
            ItemInfo item = iterator.next();
            if (name.equals(item.menuItem.getName())) {
                rsl = Optional.of(item);
                break;
            }
        }
        return rsl;
    }

    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }

        @Override
        public String toString() {
            return "SimpleMenuItem{"
                    + "name='" + name + '\''
                    + ", children=" + children
                    + ", actionDelegate=" + actionDelegate
                    + '}';
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        @Override
        public String toString() {
            return "ItemInfo{"
                    + "menuItem=" + menuItem
                    + ", number='" + number + '\''
                    + '}';
        }
    }
}
