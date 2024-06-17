package com.store.fake.controller;



import com.store.fake.config.DispatcherServletCustomConfig;
import com.store.fake.domain.ClientDomain;
import com.store.fake.request.ClientRequest;
import com.store.fake.response.ClientResponse;
import com.store.fake.service.IClientService;
import com.store.fake.utils.Autorizations;
import com.store.fake.utils.CommonConstants;
import com.store.fake.utils.GeneralResponse;
import com.store.fake.utils.ValidateModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@DispatcherServletCustomConfig.V1APIController
@RestController
public class ClientController {

    ResponseEntity<GeneralResponse> responseClient = null;
    ValidateModel validate = new ValidateModel();

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/Clients")
    @ApiResponse(responseCode = "200", description = "Get all clients")
    @Operation(summary = "Get all clients")
    public ResponseEntity<GeneralResponse> getAllClients(HttpServletRequest req) {
        try {
            List<ClientResponse> clients = clientService.getAllClient();

            if (clients == null) {
                responseClient = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
            } else {
                responseClient = Autorizations.getResponseSuccess(clients, CommonConstants.SUCCESS_RESULT);
            }

        } catch (Exception e) {

            responseClient = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseClient;
    }

    @GetMapping("/Clients/Id/{id}")
    @ApiResponse(responseCode = "200", description = "Get all clients")
    @Operation(summary = "Get all clients")
    public ResponseEntity<GeneralResponse> getAllClients(HttpServletRequest req,@PathVariable("id") Long id) {
        try {
            ClientResponse clients = clientService.getFindById(id);

            if (clients == null) {
                responseClient = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
            } else {
                responseClient = Autorizations.getResponseSuccess(clients, CommonConstants.SUCCESS_RESULT);
            }

        } catch (Exception e) {

            responseClient = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseClient;
    }

    @PostMapping("/Clients")
    @ApiResponse(responseCode = "200", description = "API para Guardar actividad economica")
    @Operation(summary = "Guardar actividad economica")
    public ResponseEntity<GeneralResponse> saveClient(HttpServletRequest req,
                                                      @RequestBody ClientRequest clientDto, BindingResult result)
            throws NumberFormatException, SecurityException {

        ClientDomain clientDomain;

        try {



            clientDomain = mapClientRequest(clientDto);
            responseClient = validate.validateModel(clientDomain);

            if (responseClient != null) {
                return responseClient;
            }
            clientDomain.setStatus(1);
            clientDomain.setCreationDate(LocalDateTime.now());
            Long id = clientService.saveClient(clientDomain);

            responseClient = Autorizations.getResponseSuccess(id, CommonConstants.SUCCESS_SAVE);


        } catch (Exception e) {

            responseClient = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseClient;

    }

    @PutMapping("/Clients")
    @ApiResponse(responseCode = "200", description = "API para Guardar actividad economica")
    @Operation(summary = "Guardar actividad economica")
    public ResponseEntity<GeneralResponse> updateClient(HttpServletRequest req,
                                                      @RequestBody ClientRequest clientDto, BindingResult result)
            throws NumberFormatException, SecurityException {

        ClientDomain clientDomain;

        try {

            clientDomain = mapClientRequest(clientDto);
            responseClient = validate.validateModel(clientDomain);

            if (responseClient != null) {
                return responseClient;
            }

            ClientResponse validateClient = clientService.getFindById(clientDomain.getIdClient());
            if(validateClient ==null){
                responseClient = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
                return responseClient;
            }

            clientDomain.setStatus(1);
            clientDomain.setPassword(validateClient.getPassword());

            Long id = clientService.saveClient(clientDomain);

            responseClient = Autorizations.getResponseSuccess(id, CommonConstants.SUCCESS_UPDATE);


        } catch (Exception e) {

            responseClient = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseClient;

    }

    private ClientDomain mapClientRequest(ClientRequest request){
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setIdClient(request.getIdClient());
        clientDomain.setEmail(request.getEmail());
        clientDomain.setUsername(request.getUsername());
        clientDomain.setPassword(request.getPassword());
        clientDomain.setFirstname(request.getFirstname());
        clientDomain.setLastname(request.getLastname());
        clientDomain.setPhone(request.getPhone());
        return clientDomain;
    }

}
