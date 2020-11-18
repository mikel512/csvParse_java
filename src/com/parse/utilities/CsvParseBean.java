package com.parse.utilities;

import java.beans.Statement;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import org.apache.commons.text.StringEscapeUtils;

/**
 * This class supports CSV text files to be parsed into a stream of JavaBean objects of the specified type.
 * Currently, the first line of the CSV must match the names of the properties of each Bean in order to correctly call each setter;
 * <p>
 * By default CsvParse supports {@code Integer, Double, Character, String,} and {@code Boolean}
 * </p>
 */
public class CsvParseBean<T> implements java.io.Serializable {
    private final BufferedReader reader;
    private final Class<T> clazz;
    private String[] firstLine;
    private final Map<String, Method> methodCache;
    private final String delimeter = ",";

    private static final Map<Class<?>, Function<String, ?>> PARSERS = new HashMap<>(Map.ofEntries(
            Map.entry(Integer.class, Integer::parseInt),
            Map.entry(Double.class, Double::parseDouble),
            Map.entry(Character.class, s -> s.charAt(0)),
            Map.entry(String.class, s -> s),
            Map.entry(Boolean.class, Boolean::parseBoolean)
    ));

    private CsvParseBean(BufferedReader reader, Class<T> clazz){
        this.reader = reader;
        this.clazz = clazz;
        setFirstLine();
        methodCache = new HashMap<>();
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
        return reader.lines().map(this::parseRow);
    }

    private T parseRow(String row){
        List<String> split = splitEscape(row);
        try {
            Object obj = clazz.getDeclaredConstructor().newInstance();

            for (int i = 0; i < firstLine.length; i++) {
                if(split.size() < firstLine.length) break;
                Method current = findMethod(clazz, "set" + firstLine[i].substring(0,1).toUpperCase() + firstLine[i].substring(1));
                Statement stmt = new Statement(obj, current.getName(),
                        new Object[] {PARSERS.get(current.getParameterTypes()[0]).apply(split.get(i))});
                stmt.execute();
            }

            return clazz.cast(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<String> splitEscape(String row) {
        List<String> tokensList = new ArrayList<String>();
        boolean inQuotes = false;
        StringBuilder b = new StringBuilder();
        for (char c : row.toCharArray()) {
            switch (c) {
                case ',':
                    if (inQuotes) {
                        b.append(c);
                    } else {
                        tokensList.add(StringEscapeUtils.unescapeCsv(b.toString()));
                        b = new StringBuilder();
                    }
                    break;
                case '\"':
                    inQuotes = !inQuotes;
                default:
                    b.append(c);
                    break;
            }
        }
        tokensList.add(StringEscapeUtils.unescapeCsv(b.toString()));

        return tokensList;
    }

    private void setFirstLine() {
        try{
            firstLine = reader.readLine().toLowerCase().split(delimeter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Method findMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
        // First, check is method is already cached
        if(methodCache.containsKey(methodName)) {
            return methodCache.get(methodName);
        }

        // Then loop through all available methods, checking them one by one.
        for (Method method : clazz.getMethods()) {

            String name = method.getName();
            if (!methodName.equals(name)) { // The method must have right name.
                continue;
            }
            // Add to cache then return
            methodCache.put(methodName, method);

            return method;

        }

        // None of our trials was successful!
        throw new NoSuchMethodException();
    }
}
