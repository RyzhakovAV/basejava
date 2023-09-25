package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index == -1) {
            storage[size] = r;
            size++;
        } else {
            System.out.printf("Добавление невозможно. %s существует\n", r.getUuid());
        }
    }

    public void update(Resume r, String uuid) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            r.setUuid(uuid);
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == size - 1) {
            storage[index] = null;
        } else {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
        }
        size--;
    }


    public Resume[] getAll() {
        Resume[] copyArray;
        copyArray = Arrays.copyOf(storage, size);
        return copyArray;
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {        //реализация поиска по массиву
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
                i = size;
            }
        }
        return index;
    }
}
