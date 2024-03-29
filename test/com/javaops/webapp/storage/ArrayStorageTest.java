package com.javaops.webapp.storage;

import com.javaops.webapp.exception.StorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception{
        try {
            storage.clear();
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}