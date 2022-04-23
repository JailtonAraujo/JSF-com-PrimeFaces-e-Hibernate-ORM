package repository;

import java.util.List;

import model.Telefone;

public interface iTelefone {

	public List<Telefone> buscarTelefones(Long id);
}
