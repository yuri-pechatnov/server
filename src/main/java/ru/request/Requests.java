package ru.request;

import javax.ws.rs.GET;
import org.slf4j.Logger;
import ru.model.Receipt;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

@Path("")
public class Requests {

    private static final Logger logger = LoggerFactory.getLogger(Requests.class);

    @Inject
    ReceiptService receiptService;

    @GET
    @Path("/{data}")
    public Response test() {
        return Response.ok("all is ok").build();
    }

    @POST
    @Path(value = "/getCategories")
    @Consumes("application/xml")
    public Response getCategories(Receipt receipt) {
        logger.info("поступил запрос на категоризацию");
        try {
            receiptService.getCategories(receipt);
            return Response
                    .ok(receipt)
                    .type(MediaType.APPLICATION_XML_TYPE)
                    .build();
        }
        catch (Exception error) {
            return Response
                    .status(500)
                    .build();
        }
    }

}
