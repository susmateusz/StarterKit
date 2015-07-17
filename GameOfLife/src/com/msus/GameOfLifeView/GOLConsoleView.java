package com.msus.GameOfLifeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.msus.GameOfLifeMVCInterfaces.Controller;
import com.msus.GameOfLifeMVCInterfaces.Model;
import com.msus.GameOfLifeMVCInterfaces.View;

public class GOLConsoleView implements View {

	private List<List<Integer>> active = new ArrayList<List<Integer>>();
	private List<Integer> bounds = new ArrayList<Integer>();
	private int counter = 0;
	
	public GOLConsoleView(int n,int m) {
		bounds.add(n);
		bounds.add(m);
		System.out.println("View initialized.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1.getClass().isInstance(active)){
			active = (List<List<Integer>>) arg1;
			counter++;
		}
	}

	@Override
	public void setModel(Model model) {
		active = model.getData();
		
	}

	@Override
	public void print() {
		System.out.print("\nGame Of Life. frame "+counter+"\n ");
		for(int j=0;j<bounds.get(1);j++)
			System.out.print("_");
		System.out.println();
		for(int i=0;i<bounds.get(0);i++){
			System.out.print("|");
			for(int j=0;j<bounds.get(1);j++){
				System.out.print(active.contains(Arrays.asList(new Integer[]{i,j}))?"#":".");
			}
			System.out.println("|");
		}
		System.out.print(" ");
		for(int j=0;j<bounds.get(1);j++)
			System.out.print("-");
	}

	@Override
	public void setController(Controller controller) {
		// TODO Auto-generated method stub
		
	}



}
