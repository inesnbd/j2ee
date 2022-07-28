package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author baha
 */
@Entity
public class Tag {

  /**.
   * id long
   */
  @GeneratedValue
  @Id
  private long id;

  /**.
   * Category
   */
  @ManyToOne
  private Category category;

  /**.
   * name string
   */
  private String name;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param newId
   */
  public void setId(final long newId) {
    this.id = newId;
  }
  /**
   * @return Category
   */
  public Category getCategory() {
    return category;
  }
  /**
   * @param  newCategory
   */
  public void setCategory(final Category newCategory) {
    this.category = newCategory;
  }
  /**
   * @return String
   */
  public String getName() {
    return name;
  }
  /**
   * @param  newName
   */
  public void setName(final String newName) {
    this.name = newName;
  }
}
