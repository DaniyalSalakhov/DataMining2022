public class Main {
    public static void main(String[] args) {
        BloomFilter filter = new BloomFilter.Builder().withSize(256).build();

        String str = "Мимо белого яблока луны " +
                "Мимо красного яблока заката " +
                "Облака из неведомой страны " +
                "К нам спешат и опять бегут кудато " +
                "Облака белогривые лошадки " +
                "Облака что вы мчитесь без оглядки " +
                "Не смотрите вы пожалуйста свысока " +
                "А по небу прокатите нас облака " +
                "Мы помчимся в заоблачную даль " +
                "Мимо гаснущих звезд на небосклоне " +
                "К нам неслышно опустится звезда " +
                "И ромашкой останется в ладони " +
                "Облака белогривые лошадки " +
                "Облака что вы мчитесь без оглядки " +
                "Не смотрите вы пожалуйста свысока " +
                "А по небу прокатите нас облака";

        String[] strArray = str.split(" ");

        for (String word : strArray) {
            filter.add(word);
        }

        for(int i = 0; i < filter.array.length; i++){
            System.out.print(filter.array[i] + ",");
        }
    }
}