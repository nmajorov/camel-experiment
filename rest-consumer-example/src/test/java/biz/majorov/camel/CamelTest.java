package biz.majorov.camel;

import biz.majorov.camel.incident.Incident;
import biz.majorov.camel.incident.IncidentService;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import javax.ws.rs.core.Response;

import org.junit.Ignore;
import org.junit.Test;



import static org.junit.Assert.*;


/**
 * created : 16.07.14 07:38
 *
 * @author Nikolaj Majorov
 */
@Ignore
public class CamelTest extends CamelBlueprintTestSupport {
    private static final String ROUTER_ADDRESS = "http://localhost:8181/cxf/inc/incident";



    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }

    @Test
    public void testPublishedEndpointUrl(){
       IncidentService service =  JAXRSClientFactory.create(ROUTER_ADDRESS, IncidentService.class);

       Incident incident = new Incident();
        incident.setDescription("test1 description");
        Response response =  service.reportIncident(incident) ;
        assertEquals(response.getStatus(), Response.Status.OK);
    }
}
