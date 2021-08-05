package com.example.transformer.service;

import java.util.List;

import com.example.transformer.model.Transformer;

public interface TransformerService {
	Transformer save(Transformer newTransformer);
	List<Transformer> findAll();
	void delete(Transformer newTransformer);
}
