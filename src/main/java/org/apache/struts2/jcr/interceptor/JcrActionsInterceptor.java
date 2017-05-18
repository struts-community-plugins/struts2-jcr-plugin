package org.apache.struts2.jcr.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.AnnotationUtils;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.portlet.context.PortletActionContext;
import org.springmodules.jcr.JcrTemplate;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

/**
 * Class used to preset javax.jcr.Node fields in action. Because JCR nodes can't easily be instantiated, like hibernate entities, they require
 * some additional effort to allow  struts2 way of interaction with them. 
 *
 * This interceptor looks for annotated getters (@see org.apache.struts2.jcr.interceptor.JcrNode annotation) and if found - interceptor tries to load
 * node from repository, if node not found in the repository interceptor adds new one using path provided.
 *
 */
public class JcrActionsInterceptor extends MethodFilterInterceptor {
	

  private static final long serialVersionUID = 8310694555009988715L;

  private static Logger logger = Logger.getLogger(JcrActionsInterceptor.class);

  JcrTemplate jcrTemplate;

  protected String doIntercept(ActionInvocation invocation) throws Exception {
    Object action = invocation.getAction();
    if (action != null) {
      Collection<Method> methods = AnnotationUtils.getAnnotatedMethods(action.getClass().getSuperclass(), JcrNode.class);
      for (Method method : methods) {
        if (!method.getName().startsWith("get") || !method.getReturnType().equals(Node.class)) {
          continue;
        }
        JcrNode annotation = action.getClass().getSuperclass().getMethod(method.getName()).getAnnotation(JcrNode.class);
        if (annotation == null) {
          continue;
        }
        long idValue = getId(action, annotation, method.getName());
        String nodePath = annotation.node();
        ValueStack stack = ActionContext.getContext().getValueStack();
        nodePath=TextParseUtil.translateVariables('$', nodePath, stack);
        if (idValue == 0) {
          String pathToParentNode = nodePath.substring(0,nodePath.lastIndexOf("/"));
          String nodeName = nodePath.substring(nodePath.lastIndexOf("/")+1);
          Node node;
          if(pathToParentNode.equals("") || pathToParentNode.equals("/jcr:root")){
            node = jcrTemplate.getRootNode().addNode(nodeName);
          }else {
            NodeIterator iterator = jcrTemplate.query(pathToParentNode).getNodes();
            if(iterator.hasNext()){
              node=iterator.nextNode().addNode(nodeName);
            }else {
              throw new PathNotFoundException("Parent node not found for path "+nodePath);
            }
          }

          populateNewNode(action, method, node);
          setNode(action, method, node);
        } else {
          String nodeIdPropertyName = annotation.id();
          if (StringUtils.isEmpty(nodeIdPropertyName)) {
            nodeIdPropertyName = idPropertyNameFromNode(nodePath);
          }
          String query = nodePath + "[@" + nodeIdPropertyName + "=" + idValue + "]";
          NodeIterator nodes = jcrTemplate.query(query).getNodes();
          if (nodes.hasNext()) {
            setNode(action, method, nodes.nextNode());
          } else {
            logger.error("No nodes " + nodePath + " with query '" + query + "'");
          }
        }
      }

    }
    return invocation.invoke();
  }

  private String idPropertyNameFromNode(String nodePath) {
    if (nodePath.indexOf('/') != -1) {
      return nodePath.substring(nodePath.lastIndexOf('/')+1) + "Id";
    } else {
      return nodePath + "Id";
    }
  }

  private void setNode(Object action, Method method, Node node) {
    try {
      Method setterMethod = action.getClass().getSuperclass().getMethod(method.getName().replaceFirst("get", "set"), Node.class);
      setterMethod.invoke(action, node);
    } catch (Exception e) {
      logger.debug(e);
    }
  }

  private void populateNewNode(Object action, Method method, Node node) {
    try {
      Method populateNewMethod = action.getClass().getSuperclass().getMethod(method.getName().replaceFirst("get", "populateNew"), Node.class);
      populateNewMethod.invoke(action, node);
    } catch (Exception e) {
      logger.debug(e);
    }
  }

  private long getId(Object action, JcrNode annotation, String methodName) {
    String param = annotation.param();
    long longResult = 0;
    if (StringUtils.isEmpty(param)) {
      List<String> possibleParamsNames = new ArrayList<String>();
      possibleParamsNames.add(annotation.id());
      String nodePath = annotation.node();
      ValueStack stack = ActionContext.getContext().getValueStack();
      nodePath=TextParseUtil.translateVariables('$', nodePath, stack);
      possibleParamsNames.add(idPropertyNameFromNode(nodePath));
      possibleParamsNames.add(methodNameToFieldName(methodName)+"Id");
      for (String possibleParamName : possibleParamsNames) {
        if(StringUtils.isEmpty(possibleParamName)){
          continue;
        }
        longResult = getParamValue(action, possibleParamName);
        if (longResult > 0) {
          return longResult;
        }
      }
    }
    return longResult;
  }

  private long getParamValue(Object action, String param) {
    Object result = null;
    long longResult = 0;
    try {
      String getterName = param;
      getterName = "get" + StringUtils.capitalize(getterName);
      Method getterMethod = action.getClass().getSuperclass().getMethod(getterName);
      result = getterMethod.invoke(action);
    } catch (Exception e) {
      logger.debug(e);
    }
    if (result != null) {
      longResult = NumberUtils.toLong(result.toString());
    }
    if (longResult == 0) {
      longResult = NumberUtils.toLong(PortletActionContext.getRequest().getParameter(param));
    }
    return longResult;
  }

  private String methodNameToFieldName(String methodName) {
    if (methodName.startsWith("get")) {
      return StringUtils.uncapitalize(methodName.replaceFirst("get", ""));
    }
    if (methodName.startsWith("set")) {
      return StringUtils.uncapitalize(methodName.replaceFirst("set", ""));
    }
    return methodName;
  }

  public void setJcrTemplate(JcrTemplate jcrTemplate) {
    this.jcrTemplate = jcrTemplate;
  }
}
