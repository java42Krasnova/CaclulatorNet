package telran.calculator.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import telran.calculator.servises.Calculator;

public class CalculatorProxy implements Calculator {
	private  PrintStream writer;
	private  BufferedReader reader;
//[YG] constructor taking hostname and port would be better                  
	public CalculatorProxy(BufferedReader reader, PrintStream writer ){		
		this.writer = writer;
		this.reader = reader;
	}

	@Override
	public double compute(String operator, double op1, double op2) {
		writer.println(String.format("%s#%s#%s", operator, Double.toString(op1), Double.toString(op2)));
		try {
			return Double.parseDouble(reader.readLine());
			//[YG] no need exception handling
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
	}
}
