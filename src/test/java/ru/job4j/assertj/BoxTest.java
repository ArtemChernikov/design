package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .startsWith("S");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotEmpty()
                .startsWith("C");
    }

    @Test
    void whenVertexEquals4() {
        Box box = new Box(8, 6);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(8)
                .isPositive()
                .isEven()
                .isGreaterThan(5);
    }

    @Test
    void whenExist() {
        Box box = new Box(8, 6);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void whenNotExist() {
        Box box = new Box(-4, 100);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void whenCubeGetArea() {
        Box box = new Box(8, 6);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(216, withPrecision(0.05))
                .isGreaterThan(200)
                .isLessThan(300);
    }

    @Test
    void whenSphereGetArea() {
        Box box = new Box(0, 10);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(1256, withPrecision(0.7))
                .isGreaterThan(1000)
                .isLessThan(10000);
    }

}