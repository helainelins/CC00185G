package br.upe.piii.mvc.verdinhas.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LocalDAOTest {

	@Autowired
	private ILocalDAO dao;

	@Test
	public void testeIncluirComSucesso() {
		final Long existentes = dao.count();

		Local sala = new Local("Sala", "Cantinho da sala perto da janela");
		Local jardim = new Local("Jardim", "Jardim");
		Local quintal = new Local("Quintal", "Quintal");

		dao.saveAll(Arrays.asList(sala, jardim, quintal));

		final List<Local> locaisInseridos = (List<Local>) dao.findAll();

		Long novaQtd = existentes + 3;
		assertThat(locaisInseridos.size(), is(equalTo(novaQtd.intValue())));
	}

	@Test
	public void testAlterarComSucesso() {
		Local local = new Local("Sala", "Cantinho da sala perto da janela");
		dao.save(local);

		String novaDescricao = "Na estante";
		local.setDescricao(novaDescricao);

		Local localAlterado = dao.save(local);

		assertThat(localAlterado.getDescricao(), is(equalTo(novaDescricao)));
	}

	@Test
	public void testExluirComSucesso() {
		final Long qtdExistentes = dao.count();

		Local local = dao.save(new Local("Terraço", "Área coberta"));
		final Long salvos = dao.count();

		assertThat(salvos, is(equalTo(qtdExistentes + 1)));

		dao.delete(local);

		final Long aindaSalvos = dao.count();

		assertThat(qtdExistentes, is(equalTo(aindaSalvos)));
	}

	@Test
	public void testListarComSucesso() {
		this.dao.deleteAll();

		Local jardimInverno = new Local("Jardim de Inverno", "");
		Local quartoCasal = new Local("Quarto Casal", "Prateleira");

		dao.saveAll(Arrays.asList(jardimInverno, quartoCasal));

		final List<Local> inseridos = (List<Local>) dao.findAll();
		assertThat(inseridos.size(), is(equalTo(2)));
	}
}
