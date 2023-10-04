package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index > 0) {
            storage[index] = r;
        } else {
            System.out.println("Ошибка обновления данных");
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Добавление не возможно, массив переполнен");
        }else if (index >= 0) {
            System.out.printf("Добавление невозможно. %s существует\n", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if(index >= 0) {
            return storage[index];
        } else {
            System.out.println("Данные не найдены");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            fillDeleteElement(index);
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

    protected abstract void insertElement(Resume r, int index);
    protected abstract void fillDeleteElement(int index);
    protected abstract int findIndex(String uuid);
}
