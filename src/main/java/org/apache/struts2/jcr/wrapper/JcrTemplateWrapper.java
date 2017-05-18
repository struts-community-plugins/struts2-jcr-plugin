package org.apache.struts2.jcr.wrapper;

import org.springmodules.jcr.JcrTemplate;
import org.springmodules.jcr.JcrCallback;

import javax.jcr.*;
import javax.jcr.query.QueryResult;
import java.util.Map;
import java.util.List;

/**
 * Wrapper class used to wrap spring's JcrTemplate to make it return wrapped javax.jcr.Node which are both java.util.Map and javax.jcr.Node
 */
public class JcrTemplateWrapper extends JcrTemplate {
  WrapperFactory wrapperFactory;
  public Object execute(JcrCallback jcrCallback, boolean b) throws Exception {
    return super.execute(jcrCallback, b);
  }

  public Object execute(JcrCallback jcrCallback) throws Exception {
    return super.execute(jcrCallback);
  }

  public Item getItem(String s) {
    return super.getItem(s);
  }

  public Node getNodeByUUID(String s) {
    return super.getNodeByUUID(s);
  }

  public Node getRootNode() {
    return wrapperFactory.createWrapped(super.getRootNode());
  }


  public QueryResult query(Node node) {
    return new QueryResultWrapperImpl(super.query(node),wrapperFactory);
  }

  public QueryResult query(String s) {
    return new QueryResultWrapperImpl(super.query(s),wrapperFactory);
  }

  public QueryResult query(String s, String s1) {
    return new QueryResultWrapperImpl(super.query(s, s1),wrapperFactory);
  }

  public Map query(List list) {
    Map map = super.query(list);
    return map;
  }

  public Map query(List list, String s, boolean b) {
    Map map = super.query(list, s, b);
    return map;
  }

  public WrapperFactory getWrapperFactory() {
    return wrapperFactory;
  }

  public void setWrapperFactory(WrapperFactory wrapperFactory) {
    this.wrapperFactory = wrapperFactory;
  }
}
