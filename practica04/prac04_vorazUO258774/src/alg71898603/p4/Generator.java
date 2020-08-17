package alg71898603.p4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Generator {
	public static void main(String[] args) { 
	    if (args.length < 6) {
	    	System.out.println("Generator [FILE_NAME] [NUMBER_OF_WORDS] [MAXIMUM_LENGH_OF_WORDS] [MINIMUM_LENGTH_OF_WORDS] [MAXIMUM_NUMBER_OF_REPETITIONS] [MINIMUM_NUMBER_OF_REPETITIONS]");
	    	System.exit(-1);
	    }

	    String fileName = args[0];
	    long numberOfWords = Integer.valueOf(args[1]);
	    int maxSizeOfWords = Integer.valueOf(args[2]);
	    int minSizeOfWords = Integer.valueOf(args[3]);
	    int maxFrequency = Integer.valueOf(args[4]);
	    int minFrequency = Integer.valueOf(args[5]);
	    
	    Generator gen = new Generator();
	    gen.run(fileName, numberOfWords, maxSizeOfWords, minSizeOfWords, maxFrequency, minFrequency);
	}
	
	public void run(String fileName, long numberOfWords, int maxSizeOfWords, int minSizeOfWords, int maxFrequency, int minFrequency) {
	    Random random = new Random();
	    HashSet<String> words = new HashSet<String>();
		int leftLimit = 97; //letter 'a'
	    int rightLimit = 122; //letter 'z'
	   
	    while (words.size() < numberOfWords) {
		    int stringLength = random.nextInt(maxSizeOfWords - minSizeOfWords + 1) + minSizeOfWords;
		    StringBuilder buffer = new StringBuilder(stringLength);
		    for (int j = 0; j < stringLength; j++) {
		        int randomInt = random.nextInt(rightLimit - leftLimit + 1) + leftLimit;
		        buffer.append((char)randomInt);
		    }
		    words.add(buffer.toString());
	    }
	    
	    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
	        for (String word : new ArrayList<String>(words)) {
	        	writer.write(word + " ");
	        	int frequency = random.nextInt(maxFrequency - minFrequency + 1) + minFrequency;
	        	writer.write(frequency + "\r\n");
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    
    	System.out.println("***File " + fileName + " generated***");
	}
}
