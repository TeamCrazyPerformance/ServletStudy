package com.tcp.study.model;

import java.util.ArrayList;

public class BeerModel {

	public ArrayList<String> getBrands(String color) {
		
		ArrayList<String> brandsList = new ArrayList<String>();
		
		if (color.equals("amber")) {
			brandsList.add("Jack Amber");
			brandsList.add("Red Moose");
		} else {
			brandsList.add("Jail Pale Ale");
			brandsList.add("Gout Stout");
		}
		
		return brandsList;
	}
	
	public static void main(String args[]) {
		
		BeerModel model = new BeerModel();
		
		System.out.println(model.getBrands("amber"));
		System.out.println(model.getBrands("red"));
	}
}
