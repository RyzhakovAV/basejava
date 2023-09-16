import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if ((String.valueOf(storage[i])).equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if((String.valueOf(storage[i])).equals(uuid)) {
                index = i;
                i = size;
            }
        }
        System.out.println(index);
        if(index == size - 1) {
            storage[index] = null;
        }else{
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
        }
        size--;
    }


    Resume[] getAll() {
        Resume[] copyArray;
        copyArray = Arrays.copyOf(storage, size);
        return copyArray;
    }

    int size() {
        return size;
    }
}
