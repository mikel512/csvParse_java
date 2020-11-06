package Logic;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.sql.Date;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * This class supports CSV text files to be parsed into a stream of objects off the specified type
 * <p>
 * By default CsvParse supports {@code Integer, Double, Character, String,} and {@code Boolean}
 * </p>
 */
public class CsvParse<T> {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.CONSTRUCTOR)
    public @interface CsvConstructor{}

    private BufferedReader reader;
    private Class<T> clazz;
    private String delimeter = ",";
    private boolean ignoreHeader = true;

    private static Map<Class<?>, Function<String, ?>> PARSERS = new HashMap<>(Map.ofEntries(
            Map.entry(Integer.class, Integer::parseInt),
            Map.entry(Double.class, Double::parseDouble),
            Map.entry(Character.class, s -> s.charAt(0)),
            Map.entry(String.class, s -> s),
            Map.entry(Boolean.class, Boolean::parseBoolean),
            Map.entry(Date.class, Date::valueOf)
    ));

    private CsvParse(BufferedReader reader, Class<T> clazz){
        this.reader = reader;
        this.clazz = clazz;
    }

    /**
     * Creates a new CsvParse instance from the specified reader
     *
     * @param reader a {@link BufferedReader} containing {@code n} lines of text, each line containing {@code m} fields seperated by a delimiter.
     * @param clazz the class type of object that each row is parsed into.
     * @param <T> the type corresponding to {@code clazz}
     * @return a new CsvParse instance
     */
    public static <T> CsvParse<T> newInstance(BufferedReader reader, Class<T> clazz){
        return new CsvParse<>(reader, clazz);
    }

    /**
     * Maps each line of the reader to a parsed instance of type {@code T}.
     * @return a Stream of instances of type {@code T} corresponding to each line
     */
    public Stream<T> rows(){
        return reader.lines().skip(ignoreHeader ? 1 : 0).map(this::parseRow);
    }

    private T parseRow(String row){
        String[] split = row.split(delimeter);
        Constructor<?> annotatedCtor = Arrays.stream(clazz.getConstructors())
                .filter(ctor -> ctor.isAnnotationPresent(CsvConstructor.class))
                .findFirst()
                .orElseThrow();

        Class<?>[] ctorParams = annotatedCtor.getParameterTypes();
        Object[] args = IntStream.range(0, ctorParams.length)
                .mapToObj(i -> PARSERS.get(ctorParams[i]).apply(split[i]))
                .toArray();
        try {
            return clazz.cast(args);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // simple csv parsing using no external libraries
/*
    private List<T> getCsvBase(String fileName, Class<T> t){
        String csvFile = "src/Data/" + fileName;
        String[] columnNames = null;
        String line = "";
        List<T> data = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            boolean isColumnNames = true;
            while((line = br.readLine()) != null){
                String[] attr = line.split("\\,");
                if(isColumnNames){
                    columnNames = attr;
                    isColumnNames = false;
                }else{
                    Constructor<T> newEntry = getClass().getDeclaredConstructor().newInstance();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }


        return data;
    }
*/

}
