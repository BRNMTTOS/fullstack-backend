package br.ufg.inf.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/tasks")
public class TaskServlet extends HttpServlet{
	private List<String> tasks = new ArrayList<>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			
			if(tasks.isEmpty()) {
				out.println("Não há tarefas disponíveis");
			}
			else {
				for(int i = 0; i < tasks.size(); i++) {
					out.println((i) + ". " + tasks.get(i));
				}
			}
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String task = request.getParameter("task");
		if(task != null && !task.isEmpty()) {
			tasks.add(task);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} 
		else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tarefa inválida.");
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		try {
			int index = Integer.parseInt(request.getParameter("index"));
			String task = request.getParameter("task");
			
			if(index >= 0 && index < tasks.size() && task != null && !task.isEmpty()){
				tasks.set(index, task);
			}
		} 
		catch(NumberFormatException e){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Índice inválido.");
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		try {
			int index = Integer.parseInt(request.getParameter("index"));
			if(index >= 0 && index < tasks.size()) {
				tasks.remove(index);
			}
			else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Índice inválido");
			}
		} 
		catch(NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Índice inválido.");
		}
	}
}
