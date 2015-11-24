package javahibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/* Таблица:
 * CREATE TABLE "USERS"
 * (
 *   "ID"     NUMBER(10,0) NOT NULL ENABLE,
 *   "NAME"   VARCHAR2(100 BYTE) NOT NULL ENABLE,
 *   "PASSWD" VARCHAR2(32 BYTE) NOT NULL ENABLE,
 *   PRIMARY KEY ("ID")
 * )
 * 
 * Аннотации: 
 * @Entity — указывает на то, что данный класс является сущностью.
 * @Table — задает имя таблицы, в которой будут храниться объекты класса
 * @Id — обозначает поле id
 * @GeneratedValue и @GenericGenerator — указывает на то, как будет генерироваться id (у нас — по возрастанию)
 * @Column — обозначает имя колонки, соответствующей данному полю.
 */

/**
 * Users - класс-сущность, который будем хранить в БД
 */
@Entity
@Table(name="Users")
public class Users implements Serializable {
    
    protected Long id;    
    protected String name;    
    protected String passwd;
    
    public Users(){
        name = null;
    }
    
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public Long getId() {
        return id;
    }
    
    @Column(name="name")
    public String getName() {
        return name;
    }
    
    @Column(name="passwd")
    public String getPasswd() {
        return passwd;
    }
    
    public void setId(Long i) {
        id = i;     
    }
    
    public void setName(String s) {
        name = s;
    }

    public void setPasswd(String s) {
        passwd = s;
    }
}
