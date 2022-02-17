package telran.calculator.controller;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import telran.calculator.net.CalculatorProxy;
import telran.calculator.servises.Calculator;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorApp {
	private static final int PORT = 2000;
	public static void main(String[] args) throws Exception {
		InputOutput io = new ConsoleInputOutput();
		Socket socket = new Socket("localhost", PORT);//added line
		Calculator calculator =  new CalculatorProxy(socket);//changed line
		ArrayList<Item> items = CalculatorActions.getCalculator(calculator);
		items.add(Item.of("Exit", iop -> {
			try {
				socket.close();//added line
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, true));
		Menu menu = new Menu("Calculator menu", items);
		menu.perform(io);
	}

}
