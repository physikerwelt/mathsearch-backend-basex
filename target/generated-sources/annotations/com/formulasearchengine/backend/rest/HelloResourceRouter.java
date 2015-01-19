package com.formulasearchengine.backend.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkNotNull;

import restx.common.Types;
import restx.*;
import restx.entity.*;
import restx.http.*;
import restx.factory.*;
import restx.security.*;
import static restx.security.Permissions.*;
import restx.description.*;
import restx.converters.MainStringConverter;
import static restx.common.MorePreconditions.checkPresent;

import javax.validation.Validator;
import static restx.validation.Validations.checkValid;

import java.io.IOException;
import java.io.PrintWriter;

@Component(priority = 0)

public class HelloResourceRouter extends RestxRouter {

    public HelloResourceRouter(
                    final HelloResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Validator validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "HelloResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, com.formulasearchengine.backend.domain.Message>("default#HelloResource#sayHello",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.formulasearchengine.backend.domain.Message>build(com.formulasearchengine.backend.domain.Message.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/message"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.formulasearchengine.backend.domain.Message> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, hasRole("hello"));
                return Optional.of(resource.sayHello(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "Message";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.formulasearchengine.backend.domain.Message";
                operation.sourceLocation = "com.formulasearchengine.backend.rest.HelloResource#sayHello()";
            }
        },
        new StdEntityRoute<Void, com.formulasearchengine.backend.domain.Message>("default#HelloResource#helloPublic",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<com.formulasearchengine.backend.domain.Message>build(com.formulasearchengine.backend.domain.Message.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/hello"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<com.formulasearchengine.backend.domain.Message> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, open());
                return Optional.of(resource.helloPublic(
                        /* [QUERY] who */ checkPresent(request.getQueryParam("who"), "query param who is required")
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription who = new OperationParameterDescription();
                who.name = "who";
                who.paramType = OperationParameterDescription.ParamType.query;
                who.dataType = "string";
                who.schemaKey = "";
                who.required = true;
                operation.parameters.add(who);


                operation.responseClass = "Message";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "com.formulasearchengine.backend.domain.Message";
                operation.sourceLocation = "com.formulasearchengine.backend.rest.HelloResource#helloPublic(java.lang.String)";
            }
        },
        });
    }

}
