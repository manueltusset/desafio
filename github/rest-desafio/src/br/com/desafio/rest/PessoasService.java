package br.com.desafio.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.desafio.dao.PessoaDAO;
import br.com.desafio.entidade.Pessoa;

@Path("/pessoas")
public class PessoasService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";
	private PessoaDAO pessoaDAO;

	@PostConstruct
	private void init() {
		pessoaDAO = new PessoaDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Pessoa> listarPessoas() {
		List<Pessoa> lista = null;

		try {
			lista = pessoaDAO.listarPessoas();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lista;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Pessoa pessoaId(@PathParam("id") int idPessoa) {
		Pessoa pessoa = null;

		try {
			pessoa = pessoaDAO.listarPessoaId(idPessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoa;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarPessoa(Pessoa pessoa, @PathParam("id") int idPessoa) {
		String msg = "";

		try {
			pessoaDAO.editarPessoa(pessoa, idPessoa);
			msg = "Pessoa editada com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar a pessoa!";
			e.printStackTrace();
		}
		return msg;
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletarPessoa(@PathParam("id") int idPessoa) {
		String msg = "";

		try {
			pessoaDAO.removerPessoa(idPessoa);
			msg = "Pessoa deletada com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao deletar a pessoa!\n" + e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirPessoa(Pessoa pessoa) {
		String msg = "";

		try {
			pessoaDAO.inserirPessoa(pessoa);
			msg = "Pessoa inclu�da com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao incluir a pessoa!";
			e.printStackTrace();
		}
		return msg;
	}

	@POST
	@Path("/test")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		String msg = "";

		try {
			msg = "Servi�o em execu��o!";
		} catch (Exception e) {
			msg = "Erro no no servi�o!";
			e.printStackTrace();
		}
		return msg;
	}
}
