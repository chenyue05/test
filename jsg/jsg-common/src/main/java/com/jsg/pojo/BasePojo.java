package com.jsg.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class BasePojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5446669086498786784L;
	private Date created;
	private Date updated;
}
