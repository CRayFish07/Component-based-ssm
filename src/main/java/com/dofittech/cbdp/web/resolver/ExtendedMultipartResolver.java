package com.dofittech.cbdp.web.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 
 * @author sh4n7ie  firheroicths@gmail.com
 * @date   2016年5月13日 下午4:15:16
 *
 */
public class ExtendedMultipartResolver extends CommonsMultipartResolver
{

    private static final String MULTIPART = "multipart";

    private boolean isMultipartContent(HttpServletRequest request)
    {
        String httpMethod = request.getMethod().toLowerCase();
        // test for allowed methods here...
        String contentType = request.getContentType();
        return (contentType != null && contentType.toLowerCase().startsWith(MULTIPART));
    }

    @Override
    public boolean isMultipart(HttpServletRequest request)
    {
        return (request != null && isMultipartContent(request));
    }
}
