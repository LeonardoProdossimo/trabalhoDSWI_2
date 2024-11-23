package org.libertas;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class PessoaAPI
 */
@WebServlet("/FilmeAPI/*")
public class FilmeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FilmeAPI() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pesquisa = request.getParameter("pesquisa");
		
		Gson gson = new Gson();
		FilmeDAO pdao = new FilmeDAO();
		
		Integer id = null;
		try {
			id = Integer.parseInt(request.getPathInfo().substring(1));
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		response.setHeader("content-type", "application/json");
		if(id == null) {
			response.getWriter().print(gson.toJson(pdao.listar(pesquisa)));
		}else {
			response.getWriter().print(gson.toJson(pdao.consultar(id)));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		Gson gson = new Gson();
		Filme p = gson.fromJson(body, Filme.class);
		FilmeDAO pdao = new FilmeDAO();
		
		response.setHeader("content-type", "application/json");
		response.getWriter().print(gson.toJson(pdao.inserir(p)));
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		Gson gson = new Gson();
		FilmeDAO pdao = new FilmeDAO();
		Filme p = gson.fromJson(body, Filme.class);
		
		response.setHeader("content-type", "application/json");
		response.getWriter().print(gson.toJson(pdao.alterar(p)));
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmeDAO pdao = new FilmeDAO();
		Gson gson = new Gson();
		
		Integer id = null;
		try {
			id = Integer.parseInt(request.getPathInfo().substring(1));
		}catch (Exception e) {
			String resp = "ID obrigatório!";
			response.getWriter().print(resp);
			return;
		}
		
		response.setHeader("content-type", "application/json");
		response.getWriter().print(gson.toJson(pdao.excluir(id)));
	}
}
