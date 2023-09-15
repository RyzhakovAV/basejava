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

        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] copyArray;
        copyArray = Arrays.copyOf(storage, size);
        return copyArray;
    }

    int size() {
        return size;
    }
}
