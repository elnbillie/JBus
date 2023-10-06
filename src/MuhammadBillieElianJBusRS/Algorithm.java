package MuhammadBillieElianJBusRS;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

public class Algorithm {
    private Algorithm(){

    }

    public static <T> boolean exists(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it,value);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T current = iterator.next();
            if(pred.Predicate(current))
                return true;
        }
        return false;
    }


    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T>pred){
        List<T> resultList = new ArrayList<>();
        for (T item : iterable) {
            if (pred.Predicate(item)) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    public static <T> List<T> collect(Iterable<T> iterable, T value){
        final Predicate<T> pred = value::equals;
        return collect(iterable, pred);
    }

    public static <T> List<T> collect(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }

    public static <T> List<T> collect(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }

    public static <T> List<T> collect(Iterator<T>iterator, T value){
        final Predicate<T> pred=value::equals;
        return collect(iterator, pred);
    }

    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
        List<T> resultList = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.Predicate(current)) {
                resultList.add(current);
            }
        }
        return resultList;
    }

    public static <T> int count(Iterator<T> iterator,T value ){
        final Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    public static <T> int count(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }

    public static <T> int count(Iterator<T> iterator, Predicate<T> pred){
        int count = 0;
        while (iterator.hasNext()) {
            if (pred.Predicate(iterator.next())) { //masih diragukan
                count++;
            }
        }
        return count;
    }

    public static <T> int count(T[] array, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    public static <T> int count(Iterable<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    public static <T> int count(Iterable<T> iterable, Predicate<T> pred){
        int count = 0;
        for (T item : iterable) {
            if (pred.Predicate(item)) { //masih diragukannnnnnnn
                count++;
            }
        }
        return count;
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> pred){
        for (T item : iterable) {
            if (pred.Predicate(item)) {
                return item;
            }
        }
        return null;
    }

    public static <T> T find(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    public static <T> T find(T[] array, Predicate<T> pred){
        return Arrays.stream(array).filter(item -> pred.Predicate(item)).findFirst().orElse(null);
    }

    public static <T> T find(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }

    public static <T> T find(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    public static <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.Predicate(current)) {
                return current;
            }
        }
        return null;
    }
}