package com.parse.utilities;

import java.io.*;
import java.util.Map;

/**
 * This class creates a .java file which contains a class adhering to the JavaBean standard from a CSV file
 * The name of the class within this new file will match the name of the .csv file
 * The properties of this new class will be extracted from the first line of the CSV
 * For purposes of this project it will also include Hibernate annotations for easy SQL access using ORM as an option
 */
public class CreateClassFileFromCsv {
    private boolean annotations = true;

    private CreateClassFileFromCsv() {}

    public static CreateClassFileFromCsv newInstance() {
        return new CreateClassFileFromCsv();
    }

    public void createAll(Map<String, File> map) {
        for (Map.Entry<String, File> pair : map.entrySet()) {
            // create JavaBean
            createFile(pair.getValue());
        }
    }

    public CreateClassFileFromCsv withoutAnnotations() {
        this.annotations = false;
        return this;
    }

    public void createFile(File csvFile) {
        String name = csvFile.getName().substring(0, csvFile.getName().lastIndexOf('.'));
        File file = createJavaFile("_" + name);
        try{
            String[] firstLn = new BufferedReader(new FileReader(csvFile)).readLine().split(",");
            writeClass(file, firstLn, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeClass(File file, String[] firstLn, String csvName) {
        try{
            FileWriter writer = new FileWriter(file);
            // first write default boilerplate
            writer.write("package com.parse.models; \n\n");
            if(annotations) {
                writer.write("import javax.persistence.*; \n\n" );
                writer.write("@Entity \n");
                writer.write("@Table (name =" + "\"_"+ csvName.toLowerCase() + "\") \n" );
            }
            writer.write("public class _" + csvName + " implements java.io.Serializable { \n");

            // write private props
            writer.write("\tprivate Long id; \n");
            for(String prop : firstLn) {
                writer.write(" \tprivate String " + prop.toLowerCase() + ";\n");
            }

            // write default constructor
            writer.write("\n\tpublic _" + csvName + "() {}\n\n");

            // generate getters and setters
            writer.write(createGetterSetter("id" , true));
            for(String prop : firstLn) {
                writer.write(createGetterSetter(prop.toLowerCase(), false));
            }

            writer.write("\n }\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String createGetterSetter(String propName, Boolean isId) {
        String capitalize = propName.substring(0, 1).toUpperCase() + propName.substring(1);
        String result;
        // create setter
        if(isId) {
            result = "\tpublic void set" + capitalize + "(Long " + propName + ") {\n";
            result += "\t\tthis." + propName + " = " + propName + ";\n\t}\n\n";
        } else {
            result = "\tpublic void set" + capitalize + "(String " + propName + ") {\n";
            result += "\t\tthis." + propName + " = " + propName + ";\n\t}\n\n";
        }
        // create getter
        if(annotations && isId){
            result += "\t@Id\n";
            result += "\t@GeneratedValue (generator = \"increment\")\n";
            result += "\t@Column (name = \"" + propName + "\")\n";
        } else if (annotations) {
            result += "\t@Column (name = \"" + propName + "\", length = 2048)\n";
        }
        if(isId){
            result += "\tpublic Long get" + capitalize + "() {\n";
            result += "\t\t return " + propName + ";\n\t}\n\n";
        } else {
            result += "\tpublic String get" + capitalize + "() {\n";
            result += "\t\t return " + propName + ";\n\t}\n\n";
        }
        return result;
    }

    private File createJavaFile(String csvName) {
        try{
            File file = new File("src/com/parse/models/" + csvName + ".java");
            //File file = new File( "classes/" + csvName + ".java");
            if(file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                System.out.println("File already exists.");
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
