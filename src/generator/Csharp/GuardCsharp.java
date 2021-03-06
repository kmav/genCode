package generator.Csharp;

import java.io.BufferedWriter;
import java.io.IOException;

import model.sequence.Guard;
import model.sequence.Specification;
import generator.GeneratorStrategy;

public class GuardCsharp implements GeneratorStrategy{
	
	private Guard guard;
	private Specification specification;
	
	public GuardCsharp(Guard guard){
		this.guard = guard;
		this.specification = guard.getSpecification();
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		
			out.write(guard.getSpecification().getBody());
		
		
	}
	
	public void genCodeValue(BufferedWriter out) throws IOException {
		out.write(specification.getValue());
	}
	
	public void genCodeVariable(BufferedWriter out) throws IOException {
		out.write(specification.getVariable());
	}

	public void genCodeForNormal(BufferedWriter out) throws IOException {
		out.write("for(int i=" + guard.getMinint() + "; i < " + 
				this.specification.getBody() + "; i++");
	}
	
}
