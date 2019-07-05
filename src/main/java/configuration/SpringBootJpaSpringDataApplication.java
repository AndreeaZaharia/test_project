package configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@ComponentScan({ "service", "controller", "configuration" })
@EnableJpaRepositories("repository")
@EnableTransactionManagement
@EntityScan({ "entity" })
@SpringBootApplication
public class SpringBootJpaSpringDataApplication
{
	//~ Methods ----------------------------------
	/**
	 * DOCUMENT ME!
	 *
	 * @param  args
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootJpaSpringDataApplication.class, args);
	}
}
