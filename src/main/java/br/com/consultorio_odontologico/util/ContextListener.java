package br.com.consultorio_odontologico.util;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		Conexao.getEntityManager().close();

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		Conexao.getEntityManager();

	}

}
