package com.javaops.webapp.storage;

import com.javaops.webapp.exception.ExistStorageException;
import com.javaops.webapp.exception.NotExistStorageException;
import com.javaops.webapp.exception.StorageException;
import com.javaops.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index > 0) {
            storage[index] = r;
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public final void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Array overflow", r.getUuid());
        }else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if(index >= 0) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            fillDeleteElement(index);
            storage[size-1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final int size() {
        return size;
    }

    protected abstract void insertElement(Resume r, int index);
    protected abstract void fillDeleteElement(int index);
    protected abstract int findIndex(String uuid);
}
