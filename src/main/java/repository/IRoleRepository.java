package repository;

import entity.Role;

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
public interface IRoleRepository extends CrudRepository<Role, Long>
{
	//~ Methods ----------------------------------
	/**
	 * DOCUMENT ME!
	 *
	 * @param   role
	 * @return
	 */
	Role findByRole(String role);
}
