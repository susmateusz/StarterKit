package com.capgemini.nodes;

/**
 * Created by ldrygala on 2015-02-09.
 */
/**
 * if id==predecessorId then node is root
 * 
 * @author MATSUS
 *
 */
public class Node {
	/**
	 * identifier of the node
	 */
	private String id;
	/**
	 * description of the node
	 */
	private String description;
	/**
	 * identifier of the predecessor
	 */
	private String predecessorId;

	/**
	 * Parametric constructor
	 * 
	 * @param id
	 *            id of the node
	 * @param description
	 *            decription of the node
	 * @param predecessorId
	 *            id of the node's predecessor
	 */
	public Node(String id, String description, String predecessorId) {
		setId(id);
		setDescription(description);
		setPredecessorId(predecessorId);
	}

	/**
	 * return string which represents node
	 */
	@Override
	public String toString() {
		return "Node [id=" + id + ", description=" + description + ", predecessorId=" + predecessorId + "]";
	}

	/**
	 * getter of Id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * setter of Id
	 * 
	 * @param id
	 *            identifier
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * getter of description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setter of description
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getter of predecesor's id
	 * 
	 * @return predecessor's id
	 */
	public String getPredecessorId() {
		return predecessorId;
	}

	/**
	 * setter of predecessor's id
	 * 
	 * @param predecessorId
	 *            id
	 */
	public void setPredecessorId(String predecessorId) {
		this.predecessorId = predecessorId;
	}

}
