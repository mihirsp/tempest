package com.mihir.codejam.practice1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class StoreCredit {
	
	private Path filePath;
	private int testCases;
	private Scanner fs;
	private List<String> outputLines;
	
	public StoreCredit(String fp) {
		// TODO Auto-generated constructor stub
		filePath = Paths.get(fp);
		outputLines = new ArrayList<String>();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Hello World!!!");
		StoreCredit sc = new StoreCredit("/Users/mihir/Desktop/Tempest/workspace/CodeTempest/com/mihir/codejam/practice1/A-large-practice.in");
		sc.setInputScanner();
		sc.getTestCaseCount();
		sc.processTestCases();
		sc.closeInputScanner();
		sc.writeToOutputFile();
	}

	private void writeToOutputFile() {
		// TODO Auto-generated method stub
		Path path = Paths.get("/Users/mihir/Desktop/Tempest/workspace/CodeTempest/com/mihir/codejam/practice1/large.out");
	    try {
			Files.write(path, outputLines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeInputScanner() {
		// TODO Auto-generated method stub
		fs.close();
	}

	private void processTestCases() {
		// TODO Auto-generated method stub
		if(testCases > 0) {
			for(int i=1; i<=testCases; i++) {
				processEachTestCase(i);
			}
				
		}
	}

	private void processEachTestCase(int caseNum) {
		// TODO Auto-generated method stub
		int credit = getCredit();
		int items = getTotalItems();
		HashMap<Integer, ArrayList<Integer>> priceItemsMap = new HashMap<Integer, ArrayList<Integer>>();
		priceItemsMap = getPriceItemsMap();
		Iterator<Integer> iter = priceItemsMap.keySet().iterator();
		while(iter.hasNext()) {
			Integer price = iter.next();
			
			if(priceItemsMap.get(credit-price)!=null) {
				if(price == (credit/2)) {
					print(caseNum, priceItemsMap.get(price).get(0),priceItemsMap.get(price).get(1));
					return;
				}
				print(caseNum, priceItemsMap.get(price).get(0),priceItemsMap.get(credit-price).get(0));
				return;
			}
		}
	}

	private HashMap<Integer, ArrayList<Integer>> getPriceItemsMap() {
		// TODO Auto-generated method stub
		HashMap<Integer,ArrayList<Integer>> pIMap = new HashMap<Integer,ArrayList<Integer>>();
		String itemPrices = fs.nextLine();
		String[] arr = itemPrices.split(" ");
		int i = 0;
		while(i< arr.length) {
			int price = Integer.parseInt(arr[i]);
			ArrayList<Integer> itemArray = pIMap.get(price);
			if(itemArray == null) {	
				itemArray = new ArrayList<Integer>();
			}
			itemArray.add(i+1);
			pIMap.put(price, itemArray);
			i++;
		}
		return pIMap;
	}

	private int getTotalItems() {
		// TODO Auto-generated method stub
		return Integer.parseInt(fs.nextLine());
	}

	private int getCredit() {
		// TODO Auto-generated method stub
		return  Integer.parseInt(fs.nextLine());
	}

	private void print(int num, Integer integer, Integer integer2) {
		// TODO Auto-generated method stub
		if(integer<= integer2) 
			outputLines.add("Case #" + num + ": " + integer + " " + integer2);
		else
			outputLines.add("Case #" + num + ": " + integer2 + " " + integer);
	}

	private void setInputScanner() {
		// TODO Auto-generated method stub
		try {
			fs = new Scanner(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getTestCaseCount() {
		// TODO Auto-generated method stub
		String line = null;
		if(fs.hasNextLine()) {
			line = fs.nextLine();
			testCases = Integer.parseInt(line);
		}
		else
			testCases = 0;
	}
	
	

}
