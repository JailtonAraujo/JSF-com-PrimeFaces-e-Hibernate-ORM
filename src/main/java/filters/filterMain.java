package filters;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jpautil.JPAUtil;
import model.Pessoa;


@WebFilter("/*")
public class filterMain extends HttpFilter implements Filter {
       
    public filterMain() {}
    
	public void destroy() {
		JPAUtil.getEntityManager().close();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		
		String url = req.getContextPath();
		
		if(url.equalsIgnoreCase("/cadastrovisitante.jsf")) {
			chain.doFilter(request, response);
			
		}
		
		else if((!url.equalsIgnoreCase("/index.jsf") && usuarioLogado == null) || usuarioLogado.getId() < 1 || usuarioLogado.getNome().trim().isEmpty()) {
			request.getRequestDispatcher("index.jsf").forward(req, response);
			return;
		}
		
		else {
		chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		JPAUtil.getEntityManager();
	}

}
