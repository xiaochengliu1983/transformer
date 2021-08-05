package com.example.transformer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.transformer.model.Transformer;
import com.example.transformer.repository.Repo;

@Service
public class TransformerServiceImpl implements TransformerService{
	
	@Autowired
	Repo repo;
	
	@Override
	public Transformer save(Transformer newTransformer) {
		
		return repo.save(newTransformer);
	}

	@Override
	public List<Transformer> findAll() {
	
		return repo.findAll();
	}

	@Override
	public void delete(Transformer newTransformer) {	
		
		 repo.delete(newTransformer);
		 
	}

}
