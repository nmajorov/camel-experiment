package biz.majorov.camel;

import biz.majorov.camel.incident.Incident;
import biz.majorov.camel.incident.IncidentService;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.camel.util.FileUtil;

import javax.ws.rs.core.Response;

import org.junit.Test;

import java.io.File;


/**
 * created : 16.07.14 07:38
 *
 * @author Nikolaj Majorov
 */
public class CamelTest extends CamelBlueprintTestSupport {
    private static final String ROUTER_ADDRESS = "http://localhost:8181/cxf/inc/incidents";


    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }

    @Test
    public void testPublishedEndpointUrl() throws Exception {
        IncidentService service = JAXRSClientFactory.create(ROUTER_ADDRESS, IncidentService.class);
        Incident incident = new Incident();
        incident.setDescription("test1 description");
        Response response = service.reportIncident(incident);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }


    @Test
    public void testRoute() {

        try {
            File incidentFile = new File(getClass().getClassLoader().getResource("incident.xml").toURI());
            File currentDir = new File(".");
            File destinationFile = new File(currentDir.getPath() + "/work/inc/input/incident.xml");
            log.info("get new incident file: " + incidentFile);
            FileUtil.copyFile(incidentFile, destinationFile);
        } catch (Exception ex) {
            log.error("route test error", ex);
            fail();
        }
    }
}
