package com.jpacourse.persistence.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.jpacourse.persistence.dao.Dao;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract base class for DAO implementations providing basic CRUD operations.
 * This class implements the {@link Dao} interface and is designed to handle common data access operations
 * for entity classes in a generic way.
 *
 * @param <T> the type of the entity
 * @param <K> the type of the entity's identifier (ID)
 */
@Transactional
public abstract class AbstractDao<T, K extends Serializable> implements Dao<T, K> {

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> domainClass;

	/**
	 * Saves the given entity to the database.
	 * This method persists the entity in the database.
	 *
	 * @param entity the entity to be saved
	 * @return the persisted entity
	 */
	@Override
	public T save(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	/**
	 * Retrieves an entity by its ID. This method returns a reference to the entity.
	 *
	 * @param id the ID of the entity to retrieve
	 * @return the entity, or a proxy if the entity is not loaded
	 */
	@Override
	public T getOne(K id) {
		return entityManager.getReference(getDomainClass(), id);
	}

	/**
	 * Finds an entity by its ID.
	 * This method returns the actual entity if it exists, or null if not.
	 *
	 * @param id the ID of the entity to find
	 * @return the entity, or null if not found
	 */
	@Override
	public T findOne(K id) {
		return entityManager.find(getDomainClass(), id);
	}

	/**
	 * Finds all entities of the type {@link T}.
	 *
	 * @return a list of all entities of type {@link T}
	 */
	@Override
	public List<T> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(getDomainClass());
		criteriaQuery.from(getDomainClass());
		TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	/**
	 * Updates the given entity.
	 * This method merges the changes to the entity in the database.
	 *
	 * @param entity the entity to be updated
	 * @return the updated entity
	 */
	@Override
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	/**
	 * Deletes the given entity from the database.
	 *
	 * @param entity the entity to be deleted
	 */
	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	/**
	 * Deletes the entity with the specified ID from the database.
	 *
	 * @param id the ID of the entity to be deleted
	 */
	@Override
	public void delete(K id) {
		entityManager.remove(getOne(id));
	}

	/**
	 * Deletes all entities of the type {@link T} from the database.
	 */
	@Override
	public void deleteAll() {
		entityManager.createQuery("delete " + getDomainClassName()).executeUpdate();
	}

	/**
	 * Counts the number of entities of the type {@link T}.
	 *
	 * @return the number of entities of type {@link T}
	 */
	@Override
	public long count() {
		return (long) entityManager.createQuery("Select count(*) from " + getDomainClassName()).getSingleResult();
	}

	/**
	 * Checks if an entity with the specified ID exists in the database.
	 *
	 * @param id the ID of the entity to check
	 * @return true if the entity exists, false otherwise
	 */
	@Override
	public boolean exists(K id) {
		return findOne(id) != null;
	}

	/**
	 * Retrieves the class of the domain entity.
	 * This is determined using reflection based on the generic type {@link T}.
	 *
	 * @return the class of the domain entity
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getDomainClass() {
		if (domainClass == null) {
			ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			domainClass = (Class<T>) type.getActualTypeArguments()[0];
		}
		return domainClass;
	}

	/**
	 * Retrieves the name of the domain class.
	 * This is used in JPQL queries for dynamic class name access.
	 *
	 * @return the name of the domain class
	 */
	protected String getDomainClassName() {
		return getDomainClass().getName();
	}
}
