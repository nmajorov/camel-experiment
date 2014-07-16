package biz.majorov.camel.incident;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Incident class is just a plain old java object, with a few properties and getters and setters.
 * created : 16.07.14 07:11
 *  By adding the @XmlRootElement annotation, we make it possible for JAXB to unmarshal this object into a XML document and
 * to marshal it back from the same XML document
 * @author Nikolaj Majorov
 */
@XmlRootElement(name = "Incident")
public class Incident {
     private long id;
     String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
