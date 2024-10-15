package sgr.com.sgrcoreapi.service.tableService;


import static sgr.com.sgrcoreapi.converters.table.TableConversionUtil.toTableDetails;
import static sgr.com.sgrcoreapi.converters.user.UserConversionUtil.toTableServiceResponsibleUser;
import static sgr.com.sgrcoreapi.validator.TableServiceValidator.validateTableServiceCreation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sgr.com.sgrcoreapi.converters.tableservice.TableServiceConversionUtil;
import sgr.com.sgrcoreapi.domain.table.CustomerTableRepository;
import sgr.com.sgrcoreapi.domain.tableservice.TableService;
import sgr.com.sgrcoreapi.domain.tableservice.TableServiceRepository;
import sgr.com.sgrcoreapi.domain.tableservice.TableServiceStatus;
import sgr.com.sgrcoreapi.domain.user.UserRepository;
import sgr.com.sgrcoreapi.infra.exception.custom.NotFoundException;
import sgr.com.sgrcoreapi.service.table.CustomerTableService;
import sgr.com.sgrcoreapi.service.tableService.dto.AddTableServiceRequest;
import sgr.com.sgrcoreapi.service.tableService.dto.TableServiceDetails;
import sgr.com.sgrcoreapi.service.user.UserService;

@Service
@RequiredArgsConstructor
@Getter
public class TableServiceServiceV2 {
    private final TableServiceRepository tableServiceRepository;
    private final CustomerTableRepository customerTableRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CustomerTableService customerTableService;

    public void createTableService(AddTableServiceRequest addTableServiceRequest) {
        TableService tableService = new TableService();

        var table = customerTableService.findTableById(addTableServiceRequest.tableId());
        var employee = userService.getUserDetails(addTableServiceRequest.employeeId());

        validateTableServiceCreation(employee, table);

        table.changeAvailability();
        tableService.setCustomerTable(table);
        tableService.setWaiter(employee);
        tableServiceRepository.save(tableService);
    }
    

    public Page<TableServiceDetails> getTableServices(TableServiceStatus tableServiceStatus, UUID waiterId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        var tableServicesPage = tableServiceRepository.findByStatusAndWaiter(tableServiceStatus, waiterId, pageable);

        List<TableServiceDetails> detailsList = tableServicesPage.getContent().stream()
                .map(tableService -> getTableServiceDetails(tableService.getId()))
                .collect(Collectors.toList());

        return new PageImpl<>(detailsList, pageable, tableServicesPage.getTotalElements());
    }

    public TableServiceDetails getTableServiceDetails(UUID tableServiceId) {
        var tableService = tableServiceRepository.findById(tableServiceId)
                .orElseThrow(NotFoundException::new);

        var customerTable = customerTableService.findTableById(tableService.getCustomerTable().getId());
        var customerTableDetails = toTableDetails(customerTable);

        var waiter = userService.getUserDetails(tableService.getWaiter().getId());
        var tableServiceResponsibleWaiter = toTableServiceResponsibleUser(waiter);

        return TableServiceConversionUtil.toTableServiceDetails(tableService, customerTableDetails, tableServiceResponsibleWaiter);
    }

}

