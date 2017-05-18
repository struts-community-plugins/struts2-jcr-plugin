package org.apache.struts2.jcr.wrapper;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.util.Map;
/*
* Interface to combine java.util.Map and javax.jcr.Node
* */
public interface NodeWrapper extends Map<String, Object>, Node {
}
