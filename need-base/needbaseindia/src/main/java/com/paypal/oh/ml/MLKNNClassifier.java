package com.paypal.oh.ml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

public class MLKNNClassifier {

	/**
	 * This tutorial show how to use a the k-nearest neighbors classifier.
	 *  
	 */
	/**
	 * Shows the default usage of the KNN algorithm.
	 */
	public static void main(String[] args) throws Exception {

		/* Load a data set */
		Dataset data = FileHandler.loadDataset(new File(
				"C:/abhishek/projects/hack/oh/needbaseindia/needbaseindia/src/main/java/com/paypal/oh/ml/missingkids.data"), 4, ",");
		/*
		 * Contruct a KNN classifier that uses 5 neighbors to make a decision.
		 */
		Classifier knn = new KNearestNeighbors(5);
		knn.buildClassifier(data);
		/**
		 * Transform pick records cal LCS, normalize and store
		 * 
		 */
        File rawFile = new File("C:/abhishek/projects/hack/oh/needbaseindia/needbaseindia/src/main/java/com/paypal/oh/ml/missingkids.data");
        StringBuilder sb = new StringBuilder();
		Scanner rawDataReader = new Scanner(rawFile);
		while(rawDataReader.hasNextLine()){
			String[] rawdata = rawDataReader.nextLine().split(",");
			LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence("","");
			int lcs = longestCommonSubsequence.getLCS(rawdata[0], rawdata[rawdata.length-1]);
			float correlation = (float)lcs/ Math.max(rawdata[0].length(), rawdata[rawdata.length-1].length())*10;
			sb.append(correlation);
			sb.append(",");
			sb.append(correlation);
			sb.append(",");
			sb.append(correlation);
			sb.append(",");
			sb.append(rawdata[3]);
			sb.append(",");
            sb.append( rawdata[rawdata.length-1]+"\n");
		}
		//Load data
		try {
			 
			String content = "This is the content to write into file";
 
			File file = new File("C:/abhishek/projects/hack/oh/needbaseindia/needbaseindia/src/main/java/com/paypal/oh/ml/missingkidsTL.data");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}	
		/*
		 * Load a data set for evaluation, this can be a different one, but we
		 * will use the same one.
		 */
		Dataset dataForClassification = FileHandler.loadDataset(new File(
				"C:/abhishek/projects/hack/oh/needbaseindia/needbaseindia/src/main/java/com/paypal/oh/ml/iris.data"), 4, ",");
		/* Counters for correct and wrong predictions. */
		
		
		int correct = 0, wrong = 0;
		/* Classify all instances and check with the correct class values */
		for (Instance inst : dataForClassification) {
			Object predictedClassValue = knn.classify(inst);
			Object realClassValue = inst.classValue();
			if (predictedClassValue.equals(realClassValue))
				correct++;
			else
				wrong++;
		}
		System.out.println("Correct predictions  " + correct);
		System.out.println("Wrong predictions " + wrong);

	}

}
