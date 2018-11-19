package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Entity
@Table(name = "role")
public class Role
{
	//~ Instance fields --------------------------
	/**  */
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;

	/**  */
	@Column(name = "role")
	private String role;
}
