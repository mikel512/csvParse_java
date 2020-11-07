package utilities;

import java.io.BufferedReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.beans.Statement;

public class CsvParseBean<T> {
    private BufferedReader reader;
    private Class<T> clazz;
    String[] firstLine;
    private List<Method> setters;
    private String delimeter = ",";
    private boolean ignoreHeader = true;

    private static Map<Class<?>, Function<String, ?>> PARSERS = new HashMap<>(Map.ofEntries(
            Map.entry(Integer.class, Integer::parseInt),
            Map.entry(Double.class, Double::parseDouble),
            Map.entry(Character.class, s -> s.charAt(0)),
            Map.entry(String.class, s -> s),
            Map.entry(Boolean.class, Boolean::parseBoolean)
    ));

    private CsvParseBean(BufferedReader reader, Class<T> clazz){
        this.reader = reader;
        this.clazz = clazz;
        setters = new ArrayList<>();
        try{
            firstLine = reader.readLine().split(delimeter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setBeanSetters();
    }

    /**
     * Creates a new CsvParse instance from the specified reader
     *
     * @param reader a {@link BufferedReader} containing {@code n} lines of text, each line containing {@code m} fields seperated by a delimiter.
     * @param clazz the class type of object that each row is parsed into.
     * @param <T> the type corresponding to {@code clazz}
     * @return a new CsvParse instance
     */
    public static <T> CsvParseBean<T> newInstance(BufferedReader reader, Class<T> clazz){
        return new CsvParseBean<>(reader, clazz);
    }

    /**
     * Maps each line of the reader to a parsed instance of type {@code T}.
     * @return a Stream of instances of type {@code T} corresponding to each line
     */
    public Stream<T> rows(){
        return reader.lines().skip(ignoreHeader ? 1 : 0).map(this::parseRow);
    }

    private void setBeanSetters() {
        // get first line to create map of columns to setters
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private T parseRow(String row){
        String[] split = row.split(delimeter);

/*
        Constructor<?> annotatedCtor = Arrays.stream(clazz.getConstructors())
                .filter(ctor -> ctor.isAnnotationPresent(CsvParse.CsvConstructor.class))
                .findFirst()
                .orElseThrow();
*/
        try {
            Object obj = clazz.getDeclaredConstructor().newInstance();

            for (int i = 0; i < firstLine.length; i++) {
                Method current = findMethod(clazz, "set" + firstLine[i].substring(0,1).toUpperCase() + firstLine[i].substring(1));
                Statement stmt = new Statement(obj, current.getName(),
                        new Object[] {PARSERS.get(current.getParameterTypes()[0]).apply(split[i])});
                stmt.execute();

            }
            return clazz.cast(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }

/*
        Class<?>[] ctorParams = annotatedCtor.getParameterTypes();
        Object[] args = IntStream.range(0, ctorParams.length)
                .mapToObj(i -> PARSERS.get(ctorParams[i]).apply(split[i]))
                .toArray();
        try {
            return (T) annotatedCtor.newInstance(args);
        } catch (Exception e){
            e.printStackTrace();
        }
*/
        return null;
    }

    private static Method findMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {

        // Then loop through all available methods, checking them one by one.
        for (Method method : clazz.getMethods()) {

            String name = method.getName();
            if (!methodName.equals(name)) { // The method must have right name.
                continue;
            }
            boolean match = true;
            if (match) {
                return method;
            }

        }

        // None of our trials was successful!
        throw new NoSuchMethodException();
    }
}
