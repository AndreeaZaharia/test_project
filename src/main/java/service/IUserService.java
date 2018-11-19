package service;

import entity.User;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public interface IUserService
{
	//~ Methods ----------------------------------
	/**
	 * DOCUMENT ME!
	 *
	 * @param   email
	 * @return
	 */
	User findUserByEmail(String email);
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  user
	 */
	void saveUser(User user);
}
