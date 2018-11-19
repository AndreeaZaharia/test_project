package repository;

import entity.User;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Repository
@Transactional
public interface IUserRepository extends CrudRepository<User, Long>
{
	//~ Methods ----------------------------------
	/**
	 * DOCUMENT ME!
	 *
	 * @param   email
	 * @return
	 */
	User findByEmail(String email);
}
