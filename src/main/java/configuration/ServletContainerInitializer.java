package configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import configuration.SpringMVCApplicationContext;

/**
 * Servlet initializer等价于web.xml，系统启动文件
 * @author sh4n7ie  firheroicths@gmail.com
 * @date   2016年5月13日 下午3:35:12
 *
 */
public class ServletContainerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        servletContext.addFilter("characterEncodingFilter", characterEncodingFilter()).addMappingForUrlPatterns(null, true, "/*");
        servletContext.addFilter("hiddenHttpMethodFilter", hiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
        super.onStartup(servletContext);
    }

    /**
     * Common configuration.
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]
        {
            ApplicationContext.class
        };
    }

    /**
     * SpringMVC configuration.
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]
        {
        	SpringMVCApplicationContext.class
        };
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[]
        {
            "/"
        };
    }

    @Override
    protected String getServletName()
    {
        return "springmvc";
    }

    @Override
    protected boolean isAsyncSupported()
    {
        return true;
    }

    private Filter characterEncodingFilter()
    {
        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding(SystemDefaultProperties.BYTE_ENCODING);
        cef.setForceEncoding(true);
        return cef;
    }

    private Filter hiddenHttpMethodFilter()
    {
        return new HiddenHttpMethodFilter();
    }
}
