package org.apache.struts2.jcr.wrapper;

import javax.jcr.RepositoryException;
import javax.jcr.NodeIterator;
import javax.jcr.query.QueryResult;
import javax.jcr.query.RowIterator;

/**
 * Wrapper class used to wrap QueryResult to make it return wrapped node iterators, which in their turn will return wrapped
 * javax.jcr.Node which are both java.util.Map and javax.jcr.Node
 */
public class QueryResultWrapperImpl implements QueryResult {
  private QueryResult delegee;
  private WrapperFactory wrapperFactory;

  public QueryResultWrapperImpl(QueryResult queryResult,WrapperFactory wrapperFactory) {
    delegee = queryResult;
    this.wrapperFactory = wrapperFactory;
  }

  public String[] getColumnNames() throws RepositoryException {
    return delegee.getColumnNames();
  }

  public RowIterator getRows() throws RepositoryException {
    return wrapperFactory.createWrapped(delegee.getRows());
  }

  public NodeIterator getNodes() throws RepositoryException {
    return wrapperFactory.createWrapped(delegee.getNodes());
  }
}
