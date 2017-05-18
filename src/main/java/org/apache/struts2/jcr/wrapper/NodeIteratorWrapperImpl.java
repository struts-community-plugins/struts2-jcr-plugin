package org.apache.struts2.jcr.wrapper;

import javax.jcr.NodeIterator;
import javax.jcr.Node;

/**
 * Wrapper class used to wrap  NodeIterator to make it return wrapped javax.jcr.Node which are both java.util.Map and javax.jcr.Node
 */
public class NodeIteratorWrapperImpl implements NodeIterator {
  private NodeIterator delegee;
  private WrapperFactory wrapperFactory;

  public NodeIteratorWrapperImpl(NodeIterator nodes,WrapperFactory wrapperFactory) {
    delegee = nodes;
    this.wrapperFactory = wrapperFactory;
  }

  public Node nextNode() {
    return wrapperFactory.createWrapped(delegee.nextNode());
  }

  public void skip(long skipNum) {
    delegee.skip(skipNum);
  }

  public long getSize() {
    return delegee.getSize();
  }

  public long getPosition() {
    return delegee.getPosition();
  }

  public boolean hasNext() {
    return delegee.hasNext();
  }

  public Node next() {
    return wrapperFactory.createWrapped(delegee.nextNode());
  }

  public void remove() {
    delegee.remove();
  }
}
