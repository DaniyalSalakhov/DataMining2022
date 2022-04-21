import java.util.Random;

public class BloomFilter{
    public int[] array;
    public Hash[] hash;

    public BloomFilter(int size, double falsePositive){
        Double arraySize = -(size * Math.log(falsePositive)) / (Math.log(2) * Math.log(2));
        Double optimalHash = (arraySize / size) * Math.log(2);
        array = new int[(int) Math.ceil(arraySize)];
        hash = new Hash[(int)Math.ceil(optimalHash)];
        for(int i = 0; i < hash.length; i++){
            hash[i] = new Hash();
        }

    }

    public void add(String word){
        if(!isPresent(word)) {
            for (int i = 0; i < hash.length; i++) {
                array[hash[i].getHash(word) % array.length] |= 1;
            }
        }
    }

    public boolean isPresent(String word){
        for(int i = 0; i < hash.length; i++){
            if(array[hash[i].getHash(word) % array.length] == 0){
                return false;
            }
        }
        return true;
    }

    class Hash{
        int a = 7;
        int s = 0;

        Hash(){
            Random r = new Random();
            s = (int)Math.floor(r.nextDouble() * a);
        }

        int getHash(String word){
            int res = 1;
            for(int i = 0; i < word.length(); i++){
                res = (int) (s * res + word.charAt(i));
            }
            return res;
        }
    }
}



