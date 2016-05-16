package com.dofittech.cbdp.core.annation.bind;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binding request parameter into mode and expose them with view<BR>
 * Different from <code>@ModelAttribute</code>
 *
 * @author Rugal Bernstein
 */
/**
 * request请求参数绑定到模型
 * @author sh4n7ie  firheroicths@gmail.com
 * @date   2016年5月13日 下午4:00:07
 *
 */
@Target(
    {
        ElementType.PARAMETER
    })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormModel
{

    /**
     * specify the prefix name of request parameter and the model for exposing
     * into view.
     *
     * <h2>Binding rule</h2>
     * <h3>1. Prefixed with object name</h3>
     * if form is：<br><br>
     * {@code <input type="text" name="student.name" value="Kate" />}<br>
     * {@code <input type="text" name="student.type" value="bachelor"/>}<br><br>
     * then process like：<br><br>
     *
     * {@code @RequestMapping(value = "/test")}<br>
     * {@code public String test(@FormModel("student") Student student)}<br><br>
     * this will bind into student.name and student.type<br><br>
     *
     * <h3>2. Just name but will be in object field</h3>
     * if form is：<br><br>
     * {@code <input type="text" name="name" value="Kate" />}<br>
     * {@code <input type="text" name="type" value="bachelor"/>}<br><br>
     * If Student class contains fields "name" and "type", then it will process
     * like：<br><br>
     *
     * {@code @RequestMapping(value = "/test")}<br>
     * {@code public String test(@FormModel("student") Student student)}<br><br>
     * this will bind into student.name and student.type
     *
     * @return the bean to be binded.
     */
    String value();
}
