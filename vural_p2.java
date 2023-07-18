import java.util.*;
import java.io.*;
import java.nio.ByteBuffer;

class vural_p2 {
    public static void main(String[] args) {
        if (args[0].equals("b2t")) convertBinaryToText(args[1], args[2]);
        else if (args[0].equals("t2b")) convertTextToBinary(args[1], args[2]);
        else {
            System.out.println("Called with bad parameters");
            System.out.println("format is: \"b2t or t2b\" \"inputFilename\" \"outputFilename\"");
            System.exit(0);
        }
    }

    private static void convertBinaryToText(String inputFilename, String outputFilename) {
        System.out.println("convertBinaryToText");
        try {
            BufferedInputStream input = (new BufferedInputStream(new FileInputStream(inputFilename)));
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));

            byte[] buffer = new byte[8];

            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            input.read(buffer, 0, 4);
            int numOfBlocks = byteBuffer.getInt(0);
            for (int i = 0; i < numOfBlocks; i++) {
                input.read(buffer, 0, 2);
                char dataType = byteBuffer.getChar(0);
                switch (dataType) {
                    case 'h':
                        output.print("short\t");
                        input.read(buffer, 0, 2);
                        output.print(byteBuffer.getShort(0));
                        break;
                    case 'i':
                        output.print("int\t");
                        input.read(buffer, 0, 4);
                        output.print(byteBuffer.getInt(0));
                        break;
                    case 'l':
                        output.print("long\t");
                        input.read(buffer, 0, 8);
                        output.print(byteBuffer.getLong(0));
                        break;
                    case 'f':
                        output.print("float\t");
                        input.read(buffer, 0, 4);
                        output.print(byteBuffer.getFloat(0));
                        break;
                    case 'd':
                        output.print("double\t");
                        input.read(buffer, 0, 8);
                        output.print(byteBuffer.getDouble(0));
                        break;
                    case 's':
                        output.print("string\t");
                        input.read(buffer, 0, 4);
                        int sizeOfString = byteBuffer.getInt(0);
                        char[] anArray = new char[sizeOfString];
                        for (int j = 0; j < sizeOfString; j++) {
                            input.read(buffer, 0, 2);
                            anArray[j] = byteBuffer.getChar(0);
                        }
                        String string = new String(anArray);
                        output.print(string);
                        break;

                    case 'a':
                        output.print("int array\t");
                        input.read(buffer, 0, 4);
                        int sizeOfIntArray = byteBuffer.getInt(0);
                        int j = 0;
                        while (true) {
                            input.read(buffer, 0, 4);
                            output.print(byteBuffer.getInt(0));
                            j++;
                            if (j >= sizeOfIntArray) // excluding last comma
                                break;
                            output.print(",");
                        }
                        break;
                    case 'e':
                        output.print("double array\t");
                        input.read(buffer, 0, 4);
                        int sizeOfDoubleArray = byteBuffer.getInt(0);
                        j = 0;
                        while (true) {
                            input.read(buffer, 0, 8);
                            output.print(byteBuffer.getDouble(0));
                            j++;
                            if (j >= sizeOfDoubleArray) // excluding last comma
                                break;
                            output.print(",");
                        }
                        break;
                }
                output.print("\n");
            }
            output.close();
            input.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(0);
        }
    }

    private static void convertTextToBinary(String inputFilename, String outputFilename) {
        System.out.println("convertTextToBinary");
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename)));
            DataOutputStream output = new DataOutputStream(new FileOutputStream(outputFilename));
            ArrayList<String> inputLines = new ArrayList<>(0);
            String inn;
            while ((inn = input.readLine()) != null) {
                inputLines.add(inn);
            }
            output.writeInt(inputLines.size());
            for (int i = 0; i < inputLines.size(); i++) {
                inn = inputLines.get(i);
                if (inn.startsWith("#") || inn.length() == 0) {
                    continue;
                }
                StringTokenizer text_lines = new StringTokenizer(inn, "\t");
                String typeOfData = text_lines.nextToken();
                String dataValue = text_lines.nextToken();

                switch (typeOfData) {
                    case "string":
                        output.writeChar('s');
                        output.writeInt(dataValue.length());
                        for (int j = 0; j < dataValue.length(); j++) {
                            output.writeChar(dataValue.charAt(j));
                        }
                        break;

                    case "double":
                        output.writeChar('d');
                        output.writeDouble(Double.valueOf(dataValue));
                        break;

                    case "int":
                        output.writeChar('i');
                        output.writeInt(Integer.valueOf(dataValue));
                        break;

                    case "float":
                        output.writeChar('f');
                        output.writeFloat(Float.valueOf(dataValue));
                        break;

                    case "long": {
                        output.writeChar('l');
                        output.writeLong(Long.valueOf(dataValue));
                        break;
                    }
                    case "short":
                        output.writeChar('h');
                        output.writeShort(Short.valueOf(dataValue));
                        break;

                    case "int array":
                        output.writeChar('a');
                        StringTokenizer stIntArray = new StringTokenizer(dataValue, ",");
                        output.writeInt(stIntArray.countTokens());
                        while (stIntArray.hasMoreTokens()) {
                            String str = stIntArray.nextToken();
                            output.writeInt(Integer.parseInt(str));
                        }
                        break;

                    case "double array":
                        output.writeChar('e');
                        StringTokenizer stDoubleArray = new StringTokenizer(dataValue, ",");
                        output.writeInt(stDoubleArray.countTokens());
                        while (stDoubleArray.hasMoreTokens()) {
                            String str = stDoubleArray.nextToken();
                            output.writeDouble(Double.parseDouble(str));
                        }
                        break;
                }
            }
            input.close();
            output.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(0);
        }
    }
}