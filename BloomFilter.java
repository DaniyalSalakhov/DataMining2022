import javafx.util.Builder;

import java.util.Objects;

public class BloomFilter {
    public final long[] array;

    public final int size;

    public BloomFilter(long[] array, int size) {
        this.array = array;
        this.size = size;
    }

    public static class Builder {
        private int size;

        public Builder withSize(int size) {
            if (Integer.bitCount(size) != 1) {
                throw new IllegalArgumentException("wrong size");
            }
            this.size = size;
            return this;
        }

        public BloomFilter build() {
            return new BloomFilter(new long[size >>> 6], size);
        }
    }

    private int mapHash(int hash){
        return hash & (size - 1);
    }

    private int getHash(String value){
        int hash = 5381;
        for(int i = 0; i < value.length(); i++){
            hash = ((hash << 5) + hash) + Character.getNumericValue(value.charAt(i));
        }
        return hash % size;
    }

    public void add(String value){
        if(!isPresent(value)) {
            int hash = mapHash(Objects.hash(value));
            array[hash >>> 6] |= 1L << hash;
        }
    }

    public boolean isPresent(String value){
        int hash = mapHash(Objects.hash(value));
        if((array[hash >>> 6] & (1L << hash)) == 0){
            return false;
        }
        return true;
    }
}

