package ru.job4j.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Actor"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        Role result = store.findById("10");
        Assertions.assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        store.add(new Role("1", "Lead"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Actor"));
    }

    @Test
    public void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        store.replace("1", new Role("1", "Lead"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Lead"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        store.replace("10", new Role("10", "Lead"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Actor"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        store.delete("1");
        Role result = store.findById("1");
        Assertions.assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Actor"));
    }
}