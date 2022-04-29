package repository;

import java.util.ArrayList;
import java.util.List;

import model.Email;

public interface iEmail {

	public boolean salvarEmail(Email email);
	
	public List<Email> listarEmails(Long userID);
}
