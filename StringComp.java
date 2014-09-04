

	import java.io.BufferedReader;
	import java.util.Set;
	import java.util.HashSet;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Collections;
import java.util.Comparator;
import java.io.*;
	public class StringComp {
		
		public ArrayList<String> getWordList(String filePath1,String filepath2) throws IOException {
			ArrayList<String> wordList = new ArrayList<String>();
			ArrayList<String> wordList1 = new ArrayList<String>();
			//String words="";
			BufferedReader in;
				try {
					in = new BufferedReader(new FileReader(filePath1));
					String line = in.readLine();
					while (line != null) {
						wordList.addAll(Arrays.asList(line.toLowerCase().split(" ")));
						line = in.readLine();
					}
					in.close();
					Set<String> set = new HashSet<String>(wordList);
					wordList1.addAll(set);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				FileWriter fw = new FileWriter(filepath2);
				BufferedWriter bw = new BufferedWriter(fw);
			Collections.sort(wordList1);
				for(String words  : wordList1)
					System.out.println(words);
			//bw.write(Dictionary);
			//bw.write("\n");
			bw.close();
			
			return wordList1;
		}
		public static String getIndexArr(String line,ArrayList wordList) throws FileNotFoundException, UnsupportedEncodingException {
			StringComp comp = new StringComp();
			
			String dataRow="";
			Integer[] row = new Integer[wordList.size()] ;
			final String[] str = line.toLowerCase().split(" ");
			ArrayList<String> sentence = new ArrayList<String>();
			sentence.addAll(Arrays.asList(line.toLowerCase().split(" ")));
			
			
			Integer[] index = new Integer[sentence.size()];
			for (int i = 0; i < index.length; i++) {
				index[i] = i + 1;
			}
			Arrays.sort(index, new Comparator<Integer>() {
				public int compare(Integer a, Integer b) {
					return str[a-1].compareTo(str[b-1]); 
				}} 
					);
			Collections.sort(sentence);
			
			for (int i = 1; i < index.length; i++) {
				index[i] += index[i - 1] * 10;
				
			}
			for(int i = 0;i<row.length;i++){
				row[i]=0;
			}
			
			for (String string : sentence){
				//System.out.println(sentence.indexOf(string));
				if(wordList.indexOf(string)==-1)
					System.out.println(string+" is not in the dictionary");
				else
				row[wordList.indexOf(string)] = index[sentence.indexOf(string)];
			
			}		
		
			for (int i = 0; i < row.length; i++) {
			dataRow=dataRow+row[i]+",";
			}
			return dataRow;
			
			
		}
		public static void main(String[] args) throws IOException {
			BufferedReader in;
			StringComp comp = new StringComp();
			String filePath1 = "/home/admin1/Desktop/ML_project/Machine_Learning_Project/data_test.txt";
			String filepath2 = "/home/admin1/Desktop/ML_project/Machine_Learning_Project/test_data.txt";
			FileWriter fw = new FileWriter("/home/admin1/Desktop/ML_project/Machine_Learning_Project/test_data.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			ArrayList<String> wordList = comp.getWordList(filePath1,filepath2);
			
            String dataRow="";
			try {
				in = new BufferedReader(new FileReader("/home/admin1/Desktop/ML_project/Machine_Learning_Project/test_Sentence.txt"));
				String line = in.readLine();
				while (line != null) {
					dataRow=getIndexArr(line,wordList);
					bw.write(dataRow);
					bw.write("incorrect sentence\n");
					dataRow="";
					line = in.readLine();
				}
				/*in = new BufferedReader(new FileReader("/home/admin1/Desktop/ML_project/Machine_Learning_Project/data_negative.txt"));
				 line = in.readLine();
				while (line != null) {
					dataRow=getIndexArr(line,wordList);
					bw.write(dataRow);
					bw.write("incorrect sentence\n");
					dataRow="";
					line = in.readLine();
				}*/
				bw.close();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
			
	}



