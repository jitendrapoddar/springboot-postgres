package com.example.demo.repo.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contact;
import com.example.demo.model.mapper.ContactMapper;
import com.example.demo.repo.CrudService;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ContactCrudService implements CrudService{

	private final NamedParameterJdbcTemplate template;

	@Override
	public List<Contact> getAllContacts() {
		final String sql = "SELECT * from contact left join individual on contact.SFID = individual.SFID";
		List<Contact> result = template.query(sql, new ContactMapper());
		
		return result;
	}

	@Override
	public Contact getContactById(String id) {
		final String sql = "SELECT * from contact left join individual on contact.SFID = individual.SFID where contactId = :contactId";
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("contactId", id);
		Contact result = template.queryForObject(sql, param, new ContactMapper());
		return result;
	}

}
