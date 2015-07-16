package com.msus.GameOfLifeMVCInterfaces;

import java.util.Observer;

public interface View extends Observer{

	void setModel(Model model);

	void print();

}
