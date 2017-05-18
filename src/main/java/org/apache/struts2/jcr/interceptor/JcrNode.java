package org.apache.struts2.jcr.interceptor;

import java.lang.annotation.*;

/**
 * Annotation used by org.apache.struts2.jcr.interceptor.JcrActionsInterceptor.
 * Contains information about node which need to be preset before action invocation.
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JcrNode {

      String node() default "";
      String id() default "";
      String param() default "";


}
