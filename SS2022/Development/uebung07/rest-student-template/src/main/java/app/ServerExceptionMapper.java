package app;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServerExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable t) {

        // Klasse fängt alle internen Fehler, gibt sie auf der Server-Konsole aus und ...
        t.printStackTrace();

        // ... sendet sie auch an den Client
        return Response.serverError()
                       .entity(t.getMessage())
                       .build();
    }
}