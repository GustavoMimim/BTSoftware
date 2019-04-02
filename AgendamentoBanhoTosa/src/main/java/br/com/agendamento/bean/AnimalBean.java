package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import br.com.agendamento.dao.AnimalDAO;
import br.com.agendamento.domain.Animal;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AnimalBean implements Serializable{

	private Animal animal;
	private Animal animalSelecionado;
	private List<Animal> animais;// atributo para listar animais
	
	//Getters e Setters
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public Animal getAnimalSelecionado() {
		return animalSelecionado;
	}

	public void setAnimalSelecionado(Animal animalSelecionado) {
		this.animalSelecionado = animalSelecionado;
	}

	//zera todos os campos de Animal
	public void novo() {
		animal = new Animal();
	}

	@PostConstruct // � chamado logo apos o construtor da classe
	public void listar() {
		try {
			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar animais!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			AnimalDAO animalDAO = new AnimalDAO();
			animalDAO.merge(animal);

			novo();
			animais = animalDAO.listar();

			Messages.addGlobalInfo("Opera��o Realizada com Sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o animal!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		animalSelecionado = (Animal) event.getObject();
		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		animal = (Animal) evento.getComponent().getAttributes().get("animalSelecionado");

		try{
			AnimalDAO animalDAO = new AnimalDAO();
			animalDAO.excluir(animal);
			Messages.addGlobalInfo("Animal " + animal.getNome() + " Exclu�do");
			
			animais = animalDAO.listar();
		}catch (RuntimeException e) {
			Messages.addGlobalError("N�o foi possivel excluir!");
			e.printStackTrace();
		}

	}
	
	public void alterar(ActionEvent evento) {
		animal = (Animal) evento.getComponent().getAttributes().get("animalSelecionado");
		if(animal == null) {
			Messages.addGlobalError("N�o foi possivel alterar!");
		}
	}
}
