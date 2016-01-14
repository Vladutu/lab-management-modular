package com.iquestint.rest.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersList implements Serializable {
  private static final long serialVersionUID = 1L;

  @XmlElement(name = "User")
  private List<User> users = new ArrayList<User>();

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

}
