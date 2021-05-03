package com.pigdroid.job.service.base;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public class AbstractPagingService<T, ID> implements PagingAndSortingRepository<T, ID> {

	private PagingAndSortingRepository<T, ID> repository;

	protected PagingAndSortingRepository<T, ID> getRepository() {
		return repository;
	}

	protected AbstractPagingService(PagingAndSortingRepository<T, ID> repo) {
		this.repository = repo;
	}

	@Override
	public <S extends T> S save(S entity) {
		return repository.save(entity);
	}

	@Override
	public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(ID id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<T> findAllById(Iterable<ID> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void deleteById(ID id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();

	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}


}
