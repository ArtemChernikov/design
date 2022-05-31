package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс описывает модель очереди со свойствами <b>in</b> и <b>out</b>
 * В классе используется модель стека {@link SimpleStack}
 *
 * @param <T> - элемент
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
public class SimpleQueue<T> {
    /**
     * Поле стек, который используется для удаления элементов
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    /**
     * Поле основной стек
     */
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод используется для удаления первого элемента в очереди
     * Использует внутри методы {@link SimpleStack#push(Object)} и {@link SimpleStack#pop()}
     * 1) Запускаем цикл пока в основном стеке {@link SimpleQueue#out} есть элементы
     * удаляем их и добавляем в {@link SimpleQueue#in} (получаем такой же стек как и был
     * только ревёрснутый, теперь с помощью метода pop(), можно удалить элемент)
     * 2) Удаляем последний элемент стека {@link SimpleQueue#in} и присваиваем значение
     * локальной переменной, для возвращения удаленного элемента из  стека
     * 3) Делаем все тоже самое, как и в п.1, только наоборот: переносим оставшиеся элементы
     * из стека {@link SimpleQueue#in} в стек {@link SimpleQueue#out}, для того, чтобы они приняли исходный порядок
     *
     * @return - возвращает удаленный элемент
     */
    public T poll() {
        if (in.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод используется для вставки элемента в конец очереди стека {@link SimpleQueue#out}
     * Использует внутри метод {@link SimpleStack#push(Object)}
     *
     * @param value - элемент
     */
    public void push(T value) {
        in.push(value);
    }
}
