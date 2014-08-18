package biz.majorov.camel.incident;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * created : 18.08.14 22:35
 *
 * @author Nikolaj Majorov
 */
public interface IncidentService {


    @POST
    @Path("/report/")
    public Response reportIncident(Incident incident);

    @GET
    @Path("/incident/{id}/")
    @Produces("application/xml")
    public javax.ws.rs.core.Response getIncident(@PathParam("id") String id);
}
