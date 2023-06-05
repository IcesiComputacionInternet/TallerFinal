package co.com.icesi.backend.service;

import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.OrderMapper;
import co.com.icesi.backend.repository.OrderRepository;
import co.com.icesi.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository roleRepository;
    private final OrderMapper roleMapper;
    private final CellphoneShopExceptionBuilder exceptionBuilder;
/*
    public ResponseOrderDTO saveOrder(RequestOrderDTO requestOrderDTO) {
        User user = userRepository.findByEmail(requestOrderDTO.getUserEmail()).orElseThrow(
                () -> exceptionBuilder.notFoundException(
                        "The user with the specified email does not exists.", requestOrderDTO.getUserEmail()));
        validateIfItemsExist(requestOrderDTO.getItems());
        validateIfPhoneIsDuplicated(requestOrderDTO.getPhoneNumber());

        checkPermissions(requestOrderDTO.getRole());
        User user = userMapper.fromUserDTO(requestOrderDTO);
        user.setUserId(UUID.randomUUID());
        user.setRole(role);
        userRepository.save(user);
        ResponseUserDTO responseUserDTO = userMapper.fromUserToResponseUserDTO(user);
        responseUserDTO.setRole(roleMapper.fromRoleToRoleDTO(role));
        return responseUserDTO;
    }

    private void validateIfItemsExist(List<String> items) {
        return documentsDTO.stream()
                .filter(documentDTO -> documentRepository.findByTitle(documentDTO.getTitle()).isPresent())
                .map(documentDTO -> new DetailBuilder(ErrorCode.ERR_DUPLICATED, "Document","Title", documentDTO.getTitle()))
                .toList();
    }*/
}
