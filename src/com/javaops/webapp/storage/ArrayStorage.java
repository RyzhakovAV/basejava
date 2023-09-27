package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Добавление не возможно, массив переполнен");
        }else if (findIndex(r.getUuid()) != -1) {
            System.out.printf("Добавление невозможно. %s существует\n", r.getUuid());
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Ошибка обновления данных");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if(index != -1) {
            return storage[index];
        } else {
            System.out.println("Данные не найдены");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size -1];
            storage[size-1] = null;
            size--;
        } else {
            System.out.println("Данные не найдены. Ошибка удаления.");
        }
    }


    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
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
