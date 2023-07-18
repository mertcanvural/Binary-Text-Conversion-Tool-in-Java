# Sophomere Year Class Project September - 2023


## To run the Binary-Text Conversion Tool project in Java, follow the instructions below:

Compile the Java source code: Open a command-line interface or terminal and navigate to the directory containing the source code file (vural_p2.java). Use the Java compiler (javac) to compile the code by executing the following command:

javac vural_p2.java

Run the program: Once the code is successfully compiled, run the program by executing the following command:


java vural_p2 [conversionType] [inputFilename] [outputFilename]

Replace [conversionType] with either "b2t" (for binary to text conversion) or "t2b" (for text to binary conversion). Specify [inputFilename] as the name of the file containing the input data, and [outputFilename] as the desired name for the output file.

For example, to convert binary data to text format, use the following command:



java vural_p2 b2t input.bin output.txt

And to convert text data to binary format, use:


    java vural_p2 t2b input.txt output.bin

View the converted data: After the program finishes running, you can find the converted data in the specified output file. The binary data will be written in binary format, while the text data will be written in a human-readable text format.

The Binary-Text Conversion Tool project provides a convenient way to convert data between binary and text formats. It supports various data types such as short, int, long, float, double, string, int array, and double array. By following the instructions above and providing the appropriate command-line arguments, you can easily convert data from one format to another, enabling seamless interoperability and data manipulation in your Java applications.
