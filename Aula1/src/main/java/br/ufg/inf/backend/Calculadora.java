package br.ufg.inf.backend;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calculadora extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		// COnfiguração de resposta
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			// Leitura de parâmetros
			double num1 = Double.parseDouble(request.getParameter("num1"));
			double num2 = Double.parseDouble(request.getParameter("num2"));
			String operacao = request.getParameter("operacao");
			
			// Validação da operação
			if (!operacao.matches("soma|subtracao|multiplicacao|divisao")) {
				throw new IllegalArgumentException("Operação inválida");
			}
			
			// Cálculo
			double resultado = 0;
			switch(operacao) {
				case "soma":
					resultado = num1 + num2;
					break;
				case "subtracao":
					resultado = num1 - num2;
					break;
				case "multiplicacao":
					resultado = num1 * num2;
					break;
				case "divisao":
					if(num2 == 0) {
						throw new ArithmeticException("Divisão por zero");
					}
					resultado = num1 / num2;
					break;
			}
			
			// Resposta com o resultado
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>Resultado</title></head>");
			out.println("<body>");
			out.println("<h1>Resultado: " + resultado + "</h1>");
			out.println("</body>");
			out.println("</html>");
			
		}
		catch(NumberFormatException e) {
			out.println("Erro: parâmetro numérico inválido");
		}
		catch(IllegalArgumentException | ArithmeticException e) {
			out.println("Erro: " + e.getMessage());
		}
	}

}
