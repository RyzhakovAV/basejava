package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillDeleteElement(int index) {
        storage[index] = storage[size - 1];
    }

    protected Integer getSearchKey(String uuid) {        //реализация поиска по массиву
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
