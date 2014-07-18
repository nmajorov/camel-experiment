package biz.majorov.camel.incident;

import org.apache.cxf.jaxrs.provider.AbstractConfigurableProvider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.apache.camel.component.file.GenericFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;

/**
 * Custom restful service provider for camel GenericFile type
 *
 * created : 16.07.14 09:28
 *
 *
 * @author Nikolaj Majorov
 */
public class NMCXFProvider extends AbstractConfigurableProvider
        implements MessageBodyReader<Object>, MessageBodyWriter<Object> {


    private static final Logger LOG = LoggerFactory.getLogger(NMCXFProvider.class);


    /**
     * Ascertain if the MessageBodyReader can produce an instance of a
     * particular type. The {@code type} parameter gives the
     * class of the object that should be produced, the {@code genericType} parameter
     * gives the {@link java.lang.reflect.Type java.lang.reflect.Type} of the object
     * that should be produced.
     * E.g. if the object to be produced is List&lt;String&gt;, the {@code type} parameter
     * will be {@code java.util.List} and the {@code genericType} parameter will be
     * {@link java.lang.reflect.ParameterizedType java.lang.reflect.ParameterizedType}.
     *
     * @param type        the class of object to be produced.
     * @param genericType the type of object to be produced. E.g. if the
     *                    message body is to be converted into a method parameter, this will be
     *                    the formal type of the method parameter as returned by
     *                    {@code Method.getGenericParameterTypes}.
     * @param annotations an array of the annotations on the declaration of the
     *                    artifact that will be initialized with the produced instance. E.g. if the
     *                    message body is to be converted into a method parameter, this will be
     *                    the annotations on that parameter returned by
     *                    {@code Method.getParameterAnnotations}.
     * @param mediaType   the media type of the HTTP entity, if one is not
     *                    specified in the request then {@code application/octet-stream} is
     *                    used.
     * @return {@code true} if the type is supported, otherwise {@code false}.
     */
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        LOG.info("isReadable");
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Read a type from the {@link java.io.InputStream}.
     *
     * @param type         the type that is to be read from the entity stream.
     * @param genericType  the type of object to be produced. E.g. if the
     *                     message body is to be converted into a method parameter, this will be
     *                     the formal type of the method parameter as returned by
     *                     {@code Method.getGenericParameterTypes}.
     * @param annotations  an array of the annotations on the declaration of the
     *                     artifact that will be initialized with the produced instance. E.g.
     *                     if the message body is to be converted into a method parameter, this
     *                     will be the annotations on that parameter returned by
     *                     {@code Method.getParameterAnnotations}.
     * @param mediaType    the media type of the HTTP entity.
     * @param httpHeaders  the read-only HTTP headers associated with HTTP entity.
     * @param entityStream the {@link java.io.InputStream} of the HTTP entity. The
     *                     caller is responsible for ensuring that the input stream ends when the
     *                     entity has been consumed. The implementation should not close the input
     *                     stream.
     * @return the type that was read from the stream.
     * @throws java.io.IOException if an IO error arises
     * @throws javax.ws.rs.WebApplicationException
     *                             if a specific
     *                             HTTP error response needs to be produced. Only effective if thrown
     *                             prior to the response being committed.
     */
    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        LOG.debug("called readFrom method ");
        throw new RuntimeException("not yet implemented");
    }

    /**
     * Ascertain if the MessageBodyWriter supports a particular type.
     *
     * @param type        the class of object that is to be written.
     * @param genericType the type of object to be written, obtained either
     *                    by reflection of a resource method return type or via inspection
     *                    of the returned instance. {@link javax.ws.rs.core.GenericEntity}
     *                    provides a way to specify this information at runtime.
     * @param annotations an array of the annotations on the resource
     *                    method that returns the object.
     * @param mediaType   the media type of the HTTP entity.
     * @return true if the type is supported, otherwise false.
     */
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        LOG.info("called isWriteable method");

        if (type == null) {
            return false;
        }

        if (type.isAssignableFrom(GenericFile.class)) {
            LOG.debug(" isWriteable true");
            return true;

        }

        return false;
    }

    /**
     * Called before {@code writeTo} to ascertain the length in bytes of
     * the serialized form of {@code t}. A non-negative return value is
     * used in a HTTP {@code Content-Length} header.
     *
     * @param o           the instance to write
     * @param type        the class of object that is to be written.
     * @param genericType the type of object to be written, obtained either
     *                    by reflection of a resource method return type or by inspection
     *                    of the returned instance. {@link javax.ws.rs.core.GenericEntity}
     *                    provides a way to specify this information at runtime.
     * @param annotations an array of the annotations on the resource
     *                    method that returns the object.
     * @param mediaType   the media type of the HTTP entity.
     * @return length in bytes or -1 if the length cannot be determined in
     *         advance
     */
    @Override
    public long getSize(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        LOG.info("getSize");
        return 0;
    }

    /**
     * Write a type to an HTTP message. The message header map is mutable
     * but any changes must be made before writing to the output stream since
     * the headers will be flushed prior to writing the message body.
     *
     * @param o            the instance to write.
     * @param type         the class of object that is to be written.
     * @param genericType  the type of object to be written, obtained either
     *                     by reflection of a resource method return type or by inspection
     *                     of the returned instance. {@link javax.ws.rs.core.GenericEntity}
     *                     provides a way to specify this information at runtime.
     * @param annotations  an array of the annotations on the resource
     *                     method that returns the object.
     * @param mediaType    the media type of the HTTP entity.
     * @param httpHeaders  a mutable map of the HTTP message headers.
     * @param entityStream the {@link java.io.OutputStream} for the HTTP entity. The
     *                     implementation should not close the output stream.
     * @throws java.io.IOException if an IO error arises
     * @throws javax.ws.rs.WebApplicationException
     *                             if a specific
     *                             HTTP error response needs to be produced. Only effective if thrown
     *                             prior to the message being committed.
     */
    @Override
    public void writeTo(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        LOG.info("writeTo");
        GenericFile file = (GenericFile) o;
        //LOG.info("file body type: " + file.getBody().getClass().getCanonicalName());  //java.io.File
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = documentBuilder.parse((File) file.getFile());
            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource source = new DOMSource(dom);

            StreamResult result = new StreamResult(entityStream);
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            LOG.error("can't initialize documentBuilder  ", e);
        } catch (SAXException e) {
            LOG.error("parsing error  ", e);
        } catch (TransformerConfigurationException e) {
            LOG.error("can't initialize xml transformer  ", e);
        } catch (TransformerException e) {
            LOG.error("can't transform xml", e);
        }
    }
}
